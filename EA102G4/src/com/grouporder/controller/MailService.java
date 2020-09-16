package com.grouporder.controller;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

public class MailService {
	
	// �]�w�ǰe�l��:�ܦ��H�H��Email�H�c,Email�D��,Email���e
	public static void main(String[]args) {
//		new MailService().sendMail("p124127@gmail.com","111","123","C:\\img\\20200908175503.jpg");
	}
	public void sendMail(String to,String mem_name,String classDetail ,byte[] pathFormQRcode) {
			
	   try {
		   // �]�w�ϥ�SSL�s�u�� Gmail smtp Server
		   Properties props = new Properties();
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.socketFactory.port", "465");
		   props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.port", "465");

       // ���]�w gmail ���b�� & �K�X (�N�ǥѧA��Gmail�ӶǰeEmail)
       // �����NmyGmail���i�w���ʸ��C�����ε{���s���v�j���}
	     final String myGmail = "p1241271@gmail.com";
	     final String myGmail_password = "ingym0917";
		   Session session = Session.getInstance(props, new Authenticator() {
			   protected PasswordAuthentication getPasswordAuthentication() {
				   return new PasswordAuthentication(myGmail, myGmail_password);
			   }
		   });
		   Message message = new MimeMessage(session);
		   MimeMultipart multipart = new MimeMultipart("related");
		   BodyPart messageBodyPart = new MimeBodyPart();
		   message.setFrom(new InternetAddress(myGmail));
		   message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
		   String htmlText ="<html>"
		     + " <body>"
		     + "  <p>"+classDetail+"</p>"
		     +"<p>�W�ҫe�@�p�ɴN�i�H���d</p><br>"
		     +"<h3 style='color=red'>����:�аȥ����нm���y�z��QRCODE!!�H�O�ұz���W���v�q</h3>"
		     + "  <img src=\"cid:attach\"/>"
		     + " </body>"
		     + "</html>"  ;
		   messageBodyPart.setContent(htmlText, "text/html;charset=utf-8;");
		   multipart.addBodyPart(messageBodyPart);
		   
		   MimeBodyPart imagePart = new MimeBodyPart();
		   imagePart.setDataHandler(new DataHandler(new ByteArrayDataSource(pathFormQRcode,"image/jpeg")));
		   
		//  �]�mID
		   imagePart.setHeader("Content-ID","<attach>");
		   imagePart.setHeader("Content-Type", "image/png");
		   imagePart.setDisposition(MimeBodyPart.INLINE);
		   imagePart.setFileName("attach.png");
		   //  ���e
		   multipart.addBodyPart(imagePart);
		   //�]�w�H�����D��  
//		   message.setSubject(subject);
		   message.setSubject("�z�bINGYM�ʶR�F�s���ν�");
		   //�]�w�H�������e 
//		   message.setText("�W�Үɶ�"+classDetail+"<br>");
//		   message.setText(pathFormQRcode)
		   message.setContent(multipart);
		   Transport.send(message);
		   System.out.println("�ǰe���\!");
     }catch (MessagingException e){
	     System.out.println("�ǰe����!");
	     e.printStackTrace();
     }
   }
}
