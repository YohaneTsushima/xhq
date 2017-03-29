package service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import model.Mail;

public class MailUtil {

	protected final Logger logger = Logger.getLogger(getClass());
	
	public boolean send(Properties prop, Mail mail) throws Exception{
		Session session = null;
		Message msg = null;
		try {
			/*// 这里是SMTP发送服务器的名字：163的如下："smtp.163.com"  
			email.setHostName(mail.getHost());
			// 字符集编码
			email.setCharset(Mail.ENCODEING);
			// 收件人
			email.addTo(mail.getReceiver());
			// 发件人
			email.setFrom(mail.getSender(), mail.getName());
			// 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码  
			email.setAuthentication(mail.getUsername(), mail.getPassword());
			// 要发送的邮件主题  
			email.setSubject(mail.getSubject());
			// 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签
			email.setMsg(mail.getMessage());
			// 发送
			email.send();*/
			session = Session.getInstance(prop);
			msg = new MimeMessage(session);
			 
			msg.setFrom(new InternetAddress("412137492@qq.com"));
			msg.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(mail.getReceiver()), /*new InternetAddress("386890746@qq.com")*/});
			msg.setSubject(mail.getSubject());
			msg.setText(mail.getMessage());
			Transport transport = session.getTransport();
			transport.connect("smtp.qq.com", "412137492@qq.com", "tjvwqkswyrzdcajg");
			transport.sendMessage(msg, msg.getAllRecipients());
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.info(msg.getFrom() + "发送邮件到" + msg.getAllRecipients() + "失败");
			logger.info(e.getMessage());
			return false;
		}
		
	}
}
