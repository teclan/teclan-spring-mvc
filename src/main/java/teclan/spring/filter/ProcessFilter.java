package teclan.spring.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public class ProcessFilter implements Runnable {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessFilter.class);
	private static final ClassPathXmlApplicationContext appContextData;


	static {
		appContextData = new ClassPathXmlApplicationContext("ApplicationContext/Application-mysql-data-source.xml");
	}
	private Object requestParams;
	private String requestUrl;

	public ProcessFilter(Object requestParams, String requestUrl) {
		this.requestParams = requestParams;
		this.requestUrl = requestUrl;
	}

	@Override
	public void run() {

		if(requestParams instanceof String){
			LOGGER.info("2 ProcessFilter 监测到 URL={}{}",requestUrl,requestParams==null||"".equals(((String) requestParams).trim())?"":"?"+requestParams);
		}else {
			LOGGER.info("2 ProcessFilter 监测到 URL={},参数:{}",requestUrl,requestParams.toString());
		}
	}
}
