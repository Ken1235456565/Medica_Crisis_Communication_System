package util;

import javax.mail.Transport;
import javax.mail.Authenticator;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.PasswordAuthentication;
import java.util.Properties;


public class EmailSender {
    
    private String smtpHost = "smtp.gmail.com";
    private String smtpPort = "587";
    private String senderEmail;
    private String senderPassword;
    
    public EmailSender() {
        // 可以从配置文件或环境变量读取邮件配置
        loadEmailConfiguration();
    }
    
    public EmailSender(String smtpHost, String smtpPort, String senderEmail, String senderPassword) {
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
        this.senderEmail = senderEmail;
        this.senderPassword = senderPassword;
    }
    
   
    private void loadEmailConfiguration() {
        // 从系统属性或配置文件加载
        this.senderEmail = System.getProperty("email.sender", "your-email@organization.com");
        this.senderPassword = System.getProperty("email.password", "your-app-password");
        this.smtpHost = System.getProperty("email.smtp.host", "smtp.gmail.com");
        this.smtpPort = System.getProperty("email.smtp.port", "587");
    }
    
  
    public void sendEmail(String recipientEmail, String subject, String body) throws Exception {
        // 验证输入参数
        if (recipientEmail == null || recipientEmail.trim().isEmpty()) {
            throw new IllegalArgumentException("Recipient email cannot be empty");
        }
        if (subject == null || subject.trim().isEmpty()) {
            throw new IllegalArgumentException("Email subject cannot be empty");
        }
        if (body == null || body.trim().isEmpty()) {
            throw new IllegalArgumentException("Email body cannot be empty");
        }
        
        // 配置邮件属性
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.ssl.trust", smtpHost);
        
        // 创建会话
        Session session = Session.getInstance(props, new Authenticator() {
            
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });
        
        try {
            // 创建邮件消息
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(body);
            
            // 发送邮件
            Transport.send(message);
            
            System.out.println("Email sent successfully to: " + recipientEmail);
            
        } catch (MessagingException e) {
            throw new Exception("Failed to send email: " + e.getMessage(), e);
        }
    }
    
    
    public void sendHtmlEmail(String recipientEmail, String subject, String htmlBody) throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.ssl.trust", smtpHost);
        
        Session session = Session.getInstance(props, new Authenticator() {
           
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });
        
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setContent(htmlBody, "text/html; charset=utf-8");
            
            Transport.send(message);
            
            System.out.println("HTML email sent successfully to: " + recipientEmail);
            
        } catch (MessagingException e) {
            throw new Exception("Failed to send HTML email: " + e.getMessage(), e);
        }
    }
    
   
    public boolean testConnection() {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", smtpHost);
            props.put("mail.smtp.port", smtpPort);
            props.put("mail.smtp.ssl.trust", smtpHost);
            
            Session session = Session.getInstance(props, new Authenticator() {
                
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderEmail, senderPassword);
                }
            });
            
            Transport transport = session.getTransport("smtp");
            transport.connect(smtpHost, senderEmail, senderPassword);
            transport.close();
            
            return true;
        } catch (Exception e) {
            System.err.println("Email connection test failed: " + e.getMessage());
            return false;
        }
    }
    
    
    public void setEmailConfiguration(String smtpHost, String smtpPort, String senderEmail, String senderPassword) {
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
        this.senderEmail = senderEmail;
        this.senderPassword = senderPassword;
    }
    
    // Getters
    public String getSmtpHost() { return smtpHost; }
    public String getSmtpPort() { return smtpPort; }
    public String getSenderEmail() { return senderEmail; }
}
