package teclan.spring.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import teclan.spring.activemq.MqQueueSendServer;
import teclan.spring.activemq.MqTopicSendServer;

public class InitServlet extends HttpServlet{
	
	private static final long serialVersionUID = 142275568750796042L;
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	private ApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();  

	@Override
	public void init() throws ServletException {
		super.init();
		
		LOGGER.info("\n\n程序初始化..\n\n");
		MqTopicSendServer mqTopicSendServer = (MqTopicSendServer) wac.getBean("mqTopicSendServer");
		MqQueueSendServer mqQueueSendServer = (MqQueueSendServer) wac.getBean("mqQueueSendServer");
		
		LOGGER.info("\n\n发送MQ测试消息..\n\n");
		
		mqTopicSendServer.sendMessage("I'm topic message");
		mqQueueSendServer.sendMessage("I'm queue message");
		
		
	}
}