package cm.pak;

import cm.pak.models.core.SettingModel;
import cm.pak.services.SettingService;
import cm.pak.services.impl.DefaultSettingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;
import java.util.Properties;

import static cm.pak.PakTrainigCons.CM_PAK;

@Configuration
@Import({TrainingDataConfig.class})
@ComponentScan(basePackages = CM_PAK)
@EnableTransactionManagement
public class TrainingCoreConfig {

    @Autowired
    private SettingService settingService;

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
       final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
       final Optional<SettingModel> setting = settingService.getSetting();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("my.gmail@gmail.com");
        mailSender.setPassword("password");
        final Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        setting.ifPresent(s -> {
            Optional.ofNullable(s.getMailHost()).ifPresent( m -> mailSender.setHost(m));
            Optional.ofNullable(s.getMailPort()).ifPresent(p -> mailSender.setPort(p));
            Optional.ofNullable(s.getMailUsername()).ifPresent(name -> mailSender.setUsername(name));
            Optional.ofNullable(s.getMailPassword()).ifPresent(pwd -> mailSender.setPassword(pwd));
            Optional.ofNullable(s.getMailTransportProtocol()).ifPresent(tr -> props.put("mail.transport.protocol", tr));
            Optional.ofNullable(s.isMailSmtpAuth()).ifPresent(v -> props.put("mail.smtp.auth", new Boolean(v).toString()));
            Optional.ofNullable(s.isMailSmtpStartTtls()).ifPresent(v -> props.put("mail.smtp.starttls.enable", new Boolean(v).toString()));
            Optional.ofNullable(s.isMailDebug()).ifPresent(v -> props.put("mail.debug", new Boolean(v).toString()));
        });
       return mailSender;
    }
}
