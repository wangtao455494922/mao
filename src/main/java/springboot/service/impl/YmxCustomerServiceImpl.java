package springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.whalin.MemCached.MemCachedClient;

import lombok.extern.slf4j.Slf4j;
import springboot.dao.YmxCustomerMapper;
import springboot.model.YmxCustomer;
import springboot.service.YmxCustomerService;

/**  
* <p>Description:用户实现类 </p>  
* @author wjt  create
* @date 2018年12月29日 下午2:01:59
*/ 
@Slf4j
@Service
@Transactional
public class YmxCustomerServiceImpl implements YmxCustomerService {
	@Autowired
	private YmxCustomerMapper mapper;
	
	@Autowired
	private MemCachedClient memCachedClient;
	@Autowired
	private MongoTemplate  mongoTemplate ;
	
	
	@Override
	public void testMysql() {
		List<YmxCustomer> list = mapper.selectAll();
		log.info(list.toString());
	}

	@Override
	@Cacheable(cacheNames="baseCache")
	public void testEhcache() {
		List<YmxCustomer> list = mapper.selectAll();
		log.info(list.toString());
	}

	@Override
	public void testMemcache() {
		 memCachedClient.add("mem", "111");
	}

	@Override
	public void testMongoDB() {
		YmxCustomer customer = new YmxCustomer(1, "111", "000000", "王三", "王浩", "2018-11-20 15:20-30");
		
		//新增
		mongoTemplate.insert(customer);
		
		//查询
		Query findQuery = new Query(Criteria.where("uuid").is(0));
		List<YmxCustomer> result = mongoTemplate.find(findQuery , YmxCustomer.class,"springboot");
		log.info(result.toString());
		
		//修改
		Query updateQuery = new Query(Criteria.where("uuid").is(1));
		Update update = new Update().set("pwd", "1111");
		mongoTemplate.updateFirst(updateQuery, update, YmxCustomer.class,"springboot");
		
		//删除
		Query removeQuery = new Query(Criteria.where("uuid").is(1));
		mongoTemplate.remove(removeQuery, YmxCustomer.class,"springboot");
	}
	
}
