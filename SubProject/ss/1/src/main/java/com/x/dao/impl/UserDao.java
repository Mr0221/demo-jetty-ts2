
package com.x.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.Assert;

import com.x.dao.AbstractBaseRedisDao;
import com.x.dao.IUserDao;
import com.x.entity.User;

/**
 * Dao
 * @author http://blog.csdn.net/java2000_wl
 * @version <b>1.0</b>
 */
public class UserDao extends AbstractBaseRedisDao<String, User> implements IUserDao {

    /**
     * ����
     *<br>------------------------------<br>
     * @param user
     * @return
     */
    public boolean add(final User user) {
        final boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(final RedisConnection connection)
                    throws DataAccessException {
                final RedisSerializer<String> serializer = getRedisSerializer();
                final byte[] key  = serializer.serialize(user.getId());
                final byte[] name = serializer.serialize(user.getName());
                return connection.setNX(key, name);
            }
        });
        return result;
    }

    /**
     * �������� ʹ��pipeline��ʽ
     *<br>------------------------------<br>
     *@param list
     *@return
     */
    public boolean add(final List<User> list) {
        Assert.notEmpty(list);
        final boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(final RedisConnection connection)
                    throws DataAccessException {
                final RedisSerializer<String> serializer = getRedisSerializer();
                for (final User user : list) {
                    final byte[] key  = serializer.serialize(user.getId());
                    final byte[] name = serializer.serialize(user.getName());
                    connection.setNX(key, name);
                }
                return true;
            }
        }, false, true);
        return result;
    }

    /**
     * ɾ��
     * <br>------------------------------<br>
     * @param key
     */
    public void delete(final String key) {
        final List<String> list = new ArrayList<String>();
        list.add(key);
        delete(list);
    }

    /**
     * ɾ�����
     * <br>------------------------------<br>
     * @param keys
     */
    public void delete(final List<String> keys) {
        redisTemplate.delete(keys);
    }

    /**
     * �޸�
     * <br>------------------------------<br>
     * @param user
     * @return
     */
    public boolean update(final User user) {
        final String key = user.getId();
        if (get(key) == null) {
            throw new NullPointerException("�����в�����, key = " + key);
        }
        final boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(final RedisConnection connection)
                    throws DataAccessException {
                final RedisSerializer<String> serializer = getRedisSerializer();
                final byte[] key  = serializer.serialize(user.getId());
                final byte[] name = serializer.serialize(user.getName());
                connection.set(key, name);
                return true;
            }
        });
        return result;
    }

    /**
     * ͨ��key��ȡ
     * <br>------------------------------<br>
     * @param keyId
     * @return
     */
    public User get(final String keyId) {
        final User result = redisTemplate.execute(new RedisCallback<User>() {
            public User doInRedis(final RedisConnection connection)
                    throws DataAccessException {
                final RedisSerializer<String> serializer = getRedisSerializer();
                final byte[] key = serializer.serialize(keyId);
                final byte[] value = connection.get(key);
                if (value == null) {
                    return null;
                }
                final String name = serializer.deserialize(value);
                return new User(keyId, name, null);
            }
        });
        return result;
    }
}
