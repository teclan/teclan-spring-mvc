package teclan.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import teclan.spring.dao.Dao;
import teclan.spring.dao.LogDao;
import teclan.spring.model.Log;
import teclan.spring.model.Model;

@Service
public class LogService extends AbstractService {

    @Autowired
    private LogDao logDao;

    @Override
    protected Dao getDao() {
        return logDao;
    }

    protected Class getModelClass() {
        return Log.class;
    }

}
