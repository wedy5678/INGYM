package com.dayoff.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.json.JSONArray;
import org.json.JSONObject;

import com.calendar.controller.CalendarVO;
import com.dayoff.model.*;

public class dayOffServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				String ctime_no = req.getParameter("ctime_no");

				/*************************** 2.�}�l�d�߸�� ****************************************/
				DayoffService dayoffSvc = new DayoffService();
				DayoffVO dayoffVO = dayoffSvc.getOneDayoff(ctime_no);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("dayoffVO", dayoffVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "updateDayoff.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("listAllDayoff.jsp");
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
				String ctime_no = req.getParameter("ctime_no");
				if (ctime_no == null || ctime_no.trim().length() == 0) {
					errorMsgs.add("�ɶ��y���s��: �ФŪť�");
				}

				String pro_ID = req.getParameter("pro_ID").trim();
				if (pro_ID == null || pro_ID.trim().length() == 0) {
					errorMsgs.add("PRO_ID�ФŪť�");
				}

				String mem_ID = req.getParameter("mem_ID").trim();
				if (mem_ID == null || mem_ID.trim().length() == 0) {
					errorMsgs.add("MEM_ID�ФŪť�");
				}
				System.out.println(mem_ID);

				Date close_date = null;
				try {
					close_date = Date.valueOf(req.getParameter("close_date").trim());
				} catch (IllegalArgumentException e) {
					close_date = new Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}

				String hr = req.getParameter("hr").trim();
				if (mem_ID == null || mem_ID.trim().length() == 0) {
					errorMsgs.add("hr�ФŪť�");
				}

				DayoffVO dayoffVO = new DayoffVO();
				dayoffVO.setCtime_no(ctime_no);
				dayoffVO.setPro_ID(pro_ID);
				dayoffVO.setMem_ID(mem_ID);
				dayoffVO.setClose_date(close_date);
				dayoffVO.setHr(hr);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("dayoffVO", dayoffVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("updateDayoff.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				DayoffService dayoffSvc = new DayoffService();
				dayoffVO = dayoffSvc.updateDayoff(ctime_no, pro_ID, mem_ID, close_date, hr);

				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				req.setAttribute("dayoffVO", dayoffVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "listProDayoff.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("updateDayoff.jsp");
				failureView.forward(req, res);
			}
		}

		/*********************** Insert new Dayoff *************************/

		if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
				String pro_ID = req.getParameter("pro_ID");
				if (pro_ID == null || pro_ID.trim().length() == 0) {
					errorMsgs.add("PRO_ID: �ФŪť�");
				}

				String mem_ID = req.getParameter("mem_ID").trim();
				if (mem_ID == null || mem_ID.trim().length() == 0) {
					errorMsgs.add("MEM_ID�ФŪť�");
				}

				java.sql.Date close_date = null;
				try {
					close_date = java.sql.Date.valueOf(req.getParameter("close_date").trim());
				} catch (IllegalArgumentException e) {
					close_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}

				String hr = null;
				try {
					hr = req.getParameter("hr").trim();
				} catch (NumberFormatException e) {
					errorMsgs.add("�ж�Ʀr.");
				}

				DayoffVO dayoffVO = new DayoffVO();
				dayoffVO.setPro_ID(pro_ID);
				dayoffVO.setMem_ID(mem_ID);
				dayoffVO.setClose_date(close_date);
				dayoffVO.setHr(hr);

				/***************************
				 * 2.���ҬO�_�����Ʈɶ�
				 ***************************************/
				DayoffService dayoffSvc = new DayoffService();
				DayoffVO dayoffCheck = dayoffSvc.getDateByProAndDate(pro_ID, close_date);

				if (dayoffCheck != null) {
					if (dayoffVO.equals(dayoffCheck)) {
						errorMsgs.add("�o�ӱƥ��w�s�b");
					}
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("dayoffVO", dayoffVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("addDayoff.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 3.�}�l�s�W��� ***************************************/
				dayoffVO = dayoffSvc.addDayoff(pro_ID, mem_ID, close_date, hr);
				/*************************** 4.�s�W����,�ǳ����(Send the Success view) ***********/
				String url = "listAllDayoff.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("addDayoff.jsp");
				failureView.forward(req, res);
			}
		}

		/*************************** Delete Dayoff **********************/

		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				String ctime_no = req.getParameter("ctime_no");

				/*************************** 2.�}�l�R����� ***************************************/
				DayoffService dayoffSvc = new DayoffService();
				dayoffSvc.deleteDayoff(ctime_no);

				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
				String url = "/front-end/ProAndClass/listAllDayoff.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/ProAndClass/listAllDayoff.jsp");
				failureView.forward(req, res);
			}
		}

//		/***************************Get Dayoff by PRO_ID�@return list**********************/

		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("pro_ID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�M�~�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				String pro_ID = null;
				try {
					pro_ID = str;
				} catch (Exception e) {
					errorMsgs.add("�M�~�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("selectDayoff.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				DayoffService dayoffSvc = new DayoffService();
				List<DayoffVO> dayoffVO = dayoffSvc.getOneProDayoff(pro_ID);
				List<CalendarVO> list = new ArrayList<CalendarVO>();

				if (dayoffVO == null) {
					errorMsgs.add("�d�L���");
				}

				for (DayoffVO dayTrans : dayoffVO) {
					CalendarVO fCal = new CalendarVO();
					fCal.setClass_no(dayTrans.getMem_ID());
					
					fCal.setClass_no(dayTrans.getCtime_no());
					fCal.setrDate(dayTrans.getClose_date());
					fCal.setHr(dayTrans.getHr());
					list.add(fCal);

				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("selectDayoff.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				JSONObject json = new JSONObject();
				JSONArray jsonarray = new JSONArray(list);
				String events = jsonarray.toString();
				PrintWriter out = res.getWriter();
				out.print(events);
				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("selectDayoff.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
