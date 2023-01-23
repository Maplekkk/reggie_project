package com.maple.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.maple.common.R;
import com.maple.pojo.User;
import com.maple.service.UserService;
import com.maple.utils.SMSUtils;
import com.maple.utils.ValidateCodeUtils;
import javafx.beans.binding.ObjectExpression;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * ClassName:UserController
 * Package: com.maple.controller
 * Description:
 * Author maple
 * Create 2023-01-23
 * Version: v1.0
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session){

        String phone = user.getPhone();
        if(StringUtils.isNotEmpty(phone)){
            // 生成4位的验证码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info("code={}", code);
            // 调用阿里云的提供的短信验证服务发生短信
            SMSUtils.sendMessage("阿里云短信测试", "SMS_154950909", phone, code);

            // 需要将生成的验证码保存到Session中
            session.setAttribute(phone, code);
            return R.success("手机验证码发送成功");
        }

        return R.error("验证码发送失败");
    }
    // 使用map来获取json数据
    @PostMapping("/login")
    public R<User> login(@RequestBody Map map, HttpSession session){

        // 获取手机号
        String phone = (String) map.get("phone");
        // 获取验证码
        String code = (String) map.get("code");
        // 从Session中获取保存的验证码
        String codeInSession = (String) session.getAttribute(phone);
        // 进行验证码的比对
        if(codeInSession != null && codeInSession.equals(code))
        {
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone, phone);
            User user = userService.getOne(queryWrapper);
            if(user == null)
            {
                user = new User();
                user.setPhone(phone);
                userService.save(user);
            }
            // 成功
            session.setAttribute("user", user.getId());
            return R.success(user);
        }
        return R.error("登陆失败");
    }
}
