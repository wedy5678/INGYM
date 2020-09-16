package com.product.controller;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.*;
import javax.servlet.http.*;

import com.product.model.ProductJDBCDAO;
import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.product_photo.model.PhotoService;
import com.product_photo.model.PhotoVO;

public class ProductServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@SuppressWarnings("null")
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);

		if ("searchProduct".equals(action)) {
			ProductService productService = new ProductService();
			List<ProductVO> list = new ArrayList<ProductVO>();
			list = productService.getAll();
			String searchStr = null;
			try {
				searchStr = req.getParameter("searchStr");

				String[] searchStrSplit = searchStr.split(" ");

				List<String> searchStrSplitList = new ArrayList<String>();
				for (String s : searchStrSplit) {
					if (s.trim().length() != 0) {
						searchStrSplitList.add(s);
					}
				}
				System.out.println(searchStrSplitList);
				List<ProductVO> collect = null;
				List<ProductVO> collect2 =  new ArrayList<ProductVO>();
				for (String s : searchStrSplitList) {
					collect = list.stream().filter(productVO -> productVO.getpName().indexOf(s) > -1)
							.collect(Collectors.toList());
					collect2.addAll(collect);
				}
				System.out.println(collect2);
				List<ProductVO> collect3 = new ArrayList<ProductVO>(new HashSet(collect2));

				String url = null;
				if (collect != null) {
					url = "/front-end/product/search-product.jsp";
					req.setAttribute("collect", collect3); // ��Ʈw���X��empVO����,�s�Jreq
					req.setAttribute("searchStr", searchStr);
					System.out.println("collect != null");
				} else {
					url = "/front-end/product/search-product-none.jsp";
					req.setAttribute("searchStr", searchStr);
					System.out.println("else");
				}
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if ("getOneForDisplay".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String productNo = req.getParameter("productNo");
				System.out.println("getOneForDisplay-productNo : " + productNo);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

//				Integer empno = null;
//				try {
//					empno = new Integer(str);
//				} catch (Exception e) {
//					errorMsgs.add("���u�s���榡�����T");
//				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				ProductService productService = new ProductService();
				ProductVO productVO = productService.getOneProduct(productNo);
				
//				EmpService empSvc = new EmpService();
//				EmpVO empVO = empSvc.getOneEmp(empno);
//				if (empVO == null) {
//					errorMsgs.add("�d�L���");
//				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("productVO", productVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front-end/product/product-detail-mine.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		///////////
		if ("getOneForDisplayRelated".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String productNo = req.getParameter("productNoRelated");
				System.out.println("getOneForDisplayRelated-productNo : " + productNo);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

//				Integer empno = null;
//				try {
//					empno = new Integer(str);
//				} catch (Exception e) {
//					errorMsgs.add("���u�s���榡�����T");
//				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				ProductService productService = new ProductService();
				ProductVO productVO = productService.getOneProduct(productNo);
//				EmpService empSvc = new EmpService();
//				EmpVO empVO = empSvc.getOneEmp(empno);
//				if (empVO == null) {
//					errorMsgs.add("�d�L���");
//				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("productVO", productVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front-end/product/product-detail-mine.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		///////
		if ("get-one-product-for-update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
//				Integer empno = new Integer(req.getParameter("empno"));
				String productNo = req.getParameter("productNo");
				System.out.println("************" + productNo + "*********************");

				/*************************** 2.�}�l�d�߸�� ****************************************/
				ProductService productService = new ProductService();
				ProductVO productVO = productService.getOneProduct(productNo);
//				EmpService empSvc = new EmpService();
//				EmpVO empVO = empSvc.getOneEmp(empno);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("productVO", productVO);
//				req.setAttribute("empVO", empVO);         // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front-end/product/update-product.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/product/list-product-by-memId.jsp");
				failureView.forward(req, res);
			}
		}

//		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
//				String productNo = req.getParameter("productNo");
//				System.out.println("productNo = " + productNo);
//				String pName = req.getParameter("pName");
//				System.out.println("pName = " + pName);
////				String pNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (pName == null || pName.trim().length() == 0) {
//					errorMsgs.add("�ӫ~�W��: �ФŪť�");
//				}
//				Double pPrice = null;
//				try {
//					pPrice = new Double(req.getParameter("pPrice").trim());
//				} catch (NumberFormatException e) {
//					pPrice = 0.0;
//					errorMsgs.add("����п�J�Ʀr.");
//				}
//
//				Double pStock = null;
//				try {
//					pStock = new Double(req.getParameter("pStock").trim());
//				} catch (NumberFormatException e) {
//					pStock = 0.0;
//					errorMsgs.add("�w�s�п�J�Ʀr.");
//				}
//				String categoryNo = req.getParameter("categoryNo");
//				if (categoryNo == null || categoryNo.trim().length() == 0) {
//					errorMsgs.add("�п�ܰӫ~����");
//				}
//				String memId = "MEM0000001";
//				String pDetail = req.getParameter("pDetail");
//				ProductVO productVO = new ProductVO();
//				productVO.setpName(pName);
//				productVO.setMemId(memId);
//				productVO.setpPrice(pPrice);
//				productVO.setpStock(pStock);
//				productVO.setCategoryNo(categoryNo);
//				productVO.setpDetail(pDetail);
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("productVO", productVO); // �t����J�榡���~��empVO����,�]�s�Jreq
//					RequestDispatcher failureView = req.getRequestDispatcher("/product/update_product.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//
//				/*************************** 2.�}�l�ק��� *****************************************/
//				ProductService productService = new ProductService();
//				productVO = productService.updateProduct(productNo, pName, memId, pPrice, pStock, categoryNo, pDetail);
//
//				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
//				req.setAttribute("productVO", productVO);
////				req.setAttribute("empVO", empVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
//				String url = "/product/listAllProduct.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
//				successView.forward(req, res);
//
//				/*************************** ��L�i�઺���~�B�z *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/product/update_product.jsp");
//				failureView.forward(req, res);
//			}
//		}

		if ("update".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
				try {
					String ifIamgeExist = req.getParameter("ifIamgeExist");
					System.out.println("ifIamgeExist : " + ifIamgeExist);
					if (ifIamgeExist != null) {
						errorMsgs.add("�ӫ~�Ϥ�: �ФW�Ǧܤ֤@�i�Ϥ�");
					}
				} catch (Exception e) {
					System.out.println("�o�O�ڭn���AifIamgeExist�]�����Ϥ��Ӯ����F");
				}

				String productNo = req.getParameter("productNo");

				String pName = req.getParameter("pName");
				if (pName == null || pName.trim().length() == 0) {
					errorMsgs.add("�ӫ~�W��: �ФŪť�");
				}

				Double pStock = null;
				try {
					pStock = new Double(req.getParameter("pStock").trim());
				} catch (NumberFormatException e) {
					pStock = 0.0;
					errorMsgs.add("�ƶq: �п�J�Ʀr");
				}
				Double pPrice = null;
				try {
					pPrice = new Double(req.getParameter("pPrice").trim());
				} catch (NumberFormatException e) {
					pPrice = 0.0;
					errorMsgs.add("����: �п�J�Ʀr");
				}
				String categoryNo = req.getParameter("categoryNo");
//				if (categoryNo == null || categoryNo.trim().length() == 0) {
//					errorMsgs2.add("�п�ܰӫ~����");
//				}
				String[] poPaymentArray = req.getParameterValues("poPayment");
				System.out.println("poPaymentArray : " + poPaymentArray);
				String poPayment = null;
				try {

					for (int i = 0; i < poPaymentArray.length; i++) {
						if (poPayment == null)
							poPayment = poPaymentArray[i];
						else
							poPayment = poPayment + poPaymentArray[i];
					}
					System.out.println("poPayment : " + poPayment);

				} catch (Exception e) {
					errorMsgs.add("�I�ڤ覡: �п�ܦܤ֤@�إI�ڤ覡");
				}

				String[] poDeliveryArray = req.getParameterValues("poDelivery");
				System.out.println("poDeliveryArray : " + poDeliveryArray);
				String poDelivery = null;
				try {
					for (int i = 0; i < poDeliveryArray.length; i++) {
						if (poDelivery == null)
							poDelivery = poDeliveryArray[i];
						else
							poDelivery = poDelivery + poDeliveryArray[i];
					}
					System.out.println("poDelivery : " + poDelivery);
				} catch (Exception e) {
					errorMsgs.add("�B�e�覡: �п�ܦܤ֤@�عB�e�覡");
				}
				////////////////////////// insert_photo////////////////////////////////

				Collection<Part> parts = req.getParts();
				List<PhotoVO> list = new ArrayList<PhotoVO>();
				byte[] pPhoto = null;
				for (Part part : parts) {
					String name = part.getName();
					if ("p_photo".equals(name)) {
						try {
							System.out.println("YESYESYESYES");
							InputStream in = part.getInputStream();

							byte[] buf = new byte[in.available()];
							in.read(buf);
							pPhoto = buf;
							in.close();
							PhotoVO vo = new PhotoVO();
							vo.setpPhoto(pPhoto);

							list.add(vo);
						} catch (IOException e) {
							e.printStackTrace();
							errorMsgs.add("�ӫ~�Ϥ�: �W�ǹϤ��o�Ͳ��`");
						}
					} else {
						System.out.println("NONONONO");
					}

				}
				////////////////////////// insert_photo////////////////////////////////

				String memId = "MEM0000001";
				String pDetail = req.getParameter("pDetail");
				ProductVO productVO = new ProductVO();
				productVO.setpName(pName);
				productVO.setMemId(memId);
				productVO.setpPrice(pPrice);
				productVO.setpStock(pStock);
				productVO.setCategoryNo(categoryNo);
				productVO.setpDetail(pDetail);
				productVO.setPoPayment(poPayment);
				productVO.setPoDelivery(poDelivery);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/update-product.jsp");
					failureView.forward(req, res);
					System.out.println("�����D");
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				ProductService productService = new ProductService();
				productVO = productService.updateProduct(productNo, pName, pPrice, pStock, categoryNo, pDetail,
						poPayment, poDelivery, list);
				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/

				String url = "/front-end/product/list-product-by-memId.jsp";
				System.out.println(url);
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/add-product.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("updateRating".equals(action)) { // �Ӧ�addEmp.jsp���ШD
			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
					Double pRating = new Double(req.getParameter("pRating"));
					String productNo = req.getParameter("productNo");
					ProductVO productVO = new ProductVO();
					productVO.setProductNo(productNo);
				/*************************** 2.�}�l�s�W��� ***************************************/
					ProductService productService = new ProductService();
					productService.updateRating(pRating, productVO);
				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
				try {
					String ifIamgeExist = req.getParameter("ifIamgeExist");
					System.out.println("ifIamgeExist : " + ifIamgeExist);
					if (ifIamgeExist != null) {
						errorMsgs.add("�ӫ~�Ϥ�: �ФW�Ǧܤ֤@�i�Ϥ�");
					}
				} catch (Exception e) {
					System.out.println("�o�O�ڭn���AifIamgeExist�]�����Ϥ��Ӯ����F");
				}

				String pName = req.getParameter("pName");
				if (pName == null || pName.trim().length() == 0) {
					errorMsgs.add("�ӫ~�W��: �ФŪť�");
				}

				Double pStock = null;
				try {
					pStock = new Double(req.getParameter("pStock").trim());
				} catch (NumberFormatException e) {
					pStock = 0.0;
					errorMsgs.add("�ƶq: �п�J�Ʀr");
				}
				Double pPrice = null;
				try {
					pPrice = new Double(req.getParameter("pPrice").trim());
				} catch (NumberFormatException e) {
					pPrice = 0.0;
					errorMsgs.add("����: �п�J�Ʀr");
				}
				String categoryNo = req.getParameter("categoryNo");
//				if (categoryNo == null || categoryNo.trim().length() == 0) {
//					errorMsgs2.add("�п�ܰӫ~����");
//				}
				String[] poPaymentArray = req.getParameterValues("poPayment");
//				System.out.println("poPaymentArray : " + poPaymentArray);
				String poPayment = null;
				try {

					for (int i = 0; i < poPaymentArray.length; i++) {
						if (poPayment == null)
							poPayment = poPaymentArray[i];
						else
							poPayment = poPayment + poPaymentArray[i];
					}
//					System.out.println("poPayment : " + poPayment);

				} catch (Exception e) {
					errorMsgs.add("�I�ڤ覡: �п�ܦܤ֤@�إI�ڤ覡");
				}

				String[] poDeliveryArray = req.getParameterValues("poDelivery");
//				System.out.println("poDeliveryArray : " + poDeliveryArray);
				String poDelivery = null;
				try {
					for (int i = 0; i < poDeliveryArray.length; i++) {
						if (poDelivery == null)
							poDelivery = poDeliveryArray[i];
						else
							poDelivery = poDelivery + poDeliveryArray[i];
					}
//					System.out.println("poDelivery : " + poDelivery);
				} catch (Exception e) {
					errorMsgs.add("�B�e�覡: �п�ܦܤ֤@�عB�e�覡");
				}
				////////////////////////// insert_photo////////////////////////////////

				Collection<Part> parts = req.getParts();
				List<PhotoVO> list = new ArrayList<PhotoVO>();
				byte[] pPhoto = null;
				for (Part part : parts) {
					String name = part.getName();
					if ("p_photo".equals(name)) {
						try {
//							System.out.println("YESYESYESYES");
							InputStream in = part.getInputStream();

							byte[] buf = new byte[in.available()];
							in.read(buf);
							pPhoto = buf;
							in.close();
							PhotoVO vo = new PhotoVO();
							vo.setpPhoto(pPhoto);

							list.add(vo);
						} catch (IOException e) {
							e.printStackTrace();
							errorMsgs.add("�ӫ~�Ϥ�: �W�ǹϤ��o�Ͳ��`");
						}
					} else {
//						System.out.println("NONONONO");
					}

				}
				////////////////////////// insert_photo////////////////////////////////

//				String memId = "MEM0000001";
				String memId = req.getParameter("memId");

				String pDetail = req.getParameter("pDetail");
				ProductVO productVO = new ProductVO();
				productVO.setpName(pName);
				productVO.setMemId(memId);
				productVO.setpPrice(pPrice);
				productVO.setpStock(pStock);
				productVO.setCategoryNo(categoryNo);
				productVO.setpDetail(pDetail);
				productVO.setPoPayment(poPayment);
				productVO.setPoDelivery(poDelivery);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVOError", productVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/add-product.jsp");
					failureView.forward(req, res);
					System.out.println("�����D");
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				ProductService productService = new ProductService();
				productVO = productService.addProductAndPhoto(pName, memId, pPrice, pStock, categoryNo, pDetail, list,
						poPayment, poDelivery);
				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/

				String url = "/front-end/product/list-product-by-memId.jsp";
				System.out.println(url);
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/add-product.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				String productNo = req.getParameter("productNo");
				System.out.println("productNo : " + productNo);

				/*************************** 2.�}�l�R����� ***************************************/
				ProductService productService = new ProductService();
				productService.deleteProduct(productNo);
				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
				String url = "/front-end/product/list-product-by-memId.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/product/listAllProduct.jsp");
				failureView.forward(req, res);
			}
		}
	if ("deleteBack".equals(action)) { // �Ӧ�listAllEmp.jsp
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				String productNo = req.getParameter("productNo");
				System.out.println("productNo : " + productNo);
				
				/*************************** 2.�}�l�R����� ***************************************/
				ProductService productService = new ProductService();
				productService.deleteProduct(productNo);
				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
				
				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/product/listAllProduct.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
