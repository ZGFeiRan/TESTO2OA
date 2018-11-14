package com.x.organization.assemble.personal.jaxrs.person;

import com.x.base.core.project.exception.PromptException;

class ExceptionOldPasswordNotMatch extends PromptException {

	private static final long serialVersionUID = -3885997486474873786L;

	ExceptionOldPasswordNotMatch() {
		super("原密码错误.");
	}

}
