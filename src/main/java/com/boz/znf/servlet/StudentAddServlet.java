package com.boz.znf.servlet;

import com.boz.znf.exception.RedisException;
import com.boz.znf.pojo.ClauseVO;
import com.boz.znf.pojo.StudentDO;
import com.boz.znf.pojo.StudentVO;
import com.boz.znf.service.StudentService;
import com.boz.znf.service.impl.StudentServiceImpl;
import com.boz.znf.utils.StudentConvertUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangNanFu
 * @date 2021年05月04日 14:48
 */
@WebServlet("/add")
public class StudentAddServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ClauseVO clauseVO = (ClauseVO) session.getAttribute("clauseVO");
        String studentName = request.getParameter("studentnameAdd");
        String birthday = request.getParameter("birthdayAdd");
        String description = request.getParameter("descriptionAdd");
        String avgScore = request.getParameter("avgScoreAdd");
        String errMsg = null;
        if (StringUtils.isBlank(studentName)) {
            errMsg = "学生姓名不能为空";
        }
        if (StringUtils.isBlank(birthday)) {
            errMsg = "出生日期不能为空";
        }
        if (StringUtils.isBlank(description)) {
            errMsg = "备注不能为空";
        }
        if (StringUtils.isBlank(avgScore)) {
            errMsg = "平均分不能为空";
        }
        session.setAttribute("errMsg", errMsg);

        StudentService studentService = new StudentServiceImpl();
        try {
            if (clauseVO == null) {
                clauseVO = new ClauseVO(1, 10, 0);
            }
            StudentDO student = StudentConvertUtil.toStudentDO(null, studentName, birthday, description, avgScore);
            System.out.println(student);
            int count = studentService.saveStudentDO(student);
            clauseVO.setTotal(count);
            session.setAttribute("clauseVO", clauseVO);
        } catch (RedisException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/list").forward(request, response);
    }
}
