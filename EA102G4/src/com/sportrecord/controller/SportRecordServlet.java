package com.sportrecord.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;


import com.mem.model.*;
import com.sportrecord.model.*;
import com.sports.model.*;



public class SportRecordServlet extends HttpServlet{
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
							.getRequestDispatcher("/front-end/sportrecord/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/sportrecord/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				SportRecordService sportRecordSvc = new SportRecordService();
				List<SportRecordVO> list = sportRecordSvc.getOneSportRecordByMemId(mem_id);
				if (list == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/sportrecord/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("list", list); // ��Ʈw���X��sportRecordVO����,�s�Jreq
				String url = "/front-end/sportrecord/listOneSportRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneSportRecord.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/sportrecord/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_From".equals(action)) {

			try {
				// Retrieve form parameters.
				String record_no = req.getParameter("record_no");
				

				SportRecordDAO sportRecorddao = new SportRecordDAO();
				SportRecordVO sportRecordVO = sportRecorddao.findByPrimaryKeyRecordNo(record_no);

				req.setAttribute("sportRecordVO", sportRecordVO); // ��Ʈw���X��sportRecordVO����,�s�Jreq
				
				//Bootstrap_modal
				String openModal="true";
				req.setAttribute("openModal",openModal );
				
				// ���X��empVO�e��listOneEmp.jsp
				RequestDispatcher successView = req
						.getRequestDispatcher("/front-end/sportrecord/sportRecord.jsp");
				successView.forward(req, res);
				return;

				// Handle any unusual exceptions
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listOneSportRecord.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String record_no = req.getParameter("record_no");
				
				/***************************2.�}�l�d�߸��****************************************/
				SportRecordService sportRecordSvc = new SportRecordService();
				SportRecordVO sportRecordVO = sportRecordSvc.getOneSportRecordByRecordNo(record_no);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("sportRecordVO", sportRecordVO);         // ��Ʈw���X��sportRecordVO����,�s�Jreq
				String url = "/front-end/sportrecord/update_sportRecord_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\���update_sportRecord_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/sportrecord/listOneSportRecord.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_sportRecord_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				
				//�B�ʬ����s��
				String record_no = req.getParameter("record_no");
				
				//�|���s��
				String mem_id = req.getParameter("mem_id");
				
				//�������
				java.sql.Date record_date = null;
				try {
					record_date = java.sql.Date.valueOf(req.getParameter("record_date").trim());
				} catch (IllegalArgumentException e) {
					record_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				
				//�B�ʶ���
				String sport_no = req.getParameter("sport_no");
				
				//�O���@
				float record1 = 0;
				String myrecord1 = req.getParameter("record1").trim();
				String record1Reg = "^[1-9]{1}[0-9]{0,3}+.{0,1}[0-9]{0,1}$";
				
				if(!myrecord1.matches(record1Reg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
						errorMsgs.add("����:�榡���~");
	            }else {
	            	record1 = new Float(myrecord1);
	            }
				
				
				//�O���G
				float record2 =0;
				String myrecord2 = req.getParameter("record2").trim();
				String record2Reg = "^[1-9]{0,1}[0-9]{0,3}+.{0,1}[0-9]{0,1}$";
				if(!myrecord1.matches(record2Reg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
						errorMsgs.add("����:�榡���~");
	            }else {
	            	record2 = new Float(myrecord2);
	            }
				
				

				SportRecordVO sportRecordVO = new SportRecordVO();
				sportRecordVO.setRecord_no(record_no);
				sportRecordVO.setMem_id(mem_id);
				sportRecordVO.setRecord_date(record_date);
				sportRecordVO.setSport_no(sport_no);
				sportRecordVO.setRecord1(record1);
				sportRecordVO.setRecord2(record2);
				
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("sportRecordVO", sportRecordVO); // �t����J�榡���~��sportRecordVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/sportrecord/updateSportRecord.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				
				SportRecordService sportRecordSvc = new SportRecordService();
				sportRecordVO = sportRecordSvc.updateSportRecord(sport_no, mem_id, record_date, record1, record2, record_no);
				
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("record_no", record_no);
//				req.setAttribute("sportRecordVO", sportRecordVO); // ��Ʈwupdate���\��,���T����sportRecordVO����,�s�Jreq
				String url = "/front-end/sportrecord/sportRecord.jsp?whichPage="+req.getParameter("whichPage");
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,��listOneBodyRecord.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/sportrecord/updateSportRecord.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // �Ӧ�addSportRecord.jsp���ШD        
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				
				
				//�|���s��
				String mem_id = req.getParameter("mem_id");
				
				//�������
				java.sql.Date record_date = null;
				try {
					record_date = java.sql.Date.valueOf(req.getParameter("record_date").trim());
				} catch (IllegalArgumentException e) {
					record_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				
				//�B�ʶ���
				String sport_no = req.getParameter("sport_no");
				
				//�O���@
				float record1 = 0;
				String myrecord1 = req.getParameter("record1").trim();
				String record1Reg = "^[1-9]{1}[0-9]{0,3}+.{0,1}[0-9]{0,1}$";
				if(myrecord1 == null) {
						errorMsgs.add("�������ର�ŭ�!!");
				}else if(!myrecord1.matches(record1Reg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
						errorMsgs.add("����:�榡���~");
	            }else {
	            	record1 = Float.parseFloat(myrecord1);
	            }
	            	
	            
				
				
				//�O���G
				float record2 = 0;
				String myrecord2 = req.getParameter("record2").trim();
				String record2Reg = "^[1-9]{0,1}[0-9]{0,3}+.{0,1}[0-9]{0,1}$";
				if(!myrecord2.matches(record2Reg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
						errorMsgs.add("����:�榡���~");
	            }else {
	            	record2 = Float.parseFloat(myrecord2);
	            }
	            	
	            
				

				SportRecordVO sportRecordVO = new SportRecordVO();
				sportRecordVO.setMem_id(mem_id);
				sportRecordVO.setRecord_date(record_date);
				sportRecordVO.setSport_no(sport_no);
				sportRecordVO.setRecord1(record1);
				sportRecordVO.setRecord2(record2);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("sportRecordVO", sportRecordVO); // �t����J�榡���~��sportRecordVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/sportrecord/sportRecord.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				SportRecordService sportRecordSvc = new SportRecordService();
				sportRecordVO = sportRecordSvc.addSportRecord(sport_no, mem_id, record_date, record1, record2);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/front-end/sportrecord/sportRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listOneSportRecord.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/sportrecord/sportRecord.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listOneSportRecord.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String record_no = req.getParameter("record_no");
				
				/***************************2.�}�l�R�����***************************************/
				SportRecordService sportRecordSvc = new SportRecordService();
				sportRecordSvc.deleteSportRecord(record_no);
				
				 
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/front-end/sportrecord/listOneSportRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/sportrecord/listOneSportRecord.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
