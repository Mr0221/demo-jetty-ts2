package redis.test;


import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.x.dao.IUserDao;
import com.x.entity.User;

/**
 * 测试
 * @author http://blog.csdn.net/java2000_wl
 * @version <b>1.0</b>
 */
@ContextConfiguration(locations = {"classpath*:config/redisContext.xml"})
public class RedisTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private IUserDao userDao;

    /**
     * 新增
     * <br>------------------------------<br>
     */
    @Test
    public void testAddUser() {
        final User user = new User();
        user.setId("user1");
        user.setName("java2000_wl");
        final boolean result = userDao.add(user);
        Assert.assertTrue(result);
    }

    /**
     * 批量新增 普通方式
     * <br>------------------------------<br>
     */
    @Test
    public void testAddUsers1() {
        final List<User> list = new ArrayList<User>();
        for (int i = 10; i < 50000; i++) {
            final User user = new User();
            user.setId("user" + i);
            user.setName("java2000_wl" + i);
            list.add(user);
        }
        final long begin = System.currentTimeMillis();
        for (final User user : list) {
            userDao.add(user);
        }
        System.out.println(System.currentTimeMillis() -  begin);
    }

    /**
     * 批量新增 pipeline方式
     * <br>------------------------------<br>
     */
    @Test
    public void testAddUsers2() {
        final List<User> list = new ArrayList<User>();
        for (int i = 10; i < 1500000; i++) {
            final User user = new User();
            user.setId("user" + i);
            user.setName("java2000_wl" + i);
            list.add(user);
        }
        final long begin = System.currentTimeMillis();
        final boolean result = userDao.add(list);
        System.out.println(System.currentTimeMillis() - begin);
        Assert.assertTrue(result);
    }

    /**
     * 修改
     * <br>------------------------------<br>
     */
    @Test
    public void testUpdate() {
        final User user = new User();
        user.setId("user1");
        user.setName("new_password");
        final boolean result = userDao.update(user);
        Assert.assertTrue(result);
    }

    /**
     * 通过key删除单个
     * <br>------------------------------<br>
     */
    @Test
    public void testDelete() {
        final String key = "user1";
        userDao.delete(key);
    }

    /**
     * 批量删除
     * <br>------------------------------<br>
     */
    @Test
    public void testDeletes() {
        final List<String> list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            list.add("user" + i);
        }
        userDao.delete(list);
    }

    /**
     * 获取
     * <br>------------------------------<br>
     */
    @Test
    public void testGetUser() {
        final String id = "user1";
        final User user = userDao.get(id);
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getName(), "java2000_wl");
    }

    /**
     * 设置userDao
     * @param userDao the userDao to set
     */
    public void setUserDao(final IUserDao userDao) {
        this.userDao = userDao;
    }
}
