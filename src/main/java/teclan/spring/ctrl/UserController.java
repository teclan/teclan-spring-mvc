package teclan.spring.ctrl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import teclan.spring.util.HttpTool;
import teclan.spring.util.ResultUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);


    @RequestMapping(value = "/login")
    @ResponseBody
    public JSONObject test(HttpServletRequest request, HttpServletResponse response) {
        try {
            String json = HttpTool.readJSONString(request);
            JSONObject parameter = JSON.parseObject(json);

            LOGGER.info("\n\n登录信息: {}",parameter);

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return ResultUtil.simpleResponse(200, "成功",null);
    }
}
