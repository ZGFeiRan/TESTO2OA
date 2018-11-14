package com.x.organization.assemble.control.jaxrs.personattribute;

import com.x.base.core.project.exception.PromptException;

class ExceptionPersonAttributeNotExist extends PromptException {

	private static final long serialVersionUID = -3439770681867963457L;

	ExceptionPersonAttributeNotExist(String name) {
		super("个人属性: {} 不存在.", name);
	}
}
