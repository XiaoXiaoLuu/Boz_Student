package com.boz.znf.service.impl;

import com.boz.znf.constatnt.RedisCons;
import com.boz.znf.exception.RedisException;
import com.boz.znf.pojo.ClauseVO;
import com.boz.znf.pojo.StudentDO;
import com.boz.znf.service.StudentService;
import com.boz.znf.utils.RedisUtil;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * @author ZhangNanFu
 * @date 2021年05月03日 18:59
 */
public class StudentServiceImpl implements StudentService {
    private byte[] key = null;
    /**
     * 按key存储StudentDO数据
     *
     * @param studentDO
     * @author ZhangNanFu
     * @date 2021/5/3 19:48
     * @throws RedisException
     */
    @Override
    public int saveStudentDO(StudentDO studentDO) throws RedisException {
        int score = 1;
        // 如果是第一次添加学生
        if (key == null) {
            key = RedisUtil.serializekey(RedisCons.STUDENT_TABLE);
        }
        try (Jedis jedis = RedisUtil.getJedis()) {
            // 获取新增元素的分数值
            long card = jedis.zcard(key);
            if (card == 0) {
                // 如果key的有序集合不存在，返回0时，就新建
                studentDO.setId(String.valueOf(score));
                jedis.zadd(key, score, RedisUtil.serializeValue(studentDO));
                return score;
            }

            // 这是当存在key的有序集合，即是说已有学生添加，那就找最后一元素的分数
            Set<byte[]> last = jedis.zrange(key, card - 1, card - 1);
            for (byte[] bytes : last) {
                score = jedis.zscore(key, bytes).intValue();
            }
            System.out.println(score);
            score++;
            // 设置记录id
            studentDO.setId(String.valueOf(score));
            jedis.zadd(key, score, RedisUtil.serializeValue(studentDO));
            return (int) ++card;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RedisException("Redis添加数据失败");
        }
    }

    /**
     * 根据key键获取返回给页面的StudentVOList数据
     *
     * @param clauseVO
     * @return java.util.List<com.boz.znf.service.pojo.UserDO>
     * @throws RedisException
     * @author ZhangNanFu
     * @date 2021/5/3 19:50
     * @throws RedisException
     */
    @Override
    public List<StudentDO> findByClause(ClauseVO clauseVO) throws RedisException {
        Set<byte[]> zrange;
        try (Jedis jedis = RedisUtil.getJedis()) {
            if (key == null) {
                key = RedisUtil.serializekey(RedisCons.STUDENT_TABLE);
            }
            int start = (clauseVO.getCurrentPage() - 1) * clauseVO.getCurrentCount();
            int stop = start + clauseVO.getCurrentCount() - 1;
            zrange = jedis.zrange(key, start, stop);
            // 查询出总共的记录数
            clauseVO.setTotal(jedis.zcard(key).intValue());
            if (zrange == null || zrange.isEmpty()) {
                return null;
            }

        } catch (Exception e) {
            throw new RedisException("Redis查询数据失败");
        }

        return RedisUtil.unSerializeValue(zrange);
    }

    /**
     * 更新Redis中studentDO
     *
     * @param studentDO
     * @throws RedisException
     * @author ZhangNanFu
     * @date 2021/5/4 13:06
     */
    @Override
    public void updateStudentDO(StudentDO studentDO) throws RedisException {
        try (Jedis jedis = RedisUtil.getJedis()) {
            if (key == null) {
                key = RedisUtil.serializekey(RedisCons.STUDENT_TABLE);
            }
            int start = Integer.parseInt(studentDO.getId());
//            // 根据score查找元素
//            int mid = Integer.parseInt(studentDO.getId());
//            Set<byte[]> bytes = jedis.zrangeByScore(key, mid, mid);
//            for (byte[] byte1:
//                 bytes) {
//                // 根据元素查找索引值
//                start = jedis.zrank(key, byte1).intValue();
//                System.out.println("start = " + start);
//            }
            if (start < 0) {
                throw new RuntimeException("索引值未找到");
            }
            System.out.println("start = " + start);

            // 先删除原来的记录
            jedis.zremrangeByScore(key, start, start);
            // 再新建已达到更新的目的
            jedis.zadd(key, Integer.parseInt(studentDO.getId()), RedisUtil.serializeValue(studentDO));

        } catch (Exception e) {
            e.printStackTrace();
            throw new RedisException("Redis更新数据失败");
        }
    }

    /**
     * 删除studentDO
     *
     * @param studentId
     * @return int
     * @throws RedisException
     * @author ZhangNanFu
     * @date 2021/5/4 13:08
     * @throws RedisException
     */
    @Override
    public int deleteStudentDO(Integer studentId) throws RedisException {
        try (Jedis jedis = RedisUtil.getJedis()) {
            if (key == null) {
                key = RedisUtil.serializekey(RedisCons.STUDENT_TABLE);
            }

            // 先删除原来的记录
            jedis.zremrangeByScore(key, studentId, studentId);
            return jedis.zcard(key).intValue();
        } catch (Exception e) {
            throw new RedisException("Redis删除数据失败");
        }
    }

}
