package com.acme.testcase.config;

import javax.servlet.ServletContext;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializerFixed {

	@Override
	public boolean enableHttpSessionEventPublisher() {
		return true;
	}

	@Override
	protected void afterSpringSecurityFilterChain(ServletContext servletContext) {
		insertFilters(servletContext, new OpenEntityManagerInViewFilter());
	}
}
