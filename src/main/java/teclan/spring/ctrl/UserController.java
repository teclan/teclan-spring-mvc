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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);


    @RequestMapping(value = "/login")
    @ResponseBody
    public JSONObject login(HttpServletRequest request, HttpServletResponse response) {
        try {
            String json = HttpTool.readJSONString(request);
            JSONObject parameter = JSON.parseObject(json);


        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return ResultUtil.get(200, "成功");
    }


    @RequestMapping(value = "/get")
    @ResponseBody
    public JSONObject get(HttpServletRequest request, HttpServletResponse response) {
        try {
            String json = HttpTool.readJSONString(request);
            JSONObject parameter = JSON.parseObject(json);

            LOGGER.info("\n\n登录信息: {}",parameter);

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        List<Map<String,Object>> users = new ArrayList<>();

        Map<String,Object> map1 = new HashMap<>();
        map1.put("id",1);
        map1.put("name","张三");
        map1.put("phone","123xxx");
        map1.put("address","北京");

        Map<String,Object> map2 = new HashMap<>();
        map2.put("id",2);
        map2.put("name","李四");
        map2.put("phone","010-11000");
        map2.put("address","上海");

        users.add(map1);
        users.add(map2);

        return ResultUtil.get(200, "成功",users);
    }
}
