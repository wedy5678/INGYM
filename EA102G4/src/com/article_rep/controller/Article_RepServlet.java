package com.article_rep.controller;

import java.io.*;
import java.util.*;


import javax.servlet.*;

import javax.servlet.annotation.*;
import javax.servlet.http.*;
import com.article_rep.model.*;



@MultipartConfig()
public class Article_RepServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");// �|��
	

		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD
			System.out.println("getOne_For_Display test");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("art_rep_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J���|�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article_rep/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				String art_rep_no = null;
				try {
					art_rep_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("���|�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article_rep/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				Article_RepService article_repSvc = new Article_RepService();
				Article_RepVO article_repVO = article_repSvc.getOneArticle_Rep(art_rep_no);
				if (article_repVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article_rep/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("article_repVO", article_repVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front-end/article_rep/listOneArticle_Rep.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article_rep/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD
			System.out.println("getOne_For_Update");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				String art_rep_no = new String(req.getParameter("art_rep_no"));
				System.out.println("01getOne_For_Update");
//				System.out.println("+++"+article_no+"+++");

				/*************************** 2.�}�l�d�߸�� ****************************************/
				Article_RepService article_repService = new Article_RepService();
				Article_RepVO article_repVO = article_repService.getOneArticle_Rep(art_rep_no);
				System.out.println("222+++"+art_rep_no+"+++");

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("article_repVO", article_repVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front-end/article_rep/update_Article_Rep.jsp";
				System.out.println("099getOne_For_Update");
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
//				System.out.println("199getOne_For_Update");
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article_rep/listAllArticle_Rep.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			System.out.println("update test");

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/

				String art_rep_no = new String(req.getParameter("art_rep_no").trim());
		
				String rep_content = req.getParameter("rep_content");
				if (rep_content == null) {
					errorMsgs.add("�峹���e");
				}
				
				String com_status = new String(req.getParameter("com_status"));

				Article_RepVO article_repVO = new Article_RepVO();

				article_repVO.setRep_content(rep_content);			
				article_repVO.setArt_rep_no(art_rep_no);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("article_repVO", article_repVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article_rep/update_Article_Rep.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				Article_RepService article_repSvc = new Article_RepService();
				article_repVO = article_repSvc.updateArticle_Rep( rep_content, art_rep_no,com_status);
				System.out.println("01");

				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				article_repVO = article_repSvc.getOneArticle_Rep(art_rep_no);
				req.setAttribute("article_repVO", article_repVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/front-end/article_rep/listOneArticle_Rep.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article_rep/update_Article_Rep.jsp");
				failureView.forward(req, res);
			}
		}
		


		if ("insert".equals(action)) { // �Ӧ�addArticle.jsp���ШD �|���峹��Ʒs�W

			System.out.println("insert test");

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
				String mem_id = req.getParameter("mem_id").trim();
				System.out.println("mem_id="+mem_id);
				String mem_idReg = "^[(MeM)(0-9)]{3,7}$";
				if (mem_id == null || mem_id.trim().length() == 0) {
					errorMsgs.put("mem_id", "�|���s���ФŪť�");
				}else if (!mem_id.trim().matches(mem_id)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.put("mem_id", "�|���s��: �u��O�^��r���B�Ʀr(�p:MEM0000001) , �B���ץ��ݦb2��10����");
				}
				
				
				String article_no = req.getParameter("article_no").trim();
				String article_noReg = "^[(ART)(0-9)]{3,3}$";
				if (article_no == null || article_no.trim().length() == 0) {
					errorMsgs.put("article_no", "�峹�s���ФŪť�");
				}else if (!article_no.trim().matches(article_no)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.put("article_no", "�峹�s��: �u��O�^��r���B�Ʀr(�p:ART001) , �B���ץ��ݦb3��6����");
				}
				
				
				

				String rep_content = req.getParameter("rep_content").trim();
				if (rep_content == null) {
					errorMsgs.put("rep_content", "���|���e�ФŪť�");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article_rep/addArticle_Rep.jsp");
					failureView.forward(req, res);
					
				}
				
				System.out.println("Servlet");
				System.out.println("article_no = "+article_no);
				System.out.println("mem_id = "+mem_id);
				System.out.println("rep_content = "+rep_content);

				/*************************** 2.�}�l�s�W��� ***************************************/
				Article_RepService article_repSvc = new Article_RepService();
				article_repSvc.addArticle_Rep(mem_id, article_no, rep_content);
				System.out.println(article_repSvc);

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				String url ="/front-end/article_rep/listAllArticle_Rep.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.put("125", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article_rep/addArticle_Rep.jsp");
				failureView.forward(req, res);
			}
		}
	}
}