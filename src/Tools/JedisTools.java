package Tools;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ClassName JedisTools
 * Description Jedis 工具类
 * Author Ganzhenghao
 * Date  2019/6/17 16:28
 * Version 1.0
 **/
public class JedisTools {
    private static JedisPool jedisPool ;
    static {
        JedisPoolConfig config = new JedisPoolConfig();
/*        //连接池最大数量
        config.setMaxTotal(50);
        //最大空闲连接数量
        config.setMaxIdle(10);*/

        InputStream is = JedisTools.class.getClassLoader().getResourceAsStream("jedis.properties");
        Properties ps = new Properties();
        try {
            ps.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        config.setMaxTotal(Integer.parseInt(ps.getProperty("maxTotal")));
        config.setMaxIdle(Integer.parseInt(ps.getProperty("maxIdle")));
        //用配置对象构造redis连接池
        jedisPool=new JedisPool(config,ps.getProperty("host"),Integer.parseInt(ps.getProperty("port")));
    }

    /**
     * 获取jedis对象
     * @return 一个jedis对象
     */
    public static Jedis getJedis(){
        return  jedisPool.getResource();
    }
    /**
     * 关闭连接jedis对象
     */
    public static void close(Jedis...jedises){
        for (Jedis jedis:jedises) {
            jedis.close();
        }
    }
}
