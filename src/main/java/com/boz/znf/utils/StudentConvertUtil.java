package com.boz.znf.utils;

import com.boz.znf.pojo.StudentDO;
import com.boz.znf.pojo.StudentVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangNanFu
 * @date 2021年05月04日 13:51
 */
public class StudentConvertUtil {
    public static List<StudentVO> toStudentVO(List<StudentDO> studentDOList){
        List<StudentVO> studentVOList = new ArrayList<>(studentDOList.size());
        for (StudentDO student :
                studentDOList) {
            StudentVO studentVO = new StudentVO();
            studentVO.setId(student.getId());
            studentVO.setName(student.getName());
            studentVO.setBirthday(DateFormatUtil.toDateString(student.getBirthday()));
            studentVO.setDescription(student.getDescription());
            studentVO.setAvgScore(student.getAvgScore().toString());

            studentVOList.add(studentVO);
        }
        return studentVOList;
    }

    public static StudentDO toStudentDO(String var1, String var2, String var3, String var4, String var5){
        StudentDO student = new StudentDO();
        student.setId(var1);
        student.setName(var2);
        student.setBirthday(DateFormatUtil.toDateString(var3));
        student.setDescription(var4);
        student.setAvgScore(Integer.valueOf(var5));

        return student;
    }
}
