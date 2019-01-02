package teclan.spring.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 对接运维反馈
 * 
 * @author teclan
 * 
 *         email: tbj621@163.com
 *
 *         2017年10月21日
 */
public interface TestService {
	
	public JSONObject test(JSONObject json);
	
	public JSONObject getUsers();
	

}
