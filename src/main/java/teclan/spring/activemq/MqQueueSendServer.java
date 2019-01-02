package teclan.spring.activemq;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service
public class MqQueueSendServer {

	@Autowired
	@Qualifier("sendQueue")
	private Destination destination;

	private final static Logger logger = LoggerFactory.getLogger(MqQueueSendServer.class);
	@Resource
	private JmsTemplate queueJMSTemplate;

	public void sendMessage(final String message) {
		try {
			queueJMSTemplate.send(destination, new MessageCreator() {
				@Override
				public Message createMessage(Session session) throws JMSException {
					return session.createTextMessage(message);
				}
			});
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
