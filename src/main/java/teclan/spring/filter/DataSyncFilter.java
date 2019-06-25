package teclan.spring.filter;

import java.io.IOException;
import java.util.*;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.base.Strings;
import com.mysql.cj.util.StringUtils;
import org.elasticsearch.common.util.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import teclan.spring.util.HttpTool;
import teclan.spring.util.Objects;
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

		Object para = "";


		 if ("application/json".equalsIgnoreCase(request.getContentType())){
			 para = HttpTool.readJSONParam(request);
		}else {
		 	Set<String> parameters = request.getParameterMap().keySet();

			List<String> query = new ArrayList<>();

			 Iterator<String> iterator =  parameters.iterator();

			 while (iterator.hasNext()){
			 	String m = iterator.next();
				 query.add(String.format("%s=%s",m,request.getParameter(m)));
			 }
			 para = Objects.Joiner("&",query);
		 }
		new Thread(new ProcessFilter(para, requestURI)).start();
	}

	@Override
	public void destroy() {
	}
}
