package cn.rollin.memory.service.impl;

import cn.rollin.memory.common.utils.EmailUtil;
import cn.rollin.memory.service.IMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * description
 *
 * @author rollin
 * @since 2024-12-28 07:32:19
 */
@Slf4j
@Component
public class MessageServiceImpl implements IMessageService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private EmailUtil emailUtil;


    @Override
    public void sendEmail(String to, String subject, String content) {
        emailUtil.sendSimpleMail(to, subject, content);
    }
}
