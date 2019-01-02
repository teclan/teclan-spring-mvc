package teclan.spring.service.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import teclan.spring.dao.TestDao;
import teclan.spring.service.TestService;

@Service
public class TestServiceImp implements TestService {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(TestServiceImp.class);

	@Resource
	private TestDao feedbackDao;

	@Override
	public JSONObject test(JSONObject json) {
		// TODO
		JSONObject jsonObject =new JSONObject();
		jsonObject.put("message", "成功");
		jsonObject.put("code", 200);
		return jsonObject;
	}
	
	public JSONObject getUsers() {
		
		JSONObject jsonObject =new JSONObject();
		List<Map<String,Object>> data= feedbackDao.getUsers();
		
		jsonObject.put("message", "成功");
		jsonObject.put("code", 200);
		jsonObject.put("data", data);
		
		return jsonObject;
		
	}
	
}
