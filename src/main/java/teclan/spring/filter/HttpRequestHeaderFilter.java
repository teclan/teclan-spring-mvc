package teclan.spring.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import teclan.spring.util.HttpTool;
import teclan.spring.util.PropertyConfigUtil;
import teclan.spring.util.ResultUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class HttpRequestHeaderFilter implements Filter {
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpRequestHeaderFilter.class);
    private final static PropertyConfigUtil propertyconfigUtil;
    private static String[] headers= null;
    private static List<String> whiteUrls=new ArrayList<>();
    private static String baseUrl=null;


    static {
        propertyconfigUtil = PropertyConfigUtil.getInstance("config.properties");
        headers = propertyconfigUtil.getValue("headers").split(",");
        whiteUrls = Arrays.asList(propertyconfigUtil.getValue("whiteUrls").split(","));
        baseUrl=propertyconfigUtil.getValue("baseUrl");
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        StatusExposingServletResponse response = new StatusExposingServletResponse(
                (HttpServletResponse) servletResponse);

        String requestURI = request.getRequestURI();

        if(!whiteUrls.contains(requestURI.replace(baseUrl,""))){

            for(String key :headers){
                String value = request.getHeader(key);
                if(value==null){
                    LOGGER.error("\n\n 请求头信息错误，字段 {} 值为空,url={},请求被拦截!!\n\n",key,requestURI);
                    HttpTool.setResponse(response,200,ResultUtil.get(403, "认证失败"));
                    return ;
                }
            }
        }

        filterChain.doFilter(servletRequest, response);
    }

    @Override
    public void destroy() {

    }


}
