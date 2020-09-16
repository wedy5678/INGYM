package com.classType.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.classType.model.*;

public class ClassTypeServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		/***************************************
		 * DISPLAY ClassType
		 ***************************************/

		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("c_type_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�ҵ{���O�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("selectClassType.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				String c_type_no = null;
				try {
					c_type_no = str.toUpperCase();
				} catch (Exception e) {
					errorMsgs.add("�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("selectClassType.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				ClassTypeService classTypeSvc = new ClassTypeService();
				ClassTypeVO classTypeVO = classTypeSvc.getOneClassType(c_type_no);
				if (classTypeVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("selectClassType.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("classTypeVO", classTypeVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "listOneClassType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("selectClassType.jsp");
				failureView.forward(req, res);
			}
		}

		/***************************************
		 * UPDATE ClassType
		 ***************************************/

		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				String c_type_no = req.getParameter("c_type_no");
				/*************************** 2.�}�l�d�߸�� ****************************************/
				ClassTypeService classTypeSvc = new ClassTypeService();
				ClassTypeVO classTypeVO = classTypeSvc.getOneClassType(c_type_no);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("classTypeVO", classTypeVO); // ��Ʈw���X��empVO����,�s�Jreq
				boolean openModal=true;
				req.setAttribute("openModal",openModal );
				String url = "listAllClassType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("listAllClassType.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String c_type_no = req.getParameter("c_type_no").trim();

				String t_name = req.getParameter("t_name");
				String t_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (t_name == null || t_name.trim().length() == 0) {
					errorMsgs.add("�ҵ{�W��: �ФŪť�");
				} else if (!t_name.trim().matches(t_nameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�ҵ{�W��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
				}
				
				ClassTypeVO classTypeVO = new ClassTypeVO();
				classTypeVO.setC_type_no(c_type_no);
				classTypeVO.setT_name(t_name);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("classTypeVO", classTypeVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/emp/update_emp_input.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				ClassTypeService classTypeSvc = new ClassTypeService();
				classTypeVO = classTypeSvc.updateClassType(c_type_no, t_name);

				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				req.setAttribute("classTypeVO", classTypeVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "listAllClassType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/emp/updateClassType.jsp");
				failureView.forward(req, res);
			}
		}

		/***************************************
		 * INSERT ClassType
		 ***************************************/
		if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
				String t_name = req.getParameter("t_name");
				String t_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (t_name == null || t_name.trim().length() == 0) {
					errorMsgs.add("���u�m�W: �ФŪť�");
				} else if (!t_name.trim().matches(t_nameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("���u�m�W: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
				}

				ClassTypeVO classTypeVO = new ClassTypeVO();
				classTypeVO.setT_name(t_name);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("classTypeVO", classTypeVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("addClassType.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				ClassTypeService classTypeSvc = new ClassTypeService();
				classTypeVO = classTypeSvc.addClassType(t_name);

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				String url = "listAllClassType.jsp";
				req.setAttribute("classTypeVO", classTypeVO);
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("addClassType.jsp");
				failureView.forward(req, res);
			}
		}

		/***************************************
		 * DELETE ClassType
		 ***************************************/

		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				String c_type_no = req.getParameter("c_type_no");

				/*************************** 2.�}�l�R����� ***************************************/
				ClassTypeService ClassTypeSvc = new ClassTypeService();
				ClassTypeSvc.deleteClassType(c_type_no);

				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
				String url = "/listAllClassType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("listAllClassType.jsp");
				failureView.forward(req, res);
			}

		}
	}
}
