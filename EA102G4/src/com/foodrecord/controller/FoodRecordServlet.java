package com.foodrecord.controller;

import java.io.*;
import java.util.*;
import java.sql.Date;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.foodrecord.model.*;
import com.foods.model.*;
@MultipartConfig
public class FoodRecordServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
//		if ("All_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
//				String mem_id = req.getParameter("mem_id");
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-end/foodrecord/select_page.jsp");
//					failureView.forward(req, res);
//					return;//�{�����_
//				}
//				
//				String work_id = null;
//				try {
//					work_id = new String(str);
//				} catch (Exception e) {
//					errorMsgs.add("���u�s���榡�����T");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-end/foodrecord/select_page.jsp");
//					failureView.forward(req, res);
//					return;//�{�����_
//				}
//				
//				/***************************2.�}�l�d�߸��*****************************************/
//				FoodRecordService foodrecorsSvc = new FoodRecordService();
//				List<FoodRecordVO> list = foodrecorsSvc.getAll();
//				if (list.isEmpty()) {
//					errorMsgs.add("�d�L���");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-end/foodrecord/select_page.jsp");
//					failureView.forward(req, res);
//					return;//�{�����_
//				}
//				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
//				req.setAttribute("list", list); // ��Ʈw���X��empVO����,�s�Jreq
//				req.setAttribute("mem_id", mem_id); //���ե�
//				String url = "/front-end/foodrecord/listAllFoodRecord.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
//				successView.forward(req, res);
//
//				/***************************��L�i�઺���~�B�z*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("�L�k���o���:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/foodrecord/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String mem_id = req.getParameter("mem_id");
				
				/***************************2.�}�l�d�߸��*****************************************/
				FoodRecordService foodrecorsSvc = new FoodRecordService();
				List<FoodRecordVO> list = foodrecorsSvc.getAllByMem(mem_id);
				if (list.isEmpty()) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/foodrecord/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
//				req.setAttribute("list", list); // ��Ʈw���X��empVO����,�s�Jreq
//				req.setAttribute("mem_id", mem_id); //���ե�
				String url = "/front-end/foodrecord/listAllFoodRecordByMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/foodrecord/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("Date_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String mem_id = req.getParameter("mem_id");			
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/foodrecord/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
//				FoodRecordService foodrecorsSvc = new FoodRecordService();
//				List<FoodRecordVO> list = foodrecorsSvc.getAllByMem(mem_id);
//				TreeSet<Date> set = new TreeSet<Date>();
//
//				if (list.isEmpty()) {
//					errorMsgs.add("�d�L���");
//				} else {
//					for(FoodRecordVO i : list ) {
//						set.add(i.getFoodr_date());
//					}
//				}
//				System.out.println(set);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/foodrecord/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				String url = "/front-end/foodrecord/listAllFoodRecordByDate2.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/foodrecord/select_page.jsp");
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
				String foodr_no = new String(req.getParameter("foodr_no"));
