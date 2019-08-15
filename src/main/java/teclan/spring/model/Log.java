package teclan.spring.model;

import teclan.spring.util.IdUtils;

public class Log extends Model {

    private String id;
    private String sessionId;
    private String url;
    private Object parameter;
    private String result;
    private String status;
    private String createdAt;


    public Log(String id,String sessionId, String url, Object parameter, String result, String status, String createdAt) {
        this.id = id;
        this.sessionId = sessionId;
        this.url = url;
        this.parameter = parameter;
        this.result = result;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Log(String sessionId, String url, Object parameter) {
        this.sessionId = sessionId;
        this.url = url;
        this.parameter = parameter;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getParameter() {
        return parameter;
    }

    public void setParameter(Object parameter) {
        this.parameter = parameter;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String getTableName() {
        return "log";
    }

}
