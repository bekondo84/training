package cm.pak.services.impl;

import cm.pak.models.core.SettingModel;
import cm.pak.services.EmailService;
import cm.pak.services.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Objects;
import java.util.Optional;

@Service
public class DefaultEmailService implements EmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private SettingService settingService;
    @Autowired
    private Environment env ;

    protected void send(String to, String subject, String text) {
        final SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom( env.getProperty("spring.mail.username"));
        settingService.getSetting().ifPresent(setting -> message.setFrom(setting.getMailUsername()));
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    @Override
    public void send(String to, String subject, String text, String... attachments) throws MessagingException {
         final MimeMessage message = mailSender.createMimeMessage();
         final MimeMessageHelper helper = new MimeMessageHelper(message, true);
         helper.setFrom( env.getProperty("spring.mail.username"));
         Optional<SettingModel> setting = settingService.getSetting();
         if (setting.isPresent()) {
             helper.setFrom(setting.get().getMailUsername());
         }
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);
        mailSender.send(message);
    }
}
