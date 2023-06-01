package swp391.group2.learninghub.Service;

import jakarta.mail.MessagingException;
import swp391.group2.learninghub.Model.DataMailDTO;

public interface MailService {
    void sendHtmlMail(DataMailDTO dataMail, String templateName) throws MessagingException;
}
