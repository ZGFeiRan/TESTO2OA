package com.x.processplatform.assemble.bam.jaxrs.period;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.x.base.core.container.EntityManagerContainer;
import com.x.base.core.container.factory.EntityManagerContainerFactory;
import com.x.base.core.project.gson.GsonPropertyObject;
import com.x.base.core.project.http.ActionResult;
import com.x.base.core.project.jaxrs.StandardJaxrsAction;
import com.x.base.core.project.logger.Logger;
import com.x.base.core.project.logger.LoggerFactory;
import com.x.base.core.project.tools.DateRange;
import com.x.base.core.project.tools.DateTools;
import com.x.processplatform.assemble.bam.Business;
import com.x.processplatform.assemble.bam.ThisApplication;
import com.x.processplatform.assemble.bam.stub.UnitStub;

class ActionListCountExpiredTaskByUnit extends BaseAction {
	
	private static Logger logger = LoggerFactory.getLogger(ActionListCountExpiredTaskByUnit.class);

	ActionResult<Wo> execute(String applicationId, String processId, String activityId) throws Exception {
		try (EntityManagerContainer emc = EntityManagerContainerFactory.instance().create()) {
			ActionResult<Wo> result = new ActionResult<>();
			Business business = new Business(emc);
			List<DateRange> os = this.listDateRange();
			Wo wo = new Wo();
			for (DateRange o : os) {
				String str = DateTools.format(o.getStart(), DateTools.format_yyyyMM);
				List<WoUnit> list = new ArrayList<>();
				wo.put(str, list);
				for (UnitStub stub : ThisApplication.period.getExpiredTaskUnitStubs()) {
					WoUnit w = new WoUnit();
					w.setName(stub.getName());
					w.setValue(stub.getValue());
					w.setLevel(stub.getLevel());
					w.setCount(this.countExpiredTask(business, o, applicationId, processId, activityId, stub.getValue(),
							StandardJaxrsAction.EMPTY_SYMBOL));
					list.add(w);
				}
			}
			result.setData(wo);
			return result;
		}
	}

	public static class Wo extends LinkedHashMap<String, List<WoUnit>> {

		private static final long serialVersionUID = 500246051007684955L;

	}

	public static class WoUnit extends GsonPropertyObject {

		private String name;
		private String value;
		private Integer level;
		private Long count;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public Long getCount() {
			return count;
		}

		public void setCount(Long count) {
			this.count = count;
		}

		public Integer getLevel() {
			return level;
		}

		public void setLevel(Integer level) {
			this.level = level;
		}
	}

	private List<UnitStub> listStub(String companyName) throws Exception {
		List<UnitStub> list = new ArrayList<>();
		for (UnitStub o : ThisApplication.period.getExpiredTaskUnitStubs()) {
			if (StringUtils.equals(o.getValue(), companyName)
					|| StringUtils.equals(companyName, StandardJaxrsAction.EMPTY_SYMBOL)) {
				list.add(o);
			}
		}
		return list;
	}

}