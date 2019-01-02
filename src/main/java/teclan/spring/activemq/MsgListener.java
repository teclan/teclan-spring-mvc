package teclan.spring.activemq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * 监听MQ
 * @author dev
 *
 */
public class MsgListener implements MessageListener {
	private final static Logger LOGGER = LoggerFactory.getLogger(MsgListener.class);

	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) message;
			try {
				String text = textMessage.getText();
				LOGGER.info("--------received data -------: "
						+ text);
				JSONObject jsonObject = JSONObject.parseObject(text);
				// TODO
			} catch (JMSException e) {
				LOGGER.error(e.getMessage(), e);
			}

		}
	}
}
