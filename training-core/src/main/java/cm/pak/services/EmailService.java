package cm.pak.services;

import javax.mail.MessagingException;

public interface EmailService {
    void send(final String to, final String subject, final String text, final String ... attachments) throws MessagingException;
}
