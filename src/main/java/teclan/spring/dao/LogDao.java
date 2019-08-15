package teclan.spring.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import teclan.spring.model.Log;

@Repository
@Mapper
public interface LogDao {

    public Log findOne(@Param("id")String  id);

    public Integer delete(@Param("id")String  id);

    public Integer deleteBatch(@Param("ids")String[]  ids);

    public Integer create(Log log);

    public Integer update(Log log);
}
