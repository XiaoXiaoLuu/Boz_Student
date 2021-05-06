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
 * @date 2021年05月04日 14:47
 */
@WebServlet("/del")
public class StudentDelServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ClauseVO clauseVO = (ClauseVO) session.getAttribute("clauseVO");
        String studentId = request.getParameter("studentId");
        StudentService studentService = new StudentServiceImpl();

        if (clauseVO == null) {
            clauseVO = new ClauseVO(1, 10, 0);
        }
        try {
            if (studentId == null) {
                request.getRequestDispatcher("/list").forward(request, response);
            } else {
                int total = studentService.deleteStudentDO(Integer.valueOf(studentId));
                clauseVO.setTotal(total);
                session.setAttribute("clauseVO", clauseVO);
            }
        } catch (RedisException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/list").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
