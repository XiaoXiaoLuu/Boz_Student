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
 * @date 2021年05月03日 19:00
 */
@WebServlet("/list")
public class StudentListServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String page = request.getParameter("currentPage");
        String count = request.getParameter("currentCount");
        String total = request.getParameter("total");
        StudentService studentService = new StudentServiceImpl();
        List<StudentVO> studentVOList;
        List<StudentDO> studentDOList;
        ClauseVO clauseVO = (ClauseVO) session.getAttribute("clauseVO");

        try {
            if (StringUtils.isNumeric(page) && StringUtils.isNumeric(count)
                    && StringUtils.isNumeric(total)) {
                clauseVO = new ClauseVO(Integer.valueOf(page), Integer.valueOf(count),
                        Integer.valueOf(total));
            } else if (clauseVO == null) {
                clauseVO = new ClauseVO(1, 10, 0);
            }

            studentDOList = studentService.findByClause(clauseVO);
            if (studentDOList != null && !studentDOList.isEmpty()) {
                studentVOList = StudentConvertUtil.toStudentVO(studentDOList);
            } else {
                studentVOList = new ArrayList<>(0);
            }
            System.out.println(studentVOList);
            session.setAttribute("studentVOList", studentVOList);
            session.setAttribute("clauseVO", clauseVO);
        } catch (RedisException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
