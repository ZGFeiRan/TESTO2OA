package com.x.attendance.assemble.control.jaxrs.attendancedetail.exception;

import com.x.base.core.project.exception.PromptException;

public class ExceptionDetaillMobileNotExists extends PromptException {

	private static final long serialVersionUID = 1859164370743532895L;

	public ExceptionDetaillMobileNotExists( String id ) {
		super("员工手机打卡信息不存在！ID:" + id );
	}
}
