package com.thoughtmechanix.zuulsvr.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
@Component
public class TrackingFilter extends ZuulFilter{
	private static final int FILTER_ORDER = 1;
	private static final boolean SHOULD_FILTER = true;
	private static final Logger logger = LoggerFactory.getLogger(TrackingFilter.class);
	
	@Autowired 
	FilterUtils filterUtils;
	
	@Override
	public Object run() throws ZuulException {
		// TODO Auto-generated method stub
		System.out.println("calling zuul filter");
		if(isCorrelationIdPresent()) {
			logger.debug("tmx correlation id found in request", filterUtils.getCorrelationId());
		}
		else {
			filterUtils.setCorrelationId(generateCorrelationID());
			logger.debug("tmx correlation id generated for request", filterUtils.getCorrelationId());
		}
		RequestContext ctx = RequestContext.getCurrentContext();
		logger.debug("Processing incoming request for {}.", ctx.getRequest().getRequestURI());
		return null;
	}

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return SHOULD_FILTER;
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return FILTER_ORDER;
	}

	@Override
	public String filterType() {
		return filterUtils.PRE_FILTER_TYPE;
		
	}
	
	private boolean isCorrelationIdPresent() {
		if (filterUtils.getCorrelationId() != null) {
			return true;
		}
		
		return false;
	}
	
	private String generateCorrelationID() {
		return java.util.UUID.randomUUID().toString();
	}
	
}
