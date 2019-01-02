package teclan.spring.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ESConfigUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(ESConfigUtil.class);

	private static PropertyConfigUtil propertyConfigUtil = PropertyConfigUtil
			.getInstance("es.properties");
	public static TransportClient client;

	synchronized static public void loads() {
		if (client == null) {
			try {
				String[] eips = propertyConfigUtil.getValue("elasticsearch.ips").split(";");
				String clustername = propertyConfigUtil
						.getValue("elasticsearch.name");
				
				String[] ports = propertyConfigUtil.getValue("elasticsearch.ports").split(";");

				Settings settings = Settings.settingsBuilder().put("cluster.name", clustername)
						.put("client.transport.ignore_cluster_name", false)
						.put("client.transport.ping_timeout", "5s")
						// .put("node.client", true)
						.put("client.transport.sniff", true).build();


				try {

					client = TransportClient
							.builder()
							.settings(settings)
							.build();
					
					for (int i = 0; i < eips.length; i++) {
						client.addTransportAddress(new InetSocketTransportAddress(
								InetAddress.getByName(eips[i]),
								Integer.valueOf(ports[i])));
					}

				} catch (UnknownHostException e) {
					LOGGER.error(e.getMessage(), e);
				}

			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
	}

	static {
		loads();
	}
}
