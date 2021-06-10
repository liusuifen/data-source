package com.example.mysqloracle.controller;

import com.example.mysqloracle.common.CommonResult;
import com.example.mysqloracle.common.ContextConst;
import com.example.mysqloracle.datasource.DataSourceContextHolder;
import com.example.mysqloracle.param.Param;
import com.example.mysqloracle.service.impl.news.ParmaryUserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mailSender")
@Slf4j
public class MailSenderController {

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/sender")
    public CommonResult sender(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("2660444092@qq.com");
        message.setTo("3568473515@qq.com");
        message.setSubject("主题：简单邮件");
        message.setText("测试邮件内容");
        mailSender.send(message);
        return new CommonResult("邮件发送成功");
    }

}
