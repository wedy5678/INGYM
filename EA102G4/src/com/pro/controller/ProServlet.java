package com.pro.controller;

import java.io.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.pro.model.*;
import com.classAuth.model.ClassAuthService;
import com.classAuth.model.ClassAuthVO;
import com.classType.model.*;
import com.individualClass.model.IndividualClassService;
import com.license.model.LicenseVO;
import com.mem.model.*;

@MultipartConfig()
public class ProServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		/***************************
		 * 1.Search for One
		 ***************************/
		if ("getOneClassAuth".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z ****************************************/
				String c_type_no = req.getParameter("c_type_no");
				/*************************** 2.�}�l�d�߸�� ****************************************/
				ClassTypeService classTypeSvc = new ClassTypeService();
				ClassTypeVO classTypeVO = classTypeSvc.getOneClassType(c_type_no);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("classTypeVO", classTypeVO);
				boolean openModal=true;
				req.setAttribute("openModal",openModal );
				
				String url = "listAllClassType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("listAllClassType.jsp");
				failureView.forward(req, res);
			}
		}

		/***************************
		 * 1.Search for One
		 ***************************/
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("pro_ID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�s��");
				}
				
				String pro_ID = null;
				try {
					pro_ID = str;
				} catch (Exception e) {
					errorMsgs.add("�s���榡�����T");
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				ProService proSvc = new ProService();
				ProVO proVO = proSvc.getOnePro(pro_ID);
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/ProAndClass/pro.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("proVO", proVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url ="/front-end/ProAndClass/proDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/ProAndClass/pro.jsp");
				failureView.forward(req, res);
			}
		}
		
		/***************************
		 * 2.UPDATE
		 ***************************/

		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				String pro_ID = req.getParameter("pro_ID");

				/*************************** 2.�}�l�d�߸�� ****************************************/
				ProService proSvc = new ProService();
				ProVO proVO = proSvc.getOnePro(pro_ID);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("proVO", proVO);
				String url = "/front-end/ProAndClass/updatePro.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/ProAndClass/listOnePro.jsp");
				failureView.forward(req, res);
			}
		}

		/*****************************************
		 * 3. update
		 *****************************************/
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
				String pro_ID = req.getParameter("pro_ID").trim();
				String name = req.getParameter("mem_name");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";

				if (name == null || name.trim().length() == 0) {
					errorMsgs.add("�|���m�W: �ФŪť�");
				} else if (!name.trim().matches(enameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�|���m�W: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
				}

				String mem_ID = req.getParameter("mem_ID");

				Integer pro_auth = null;
				try {
					pro_auth = new Integer(req.getParameter("job").trim());
				} catch (Exception e) {
					errorMsgs.add("�s�������T");
				}
				if (pro_auth == null) {
					errorMsgs.add("¾��ФŪť�");
				}

				Integer p_rating = null;
				try {
					p_rating = new Integer(req.getParameter("p_rating"));
				} catch (Exception e) {
					errorMsgs.add("�s�������T");
				}

				Integer t_rating = null;
				try {
					p_rating = new Integer(req.getParameter("t_rating"));
				} catch (Exception e) {
					errorMsgs.add("�s�������T");
				}

				String expr = new String(req.getParameter("expr"));

				ProVO proVO = new ProVO();
				proVO.setPro_ID(pro_ID);
				proVO.setMem_ID(mem_ID);
				proVO.setP_rating(p_rating);
				proVO.setT_rating(t_rating);
				proVO.setExpr(expr);

				MemVO memVO = new MemVO();
				memVO.setMem_name(name);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("proVO", proVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/ProAndClass/listOnePro.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				ProService proSvc = new ProService();
				proSvc.updatePro(pro_ID, mem_ID, t_rating, p_rating, pro_auth, expr);

				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				System.out.println("Servlet = " + proVO.getMem_ID());
				req.setAttribute("proVO", proVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/listOnePro.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/ProAndClass/listOnePro.jsp");
				failureView.forward(req, res);
			}
		}

		/*****************************************
		 * 3. Insert with transaction ProApplication
		 *****************************************/
		if ("insert".contentEquals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
				String mem_ID = req.getParameter("mem_id");
				Integer pro_auth = 0;
				Integer p_rating = 0;
				Integer t_rating = 0;
				String expr = new String(req.getParameter("expr"));
				
				// set data into ProVO
				ProVO proVO = new ProVO();
				proVO.setPro_auth(pro_auth);
				proVO.setMem_ID(mem_ID);
				proVO.setP_rating(p_rating);
				proVO.setT_rating(t_rating);
				proVO.setExpr(expr);


				// get license variables
				String []lic_names = req.getParameterValues("lic_name");
				String []no_regs = req.getParameterValues("no_reg");
				
				//receive and error check
				String lic_name = null;
				String no_reg = null;
				for(int i=0; i<lic_names.length; i++) {
					
					lic_name = lic_names[i].trim();
					if (lic_name == null || lic_name.trim().length() == 0) {
						errorMsgs.add("lic_name�ФŪť�");
					}
					
					no_reg = no_regs[i].trim();
					if (no_reg == null || no_reg.trim().length() == 0) {
						errorMsgs.add("no_regs�ФŪť�");
					}
				}

				// get PICTURES;
				byte[] l_pic = null;
				List<byte[]> list = new ArrayList<byte[]>();
				Collection<Part> part = req.getParts();
				
				for (Part part1 : part) {
					String filename = getFileNameFromPart(part1);
					if (filename != null && part1.getContentType() != null) {
						InputStream in = part1.getInputStream();
						byte[] buf = new byte[in.available()];
						in.read(buf);
						l_pic = buf;
						list.add(l_pic);
						in.close();
					}
				}
				
				//set correct datas in licenseVO
				List<LicenseVO> license = new ArrayList<LicenseVO>();
				for(int i =0; i<lic_names.length;i++) {
				LicenseVO licenseVO = new LicenseVO();
				licenseVO.setLic_name(lic_names[i]);
				licenseVO.setNo_reg(no_regs[i]);
				licenseVO.setL_pic(list.get(i));
				license.add(licenseVO);
				}
								
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("proVO", proVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("proApplication.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				ProService proSvc = new ProService();
				proVO = proSvc.addPro1(mem_ID, t_rating, p_rating, pro_auth, expr, license);

				/*************************** 3.�s�W���нm������X�нmID�s�W�v��*****************************************/
				// get all type_no from jsp
				String[] c_type_no = req.getParameterValues("c_type_no");

				//set ClassAuth
				//get coordinated pro_ID with mem_ID
				proVO = proSvc.getProByMem(mem_ID);
				
				List<ClassAuthVO>ctList = new ArrayList <ClassAuthVO>();
				for(String ct_no:c_type_no) {
					ClassAuthVO classAuthVO = new ClassAuthVO();
					classAuthVO.setPro_ID(proVO.getPro_ID());
					classAuthVO.setC_type_no(ct_no);
					ctList.add(classAuthVO);
					System.out.println(classAuthVO.getC_type_no());
				}
				
				//batch of authority pro applied
				ClassAuthService classAuthService = new ClassAuthService();
				classAuthService.addAuthBatch(ctList);
				
				/*************************** 4.�ק粒��,�ǳ����(Send the Success view) *************/
				req.setAttribute("proVO", proVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/front-end/index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("proApplication.jsp");
				failureView.forward(req, res);
			}
		}

		/**************************************
		 * UPDATE EXP ONLY
		 **************************************/

		if ("updateExp".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
				String pro_ID = req.getParameter("pro_ID");
				String exp = new String(req.getParameter("exp"));
				if (exp == null || exp.trim().length() == 0) {
					errorMsgs.add("�g��: �ФŪť�");
				}
				ProVO proVO = new ProVO();
				proVO.setPro_ID(pro_ID);
				proVO.setExpr(exp);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("proVO", proVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/ProAndClass/myClass.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				ProService proSvc = new ProService();
				proSvc.updateExp(pro_ID, exp);
				proVO = proSvc.getOnePro(pro_ID);
				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				HttpSession session = req.getSession();
				session.setAttribute("proVOLogin", proVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/front-end/ProAndClass/myClass.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/ProAndClass/myClass.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		/**************************************
		 * UPDATE Pro_Auth ONLY
		 **************************************/

		if ("updateProAuth".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
				String pro_ID = req.getParameter("pro_ID");
				Integer pro_auth = new Integer(req.getParameter("pro_auth"));

				/*************************** 2.�}�l�ק��� *****************************************/
				ProService proSvc = new ProService();
				proSvc.updateAuth(pro_ID, pro_auth);

				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				String url = "/back-end/class/listCoachAuth.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/class/listCoachAuth.jsp");
				failureView.forward(req, res);
			}
		}
		
		/**************************************
		 * UPDATE ClassAuth ONLY
		 **************************************/

		if ("updateClassAuth".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
				String [] auth_noBatch = req.getParameterValues("auth_no");
				String [] ca_statusBatch = req.getParameterValues("ca_status");
				
				List <ClassAuthVO> caList = new ArrayList <ClassAuthVO>();
				
				for(int i = 0; i<auth_noBatch.length; i++) {
					ClassAuthVO classAuthVO = new ClassAuthVO();
					Integer ca_status = new Integer(ca_statusBatch[i]);
					String auth_no =auth_noBatch[i];
					
					classAuthVO.setCa_status(ca_status);
					classAuthVO.setAuth_no(auth_no);
					caList.add(classAuthVO);
				}
				/*************************** 2.�}�l�ק��� *****************************************/
				ClassAuthService classAuthSvc = new ClassAuthService();
				classAuthSvc.modifyAuthBatch(caList);
				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				String url = "/back-end/class/listCoachClassAuth.jsp";
				res.sendRedirect(req.getContextPath()+url);

//			} catch (Exception e) {
//				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/ProAndClass/CoachAuth.jsp");
//				failureView.forward(req, res);
//			}
		}
		
	}

	// Get multiple pictures from application;
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}

}
