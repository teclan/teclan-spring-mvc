package teclan.spring.ctrl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import teclan.spring.service.TestService;
import teclan.spring.util.HttpTool;
import teclan.spring.util.ResultUtil;

/**
 * 修改维修单和修改处警单反馈接口
 * 
 * @author teclan
 * 
 *         email: tbj621@163.com
 *
 *         2017年10月21日
 */
@Controller
public class TestCtrl {
	private static final Logger LOGGER = LoggerFactory.getLogger(TestCtrl.class);

	@Resource
	private TestService testService;

	@RequestMapping(value = "/test")
	@ResponseBody
	public JSONObject test(HttpServletRequest request, HttpServletResponse response) {
		try {
			String json = HttpTool.readJSONString(request);
			JSONObject parameter = JSON.parseObject(json);
			
			return testService.test(parameter);
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return ResultUtil.jsonResultList(500, "失败");
	}

	@RequestMapping(value = "test1")
	@ResponseBody
	public String getAlertByCondition(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("json") String json){
		LOGGER.info("json={}",json);
         return json;
	}
	
	@RequestMapping(value = "/getUsers")
	@ResponseBody
	public JSONObject getUsers(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			return testService.getUsers();
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return ResultUtil.jsonResultList(500, "失败");
	}
	
}
