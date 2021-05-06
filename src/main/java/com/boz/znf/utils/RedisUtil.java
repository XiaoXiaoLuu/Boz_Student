package com.boz.znf.utils;

import com.boz.znf.pojo.StudentDO;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author ZhangNanFu
 * @date 2021年05月03日 20:28
 */
public class RedisUtil {
    private static JedisPool jedisPool;

    static {
        // 配置连接池
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(5);
        config.setMaxIdle(3);
        config.setMinIdle(2);

        // 创建连接池
        jedisPool = new JedisPool(config, "localhost", 6379);
    }

    /**
     * 获取redis连接
     */
    public static Jedis getJedis() {
        return jedisPool.getResource();
    }


    /**
     * 序列化数据库中取出的List<UserDO>对象
     * @author ZhangNanFu
     * @date 2021/5/3 23:39
     * @param studentDO
     * @return byte[]
     */
    public static byte[] serializeValue(StudentDO studentDO) {
        byte[] op = new byte[0];
        ObjectOutput oos;
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            //创建一个byte的数组的流
            byteArrayOutputStream = new ByteArrayOutputStream();
            //创建对象流将对象写入到byte数组里
            oos = new ObjectOutputStream(byteArrayOutputStream);
            //进行写入操作
            oos.writeObject(studentDO);
            //将byte数组的对象进行转换为byte的数组
            op = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return op;
    }

    /**
     * 反序列化redis缓存中的数据
     * @author ZhangNanFu
     * @date 2021/5/3 23:30
     * @param zrange
     * @return java.util.List<com.boz.znf.pojo.StudentDO>
     */
    public static List<StudentDO> unSerializeValue(Set<byte[]> zrange) {
        ByteArrayInputStream byteArrayInputStream;
        List<StudentDO> studentDOList = new ArrayList<>(zrange.size());
        try {
            for (byte[] obj :
                    zrange) {
                // 创建一个byte的数组的读入流对其byte数组
                byteArrayInputStream = new ByteArrayInputStream(obj);
                // 对象的输入流，用于读取对象
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                // 将byte格式的转化为StudentDO对象
                StudentDO student = (StudentDO) objectInputStream.readObject();
                studentDOList.add(student);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return studentDOList;
    }

    /**
     * 将key序列化
     * @author ZhangNanFu
     * @date 2021/5/4 9:54
     * @param key
     * @return byte[]
     */
    public static byte[] serializekey(String key) {
        byte[] op = new byte[0];
        ObjectOutput oos;
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            //创建一个byte的数组的流
            byteArrayOutputStream = new ByteArrayOutputStream();
            //创建对象流将对象写入到byte数组里
            oos = new ObjectOutputStream(byteArrayOutputStream);
            //进行写入操作
            oos.writeObject(key);
            //将byte数组的对象进行转换为byte的数组
            op = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return op;
    }

    /**
     * 反序列化key
     * @author ZhangNanFu
     * @date 2021/5/3 23:30
     * @param value
     * @return java.util.List<com.boz.znf.service.pojo.UserDO>
     */
    public static String unSerializeKey(byte[] value) {
        ByteArrayInputStream byteArrayInputStream;
        String obj = null;
        //创建一个byte的数组的读入流对其byte数组
        byteArrayInputStream =new ByteArrayInputStream(value);
        try {
            //对象的输入流，用于读取对象
            ObjectInputStream objectInputStream=new ObjectInputStream(byteArrayInputStream);
            //将byte格式的转化为List<UserDO>对象
            obj = (String) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return obj;
    }

}
