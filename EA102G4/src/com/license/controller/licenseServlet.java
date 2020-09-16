package com.license.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


import com.license.model.LicenseService;
import com.license.model.LicenseVO;

@MultipartConfig()
public class licenseServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("pro_ID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�нm�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/ProAndClass/selectLicense.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				String pro_ID = null;
				try {
					pro_ID = str;
				} catch (Exception e) {
					errorMsgs.add("�нm�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/ProAndClass/selectLicense.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				LicenseService LicenseSvc = new LicenseService();
				List <LicenseVO> list = LicenseSvc.findByPro(pro_ID);
				if (list == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/ProAndClass/selectLicense.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("list", list); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front-end/ProAndClass/listLicense.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/ProAndClass/selectLicense.jsp");
				failureView.forward(req, res);
			}
		}
		
		/*********************** 
		 * insert
		 **************************/
		if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
				String pro_ID = req.getParameter("pro_ID");
				System.out.println(pro_ID);
				if (pro_ID == null || pro_ID.trim().length() == 0) {
					errorMsgs.add("PRO_ID: �ФŪť�");
				}
				
				String lic_name = req.getParameter("lic_name").trim();
				if (lic_name == null || lic_name.trim().length() == 0) {
					errorMsgs.add("lic_name�ФŪť�");
				}

				String no_reg = req.getParameter("no_reg").trim();
				if (no_reg == null || no_reg.trim().length() == 0) {
					errorMsgs.add("no_reg�ФŪť�");
				}
				

				byte[] l_pic = null;
				Part part = req.getPart("l_pic");
				InputStream in = part.getInputStream();
				byte[] buf = new byte[in.available()];
				in.read(buf);
				l_pic = buf;
				in.close();
				
				LicenseVO licenseVO = new LicenseVO();
				licenseVO.setPro_ID(pro_ID);
				licenseVO.setLic_name(lic_name);
				licenseVO.setNo_reg(no_reg);
				licenseVO.setL_pic(l_pic);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("licenseVO", licenseVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/ProAndClass/myClass.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				LicenseService licenseSvc = new LicenseService();
				licenseVO = licenseSvc.addLicense( pro_ID,  lic_name,  no_reg, l_pic);
				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				req.setAttribute("licenseVO",licenseVO);
				String url = "/front-end/ProAndClass/myClass.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);
				
				/*************************** ��L�i�઺���~�B�z **********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/ProAndClass/myClass.jsp");
//				failureView.forward(req, res);
//			}
		}
		
	}
}
