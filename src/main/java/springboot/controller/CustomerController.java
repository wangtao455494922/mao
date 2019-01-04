package springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import springboot.service.YmxCustomerService;

@RestController
public class CustomerController {
	@Autowired
	private YmxCustomerService ymxCustomerService;
	
	/**  
	 * <p>Description:请求Mysql测试方法 </p>  
	 */
	@PostMapping(value="/testMysql")
	public String testMysql() {
		ymxCustomerService.testMysql();
		return "testMysql成功!";
	}
	
	/**  
	 * <p>Description:请求Memcache测试方法 </p>  
	 */
	@PostMapping(value="/testMemcache")
	public String testMemcache() {
		ymxCustomerService.testMemcache();
		return "testMemcache成功!";
	}
	
	/**  
	 * <p>Description:请求Ehcache测试方法 </p>  
	 */
	@PostMapping(value="/testEhcache")
	public String testEhcache() {
		ymxCustomerService.testEhcache();
		return "testEhcache成功!";
	}
	
	/**  
	 * <p>Description:请求MongoDB测试方法 </p>  
	 */
	@PostMapping(value="/testMongoDB")
	public String testMongoDB() {
		ymxCustomerService.testMongoDB();
		return "testMongoDB成功!";
	}
}
