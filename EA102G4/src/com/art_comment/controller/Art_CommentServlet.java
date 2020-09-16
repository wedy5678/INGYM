package com.art_comment.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.art_comment.model.*;
import com.art_comment.model.Art_CommentVO;
import com.article.model.ArticleService;
import com.article.model.ArticleVO;




@MultipartConfig()
public class Art_CommentServlet extends HttpServlet {

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
				String str = req.getParameter("com_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�d���s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/art_comment/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				String com_no = null;
				try {
					com_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("�d���s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/art_comment/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				Art_CommentService art_commentSvc = new Art_CommentService();
				Art_CommentVO art_commentVO = art_commentSvc.getOneArt_Comment(com_no);
				if (art_commentVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/art_comment/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("art_commentVO", art_commentVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front-end/art_comment/listOneArt_Comment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/art_comment/select_page.jsp");
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
				String com_no = new String(req.getParameter("com_no"));

				/*************************** 2.�}�l�d�߸�� ****************************************/
				Art_CommentService artcommentService = new Art_CommentService();
				Art_CommentVO art_commentVO = artcommentService.getOneArt_Comment(com_no);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("art_commentVO", art_commentVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front-end/art_comment/update_Art_Comment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);
				return;

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/art_comment/listAllArt_Comment.jsp");
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

				String com_no = new String(req.getParameter("com_no").trim());
				System.out.println(com_no+"142");
														
				String mes_content = req.getParameter("mes_content");
				System.out.println(mes_content+"145");
				if (mes_content == null) {
					errorMsgs.add("�d�����e�ק�");
				}
				
				Art_CommentVO art_commentVO = new Art_CommentVO();

				art_commentVO.setMes_content(mes_content);			
				art_commentVO.setCom_no(com_no);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("art_commentVO", art_commentVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/art_comment/update_Art_Comment.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				Art_CommentService art_commentSvc = new Art_CommentService();
				art_commentVO = art_commentSvc.updateArt_Comment( mes_content, com_no);

				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				art_commentVO = art_commentSvc.getOneArt_Comment(com_no);
				req.setAttribute("art_commentVO", art_commentVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/front-end/art_comment/listOneArt_Comment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/art_comment/update_Art_Comment.jsp");
				failureView.forward(req, res);
			}
		}
		


		if ("insert".equals(action)) { // �Ӧ�addArticle.jsp���ШD �|���d����Ʒs�W

			System.out.println("insert test");

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
				String article_no = req.getParameter("article_no").trim();
				String article_noReg = "^[(ART)(0-9)]{3,3}$";
				if (article_no == null || article_no.trim().length() == 0) {
					errorMsgs.put("article_no", "�峹�s���ФŪť�");
				}else if (!article_no.trim().matches(article_no)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.put("article_no", "�峹�s��: �u��O�^��r���B�Ʀr(�p:ART001) , �B���ץ��ݦb3��6����");
				}
				
				String mem_id = req.getParameter("mem_id").trim();
				String mem_idReg = "^[(MeM)(0-9)]{3,7}$";
				if (mem_id == null || mem_id.trim().length() == 0) {
					errorMsgs.put("mem_id", "�|���s���ФŪť�");
				}else if (!mem_id.trim().matches(mem_id)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.put("mem_id", "�|���s��: �u��O�^��r���B�Ʀr(�p:MEM0000001) , �B���ץ��ݦb2��10����");
				}
				

				String mes_content = req.getParameter("mes_content").trim();
				if (mes_content == null) {
					errorMsgs.put("mes_content", "�d�����e�ФŪť�");
				}
				
				String com_status = req.getParameter("com_status").trim();
				if (com_status == null || com_status.trim().length() == 0) {
					errorMsgs.put("com_status", "�d�����A : �ФŪť�");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/listOneArticle.jsp");
					failureView.forward(req, res);
					return;
				}
				
				System.out.println("Servlet");
				System.out.println("article_no = "+article_no);
				System.out.println("mem_id = "+mem_id);
				System.out.println("mes_content = "+mes_content);
				System.out.println("com_status = "+com_status);

				/*************************** 2.�}�l�s�W��� ***************************************/
				Art_CommentService art_commentSvc = new Art_CommentService();
				art_commentSvc.addArt_Comment(article_no, mem_id, mes_content, com_status);
				

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				ArticleService articleSvc = new ArticleService();
				ArticleVO articleVO = new ArticleService().getOneArticle(article_no);
				
				Set<Art_CommentVO> Art_CommentVOs = articleSvc.getArt_CommentsByArticle_no(article_no);
				req.setAttribute("articleVO", articleVO); // ��Ʈw���X��empVO����,�s�Jreq
				req.setAttribute("Art_CommentVOs", Art_CommentVOs);
				
				String url = "/front-end/article/listOneArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
//				e.printStackTrace();
				errorMsgs.put("125", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/listOneArticle.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp
			System.out.println("action="+action);
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String com_no = new String(req.getParameter("com_no"));
				System.out.println("com_no="+com_no);
				/***************************2.�}�l�R�����***************************************/
				Art_CommentService art_commentSvc = new Art_CommentService();
				art_commentSvc.deleteArt_Comment(com_no);
				System.out.println("com_no12="+com_no);

				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/front-end/article/listOneArticle.jsp";
				System.out.println("com_nowwwww="+com_no);
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/art_comment/listAllArt_Comment.jsp");
				failureView.forward(req, res);
			}
		}
	}
}