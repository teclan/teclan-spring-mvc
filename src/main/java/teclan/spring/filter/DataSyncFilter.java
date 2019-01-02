package teclan.spring.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import teclan.spring.util.HttpTool;
import teclan.spring.util.PropertyConfigUtil;

public class DataSyncFilter implements Filter {
	private final static Logger LOGGER = LoggerFactory.getLogger(DataSyncFilter.class);
	private final static PropertyConfigUtil propertyconfigUtil;

	static {
		propertyconfigUtil = PropertyConfigUtil.getInstance("config.properties");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		// 强制类型转换
		BodyReaderHttpServletRequestWrapper request = new BodyReaderHttpServletRequestWrapper(
				(HttpServletRequest) servletRequest);
		StatusExposingServletResponse response = new StatusExposingServletResponse(
				(HttpServletResponse) servletResponse);

		filterChain.doFilter(request, response);


		String requestURI = request.getRequestURI();

		String jsonStr = "";
		if (request.getContentType() != null
				&& request.getContentType().toLowerCase().contains("multipart/form-data")) {
			CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
			MultipartHttpServletRequest multipartRequest = commonsMultipartResolver.resolveMultipart(request);
			jsonStr = multipartRequest.getParameter("json");
		} else {
			jsonStr = HttpTool.readJSONString(request);
		}
		new Thread(new ProcessFilter(jsonStr, requestURI)).start();
	}

	@Override
	public void destroy() {
	}
}
