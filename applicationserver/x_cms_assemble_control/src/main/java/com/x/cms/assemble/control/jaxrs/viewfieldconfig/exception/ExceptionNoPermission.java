package com.x.cms.assemble.control.jaxrs.viewfieldconfig.exception;

import com.x.base.core.project.exception.PromptException;

public class ExceptionNoPermission extends PromptException {

	private static final long serialVersionUID = 1859164370743532895L;

	public ExceptionNoPermission( String personName ) {
		super( "person{name:" + personName + "} 用户没有内容管理展示列配置信息信息操作的权限！" );
	}
}
