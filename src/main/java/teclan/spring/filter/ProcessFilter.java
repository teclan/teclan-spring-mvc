package teclan.spring.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.fastjson.JSONObject;

public class ProcessFilter implements Runnable {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessFilter.class);
	private static final ClassPathXmlApplicationContext appContextData;


	static {
		appContextData = new ClassPathXmlApplicationContext("ApplicationContext/Application-mysql-data-source.xml");
	}
	private String requestParams;
	private String requestUrl;

	public ProcessFilter(String requestParams, String requestUrl) {
		this.requestParams = requestParams;
		this.requestUrl = requestUrl;
	}

	@Override
	public void run() {

		JSONObject params=null;
		try {
		  params = JSONObject.parseObject(requestParams);
		}catch(Exception e) {
			LOGGER.error(e.getMessage(),e);
		}
		
		// TODO
		LOGGER.info("ProcessFilter 监测到 URL={},参数:{}",requestUrl,params);

	}
}
