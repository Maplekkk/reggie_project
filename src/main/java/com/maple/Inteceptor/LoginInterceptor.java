package com.maple.Inteceptor;

import com.alibaba.fastjson.JSON;
import com.maple.common.R;
import com.maple.pojo.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ClassName:LoginInterceptor
 * Package: com.maple.Inteceptor
 * Description:
 * Author maple
 * Create 2023-01-11
 * Version: v1.0
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        log.info(request.getRequestURI());
        Employee employee = (Employee) session.getAttribute("employee");
        if(session == null || session.getAttribute("employee") == null)
        {
            response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
            return false;
        }
        return true;
    }
}
