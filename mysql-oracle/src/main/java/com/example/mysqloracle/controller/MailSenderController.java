package com.example.mysqloracle.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import com.example.mysqloracle.common.CommonResult;
import com.example.mysqloracle.common.ContextConst;
import com.example.mysqloracle.datasource.DataSourceContextHolder;
import com.example.mysqloracle.param.Param;
import com.example.mysqloracle.service.impl.news.ParmaryUserServiceImpl;
import com.example.mysqloracle.service.mail.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mailSender")
@Slf4j
public class MailSenderController {

    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;



    // 发件人要跟yml配置文件里填写的邮箱一致
    String mailFrom = "2660444092@qq.com";
    // 收件人
    String mailTo = "3568473515@qq.com,2660444092@qq.com";
    // 抄送
    String cc = "2260844229@qq.com,2660444092@qq.com";


    @PostMapping("/sender")
    public CommonResult sender(@RequestBody Param param){
        TimeInterval timer = DateUtil.timer();
        if(param.getChannelId()==1){
            mailService.sendSimpleMail(mailFrom, "blueSky", mailTo, cc, "TestMail", "普通邮件测试,Hello World !");
        }else if(param.getChannelId()==2){
            File taxFile = new File("C:\\Users\\bl007\\IdeaProjects\\data-source\\mysql-oracle\\src\\main\\resources\\static\\数据迁移流程.sql");
            List<File> fileList = new ArrayList<>();
            fileList.add(taxFile);
            mailService.sendMailWithAttachments(mailFrom, "blueSky", mailTo, cc, "TestMail", "文件附件邮件测试,Hello World !", fileList);
        }else if(param.getChannelId()==3){
            String image01Path = "C:\\Users\\bl007\\IdeaProjects\\data-source\\mysql-oracle\\src\\main\\resources\\static\\1.png";
            String image02Path = "C:\\Users\\bl007\\IdeaProjects\\data-source\\mysql-oracle\\src\\main\\resources\\static\\2.jpg";
            String[] imageArr = new String[]{image01Path, image02Path};
            String[] imageIdArr = new String[]{"image01", "image02"};
            String contentHtml = "这是图片1:<div><img src='cid:image01'/></div>" +
                    "这是图片2:<div><img src='cid:image02'/></div>";
            mailService.sendMailWithImage(mailFrom, "blueSky", mailTo, cc, "正文带图片测试 TestMail", contentHtml, imageArr, imageIdArr);
        }else if(param.getChannelId()==4){
            // 注意导入的包是org.thymeleaf.context
            Context context = new Context();
            context.setVariable("username", "比尔盖茨");
            context.setVariable("age", "18");
            String content = templateEngine.process("mailTemplate01.html", context);
            mailService.sendHtmlMailThymeLeaf(mailFrom, "blueSky", mailTo, cc, "ThymeLeaf测试 TestMail", content);
        }
        long restart = timer.intervalRestart();
        log.info("重置接口耗时：{}ms",restart);
        long interval = timer.interval();
        log.info("接口花费时间：{}ms",interval);
        return new CommonResult("邮件发送成功");
    }

}
