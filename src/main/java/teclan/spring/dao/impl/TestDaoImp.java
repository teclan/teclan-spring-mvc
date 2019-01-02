package teclan.spring.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;

import teclan.spring.dao.TestDao;
import teclan.spring.util.ESConfigUtil;

@Repository
public class TestDaoImp implements TestDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(TestDaoImp.class);
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	public List<Map<String, Object>> getUsers(){
		return jdbcTemplate.queryForList("select * from users");
		
	}

}
