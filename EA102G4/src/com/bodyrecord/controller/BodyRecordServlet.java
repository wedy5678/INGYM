package com.bodyrecord.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;


import com.mem.model.*;
import com.bodyrecord.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class BodyRecordServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		
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
							.getRequestDispatcher("/front-end/bodyrecord/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/bodyrecord/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				BodyRecordService bodyRecordSvc = new BodyRecordService();
				List<BodyRecordVO> list = bodyRecordSvc.getOneBodyRecordByMemId(mem_id);
				if (list == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/bodyrecord/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("list", list); // ��Ʈw���X��bodyRecordVO����,�s�Jreq
				String url = "/front-end/bodyrecord/bodyRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneBodyRecord.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/bodyrecord/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listOneBodyRecord.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String body_no = req.getParameter("body_no");
				
				
				/***************************2.�}�l�d�߸��****************************************/
				BodyRecordService bodyRecordSvc = new BodyRecordService();
				BodyRecordVO bodyRecordVO = bodyRecordSvc.getOneBodyRecordByBodyNo(body_no);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("bodyRecordVO", bodyRecordVO);         // ��Ʈw���X��bodyRecordVO����,�s�Jreq
				String url = "/front-end/bodyrecord/update_bodyRecord_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\���update_bodyRecord_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/bodyrecord/bodyRecord.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_bodyRecord_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				
				//����ƾڬ����s��
				String body_no = req.getParameter("body_no");
				
				//�|���s��
				String mem_id = req.getParameter("mem_id");
				
				//�������
				java.sql.Date body_date = null;
				try {
					body_date = java.sql.Date.valueOf(req.getParameter("body_date").trim());
				} catch (IllegalArgumentException e) {
					body_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				
				
				//����
				float mem_height=0;
				String myheight = req.getParameter("mem_height").trim();
				String heightReg = "^[1-9]{1}[0-9]{1,2}+.{0,1}+[0-9]{0,1}$";
				if (myheight == null || myheight.length() == 0) {
					errorMsgs.add("����: �ФŪť�");
				} else if(!myheight.matches(heightReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
						errorMsgs.add("����: �п�J���T������");
	            }else {
	            	mem_height = new Float(myheight);
	            }
	            	
	            
				
				
				//�魫
				float mem_weight = 0;
				String myweight = req.getParameter("mem_weight").trim();
				String weightReg = "^[1-9]{1}[0-9]{1,2}+.{0,1}+[0-9]{0,1}$";
				if (myweight == null || myweight.length() == 0) {
					errorMsgs.add("�魫: �ФŪť�");
				} else if(!myweight.matches(weightReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�魫: �п�J���T���魫");
	            }else {
	            	mem_weight = new Float(myweight);
	            }
	            	
	            
				
				
				//BMI����
				float mem_bmi = 0;
				String mybmi = req.getParameter("mem_bmi").trim();
				String bmiReg = "^[1-9]{1}[0-9]{0,1}+.{0,1}+[0-9]{0,1}$";
				if (mybmi == null || mybmi.length() == 0) {
					errorMsgs.add("BMI����: �ФŪť�");
				} else if(!mybmi.matches(bmiReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("BMI����: �п�J���T��BMI����");
	            }else {
	            	mem_bmi = new Float(mybmi);
	            }
	            	
	            
				
				
				//�~��
				int mem_old = 0;
				String myold = req.getParameter("mem_old").trim();
				String oldReg = "^[1-9]{1}[0-9]{0,2}$";
				if (myold == null || myold.length() == 0) {
					errorMsgs.add("�~��: �ФŪť�");
				} else if(!myold.matches(oldReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�~��: �A�O���a!!");
	            }else {
	            	mem_old = new Integer(myold);
	            }
	            	
	            
				
				
				
				//BMR����
				float mem_bmr = 0;
				String mybmr = req.getParameter("mem_bmr").trim();
				String bmrReg = "^[1-9]{1}[0-9]{2,3}+.{0,1}+[0-9]{0,1}$";
				if (mybmr == null || mybmr.length() == 0) {
					errorMsgs.add("BMR����: �ФŪť� ");
				} else if(!mybmr.matches(bmrReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("BMR����: �п�J���T��BMR����");
	            }else {
	            	mem_bmr = new Float(mybmr);
	            }
	            	
	            
				
				
				//�B���W�v
				int frequency = new Integer(req.getParameter("frequency").trim());
				
				
				//TDEE����
				float mem_tdee = 0;
				String mytdee = req.getParameter("mem_tdee").trim();
				String tdeeReg = "^[1-9]{1}[0-9]{2,3}+.{0,1}+[0-9]{0,1}$";
				if (mytdee == null || mytdee.length() == 0) {
					errorMsgs.add("TDEE����: �ФŪť�");
				} else if(!mytdee.matches(tdeeReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("TDEE����: �п�J���T��TDEE����");
	            }else {
	            	mem_tdee = new Float(mytdee);
	            }
	            	
	            
				
				
				
				//�Ӥ�
				BodyRecordService bodyRecordSvc = new BodyRecordService();
				byte[] photo = null;
				Part part = req.getPart("photo");
				InputStream in = part.getInputStream();
				if(in.available() != 0) {
					byte[] buf = new byte[in.available()];
					in.read(buf);
					photo = buf;
					in.close();
				}else {
					photo = bodyRecordSvc.getOneBodyRecordByBodyNo(body_no).getPhoto();
				}
				

				BodyRecordVO bodyRecordVO = new BodyRecordVO();
				bodyRecordVO.setBody_no(body_no);
				bodyRecordVO.setMem_id(mem_id);
				bodyRecordVO.setBody_date(body_date);
				bodyRecordVO.setMem_height(mem_height);
				bodyRecordVO.setMem_weight(mem_weight);
				bodyRecordVO.setMem_bmi(mem_bmi);
				bodyRecordVO.setMem_old(mem_old);
				bodyRecordVO.setMem_bmr(mem_bmr);
				bodyRecordVO.setFrequency(frequency);
				bodyRecordVO.setMem_tdee(mem_tdee);
				bodyRecordVO.setPhoto(photo);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("bodyRecordVO", bodyRecordVO); // �t����J�榡���~��bodyRecordVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/bodyrecord/updateBodyRecord.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				
				bodyRecordVO = bodyRecordSvc.updateBodyRecord(mem_id, mem_height, mem_weight,mem_bmi, mem_old, mem_bmr, frequency, mem_tdee, body_date,photo, body_no);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				
				req.setAttribute("body_no", body_no);
				String url = "/front-end/bodyrecord/bodyRecord.jsp?whichPage="+req.getParameter("whichPage");
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,��listOneBodyRecord.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/bodyrecord/updateBodyRecord.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // �Ӧ�addBodyRecord.jsp���ШD        
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				
				//�|���s��
				String mem_id = req.getParameter("mem_id").trim();

				
				//�������
				java.sql.Date body_date = null;
				try {
					body_date = java.sql.Date.valueOf(req.getParameter("body_date").trim());
				} catch (IllegalArgumentException e) {
					body_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				
			
				//����
				float mem_height = 0;
				String myheight = req.getParameter("mem_height").trim();
				String heightReg = "^[1-9]{1}[0-9]{1,2}+.{0,1}+[0-9]{0,1}$";
				if (myheight == null || myheight.length() == 0) {
					errorMsgs.add("����: �ФŪť�");
				} else if(!myheight.matches(heightReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
						errorMsgs.add("����: �п�J���T������");
	            }else {
	            	mem_height = new Float(myheight);
	            }
	            	
	            
				
				
				//�魫
				float mem_weight = 0;
				String myweight = req.getParameter("mem_weight").trim();
				String weightReg = "^[1-9]{1}[0-9]{0,2}+.{0,1}+[0-9]{0,1}$";
				if (myweight == null || myweight.length() == 0) {
					errorMsgs.add("�魫: �ФŪť�");
				} else if(!myweight.matches(weightReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�魫: �п�J���T���魫");
	            }else {
	            	mem_weight = new Float(myweight);
	            }
	            	
	            
				
				//BMI����
				float mem_bmi = 0;
				String mybmi = req.getParameter("mem_bmi").trim();
				String bmiReg = "^[1-9]{1}[0-9]{0,1}+.{0,1}+[0-9]{0,1}$";
				if (mybmi == null || mybmi.length() == 0) {
					errorMsgs.add("BMI����: �ФŪť�");
				} else if(!mybmi.matches(bmiReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("BMI����: �п�J���T��BMI����");
	            }else {
	            	mem_bmi = new Float(mybmi);
	            }
	            	
	            
				
				
				//�~��
				int mem_old = 0;
				String myold = req.getParameter("mem_old").trim();
				String oldReg = "^[1-9]{1}[0-9]{0,2}$";
				if (myold == null || myold.length() == 0) {
					errorMsgs.add("�~��: �ФŪť�");
				} else if(!myold.matches(oldReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�~��: �A�O���a!!");
	            }else {
	            	mem_old = new Integer(myold);
	            }
	            	
	            
				
				//BMR����
				float mem_bmr = 0;
				String mybmr = req.getParameter("mem_bmr").trim();
				String bmrReg = "^[1-9]{1}[0-9]{2,3}+.{0,1}+[0-9]{0,1}$";
				if (mybmr == null || mybmr.length() == 0) {
					errorMsgs.add("BMR����: �ФŪť� ");
				} else if(!mybmr.matches(bmrReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("BMR����: �п�J���T��BMR����");
	            }else {
	            	mem_bmr = new Float(mybmr);
	            }
	            	
	            
				
				
				//�B���W�v
				int frequency = new Integer(req.getParameter("frequency").trim());
				
				
				//TDEE����
				float mem_tdee = 0;
				String mytdee = req.getParameter("mem_tdee").trim();
				String tdeeReg = "^[1-9]{1}[0-9]{2,3}+.{0,1}+[0-9]{0,1}$";
				if (mytdee == null || mytdee.length() == 0) {
					errorMsgs.add("TDEE����: �ФŪť�");
				} else if(!mytdee.matches(tdeeReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("TDEE����: �п�J���T��TDEE����");
	            }else {
	            	mem_tdee = new Float(mytdee);
	            }
	            	
	            
				
				
				
				//�Ӥ�
				Part part = req.getPart("photo");
				InputStream in = part.getInputStream();
				byte[] photo = new byte[in.available()];
				in.read(photo);
				in.close();
				
				

				BodyRecordVO bodyRecordVO = new BodyRecordVO();
				
				bodyRecordVO.setMem_id(mem_id);
				bodyRecordVO.setBody_date(body_date);
				bodyRecordVO.setMem_height(mem_height);
				bodyRecordVO.setMem_weight(mem_weight);
				bodyRecordVO.setMem_bmi(mem_bmi);
				bodyRecordVO.setMem_old(mem_old);
				bodyRecordVO.setMem_bmr(mem_bmr);
				bodyRecordVO.setFrequency(frequency);
				bodyRecordVO.setMem_tdee(mem_tdee);
				bodyRecordVO.setPhoto(photo);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("bodyRecordVO", bodyRecordVO); // �t����J�榡���~��bodyRecordVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/bodyrecord/insertBodyRecord.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				BodyRecordService bodyRecordSvc = new BodyRecordService();
				bodyRecordVO = bodyRecordSvc.addBodyRecord(mem_id, mem_height, mem_weight,mem_bmi, mem_old, mem_bmr, frequency, mem_tdee, body_date, photo);
				
				req.setAttribute("body_no", bodyRecordVO.getBody_no());
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/front-end/bodyrecord/bodyRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllMem.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/bodyrecord/insertBodyRecord.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listOneBodyRecord.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String body_no = req.getParameter("body_no");
				
				/***************************2.�}�l�R�����***************************************/
				BodyRecordService bodyRecordSvc = new BodyRecordService();
				bodyRecordSvc.deleteBodyRecord(body_no);
				
				 
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/front-end/bodyrecord/listOneBodyRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/bodyrecord/listOneBodyRecord.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
