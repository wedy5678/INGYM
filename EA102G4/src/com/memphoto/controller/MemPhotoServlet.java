package com.memphoto.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.bodyrecord.model.BodyRecordService;
import com.bodyrecord.model.BodyRecordVO;
import com.mem.model.*;
import com.memphoto.model.*;



@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MemPhotoServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		MemVO memVOLogin = (MemVO)session.getAttribute("memVOLogin");
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
							.getRequestDispatcher("/front-end/memphoto/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/memphoto/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				MemPhotoService memPhotoSvc = new MemPhotoService();
				List<MemPhotoVO> list = memPhotoSvc.getOneMemPhoto(mem_id);
				if (list == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/memphoto/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("list", list); // ��Ʈw���X��memPhotoVO����,�s�Jreq
				String url = "/front-end/memphoto/listOneMemPhoto.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneBodyRecord.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/memphoto/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listOneMemPhoto.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String photo_no = req.getParameter("photo_no");
				
				/***************************2.�}�l�d�߸��****************************************/
				MemPhotoService memPhotoSvc = new MemPhotoService();
				MemPhotoVO memPhotoVO = memPhotoSvc.getOneMemPhotoPhotoNo(photo_no);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("memPhotoVO", memPhotoVO);         // ��Ʈw���X��memPhotoVO����,�s�Jreq
				String url = "/front-end/memphoto/update_memPhoto_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\���update_memPhoto_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/memphoto/listOneMemPhoto.jsp");
				failureView.forward(req, res);
			}
		}
		
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
							.getRequestDispatcher("/front-end/memphoto/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/memphoto/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				MemPhotoService memPhotoSvc = new MemPhotoService();
				List<MemPhotoVO> list = memPhotoSvc.getOneMemPhoto(mem_id);
				if (list == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/memphoto/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("list", list); // ��Ʈw���X��memPhotoVO����,�s�Jreq
				String url = "/front-end/memphoto/listOneMemPhoto.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneBodyRecord.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/memphoto/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		
		if ("update".equals(action)) { // �Ӧ�update_memPhoto_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				
				//�Ӥ��s��
				String photo_no = req.getParameter("photo_no");

				MemPhotoVO memPhotoVO = new MemPhotoVO();
				memPhotoVO.setPhoto_no(photo_no);
				
				/***************************2.�}�l�ק���*****************************************/
				
				MemPhotoService memPhotoSvc = new MemPhotoService();
				memPhotoSvc.update( photo_no);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				String url = "/front-end/memphoto/listOneMemPhoto.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,��listOneBodyRecord.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/memphoto/update_memPhoto_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // �Ӧ�addMemPhoto.jsp���ШD        
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				
				//�|���s��
				String mem_id = memVOLogin.getMem_id();

				//�Ӥ�
				Part part = req.getPart("photo");
				InputStream in = part.getInputStream();
				byte[] photo = new byte[in.available()];
				if(in.available() == 0) {
					errorMsgs.add("�ФW�ǹϤ�");
				}
				in.read(photo);
				in.close();
				
				
				
				MemPhotoVO memPhotoVO = new MemPhotoVO();
				
				memPhotoVO.setMem_id(mem_id);
				memPhotoVO.setPhoto(photo);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memPhotoVO", memPhotoVO); // �t����J�榡���~��memPhotoVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/memphoto/changeHeadPhoto.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				MemPhotoService memPhotoSvc = new MemPhotoService();
				
				MemPhotoVO ex_memPhotoVO = memPhotoSvc.getOneMemPhotoByStatus(mem_id);
				
				if(ex_memPhotoVO != null) {
					memPhotoSvc.update(ex_memPhotoVO.getPhoto_no());
				}
				
				memPhotoVO = memPhotoSvc.addMemPhoto(mem_id, photo);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = req.getContextPath()+"/front-end/mem/memDetail.jsp";
				res.sendRedirect(url);		
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/memphoto/changeHeadPhoto.jsp");
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
				String photo_no = req.getParameter("photo_no");
				
				/***************************2.�}�l�R�����***************************************/
				MemPhotoService memPhotoSvc = new MemPhotoService();
				memPhotoSvc.deleteMemPhoto(photo_no);
				
				res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				String data = "success";
				out.write(data);
				out.flush();
				out.close();
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				PrintWriter out = res.getWriter();
				String data = "fail";
				out.write(data);
				out.flush();
				out.close();
				
			}
		}
	}
}
