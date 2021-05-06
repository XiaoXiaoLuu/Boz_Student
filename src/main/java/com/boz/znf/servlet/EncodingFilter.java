package com.boz.znf.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author ZhangNanFu
 * @date 2021年05月04日 19:53
 */
@WebFilter("/*")
public class EncodingFilter implements Filter {
    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = ((HttpServletRequest) request);
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String requestURI = req.getRequestURI();
        if (requestURI.contains("/css/")
                || requestURI.contains("/js/")
                || requestURI.contains("/img")) {
            response.setContentType("text/css;charset=utf-8");
            chain.doFilter(request, response);
            return;
        }
        response.setContentType("text/html;charset=utf-8");
        chain.doFilter(request, response);
    }


    @Override
    public void init(FilterConfig cfg) throws ServletException {


    }
}