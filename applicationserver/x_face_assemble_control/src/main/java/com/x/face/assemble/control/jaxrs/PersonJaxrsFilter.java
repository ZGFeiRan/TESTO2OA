package com.x.face.assemble.control.jaxrs;

import javax.servlet.annotation.WebFilter;

import com.x.base.core.project.jaxrs.ManagerUserJaxrsFilter;

@WebFilter(urlPatterns = "/jaxrs/person/*", asyncSupported = true)
public class PersonJaxrsFilter extends ManagerUserJaxrsFilter {

}
