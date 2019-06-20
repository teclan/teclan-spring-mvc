package teclan.spring.util;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class ResultUtil {

	public static JSONObject get(int code, String message) {
		JSONObject jsonResult = new JSONObject();
		jsonResult.put("code", code);
		jsonResult.put("message", message);
		return jsonResult;
	}

	public static JSONObject get(int code, String message, Object data) {
		JSONObject jsonResult = new JSONObject();
		jsonResult.put("code", code);
		jsonResult.put("message", message);
		jsonResult.put("data", data);
		return jsonResult;
	}
	
}
