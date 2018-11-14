package com.x.cms.assemble.control.jaxrs.data;

import com.x.base.core.container.EntityManagerContainer;
import com.x.base.core.container.factory.EntityManagerContainerFactory;
import com.x.base.core.project.http.ActionResult;
import com.x.base.core.project.http.EffectivePerson;
import com.x.base.core.project.jaxrs.WoId;
import com.x.cms.assemble.control.Business;
import com.x.cms.assemble.control.jaxrs.data.exception.ExceptionDocumentNotExist;
import com.x.cms.core.entity.Document;

class ActionDeleteWithDocumentPath3 extends BaseAction {

	ActionResult<Wo> execute(EffectivePerson effectivePerson, String id, String path0, String path1,
			String path2, String path3) throws Exception {
		try (EntityManagerContainer emc = EntityManagerContainerFactory.instance().create()) {
			ActionResult<Wo> result = new ActionResult<>();
			Business business = new Business(emc);
			Document document = emc.find(id, Document.class);
			if (null == document) {
				throw new ExceptionDocumentNotExist(id);
			}
			this.deleteData(business, document, path0, path1, path2, path3);
			emc.commit();
			Wo wo = new Wo();
			wo.setId(document.getId());
			result.setData(wo);
			return result;
		}
	}

	public static class Wo extends WoId {

	}
}
