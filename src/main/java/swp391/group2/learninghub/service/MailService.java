package swp391.group2.learninghub.service;

import jakarta.mail.MessagingException;
import swp391.group2.learninghub.model.DataMailDTO;

public interface MailService {
    void sendHtmlMail(DataMailDTO dataMail, String templateName) throws MessagingException;
}
