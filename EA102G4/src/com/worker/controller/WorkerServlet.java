package com.worker.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.worker.model.*;
import com.workpower.model.*;
@MultipartConfig
public class WorkerServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("login".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				//�b��
				String w_acc = req.getParameter("w_acc");
				if (w_acc == null || (w_acc.trim()).length() == 0) {
					errorMsgs.add("�b��: �ФŪť�");
				}
				//�K�X
				String w_pw = req.getParameter("w_pw");
				if (w_pw == null || (w_pw.trim()).length() == 0) {
					errorMsgs.add("�K�X: �ФŪť�");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/worker/login.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}

				/***************************2.�}�l�d�߸��*****************************************/
				WorkerService workerSvc = new WorkerService();
				WorkerVO workerVO = workerSvc.getOneByAcc(w_acc);
				if (workerVO == null || !(workerVO.getW_pw().equals(w_pw))) {
					errorMsgs.add("�b���αK�X���~");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/worker/login.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				HttpSession session = req.getSession();
				session.setAttribute("workerVOLogin", workerVO);
				
				String url = "/back-end/index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/worker/login.jsp");
				failureView.forward(req, res);
			}
		}
		
		//�n�X
		if ("logout".equals(action)) { // �Ӧ�index.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
										
			HttpSession session = req.getSession();
			session.invalidate();
			String url = "/back-end/worker/login.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneMem.jsp
			successView.forward(req, res);
		}
		
		//�ק�K�X
		if ("update_pw".equals(action)) { // �Ӧ�index.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				//�|���s��
				String work_id = req.getParameter("work_id");
				//�±K�X
				String w_pw = req.getParameter("w_pw");
				//�s�K�X
				String w_npw = req.getParameter("w_npw");

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/index.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}

				/***************************2.�}�l�d�߸��*****************************************/
				WorkerService workerSvc = new WorkerService();
				workerSvc.updatePw(work_id, w_npw);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/worker/login.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				String url = "/back-end/index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/index.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("work_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J���u�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/worker/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				String work_id = null;
				try {
					work_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("���u�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/worker/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				WorkerService workerSvc = new WorkerService();
				WorkerVO workerVO = workerSvc.getOneEmp(work_id);
				if (workerVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/worker/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("workerVO", workerVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/back-end/worker/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/worker/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String work_id = new String(req.getParameter("work_id"));
				
				/***************************2.�}�l�d�߸��****************************************/
				WorkerService workerSvc = new WorkerService();
				WorkerVO workerVO = workerSvc.getOneEmp(work_id);
				
				//���X���u�v��,���ন�r��
				WorkPowerService workpowerSvc = new WorkPowerService();
				List<WorkPowerVO> wpVO = workpowerSvc.getByWorker(work_id);
				StringBuffer sb = new StringBuffer();
				for(WorkPowerVO i:wpVO) {
					sb.append(i.getPower_id());
				}
				String wp = sb.toString();
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("workerVO", workerVO);// ��Ʈw���X��empVO����,�s�Jreq
				req.setAttribute("wp",wp);// �N�o�쪺���u�v���r��,�s�Jreq
				
				String url = "/back-end/worker/update_emp_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/worker/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
									
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String work_id = new String(req.getParameter("work_id").trim());
				
				String w_name = req.getParameter("w_name");
				String w_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (w_name == null || w_name.trim().length() == 0) {
					errorMsgs.add("���u�m�W: �ФŪť�");
				} else if(!w_name.trim().matches(w_nameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("���u�m�W: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
	            }
				
				String w_acc = req.getParameter("w_acc").trim();
				if (w_acc == null || w_acc.trim().length() == 0) {
					errorMsgs.add("�b���ФŪť�");
				}
				
				String w_pw = req.getParameter("w_pw").trim();
				if (w_pw == null || w_pw.trim().length() == 0) {
					errorMsgs.add("�K�X�ФŪť�");
				}
				
				String email = req.getParameter("email").trim();
				if (email == null || email.trim().length() == 0) {
					errorMsgs.add("�K�X�ФŪť�");
				}	
				
				java.sql.Date reg_date = null;
				try {
					reg_date = java.sql.Date.valueOf(req.getParameter("reg_date").trim());
				} catch (IllegalArgumentException e) {
					reg_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}

				byte[] photo = null;				
				Part part = req.getPart("photo");
				InputStream in = part.getInputStream();
				if (in.available() != 0) {
				byte[] buf = new byte[in.available()];
				in.read(buf);
				photo = buf;				
				} else {
					photo = new WorkerService().getOneEmp(work_id).getPhoto();
				}
				
				WorkerVO workerVO = new WorkerVO();
				workerVO.setWork_id(work_id);
				workerVO.setW_name(w_name);
				workerVO.setW_acc(w_acc);
				workerVO.setW_pw(w_pw);
				workerVO.setEmail(email);
				workerVO.setReg_date(reg_date);
				workerVO.setPhoto(photo);
				
				String power[] = req.getParameterValues("POWER");
//				if (power == null) {
//					errorMsgs.add("�п���v��");
//				}
				
				StringBuffer sb = new StringBuffer();
				if (power == null) {
					sb.append("");
				} else {
					for(String i:power) {
						sb.append(i);
					}
				}
				String wp = sb.toString();

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("workerVO", workerVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					req.setAttribute("wp", wp);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/worker/update_emp_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				WorkerService workerSvc = new WorkerService();
				workerVO = workerSvc.updateEmp(work_id, w_name, w_acc, w_pw, email, reg_date, photo);
				WorkPowerService workpowerSvc = new WorkPowerService();

				if(power != null) {
					WorkPowerVO workPowerVO = new WorkPowerVO();
					workpowerSvc.delete(work_id);
					for(String i:power){
						workPowerVO = workpowerSvc.addWorkPower(work_id, i);
					}			
				}else {
					workpowerSvc.delete(work_id);
				}
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
//				req.setAttribute("workerVO", workerVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				
				String url = "/back-end/worker/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/worker/update_emp_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD  
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				String w_name = req.getParameter("w_name");
				String w_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (w_name == null || w_name.trim().length() == 0) {
					errorMsgs.add("���u�m�W�ФŪť�");
				} else if(!w_name.trim().matches(w_nameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("���u�m�W: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
	            }
				
				String w_acc = req.getParameter("w_acc").trim();
				if (w_acc == null || w_acc.trim().length() == 0) {
					errorMsgs.add("�b������ť�");
				}
				
//				String w_pw = req.getParameter("w_pw").trim();
//				if (w_pw == null || w_pw.trim().length() == 0) {
//					errorMsgs.add("�K�X����ť�");
//				}
			    String w_pw = getRandomPassword();
				
				String email = req.getParameter("email").trim();
				if (email == null || email.trim().length() == 0) {
					errorMsgs.add("�H�c����ť�");
				}
				
				java.sql.Date reg_date = null;
				try {
					reg_date = java.sql.Date.valueOf(req.getParameter("reg_date").trim());
				} catch (IllegalArgumentException e) {
					reg_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				
				byte[] photo = null;
				Part part = req.getPart("photo");
			
				InputStream in = part.getInputStream();
				if (in.available() == 0) {
					errorMsgs.add("�п�ܹϤ�");
				} else {
				byte[] buf = new byte[in.available()];
				in.read(buf);
				photo = buf;
				}

				WorkerVO workerVO = new WorkerVO();
				workerVO.setW_name(w_name);
				workerVO.setW_acc(w_acc);
				workerVO.setW_pw(w_pw);
				workerVO.setEmail(email);
				workerVO.setReg_date(reg_date);
				workerVO.setPhoto(photo);

				String power[] = req.getParameterValues("POWER");

				StringBuffer sb = new StringBuffer();
				if (power == null) {
					sb.append("");
				} else {
					for(String i:power) {
						sb.append(i);
					}
				}
				String wp = sb.toString();

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("workerVO", workerVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					req.setAttribute("wp", wp); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/worker/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}

//				
				/***************************2.�}�l�s�W���***************************************/
				WorkerService workerSvc = new WorkerService();
				workerVO = workerSvc.addEmp(w_name, w_acc, w_pw, email, reg_date, photo);
				workerVO = workerSvc.getOneByAcc(w_acc);
				
				if (power != null) {
					WorkPowerService workpowerSvc = new WorkPowerService();
					WorkPowerVO workPowerVO = new WorkPowerVO();
					for(String i:power){
						workPowerVO = workpowerSvc.addWorkPower(workerVO.getWork_id(), i);
					}
				}
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
			    //�H�eE-Mail
			    String to = email;			      
			    String subject = "�K�X�q��";			      
			    String ch_name = w_name;
			    String messageText = "Hello! " + ch_name + " �H�U�O�A���b�K" + "\n" + 
			    					 "�b��:" + w_acc+ "\n" +
			    		             "�K�X:" + w_pw + "\n" + 
			    					 " (�w�g�ҥ�)"; 
			       
			    MailService mailService = new MailService();
			    mailService.sendMail(to, subject, messageText);
			    //
				
				String url = "/back-end/worker/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/worker/addEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String work_id = new String(req.getParameter("work_id"));
				
				/***************************2.�}�l�R�����***************************************/
				WorkerService workerSvc = new WorkerService();
				workerSvc.deleteEmp(work_id);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/back-end/worker/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/worker/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
	private String getRandomPassword() {
	    int z;
	    StringBuilder sb = new StringBuilder();
	    int i;
	    for (i = 0; i < 5; i++) {
	      z = (int) ((Math.random() * 7) % 3);
	 
	      if (z == 1) { // ��Ʀr
	        sb.append((int) ((Math.random() * 10) + 48));
	      } else if (z == 2) { // ��j�g�^��
	        sb.append((char) (((Math.random() * 26) + 65)));
	      } else {// ��p�g�^��
	        sb.append(((char) ((Math.random() * 26) + 97)));
	      }
	    }
	    return sb.toString();
	  }
}
