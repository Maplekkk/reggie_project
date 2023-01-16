package com.maple.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maple.common.R;
import com.maple.pojo.Employee;
import com.maple.service.EmployeeService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;
import org.springframework.util.SerializationUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

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
        log.info("save内的线程id{}", Thread.currentThread().getId());

        employeeService.save(employee);

        return R.success("添加成功");
    }

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        Page<Employee> pageInfo = new Page(page, pageSize);
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null && name != "", Employee::getName, name);
        queryWrapper.orderByDesc(Employee::getUpdateTime);
        employeeService.page(pageInfo, queryWrapper);
        // 若当前的页数大于总页数, 就按照最大的页数再去查一次
        if (page > pageInfo.getPages())
        {
            pageInfo.setCurrent(pageInfo.getPages());
            employeeService.page(pageInfo, queryWrapper);
        }
        return R.success(pageInfo);
    }
    @PutMapping
    public R<String> update(HttpServletRequest request, @RequestBody Employee employee){
        log.info("线程id为:{}", Thread.currentThread().getId());
        log.info(employee.toString());
        Long id = (Long) request.getSession().getAttribute("employee");
        //employee.setUpdateTime(LocalDateTime.now());
        //employee.setUpdateUser(id);
        boolean b = employeeService.updateById(employee);
        return b ? R.success("ok") : R.error("GG");
    }


    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id){
        Employee employee = employeeService.getById(id);
        log.info(employee.toString());
        return R.success(employee);
    }
}
