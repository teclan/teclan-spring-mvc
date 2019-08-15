package teclan.spring.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import teclan.spring.model.Log;

@Repository
@Mapper
public interface LogDao {

    public Log findOne(@Param("id")Integer  id);

    public Integer delete(@Param("id")Integer  id);

    public Integer deleteBatch(@Param("ids")String[]  ids);

}
