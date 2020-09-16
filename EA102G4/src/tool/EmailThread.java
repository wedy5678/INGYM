package tool;

import tool.MailService;
import com.mem.model.MemVO;


public class EmailThread extends Thread {

	
	MemVO memVO;
	

	public EmailThread() {
	}

	public EmailThread( MemVO memVO) {
		this.memVO = memVO;
	}

	public void run() {
		MailService mailSvc = new MailService();

		
			String subject = "�K�X�q��";
			String messageText = "Hello! " + memVO.getMem_name() +",���԰O���K�X: "+ memVO.getMem_psw() +"\n(�b���w�ҥ�)";
			
			MailService mailservice = new MailService();
			mailservice.sendMail(memVO.getMem_email(), subject, messageText);
		
	}
}
