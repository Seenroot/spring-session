import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class SimpleUseRedis {
    public static void main(String[] args) {
        jedisClient();
        jedisPool();
        testJedisPool();
    }

    public static void jedisClient() {
        // 创建Jedis
        Jedis jedis = new Jedis("106.13.177.87", 6379);
        Jedis jedis2 = new Jedis("127.0.0.1", 6379);
        // 通过Jedis赋值
        jedis.set("s2", "222");
        // 通过Jedis取值
        String s2 = jedis.get("s2");
        System.out.println(s2);
        // 关闭Jedis
        jedis.close();
    }

    public static void jedisPool() {
        // JedisPool
        JedisPool jedisPool = new JedisPool("106.13.177.87", 6379);
        JedisPool jedisPool2 = new JedisPool("127.0.0.1", 6379);
        // 通过连接池获取jedis对象
        Jedis jedis = jedisPool.getResource();
        String s2 = jedis.get("s2");
        System.out.println(s2);
        // 关闭jedis客户端
        jedis.close();
        // 关闭连接池
        jedisPool.close();
    }

    public static void testJedisPool() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/*.xml");
        System.out.println("applicationContext = " + applicationContext);
        JedisPool pool = (JedisPool) applicationContext.getBean("jedisPool");
        Jedis jedis = null;
        try {
            jedis = pool.getResource();

            jedis.set("name", "lisi");
            String name = jedis.get("name");
            System.out.println(name);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (jedis != null) {
                // 关闭连接
                jedis.close();
            }
        }
    }
}
