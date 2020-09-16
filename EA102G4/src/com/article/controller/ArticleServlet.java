package com.article.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.art_comment.model.Art_CommentVO;
import com.article.model.ArticleService;
import com.article.model.ArticleVO;
import com.mem.model.MemService;
import com.mem.model.MemVO;


@MultipartConfig()
public class ArticleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");// �|��
		String action1 = req.getParameter("action1");// ���u
//		String action2 = req.getParameter("action2");// ���u

		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD
			System.out.println("getOne_For_Display test");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		System.out.println("111");
			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("article_no");
				System.out.println(str);	
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�峹�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				String article_no = null;
				try {
					article_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("�峹�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}
				
				/*************************** 2.�}�l�d�߸�� *****************************************/
				ArticleService articleSvc = new ArticleService();
				ArticleVO articleVO = articleSvc.getOneArticle(article_no);

				
				if (articleVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}
				
				req.setAttribute("article_no", article_no);
//				Set<Art_CommentVO> art_commentVOs = articleSvc.getArt_CommentsByArticle_no(article_no);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("articleVO", articleVO); // ��Ʈw���X��empVO����,�s�Jreq
//				req.setAttribute("art_commentVOs", art_commentVOs);
				String url = "/front-end/article/listOneArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD
//			System.out.println("getOne_For_Update");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				String article_no = new String(req.getParameter("article_no"));
//				System.out.println("+++"+article_no+"+++");

				/*************************** 2.�}�l�d�߸�� ****************************************/
				ArticleService articleService = new ArticleService();
				ArticleVO articleVO = articleService.getOneArticle(article_no);
//				System.out.println("222+++"+article_no+"+++");

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("articleVO", articleVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front-end/article/update_Article.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/listAllArticle.jsp");
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

				String article_no = new String(req.getParameter("article_no").trim());

//				String query_mem_id = req.getParameter("query_mem_id").trim(); // �ק�e���|���s��
//				boolean isMenId = !(query_mem_id == null || query_mem_id.trim().length() == 0);
//
//				String query_work_id = req.getParameter("query_work_id").trim(); // �ק�e���­��u�s��
//				boolean isWorkId = !(query_work_id == null || query_work_id.trim().length() == 0);
//
//				String mem_id = req.getParameter("mem_id").trim();
//				if (isMenId && (mem_id == null || mem_id.trim().length() == 0)) {
//					errorMsgs.add("�|���s��");
//				}
//
//				String work_id = req.getParameter("work_id").trim();
//				if (isWorkId && (work_id == null || work_id.trim().length() == 0)) {
//					errorMsgs.add("���u�s��");
//				}

				String title = req.getParameter("title");
				String titleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (title == null || title.trim().length() == 0) {
					errorMsgs.add("�峹���D");
				} else if (!title.trim().matches(title)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�峹���D");
				}

				String art_content = req.getParameter("art_content").trim();
				if (art_content == null) {
					errorMsgs.add("�峹���e");
				}

				ArticleVO articleVO = new ArticleVO();
//				if (isWorkId) {
//					articleVO.setWork_id(work_id);
//				}
//				if (isMenId) {
//					articleVO.setMem_id(mem_id);
//				}
				articleVO.setTitle(title);
				articleVO.setArt_content(art_content);
				articleVO.setArticle_no(article_no);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("articleVO", articleVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/update_Article.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				ArticleService articleSvc = new ArticleService();
//				if (isWorkId && isMenId) { // �ק�|���έ��u�s��
				articleVO = articleSvc.updateArticle(title, art_content, article_no);
//				} else if (isWorkId) { // �ק���u�s��
//					articleVO = articleSvc.updateArticle1( title, art_content, article_no);
//				} else { // �ק�|���s��
//					articleVO = articleSvc.updateArticle(mem_id, title, art_content, link_count, com_count, a_status,
//							article_no);
//				}

				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				articleVO = articleSvc.getOneArticle(article_no);
				req.setAttribute("articleVO", articleVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				
				Set<Art_CommentVO> Art_CommentVOs = articleSvc.getArt_CommentsByArticle_no(article_no);
				req.setAttribute("articleVO", articleVO); // ��Ʈw���X��empVO����,�s�Jreq
				req.setAttribute("Art_CommentVOs", Art_CommentVOs);
				
				String url = "/front-end/article/listOneArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/update_Article.jsp");
				failureView.forward(req, res);
			}
		}

		// ---------------------------���u��ƭק�---------------

//		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD
//			System.out.println("getOne_For_Update");
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/*************************** 1.�����ШD�Ѽ� ****************************************/
//				String article_no = new String(req.getParameter("article_no"));
//				System.out.println("22+++" + article_no + "+++");
//
//				/*************************** 2.�}�l�d�߸�� ****************************************/
//				ArticleService articleService = new ArticleService();
//				ArticleVO articleVO = articleService.getOneArticle(article_no);
//				System.out.println("222+++" + article_no + "+++");
//
//				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
//				req.setAttribute("articleVO", articleVO); // ��Ʈw���X��empVO����,�s�Jreq
//				String url = "/article/update_Article1.jsp";
//				System.out.println("22002+++" + article_no + "+++");
//				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
//				successView.forward(req, res);
//
//				/*************************** ��L�i�઺���~�B�z **********************************/
//			} catch (Exception e) {
//				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
//				System.out.println("22002+++");
//				RequestDispatcher failureView = req.getRequestDispatcher("/article/listAllArticle.jsp");
//				failureView.forward(req, res);
//			}
//		}

//		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
//			System.out.println("update1 test");
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
//
//				String article_no = new String(req.getParameter("article_no").trim());
//				String work_id = req.getParameter("work_id").trim();
//				if (work_id == null || work_id.trim().length() == 0) {
//					errorMsgs.add("work_id");
//				}
//
//				String title = req.getParameter("title");
//				String titleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (title == null || title.trim().length() == 0) {
//					errorMsgs.add("�峹���D");
//				} else if (!title.trim().matches(title)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
//					errorMsgs.add("�峹���D");
//				}
//
//				String art_content = req.getParameter("art_content").trim();
//				if (art_content == null || art_content.trim().length() == 0) {
//					errorMsgs.add("�峹���e");
//				}
//
//				Integer link_count = null;
//				try {
//					link_count = new Integer(req.getParameter("link_count").trim());
//				} catch (NumberFormatException e) {
//					errorMsgs.add("�g��");
//				}
//
//				Integer com_count = null;
//				try {
//					com_count = new Integer(req.getParameter("com_count").trim());
//				} catch (NumberFormatException e) {
//					errorMsgs.add("�d����");
//				}
//
//				String a_status = req.getParameter("a_status").trim();
//				System.out.println(a_status);
//				if (a_status == null || a_status.trim().length() == 0) {
//					errorMsgs.add("�峹���A");
//				}
//
//				String Article_no = new String(req.getParameter("article_no").trim());
//
//				ArticleVO articleVO = new ArticleVO();
//
//				articleVO.setWork_id(work_id);
//				articleVO.setTitle(title);
//				articleVO.setArt_content(art_content);
//				articleVO.setLink_count(link_count);
//				articleVO.setCom_count(com_count);
//				articleVO.setA_status(a_status);
//				articleVO.setArticle_no(article_no);
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("articleVO", articleVO); // �t����J�榡���~��empVO����,�]�s�Jreq
//					RequestDispatcher failureView = req.getRequestDispatcher("/article/update_Article1.jsp");
//					failureView.forward(req, res);
//					return; // �{�����_
//				}
//
//				/*************************** 2.�}�l�ק��� *****************************************/
//				ArticleService articleSvc = new ArticleService();
//				articleVO = articleSvc.updateArticle(work_id, title, art_content, link_count, com_count, a_status,
//						article_no);
//
//				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
//				req.setAttribute("articleVO", articleVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
//				String url = "/article/listOneArticle.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
//				successView.forward(req, res);
//
//				/*************************** ��L�i�઺���~�B�z *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/article/update_Article1.jsp");
//				failureView.forward(req, res);
//			}
//		}

//		System.out.println("action = "+action);

		if ("insert".equals(action)) { // �Ӧ�addArticle.jsp���ШD �|���峹��Ʒs�W

//			System.out.println("insert test");

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/

				String mem_id = req.getParameter("mem_id").trim();
				String mem_idReg = "^[(MeM)(0-9)]{3,7}$";
				if (mem_id == null || mem_id.trim().length() == 0) {
					errorMsgs.put("mem_id", "�|���s���ФŪť�");
				} else if (!mem_id.trim().matches(mem_id)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.put("mem_id", "�|���s��: �u��O�^��r���B�Ʀr(�p:MEM0000001) , �B���ץ��ݦb2��10����");
				}

				String title = req.getParameter("title");
				String titleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (title == null || title.trim().length() == 0) {
					errorMsgs.put("title", "�峹���D: �ФŪť�");
				} else if (!title.trim().matches(title)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.put("title", "�峹���D: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
				}

				String art_content = req.getParameter("art_content").trim();
				if (art_content == null || art_content.trim().length() == 0) {
					errorMsgs.put("art_count", "�峹���e�ФŪť�");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/addArticle.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				ArticleService articleSvc = new ArticleService();
				articleSvc.addArticle(mem_id, title, art_content);

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				String url = "/front-end/article/listAllArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.put("125", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/addArticle.jsp");
				failureView.forward(req, res);
			}
		}

		// -----------���u��Ʒs�W�P�_---------------------------------------------

		if ("insert1".equals(action1)) { // �Ӧ�addArticleWork.jsp���ШD ���u�峹��Ʒs�W

//			System.out.println("insert test");

			Map<String, String> errorMsgs1 = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs1", errorMsgs1);

			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/

				String work_id = req.getParameter("work_id").trim();
				String work_idReg = "^[(WORK)(0-9)]{4,6}$";
				if (work_id == null || work_id.trim().length() == 0) {
					errorMsgs1.put("work_id", "���u�s���ФŪť�");
				} else if (!work_id.trim().matches(work_id)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs1.put("work_id", "�|���s��: �u��O�^��r���B�Ʀr(�pWORK000001) �B���ץ��ݦb2��10����");
				}

				String title = req.getParameter("title");
				String titleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (title == null || title.trim().length() == 0) {
					errorMsgs1.put("title", "�峹���D: �ФŪť�");
				} else if (!title.trim().matches(title)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs1.put("title", "�峹���D: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
				}

				String art_content = req.getParameter("art_content").trim();
				if (art_content == null || art_content.trim().length() == 0) {
					errorMsgs1.put("art_count", "�峹���e�ФŪť�");
				}

				if (!errorMsgs1.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/addArticleWork.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				ArticleService articleSvc = new ArticleService();
				articleSvc.addArticle1(work_id, title, art_content);

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				String url = "/front-end/article/listAllArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs1.put("125", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/addArticleWork.jsp");
				failureView.forward(req, res);
			}
		}
		
		

		if ("delete_Article".equals(action)) { // �Ӧ�/dept/listAllDept.jsp���ШD
			
			System.out.println("action="+action);

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String article_no = new String(req.getParameter("article_no"));
				
				/***************************2.�}�l�R�����***************************************/
				ArticleService articleSvc = new ArticleService();
				articleSvc.deleteArticle(article_no);
				
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/
				String url = "/article/listAllArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��, ���\��� �^�� /dept/listAllDept.jsp
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z***********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/article/listAllArticle.jsp");
				failureView.forward(req, res);
			}
		}
			
		
	}
}