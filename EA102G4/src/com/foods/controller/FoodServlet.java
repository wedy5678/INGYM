package com.foods.controller;

import java.io.*;
import java.util.*;
import java.sql.Date;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.worker.model.*;
import com.workpower.model.*;
import com.foodrecord.model.*;
import com.foods.model.*;
@MultipartConfig
public class FoodServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String food_no = new String(req.getParameter("food_no"));
				
				/***************************2.�}�l�d�߸��****************************************/
				FoodService foodSvc = new FoodService();
				FoodVO foodVO = foodSvc.getOneFood(food_no);			
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("foodVO", foodVO);// ��Ʈw���X��empVO����,�s�Jreq
				
				String url = "/back-end/food/update_food_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/food/listAllFoodBackEnd.jsp");
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
				String food_no = req.getParameter("food_no");
				
				String food_name = req.getParameter("food_name");
				if (food_name == null || food_name.trim().length() == 0) {
					errorMsgs.add("�W�ٽФŪť�");
				}
				
				Double starch = null;
				try {
					starch = new Double(req.getParameter("starch"));
					if (starch < 0) {
						errorMsgs.add("���i�p��0");
					}
				} catch (NumberFormatException e) {
					starch = 0.0;
					errorMsgs.add("�ж�Ʀr.");
				}
				
				Double protein = null;
				try {
					protein = new Double(req.getParameter("protein"));
					if (protein < 0) {
						errorMsgs.add("���i�p��0");
					}
				} catch (NumberFormatException e) {
					protein = 0.0;
					errorMsgs.add("�ж�Ʀr.");
				}
				
				Double fat = null;
				try {
					fat = new Double(req.getParameter("fat"));
					if (fat < 0) {
						errorMsgs.add("���i�p��0");
					}
				} catch (NumberFormatException e) {
					fat = 0.0;
					errorMsgs.add("�ж�Ʀr.");
				}
				
				Double kcal = null;
				try {
					kcal = new Double(req.getParameter("kcal"));
					if (kcal < 0) {
						errorMsgs.add("���i�p��0");
					}
				} catch (NumberFormatException e) {
					kcal = 0.0;
					errorMsgs.add("�ж�Ʀr.");
				}
				
				FoodVO foodVO = new FoodVO();
				foodVO.setFood_no(food_no);
				foodVO.setFood_name(food_name);
				foodVO.setStarch(starch);
				foodVO.setProtein(protein);
				foodVO.setFat(fat);
				foodVO.setKcal(kcal);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("foodVO", foodVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/food/update_food_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				FoodService foodSvc = new FoodService();
				foodVO = foodSvc.update(food_no, food_name, starch, protein, fat, kcal);		
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("foodVO", foodVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				
				String url = "/back-end/food/listAllFoodBackEnd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/food/update_food_input.jsp");
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
				String food_name = req.getParameter("food_name");
				if (food_name == null || food_name.trim().length() == 0) {
					errorMsgs.add("�W�ٽФŪť�");
				}
				
				Double starch = null;
				try {
					starch = new Double(req.getParameter("starch"));
					if (starch < 0) {
						errorMsgs.add("���i�p��0");
					}
				} catch (NumberFormatException e) {
					starch = 0.0;
					errorMsgs.add("�ж�Ʀr.");
				}
				
				Double protein = null;
				try {
					protein = new Double(req.getParameter("protein"));
					if (protein < 0) {
						errorMsgs.add("���i�p��0");
					}
				} catch (NumberFormatException e) {
					protein = 0.0;
					errorMsgs.add("�ж�Ʀr.");
				}
				
				Double fat = null;
				try {
					fat = new Double(req.getParameter("fat"));
					if (fat < 0) {
						errorMsgs.add("���i�p��0");
					}
				} catch (NumberFormatException e) {
					fat = 0.0;
					errorMsgs.add("�ж�Ʀr.");
				}
				
				Double kcal = null;
				try {
					kcal = new Double(req.getParameter("kcal"));
					if (kcal < 0) {
						errorMsgs.add("���i�p��0");
					}
				} catch (NumberFormatException e) {
					kcal = 0.0;
					errorMsgs.add("�ж�Ʀr.");
				}
				
				FoodVO foodVO = new FoodVO();
				foodVO.setFood_name(food_name);
				foodVO.setStarch(starch);
				foodVO.setProtein(protein);
				foodVO.setFat(fat);
				foodVO.setKcal(kcal);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("foodVO", foodVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/food/addFood.jsp");
					failureView.forward(req, res);
					return;
				}
			
				/***************************2.�}�l�s�W���***************************************/
				FoodService foodSvc = new FoodService();
				foodVO = foodSvc.addFood(food_name, starch, protein, fat, kcal);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/back-end/food/listAllFoodBackEnd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/food/addFood.jsp");
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
				String food_no = new String(req.getParameter("food_no"));
				
				/***************************2.�}�l�R�����***************************************/
				FoodService foodSvc = new FoodService();
				foodSvc.delete(food_no);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/back-end/food/listAllFoodBackEnd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/food/listAllFoodBackEnd.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
	
}