//				String mem_id = new String(req.getParameter("mem_id"));
				
				/***************************2.�}�l�d�߸��****************************************/
				FoodRecordService foodrecordSvc = new FoodRecordService();
				FoodRecordVO foodrecordVO = foodrecordSvc.getOneFoodRecord(foodr_no);
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("foodrecordVO", foodrecordVO);// ��Ʈw���X��empVO����,�s�Jreq
				
				String url = "/front-end/foodrecord/updateFoodRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/foodrecord/foodRecord.jsp");
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
				String foodr_no = new String(req.getParameter("foodr_no"));
				String mem_id = new String(req.getParameter("mem_id"));
				
				StringBuffer sb = new StringBuffer();
				String food_name = req.getParameter("food_name");
				if (food_name == null || food_name.trim().length() == 0) {
					errorMsgs.add("�����ФŪť�");
				} else {
				FoodService foodSvc = new FoodService(); 
					try {
						FoodVO foodVO = foodSvc.getOneByName(food_name);
						if(foodVO.getFood_no() != null) {
						sb.append(foodVO.getFood_no());
						}
					} catch (NullPointerException e) {
						errorMsgs.add("�L���������");
					}
				}
				String food_no = sb.toString();
							
				String foodr_time = new String(req.getParameter("foodr_time"));
				
				java.sql.Date foodr_date = null;
				try {
					foodr_date = java.sql.Date.valueOf(req.getParameter("foodr_date").trim());
				} catch (IllegalArgumentException e) {
					foodr_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				
				Integer foodr_weight = null;
				try {
					foodr_weight = new Integer(req.getParameter("foodr_weight"));
					if (foodr_weight < 0 || foodr_weight >= 999) {
						errorMsgs.add("�ж�T��ƥH���B���i�p��0");
					}
				} catch (NumberFormatException e) {
					foodr_weight = 1;
					errorMsgs.add("�ж�Ʀr.");
				}
				
				FoodRecordVO foodrecordVO = new FoodRecordVO();
				foodrecordVO.setFoodr_no(foodr_no);
				foodrecordVO.setMem_id(mem_id);
				foodrecordVO.setFood_no(food_no);
				foodrecordVO.setFoodr_time(foodr_time);
				foodrecordVO.setFoodr_date(foodr_date);
				foodrecordVO.setFoodr_weight(foodr_weight);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("foodrecordVO", foodrecordVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/foodrecord/updateFoodRecord.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				FoodRecordService foodrecordSvc = new FoodRecordService();
				foodrecordVO = foodrecordSvc.updateFoodRecord(mem_id, food_no, foodr_time, foodr_date, foodr_weight, foodr_no);			
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("foodrecordVO", foodrecordVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				
				String url = "/front-end/foodrecord/foodRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/foodrecord/updateFoodRecord.jsp");
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
				String mem_id = req.getParameter("mem_id");
				
				StringBuffer sb = new StringBuffer();
				String food_name = req.getParameter("food_name");
				if (food_name == null || food_name.trim().length() == 0) {
					errorMsgs.add("�����ФŪť�");
				}
				FoodService foodSvc = new FoodService(); 
				try {
					FoodVO foodVO = foodSvc.getOneByName(food_name);
					
					if(foodVO != null) {
						sb.append(foodVO.getFood_no());
					} else {
						errorMsgs.add("�L���������");
					}				
				} catch (NullPointerException e) {
					errorMsgs.add("�L���������");
				}
				String food_no = sb.toString();
				
				String foodr_time = req.getParameter("foodr_time");
				
				java.sql.Date foodr_date = null;
				try {
					foodr_date = java.sql.Date.valueOf(req.getParameter("foodr_date").trim());
				} catch (IllegalArgumentException e) {
					foodr_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				
				Integer foodr_weight = null;
				try {
					foodr_weight = new Integer(req.getParameter("foodr_weight"));
					if (foodr_weight < 0 || foodr_weight >= 999) {
						errorMsgs.add("�ж�T��ƥH���B���i�p��0");
					}
				} catch (NumberFormatException e) {
					foodr_weight = 1;
					errorMsgs.add("�ж�Ʀr.");
				}
				
				FoodRecordVO foodrecordVO = new FoodRecordVO();
				foodrecordVO.setMem_id(mem_id);
				foodrecordVO.setFood_no(food_no);
				foodrecordVO.setFoodr_time(foodr_time);
				foodrecordVO.setFoodr_date(foodr_date);
				foodrecordVO.setFoodr_weight(foodr_weight);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("foodrecordVO", foodrecordVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/foodrecord/addFoodRecord.jsp");
					failureView.forward(req, res);
					return;
				}
			
				/***************************2.�}�l�s�W���***************************************/
				FoodRecordService foodrecordSvc = new FoodRecordService();
				foodrecordVO = foodrecordSvc.addFoodRecord(mem_id, food_no, foodr_time, foodr_date, foodr_weight);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/front-end/foodrecord/foodRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/foodrecord/addFoodRecord.jsp");
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
				String foodr_no = new String(req.getParameter("foodr_no"));

				/***************************2.�}�l�R�����***************************************/
				FoodRecordService foodrecordSvc = new FoodRecordService();
				foodrecordSvc.deleteFoodRecord(foodr_no);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/front-end/foodrecord/listAllFoodRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/foodrecord/listAllFoodRecord.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
	
}
