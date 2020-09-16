package com.mem.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.google.gson.Gson;
import com.mem.model.*;
import com.memphoto.model.*;
//import com.pro.model.*;
import com.pro.model.ProService;
import com.pro.model.ProVO;

import tool.EmailThread;
import tool.MailService;


public class MemServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		//��ѫǨ����Ҧ��|��
		if ("getAll".equals(action)) { // �Ӧ�index.jsp���ШD
			System.out.println("getAll");
			MemService memSvc = new MemService();
			List<MemVO> allMember = memSvc.getAll();
			
			String str = "{";
			for(int i = 0;i<allMember.size();i++) {
				str += (str == "{" ) ? "\""+allMember.get(i).getMem_id()+"\":"+"\""+allMember.get(i).getMem_name()+"\"" : "," + "\""+allMember.get(i).getMem_id()+"\":"+"\""+allMember.get(i).getMem_name()+"\"";
			}
			str += "}";
			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			out.write(str);
			out.flush();
			out.close();
			return;// �{�����_
			
		}
		
		//�n�X
		if ("logout".equals(action)) { // �Ӧ�index.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
						
			HttpSession session = req.getSession();
			session.invalidate();
			String url = "/front-end/index.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneMem.jsp
			successView.forward(req, res);
		}
		
		//�n�J
		if ("login".equals(action)) { // �Ӧ�login.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				//�b��
				String mem_email = req.getParameter("mem_email").trim();
				mem_email = mem_email.toLowerCase();
				if (mem_email == null || mem_email.length() == 0) {
					errorMsgs.add("�b��: �ФŪť�");
				}
				
				//�K�X
				String mypsw = req.getParameter("mem_psw").trim();
				String pswReg = "^[(a-zA-Z0-9)]{2,10}$";
				if (mypsw == null || mypsw.length() == 0) {
					errorMsgs.add("�K�X: �ФŪť�");
				} else if(!mypsw.matches(pswReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�K�X: �u��O�^��r���B�Ʀr , �B���ץ��ݦb2��10����");
	            }
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/mem/signin.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMemByMemAcc(mem_email);
				if (memVO == null || !(memVO.getMem_psw().equals(mypsw))) {
					errorMsgs.add("�b���αK�X���~");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/mem/signin.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				ProService proSvc = new ProService();
			    ProVO proVO = proSvc.getProByMem(memVO.getMem_id());
			    
			    /***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
			    HttpSession session = req.getSession();
			    session.setAttribute("memVOLogin", memVO);
			    if(proVO != null) {
			    	if(proVO.getPro_auth()==1) {
			    session.setAttribute("proVOLogin", proVO);
			    }
			    }
			    
			    String url = (String)session.getAttribute("location");
			    System.out.println( "location =" + url);
			    if(url == "" || url == null) {
			    	url = "/front-end/index.jsp";
			    }
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneMem.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/mem/signin.jsp");
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
				String mem_id = req.getParameter("mem_id");
				if (mem_id == null || (mem_id.trim()).length() == 0) {
					errorMsgs.add("�п�J�|���s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/mem/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/mem/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(mem_id);
				if (memVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/mem/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("memVO", memVO); // ��Ʈw���X��memVO����,�s�Jreq
				String url = "/front-end/mem/listOneMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneMem.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/mem/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllMem.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String mem_id = req.getParameter("mem_id");
				
				/***************************2.�}�l�d�߸��****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(mem_id);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("memVO", memVO);         // ��Ʈw���X��memVO����,�s�Jreq
				String url = "/front-end/mem/update_mem_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_mem_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/mem/listAllMem.jsp");
				failureView.forward(req, res);
			}
		}
		
		System.out.println(action);

		if ("update_auth".equals(action)) { // �Ӧ�update_mem_input.jsp���ШD
		System.out.println(1);
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				
				//�|���s��
				String mem_id = req.getParameter("mem_id");
				System.out.println(mem_id);
				
				//��a�v��
				String sel_auth = req.getParameter("sel_auth");
				
				//�o���v��
				String art_auth = req.getParameter("art_auth");
				
				//�d���v��
				String com_auth = req.getParameter("com_auth");
				

				MemVO memVO = new MemVO();
				memVO.setMem_id(mem_id);
				memVO.setSel_auth(sel_auth);
				memVO.setArt_auth(art_auth);
				memVO.setCom_auth(com_auth);
				
				
				/***************************2.�}�l�ק���*****************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.updateMemAuth(sel_auth, art_auth, com_auth, mem_id);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("mem_id", memVO.getMem_id()); // ��Ʈwupdate���\��,���T����memVO����,�s�Jreq
				String url = "/back-end/mem/listAllMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneMem.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/mem/listAllMem.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_mem_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				
				//�|���s��
				String mem_id = req.getParameter("mem_id");
				
				//�|���m�W
				String mem_name = req.getParameter("mem_name").trim();
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z_)]{2,10}$";
				if (mem_name == null || mem_name.length() == 0) {
					errorMsgs.add("�|���m�W: �ФŪť�");
				} else if(!mem_name.matches(enameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�|���m�W: �u��O���B�^��r���M_ , �B���ץ��ݦb2��10����");
	            }
				
				//�K�X
				String mem_psw = req.getParameter("mem_psw").trim();
				
				
				//�ͤ�
				java.sql.Date mem_bir = null;
				try {
					mem_bir = java.sql.Date.valueOf(req.getParameter("mem_bir").trim());
				} catch (IllegalArgumentException e) {
					mem_bir=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				
				//�ʧO
				String sex = req.getParameter("sex").trim();
				
				
				//�a�}
				String zipcode = req.getParameter("zipcode");
				String country = req.getParameter("country");
				String district = req.getParameter("district");
				String address = req.getParameter("address").trim();
				String addrReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,66}$";
				if (address == null || address.length() == 0 || country == null ||country.length() == 0 || district == null || district.length() == 0 || zipcode == null || zipcode.length() == 0) {
					errorMsgs.add("�a�}: �ФŪť� ");
				} else if(!address.matches(addrReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�a�}: �u��O����B�^��r���B�Ʀr ");
	            }
				String mem_addr = zipcode+country+district+address;
				
				
				//�q�l�H�c
				String mem_email = req.getParameter("mem_email").trim();
				
				
				
				//�q��
				String mem_phone = req.getParameter("mem_phone").trim();
				String phoneReg = "^(09)[(0-9)]{8}$";
				if(mem_phone == null || mem_phone.length() == 0) {
					errorMsgs.add("������X: �ФŪť�");
				}else if(!mem_phone.matches(phoneReg)) {
					errorMsgs.add("������X: �榡���~");
				}
				
				//�а�����
				Integer mem_absent = new Integer(req.getParameter("mem_absent"));
				
				//�I��
				Integer coin = new Integer(req.getParameter("coin"));
				
				//�ۧڤ���
				String mem_resume = req.getParameter("mem_resume");
				
				//���U�ɶ�
				java.sql.Timestamp m_reg_date = Timestamp.valueOf(req.getParameter("m_reg_date"));
				
				//��a�v��
				String sel_auth = req.getParameter("sel_auth");
				
				//�o���v��
				String art_auth = req.getParameter("art_auth");
				
				//�d���v��
				String com_auth = req.getParameter("com_auth");
				

				MemVO memVO = new MemVO();
				memVO.setMem_id(mem_id);
				memVO.setMem_name(mem_name);
				memVO.setMem_psw(mem_psw);
				memVO.setMem_bir(mem_bir);
				memVO.setSex(sex);
				memVO.setMem_addr(mem_addr);
				memVO.setMem_email(mem_email);
				memVO.setMem_phone(mem_phone);
				memVO.setMem_absent(mem_absent);
				memVO.setCoin(coin);
				memVO.setMem_resume(mem_resume);
				memVO.setM_reg_date(m_reg_date);
				memVO.setSel_auth(sel_auth);
				memVO.setArt_auth(art_auth);
				memVO.setCom_auth(com_auth);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // �t����J�榡���~��memVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/mem/updateMemDetail.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.updateMem(mem_name, mem_psw, mem_bir, sex, mem_addr, mem_email, mem_phone, mem_absent, coin, mem_resume, m_reg_date, sel_auth, art_auth, com_auth, mem_id);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("memVO", memVO); // ��Ʈwupdate���\��,���T����memVO����,�s�Jreq
				HttpSession session = req.getSession();
			    session.setAttribute("memVOLogin", memVO);
				String url = "/front-end/mem/memDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneMem.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/mem/updateMemDetail.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // �Ӧ�signup.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				
				//�|���m�W
				String mem_name = req.getParameter("mem_name").trim();
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z_)]{2,10}$";
				if (mem_name == null || mem_name.length() == 0) {
					errorMsgs.add("�|���m�W: �ФŪť�");
				} else if(!mem_name.matches(enameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�|���m�W: �u��O���B�^��r���M_ , �B���ץ��ݦb2��10����");
	            }
				//�K�X
				String mem_psw = "";
				int category;
				for (int i = 0; i < 8; i++) {
					category = (int) (Math.random() * 3 + 1);
					if (category == 1) {
						mem_psw += (char) ((int) (Math.random() * 10) + 48);
					} else if (category == 2) {
						mem_psw += (char) ((int) (Math.random() * 26) + 65);
					} else {
						mem_psw += (char) ((int) (Math.random() * 26) + 97);
					}
				}
				System.out.println(mem_psw);
				
				
				//�ͤ�
				java.sql.Date mem_bir = null;
				try {
					mem_bir = java.sql.Date.valueOf(req.getParameter("mem_bir").trim());
				} catch (IllegalArgumentException e) {
					mem_bir=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				
				//�ʧO
				String sex = req.getParameter("sex").trim();
				
				
				//�a�}
				String zipcode = req.getParameter("zipcode");
				String country = req.getParameter("country");
				String district = req.getParameter("district");
				String address = req.getParameter("address").trim();
				String addrReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,66}$";
				if (address == null || address.length() == 0 || country == null ||country.length() == 0 || district == null || district.length() == 0 || zipcode == null || zipcode.length() == 0) {
					errorMsgs.add("�a�}: �ФŪť� ");
				} else if(!address.matches(addrReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�a�}: �u��O����B�^��r���B�Ʀr ");
	            }
				String mem_addr = zipcode+country+district+address;
				
				
				//�q�l�H�c
				String mem_email = req.getParameter("mem_email").trim();
				
				String emailReg = "^([a-zA-Z0-9_]+@[a-zA-Z0-9.]+.[a-zA-Z]{2,4})*$";
				if (mem_email == null || mem_email.length() == 0) {
					errorMsgs.add("�q�l�H�c: �ФŪť�");
				} else if(!mem_email.matches(emailReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�q�l�H�c: �榡���~");
	            }
				mem_email = mem_email.toLowerCase();
				MemService memSvc = new MemService();
				List<String> list = memSvc.getAllEmail(); 
				if(list.contains(mem_email)){
					errorMsgs.add("�q�l�H�c: ���H�c�w�Q���U!");
				}
				
				
				//�q��
				String mem_phone = req.getParameter("mem_phone").trim();
				String phoneReg = "^(09)[(0-9)]{8}$";
				if(mem_phone == null || mem_phone.length() == 0) {
					errorMsgs.add("������X: �ФŪť�");
				}else if(!mem_phone.matches(phoneReg)) {
					errorMsgs.add("������X: �榡���~");
				}
				

				MemVO memVO = new MemVO();
				memVO.setMem_name(mem_name);
				memVO.setMem_psw(mem_psw);
				memVO.setMem_bir(mem_bir);
				memVO.setSex(sex);
				memVO.setMem_addr(mem_addr);
				memVO.setMem_email(mem_email);
				memVO.setMem_phone(mem_phone);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // �t����J�榡���~��memVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/signup.jsp");
					failureView.forward(req, res);
					return;
				}
				
				//�o�e�K�X���|��
			    EmailThread emailThread = new EmailThread(memVO);
			    emailThread.start();
				
				/***************************2.�}�l�s�W���***************************************/
				memVO = memSvc.addMem(mem_name, mem_psw, mem_bir,sex, mem_addr, mem_email, mem_phone);
				
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/front-end/mem/signin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllMem.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/mem/signup.jsp");
				failureView.forward(req, res);
			}
		}
        
if ("update_psw".equals(action)) { // �Ӧ�update_mem_input_psw.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				HttpSession session = req.getSession();
				MemVO memVOLogin = (MemVO)session.getAttribute("memVOLogin");
				MemService memSvc = new MemService();
				memVOLogin = memSvc.getOneMem(memVOLogin.getMem_id());
				
				//�K�X
				String mem_psw = memVOLogin.getMem_psw();
				String pswReg = "^[(a-zA-Z0-9)]{2,10}$";
				
				//��K�X�T�{
				String mem_psw_check = req.getParameter("mem_psw_check").trim();
				if (mem_psw_check == null || mem_psw_check.length() == 0) {
					errorMsgs.add("��K�X: �ФŪť�");
				} else if(!mem_psw.equals(mem_psw_check)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("��K�X :�P��K�X����");
	            }
				//�s�K�X
				String mem_psw_new = req.getParameter("mem_psw_new").trim();
				if (mem_psw_new == null || mem_psw_new.length() == 0) {
					errorMsgs.add("�s�K�X: �ФŪť�");
				} else if(!mem_psw_new.matches(pswReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�s�K�X: �u��O�^��r���B�Ʀr , �B���ץ��ݦb2��10����");
	            }
				if(mem_psw.equals(mem_psw_new)) {
					errorMsgs.add("�s�K�X: ���i�P��K�X����");
				}
				//�s�K�X�T�{
				String mem_psw_newcheck = req.getParameter("mem_psw_newcheck").trim();
				if (mem_psw_newcheck == null || mem_psw_newcheck.length() == 0) {
					errorMsgs.add("�T�{�K�X: �ФŪť�");
				} else if(!mem_psw_new.equals(mem_psw_newcheck)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�T�{�K�X :�P�s�K�X����");
	            }
				
				System.out.println(mem_psw+"    659");
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					 // �t����J�榡���~��memVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/mem/changeMyPassword.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				
				memVOLogin = memSvc.updateMem(memVOLogin.getMem_name(), mem_psw_new, memVOLogin.getMem_bir(), memVOLogin.getSex(), memVOLogin.getMem_addr(), memVOLogin.getMem_email(), memVOLogin.getMem_phone(), memVOLogin.getMem_absent(), memVOLogin.getCoin(), memVOLogin.getMem_resume(), memVOLogin.getM_reg_date(), memVOLogin.getSel_auth(), memVOLogin.getArt_auth(), memVOLogin.getCom_auth(),memVOLogin.getMem_id());
				
				System.out.println(memVOLogin.getMem_psw() +"  673   �ק令�\ ????");
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				session.setAttribute("memVOLogin", memVOLogin); // ��Ʈwupdate���\��,���T����memVO����,�s�Jreq
				String url = "/front-end/mem/memDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneMem.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/mem/changeMyPassword.jsp");
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
				String mem_id = req.getParameter("mem_id");
				
				/***************************2.�}�l�R�����***************************************/
				MemService memSvc = new MemService();
				memSvc.deleteMem(mem_id);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/front-end/mem/listAllMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/mem/listAllMem.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
