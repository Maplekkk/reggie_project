package com.maple.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maple.pojo.Employee;
import com.maple.service.EmployeeService;
import com.maple.mapper.EmployeeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author maple
* @description 针对表【employee(员工信息)】的数据库操作Service实现
* @createDate 2023-01-11 16:34:22
*/
@Service
@Slf4j
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee>
    implements EmployeeService{

}




