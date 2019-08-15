package teclan.spring.ctrl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import teclan.spring.dao.LogDao;
import teclan.spring.model.Log;
import teclan.spring.util.HttpTool;
import teclan.spring.util.ResultUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/log")
public class LogController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogController.class);

    @Autowired
    private LogDao logDao;

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject get(HttpServletRequest request, HttpServletResponse response,String id) {
        try {
            Log log = logDao.findOne(id);

            return ResultUtil.get(200, "查询成功",log);

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResultUtil.get(500, "查询失败",e.getMessage());
        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject delete(HttpServletRequest request, HttpServletResponse response,String id) {
        try {
            int row = logDao.delete(id);
            return ResultUtil.get(200, row>0?"删除成功":"删除失败","受影响行数:"+row);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResultUtil.get(500, "删除失败",e.getMessage());
        }
    }

    @RequestMapping(value = "/deleteBatch",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject deleteBatch(HttpServletRequest request, HttpServletResponse response,String ids) {
        try {
            int row = logDao.deleteBatch(ids.split(","));
            return ResultUtil.get(200, row>0?"删除成功":"删除失败","受影响行数:"+row);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResultUtil.get(500, "删除失败",e.getMessage());
        }
    }


    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject create(HttpServletRequest request, HttpServletResponse response) {
        try {
            String json = HttpTool.readJSONString(request);
            Log log = JSON.parseObject(json,Log.class);
            int row =logDao.create(log);
            return ResultUtil.get(200, row>0?"创建成功":"创建失败","受影响行数:"+row);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResultUtil.get(500, "创建失败",e.getMessage());
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject update(HttpServletRequest request, HttpServletResponse response) {
        try {
            String json = HttpTool.readJSONString(request);
            Log log = JSON.parseObject(json,Log.class);
            int row =logDao.update(log);
            return ResultUtil.get(200, row>0?"更新成功":"更新失败","受影响行数:"+row);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResultUtil.get(500, "更新失败",e.getMessage());
        }
    }

    @RequestMapping(value = "/query",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject query(HttpServletRequest request, HttpServletResponse response) {
        try {
            JSONObject jsonObject = HttpTool.readJSONParam(request);
             List<Log> logs =logDao.query(jsonObject);
            logDao.countQuery(jsonObject);
            return ResultUtil.get(200, "查询成功",logs);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResultUtil.get(500, "查询失败",e.getMessage());
        }
    }



}
