package teclan.spring.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import teclan.spring.dao.TestDao;

@Repository
public class TestDaoImp implements TestDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(TestDaoImp.class);
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	public List<Map<String, Object>> getUsers(){
		return jdbcTemplate.queryForList("select * from users");
		
	}

}
