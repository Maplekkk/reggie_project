package com.maple.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maple.pojo.User;
import com.maple.service.UserService;
import com.maple.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author maple
* @description 针对表【user(用户信息)】的数据库操作Service实现
* @createDate 2023-01-23 10:55:39
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




