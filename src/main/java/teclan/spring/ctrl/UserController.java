package teclan.spring.ctrl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import teclan.spring.util.HttpTool;
import teclan.spring.util.PagesUtils;
import teclan.spring.util.ResultUtil;

import javax.annotation.Resource;
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

    @Resource
    private JdbcTemplate jdbcTemplate;


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

            int current = parameter.getInteger("currentPage");
            int pageSize = parameter.getInteger("pageSize");


            LOGGER.info("\n\n查询用户信息: {}",parameter);


            int total = jdbcTemplate.queryForObject("select count(*) from user_info",Integer.class);
            int offset = PagesUtils.getOffset(current,pageSize);

            List<Map<String,Object>> maps = jdbcTemplate.queryForList(String.format("select id,name,phone,id_card from user_info limit %s,%s",offset,pageSize));

            return ResultUtil.get(200, "成功",maps,PagesUtils.getPageInfo(current,pageSize,total));


        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        return ResultUtil.get(200, "成功");
    }
}
