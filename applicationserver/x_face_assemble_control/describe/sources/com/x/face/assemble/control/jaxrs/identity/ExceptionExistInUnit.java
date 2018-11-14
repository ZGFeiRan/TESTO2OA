package com.x.face.assemble.control.jaxrs.identity;

import com.x.base.core.project.exception.PromptException;
import com.x.organization.core.entity.Person;
import com.x.organization.core.entity.Unit;

class ExceptionExistInUnit extends PromptException {

	private static final long serialVersionUID = 4132300948670472899L;

	ExceptionExistInUnit(Person person, Unit unit) {
		super("人员:{}, 在组织:{} 中的身份已存在.", person.getName(), unit.getName());
	}
}
