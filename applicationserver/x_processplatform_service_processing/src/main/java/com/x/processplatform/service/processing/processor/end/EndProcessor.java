package com.x.processplatform.service.processing.processor.end;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import com.x.base.core.container.EntityManagerContainer;
import com.x.base.core.project.config.Config;
import com.x.base.core.project.logger.Logger;
import com.x.base.core.project.logger.LoggerFactory;
import com.x.base.core.project.tools.ListTools;
import com.x.processplatform.core.entity.content.Work;
import com.x.processplatform.core.entity.content.WorkCompleted;
import com.x.processplatform.core.entity.element.ActivityType;
import com.x.processplatform.core.entity.element.End;
import com.x.processplatform.core.entity.element.Form;
import com.x.processplatform.core.entity.element.Route;
import com.x.processplatform.service.processing.processor.AeiObjects;

public class EndProcessor extends AbstractEndProcessor {

	private static Logger logger = LoggerFactory.getLogger(EndProcessor.class);

	public EndProcessor(EntityManagerContainer entityManagerContainer) throws Exception {
		super(entityManagerContainer);
	}

	@Override
	protected Work arriving(AeiObjects aeiObjects, End end) throws Exception {
		return aeiObjects.getWork();
	}

	@Override
	protected void arrivingCommitted(AeiObjects aeiObjects, End end) throws Exception {
	}

	@Override
	protected List<Work> executing(AeiObjects aeiObjects, End end) throws Exception {
		List<Work> results = new ArrayList<>();
		if (!aeiObjects.getWorks().stream().allMatch(o -> Objects.equals(o.getActivityType(), ActivityType.end))) {
			/* 如果还有多个副本没有到达end节点那么不能路由 */
			return results;
		}
		/* 删除所有待办 */
		Work oldest = aeiObjects.getWorks().stream()
				.sorted(Comparator.comparing(Work::getCreateTime, Comparator.nullsLast(Date::compareTo))).findFirst()
				.get();
		WorkCompleted workCompleted = this.createWorkCompleted(oldest);
		aeiObjects.getCreateWorkCompleteds().add(workCompleted);
		aeiObjects.getTasks().stream().forEach(o -> aeiObjects.getDeleteTasks().add(o));
		aeiObjects.getHints().stream().forEach(o -> aeiObjects.getDeleteHints().add(o));
		aeiObjects.getTaskCompleteds().stream().forEach(o -> {
			/* 已办的完成时间是不需要更新的 */
			o.setCompleted(true);
			o.setWorkCompleted(workCompleted.getId());
			/* 加入到更新队列保证事务开启 */
			aeiObjects.getUpdateTaskCompleteds().add(o);
		});
		aeiObjects.getReads().stream().forEach(o -> {
			/* 待阅的完成时间是不需要更新的 */
			o.setCompleted(true);
			o.setWorkCompleted(workCompleted.getId());
			/* 加入到更新队列保证事务开启 */
			aeiObjects.getUpdateReads().add(o);
		});
		aeiObjects.getReadCompleteds().stream().forEach(o -> {
			/* 已阅的完成时间是不需要更新的 */
			o.setCompleted(true);
			o.setWorkCompleted(workCompleted.getId());
			/* 加入到更新队列保证事务开启 */
			aeiObjects.getUpdateReadCompleteds().add(o);
		});
		aeiObjects.getReviews().stream().forEach(o -> {
			o.setCompleted(true);
			o.setWorkCompleted(workCompleted.getId());
			o.setCompletedTime(workCompleted.getCompletedTime());
			o.setCompletedTimeMonth(workCompleted.getCompletedTimeMonth());
			/* 加入到更新队列保证事务开启 */
			aeiObjects.getUpdateReviews().add(o);
		});
		aeiObjects.getWorkLogs().stream().forEach(o -> {
			o.setSplitting(false);
			o.setSplitToken("");
			o.setSplitTokenList(new ArrayList<String>());
			o.setSplitValue("");
			o.setCompleted(true);
			o.setWorkCompleted(workCompleted.getId());
			/* 加入到更新队列保证事务开启 */
			aeiObjects.getUpdateWorkLogs().add(o);
			/* 删除未连接的WorkLog */
			if (BooleanUtils.isNotTrue(o.getConnected())) {
				aeiObjects.getDeleteWorkLogs().add(o);
			}
		});
		aeiObjects.getAttachments().stream().forEach(o -> {
			o.setCompleted(true);
			o.setWorkCompleted(workCompleted.getId());
			/* 加入到更新队列保证事务开启 */
			aeiObjects.getUpdateAttachments().add(o);
		});
		/* 已workCompleted数据为准进行更新 */
		aeiObjects.getData().setWork(workCompleted);
		aeiObjects.getData().setAttachmentList(aeiObjects.getAttachments());
		aeiObjects.getDeleteWorks().addAll(aeiObjects.getWorks());
		return results;
	}

	@Override
	protected void executingCommitted(AeiObjects aeiObjects, End end) throws Exception {
	}

	@Override
	protected List<Route> inquiring(AeiObjects aeiObjects, End end) throws Exception {
		return new ArrayList<Route>();
	}

	private WorkCompleted createWorkCompleted(Work work) throws Exception {
		Date completedTime = new Date();
		Long duration = Config.workTime().betweenMinutes(work.getStartTime(), completedTime);
		String formData = "";
		String formMobileData = "";
		if (StringUtils.isNotEmpty(work.getForm())) {
			Form form = this.entityManagerContainer().fetch(work.getForm(), Form.class,
					ListTools.toList(Form.data_FIELDNAME, Form.mobileData_FIELDNAME));
			if (null != form) {
				formData = form.getData();
				formMobileData = form.getMobileData();
			}
		}
		WorkCompleted workCompleted = new WorkCompleted(work, completedTime, duration, formData, formMobileData);
		return workCompleted;
	}

	@Override
	protected void inquiringCommitted(AeiObjects aeiObjects, End end) throws Exception {
	}
}