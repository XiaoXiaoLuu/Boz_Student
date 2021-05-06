package com.boz.znf.service;

import com.boz.znf.exception.RedisException;
import com.boz.znf.pojo.ClauseVO;
import com.boz.znf.pojo.StudentDO;

import java.util.List;

/**
 * @author ZhangNanFu
 * @date 2021年05月03日 18:59
 */
public interface StudentService {
    /**
     * 按key存储StudentDO数据，并且返回总元素数
     * @author ZhangNanFu
     * @date 2021/5/3 19:55
     * @param studentDO
     * @return int
     * @throws RedisException
     */
    int saveStudentDO(StudentDO studentDO)  throws RedisException;

    /**
     * 根据key键获取返回给页面的userVOList数据
     * @author ZhangNanFu
     * @date 2021/4/27 19:50
     * @param clauseVO
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @throws RedisException
     */
    List<StudentDO> findByClause(ClauseVO clauseVO)  throws RedisException;

    /**
     * 更新Redis中studentDO
     * @author ZhangNanFu
     * @date 2021/5/4 13:06
     * @param studentDO
     * @throws RedisException
     */
    void updateStudentDO(StudentDO studentDO) throws RedisException;

    /**
     * 删除studentDO
     * @author ZhangNanFu
     * @date 2021/5/4 13:08
     * @param studentId
     * @return int
     * @throws RedisException
     */
    int deleteStudentDO(Integer studentId) throws RedisException;
}
