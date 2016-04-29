import com.shike.cart.redis.exception.RedisException;
import com.shike.cart.redis.service.RedisService;
import com.shike.cart.redis.service.impl.RedisServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shike on 16/4/29.
 */

@ContextConfiguration(locations = ("classpath:spring-redis.xml"))
@RunWith(SpringJUnit4ClassRunner.class)
public class TestRedis {
    @Resource(name = "redisService")
    private RedisServiceImpl redisService;
    @Test
    public void testAdd() {
        try {
            redisService.zadd("shike", 99, "yuwen");
            redisService.zadd("shike", 99, "数学");
            redisService.zadd("shike", 100, "地理");
            System.out.println(redisService.zrange("shike", 0, -1));
            System.out.println(redisService.zcard("shike"));
            String [] str = {"yuwen", "数学"};
            redisService.zrem("shike", str);
            System.out.println(redisService.zrange("shike", 0, -1));
            System.out.println(redisService.zcard("shike"));
        } catch (RedisException rex) {
            rex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testHash() {
        String pre = "cartId:";
        Map<String, String> cartInfo = new HashMap<String, String>();
        cartInfo.put("cartId", "2016042901001000126");
        cartInfo.put("shopId", "101277");
        cartInfo.put("skuId", "200398");
        cartInfo.put("amount", "2");
        cartInfo.put("price", "1000");
        cartInfo.put("userId", "13121911101");
        cartInfo.put("status", "0");
        cartInfo.put("description", "");
        String key = pre + "2016042901001000126";
        try {
            redisService.hmset(key, cartInfo);
            String [] fields = {"cartId", "skuId", "amount", "price"};
            System.out.println("hmget: " + redisService.hmget(key, fields));
            System.out.println("hgetall: " + redisService.hgetAll(key));
            System.out.println("hexists: " + redisService.hexists(key, "userId"));
            System.out.println("hlen: " + redisService.hlen(key));
            String [] delFields = {"status"};
            System.out.println("hdel: " + redisService.hdel(key, delFields));
            System.out.println("hlen: " + redisService.hlen(key));
            System.out.println("hgetall: " + redisService.hgetAll(key));
        } catch (RedisException rex) {
            rex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
