package com.maple.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.maple.common.R;
import com.maple.pojo.Employee;
import com.maple.service.EmployeeService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

/**
 * ClassName:EmployeeController
 * Package: com.maple.controller
 * Description:
 * Author maple
 * Create 2023-01-11
 * Version: v1.0
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request,  @RequestBody Employee employee){
        String password = employee.getPassword();
        // 1. MD5加密
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        // 2. 查询
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);

        // 3. 是否有结果
        if(emp == null)
        {
            return R.error("登陆失败");
        }

        // 4. 比对密码
        if(emp.getPassword()!= null && !emp.getPassword().equals(password))
        {
            return R.error("登陆失败");
        }

        // 5. 查看员工状态
        if(emp.getStatus() == 0)
        {
            return R.error("账号被禁用");
        }

        request.getSession().setAttribute("employee", emp.getId());

        return R.success(emp);

    }


    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }


    @PostMapping
    public R<String> save(HttpServletRequest request, @RequestBody Employee employee){
        // 设置初始密码
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        Long id = (Long) request.getSession().getAttribute("employee");
        employee.setCreateUser(id);
        employee.setUpdateUser(id);

        employeeService.save(employee);

        return R.success("添加成功");
    }

}
