package springboot.common.memcache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

/**  
* <p>Description:memcache配置文件 </p>  
* @author wjt  create
* @date 2018年12月29日 下午1:54:56
*/ 
@Configuration
public class MemcacheConfiguration {
	@Autowired
	private MemcacheProperties memcacheProperties;

	@Bean	
	public SockIOPool sockIOPool() {
		// 获取连接池的实例
		SockIOPool pool = SockIOPool.getInstance();
		// 服务器列表及其权重
		String[] servers = memcacheProperties.getServers();
		Integer[] weights = memcacheProperties.getWeights();
		// 设置服务器信息
		pool.setServers(servers);
		pool.setWeights(weights);
		// 设置初始连接数、最小连接数、最大连接数、最大处理时间
		pool.setInitConn(memcacheProperties.getInitConn());
		pool.setMinConn(memcacheProperties.getMinConn());
		pool.setMaxConn(memcacheProperties.getMaxConn());
		// 设置连接池守护线程的睡眠时间
		pool.setMaintSleep(memcacheProperties.getMaintSleep());
		// 设置TCP参数，连接超时
		pool.setNagle(memcacheProperties.isNagle());
		pool.setSocketConnectTO(memcacheProperties.getSocketTO());
		// 初始化并启动连接池
		pool.initialize();
		return pool;
	}
	
	@Bean
    public MemCachedClient memCachedClient(){
        return new MemCachedClient();
    }

}
