package teclan.spring.util;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class ResultUtil {
	
	public static <T> JSONObject jsonResult(
	        Integer code, String message,JSONObject json) {
		JSONObject jsonResult = new JSONObject();
		JSONObject result = new JSONObject();
		result.put("code", code);
		result.put("message", message);
		jsonResult.put("result", result);
		jsonResult.put("json", json);
		return jsonResult;
	}
	
	public static <T> JSONObject jsonResultList(
	        Integer code, String message) {
		JSONObject jsonResult = new JSONObject();
		JSONObject result = new JSONObject();
		result.put("code", code);
		result.put("message", message);
		jsonResult.put("result", result);
		return jsonResult;
	}

	public static <T> JSONObject jsonResultList(
	        Integer code, String message, List<T> list) {
		JSONObject jsonResult = new JSONObject();
		JSONObject result = new JSONObject();
		result.put("code", code);
		result.put("message", message);
		jsonResult.put("result", result);
		jsonResult.put("alertPojo", list);
		return jsonResult;
	}
	
	public static <T> JSONObject jsonResultList(
	        Integer code, String message, List<T> list,JSONObject pageInfo) {
		JSONObject jsonResult = new JSONObject();
		JSONObject result = new JSONObject();
		result.put("code", code);
		result.put("message", message);
		jsonResult.put("result", result);
		jsonResult.put("alertPojo", list);
		jsonResult.put("pageInfo", pageInfo);
		return jsonResult;
	}
	
	public static <T> JSONObject jsonResultListP(
	        Integer code, String message, List<T> list,JSONObject pageInfo) {
		JSONObject jsonResult = new JSONObject();
		JSONObject result = new JSONObject();
		result.put("code", code);
		result.put("message", message);
		jsonResult.put("result", result);
		jsonResult.put("pretreatmentPojo", list);
		jsonResult.put("pageInfo", pageInfo);
		return jsonResult;
	}
	
	public static <T> JSONObject jsonResultListM(
	        Integer code, String message, List<T> list,JSONObject pageInfo) {
		JSONObject jsonResult = new JSONObject();
		JSONObject result = new JSONObject();
		result.put("code", code);
		result.put("message", message);
		jsonResult.put("result", result);
		jsonResult.put("maintenancePojo", list);
		jsonResult.put("pageInfo", pageInfo);
		return jsonResult;
	}
	
	public static <T> JSONObject jsonResultBasicP(
			Integer code, String message, T obj) {
		JSONObject jsonResult = new JSONObject();
		JSONObject result = new JSONObject();
		result.put("code", code);
		result.put("message", message);
		jsonResult.put("result", result);
		jsonResult.put("pretreatmentPojo", obj);
		return jsonResult;
	}
	
	public static <T> JSONObject jsonResultBasicM(
			Integer code, String message, T obj) {
		JSONObject jsonResult = new JSONObject();
		JSONObject result = new JSONObject();
		result.put("code", code);
		result.put("message", message);
		jsonResult.put("result", result);
		jsonResult.put("maintenancePojo", obj);
		return jsonResult;
	}
	
	public static <T> JSONObject jsonResultBasicS(
			Integer code, String message, T obj) {
		JSONObject jsonResult = new JSONObject();
		JSONObject result = new JSONObject();
		result.put("code", code);
		result.put("message", message);
		jsonResult.put("result", result);
		jsonResult.put("statistics", obj);
		return jsonResult;
	}
	
	public static <T> JSONObject jsonResultBasicU(
			Integer code, String message, T obj) {
		JSONObject jsonResult = new JSONObject();
		JSONObject result = new JSONObject();
		result.put("code", code);
		result.put("message", message);
		jsonResult.put("result", result);
		jsonResult.put("userZonePojo", obj);
		return jsonResult;
	}
	
	public static JSONObject simpleResponse(int code, String message, String detail) {
		JSONObject jsonResult = new JSONObject();
		jsonResult.put("code", code);
		jsonResult.put("message", message);
		jsonResult.put("detail", detail);
		return jsonResult;
	}
	
}
