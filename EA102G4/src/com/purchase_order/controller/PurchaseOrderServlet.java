package com.purchase_order.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.purchase_order.model.PurchaseOrderService;
import com.purchase_order.model.PurchaseOrderVO;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutOneTime;
import ecpay.payment.integration.domain.InvoiceObj;
import tool.MailService;

public class PurchaseOrderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);

		/** green-pay-start **/
//		���
		if ("green-pay".equals(action)) {
			String poNo = req.getParameter("poNo");
			String member_id = req.getParameter("member_id");
			double amountD = new Double(req.getParameter("amountStr"));
			int i = (int) amountD;
			String amount = String.valueOf(i);
			HttpSession session = req.getSession();
			String pName = req.getParameter("pName");

			InvoiceObj invoice = null;
			System.out.println(pName);
			System.out.println(member_id);
			System.out.println(amount);
			System.out.println(new String(req.getRequestURL()));
			System.out.println("-------new String(req.getRequestURL())--------");
			AllInOne allInOne = new AllInOne("");
			AioCheckOutOneTime obj = new AioCheckOutOneTime();
			Date date = new Date();
			DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			System.out.println(sdf.format(date));

			obj.setMerchantTradeNo("P0" + date.getTime());
			obj.setMerchantTradeDate(sdf.format(date));
			obj.setTotalAmount(amount);
			obj.setTradeDesc("test Description");
			obj.setItemName(pName);
			obj.setReturnURL(new String(req.getRequestURL()));
			obj.setNeedExtraPaidInfo("N");
			obj.setRedeem("N");
			obj.setClientBackURL(req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath()
					+ "/front-end/purchase-order/list-one-order-by-pono-buyer.jsp?poNo=" + poNo + "&showSuccess=true");
			obj.setChooseSubPayment("Credit");
			res.setContentType("text/html; charset=UTF-8");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			String form = allInOne.aioCheckOut(obj, invoice);
			out.println("<HTML>");
			out.println("<HEAD><TITLE></TITLE></HEAD>");
			out.println("<BODY>");
			out.print(form);
			out.println("</BODY></HTML>");

		}
		/** green-pay-end **/

		/** get-one-order-for-display-seller-start **/
		if ("get-one-order-for-display-seller".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
//				Integer empno = new Integer(req.getParameter("empno"));
				String poNo = req.getParameter("poNo");
				System.out.println("************" + poNo + "**********getOneForUpdate***********");

				/*************************** 2.�}�l�d�߸�� ****************************************/
				PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
				ProductService productService = new ProductService();

				PurchaseOrderVO purchaseOrderVO = purchaseOrderService.getOneOrder(poNo);

				ProductVO productVO = productService.getOneProduct(purchaseOrderVO.getProductNo());
//				EmpService empSvc = new EmpService();
//				EmpVO empVO = empSvc.getOneEmp(empno);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("purchaseOrderVO", purchaseOrderVO);
				req.setAttribute("productVO", productVO);
//				req.setAttribute("empVO", empVO);         // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front-end/purchase-order/list-one-order-by-pono-seller.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/purchase_order/listAllOrder.jsp");
				failureView.forward(req, res);
			}
		}
		/** get-one-order-for-display-seller-end **/

		/** get-one-order-for-display-buyer-start **/
		if ("get-one-order-for-display-buyer".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
//				Integer empno = new Integer(req.getParameter("empno"));
				String poNo = req.getParameter("poNo");
				System.out.println("************" + poNo + "**********getOneForUpdate***********");

				/*************************** 2.�}�l�d�߸�� ****************************************/
				PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
				ProductService productService = new ProductService();

				PurchaseOrderVO purchaseOrderVO = purchaseOrderService.getOneOrder(poNo);

				ProductVO productVO = productService.getOneProduct(purchaseOrderVO.getProductNo());
//				EmpService empSvc = new EmpService();
//				EmpVO empVO = empSvc.getOneEmp(empno);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("purchaseOrderVO", purchaseOrderVO);
				req.setAttribute("productVO", productVO);
//				req.setAttribute("empVO", empVO);         // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front-end/purchase-order/list-one-order-by-pono-buyer.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/purchase_order/listAllOrder.jsp");
				failureView.forward(req, res);
			}
		}
		/** get-one-order-for-display-buyer-end **/

		/** getOneForUpdateStatusSeller-start **/
		if ("getOneForUpdateStatusSeller".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				String poNo = req.getParameter("poNo");
				String poStatus = req.getParameter("changePoStatus");
				String listAllOrListOne = req.getParameter("listAllOrListOne");
				System.out.println("poNo: " + poNo);
				System.out.println("poStatus: " + poStatus);
				System.out.println("listAllOrListOne: " + listAllOrListOne);

				/*************************** 2.�}�l�d�߸�� ****************************************/
				PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
				ProductService productService = new ProductService();

				PurchaseOrderVO purchaseOrderVO = purchaseOrderService.getOneOrder(poNo);
				purchaseOrderService.updateOrderStatus(purchaseOrderVO, poStatus);

				ProductVO productVO = productService.getOneProduct(purchaseOrderVO.getProductNo());

//				EmpService empSvc = new EmpService();
//				EmpVO empVO = empSvc.getOneEmp(empno);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("purchaseOrderVO", purchaseOrderVO);
				req.setAttribute("productVO", productVO);
//				req.setAttribute("empVO", empVO);         // ��Ʈw���X��empVO����,�s�Jreq
				String url = null;
				if (listAllOrListOne.equals("one")) {
					url = "/front-end/purchase-order/list-one-order-by-pono-seller.jsp";
				} else {
					url = "/front-end/purchase-order/list-order-by-memId-seller.jsp";
				}
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/purchase_order/listAllOrder.jsp");
				failureView.forward(req, res);
			}
		}
		/** getOneForUpdateStatusSeller-end **/

		/** getOneForUpdateStatusBuyer-start **/
		if ("getOneForUpdateStatusBuyer".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				String poNo = req.getParameter("poNo");
				String poStatus = req.getParameter("changePoStatus");
				String listAllOrListOne = req.getParameter("listAllOrListOne");
				System.out.println("poNo : " + poNo);
				System.out.println("poStatus : " + poStatus);
				System.out.println("listAllOrListOne : " + listAllOrListOne);

				/*************************** 2.�}�l�d�߸�� ****************************************/
				PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
				ProductService productService = new ProductService();

				PurchaseOrderVO purchaseOrderVO = purchaseOrderService.getOneOrder(poNo);
				purchaseOrderService.updateOrderStatus(purchaseOrderVO, poStatus);

				ProductVO productVO = productService.getOneProduct(purchaseOrderVO.getProductNo());

//				EmpService empSvc = new EmpService();
//				EmpVO empVO = empSvc.getOneEmp(empno);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("purchaseOrderVO", purchaseOrderVO);
				req.setAttribute("productVO", productVO);
//				req.setAttribute("empVO", empVO);         // ��Ʈw���X��empVO����,�s�Jreq
				String url = null;
				if (listAllOrListOne.equals("one")) {
					url = "/front-end/purchase-order/list-one-order-by-pono-buyer.jsp";
				} else {
					url = "/front-end/purchase-order/list-order-by-memId-buyer.jsp";
				}
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/purchase_order/listAllOrder.jsp");
				failureView.forward(req, res);
			}
		}
		/** getOneForUpdateStatusBuyer-end **/

		/****/
		if ("getOneForUpdateStatusBack".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				String poNo = req.getParameter("poNo");
				String poStatus = req.getParameter("changePoStatus");
				System.out.println("poNo : " + poNo);
				System.out.println("poStatus : " + poStatus);

				/*************************** 2.�}�l�d�߸�� ****************************************/
				PurchaseOrderService purchaseOrderService = new PurchaseOrderService();

				PurchaseOrderVO purchaseOrderVO = purchaseOrderService.getOneOrder(poNo);
				purchaseOrderService.updateOrderStatus(purchaseOrderVO, poStatus);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/purchase_order/listAllOrder.jsp");
				failureView.forward(req, res);
			}
		}
		/****/

		if ("getOneForUpdate".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
//				Integer empno = new Integer(req.getParameter("empno"));
				String poNo = req.getParameter("poNo");
				System.out.println("************" + poNo + "**********getOneForUpdate***********");

				/*************************** 2.�}�l�d�߸�� ****************************************/
				PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
				ProductService productService = new ProductService();

				PurchaseOrderVO purchaseOrderVO = purchaseOrderService.getOneOrder(poNo);

				System.out.println("getOneForUpdate�����" + purchaseOrderVO.getPoDelivery());
				ProductVO productVO = productService.getOneProduct(purchaseOrderVO.getProductNo());
//				EmpService empSvc = new EmpService();
//				EmpVO empVO = empSvc.getOneEmp(empno);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("purchaseOrderVO", purchaseOrderVO);
				req.setAttribute("productVO", productVO);
//				req.setAttribute("empVO", empVO);         // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front-end/purchase-order/update-order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/purchase-order/update-order.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			ProductVO productVO = new ProductVO();
			PurchaseOrderVO purchaseOrderVO = new PurchaseOrderVO();
			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				////
				String productNo = req.getParameter("productNo");

				String poPayment = req.getParameter("poPayment");
				System.out.println("poPayment : " + poPayment);

				if (poPayment.equals("�п�ܥI�ڤ覡")) {
					errorMsgs.add("�п�ܥI�ڤ覡");
				}
				String memId = req.getParameter("memId");
				String buyerName = req.getParameter("buyerName");
				if (buyerName == null || buyerName.trim().length() == 0) {
					errorMsgs.add("�q�ʤH�m�W: �ФŪť�");
				}
				String buyerPhone = req.getParameter("buyerPhone");
				if (buyerPhone == null || buyerPhone.trim().length() == 0) {
					errorMsgs.add("�q�ʤH�q��: �ФŪť�");
				}

				String poDelivery = req.getParameter("poDelivery");

				if (poDelivery.equals("�п�ܱH�e�覡")) {
					errorMsgs.add("�п�ܱH�e�覡");
				}

				String deliveryAddress = req.getParameter("deliveryAddress");
				System.out.println("deliveryAddress :�@" + deliveryAddress);

				try {

					if (poDelivery.indexOf("10") != -1) {
						if (deliveryAddress.trim().length() == 0) {
							errorMsgs.add("�п�J�a�}");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				Double pStock = Double.parseDouble(req.getParameter("pStock"));
				System.out.println("�w�s��ܤ�! : " + pStock);
				Double quantity = Double.parseDouble(req.getParameter("qty"));
				System.out.println("quantity : " + quantity);
				Double afterBuyStock = pStock - quantity;

				String poStatus = "10"; // �q���U�w�]��10-�ݥI�� �p�G�����f�I�ګh�O����30-�ݥX�f
				String poNo = req.getParameter("poNo");

				if (poPayment.equals("30")) {
					poStatus = "30";
				}

				String purchaseDetail = null;

				purchaseDetail = req.getParameter("purchaseDetail");
				System.out.println("purchaseDetail : " + purchaseDetail);

				purchaseOrderVO = new PurchaseOrderVO();
				purchaseOrderVO.setPoPayment(poPayment);
				purchaseOrderVO.setPoDelivery(poDelivery);
				purchaseOrderVO.setDeliveryAddress(deliveryAddress);
				purchaseOrderVO.setQuantity(quantity);
				purchaseOrderVO.setProductNo(productNo);
				purchaseOrderVO.setPoStatus(poStatus);
				purchaseOrderVO.setPurchaseDetail(purchaseDetail);
				purchaseOrderVO.setBuyerName(buyerName);
				purchaseOrderVO.setBuyerPhone(buyerPhone);
				purchaseOrderVO.setPoNo(poNo);
				System.out.println(poPayment);
				System.out.println(poDelivery);
				System.out.println(deliveryAddress);
				System.out.println(quantity);
				System.out.println(productNo);
				System.out.println(purchaseDetail);

				PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
				ProductService productService = new ProductService();
				if (!errorMsgs.isEmpty()) {
					productVO = productService.getOneProduct(productNo);
					req.setAttribute("productVO", productVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					req.setAttribute("purchaseOrderVO", purchaseOrderVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/purchase-order/add-order.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				purchaseOrderService = new PurchaseOrderService();
				productService = new ProductService();

				productVO = productService.afterBuyStock(productNo, afterBuyStock);// �B�z�q��w�s �`��-�ʶR�ƶq
				purchaseOrderVO = purchaseOrderService.updateOrder(purchaseDetail, poPayment, buyerName, buyerPhone,
						poDelivery, deliveryAddress, quantity, poStatus, poNo);
				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				String url = "/front-end/purchase-order/list-order-by-memId-buyer.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				req.setAttribute("purchaseOrderVO", purchaseOrderVO);
				req.setAttribute("productVO", productVO); // �t����J�榡���~��empVO����,�]�s�Jreq
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/purchase_order/update_order.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOneForOrder".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/

				String productNo = req.getParameter("productNo");
				System.out.println("************" + productNo + "*********************");

				/*************************** 2.�}�l�d�߸�� ****************************************/
				ProductService productService = new ProductService();
				ProductVO productVO = productService.getOneProduct(productNo);
				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("productVO", productVO);

				String url = "/front-end/purchase-order/add-order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� addOrder.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/product/listAllProduct.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			ProductVO productVO = new ProductVO();
			PurchaseOrderVO purchaseOrderVO = new PurchaseOrderVO();
			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
				////

				String productNo = req.getParameter("productNo");

				String poPayment = req.getParameter("poPayment");
				System.out.println("poPayment : " + poPayment);

				if (poPayment.equals("�п�ܥI�ڤ覡")) {
					errorMsgs.add("�п�ܥI�ڤ覡");
				}
				String memId = req.getParameter("memId");
				System.out.println("memId: " + memId);

				String buyerName = req.getParameter("buyerName");
				if (buyerName == null || buyerName.trim().length() == 0) {
					errorMsgs.add("�q�ʤH�m�W: �ФŪť�");
				}
				String buyerPhone = req.getParameter("buyerPhone");
				if (buyerPhone == null || buyerPhone.trim().length() == 0) {
					errorMsgs.add("�q�ʤH�q��: �ФŪť�");
				}

				String poDelivery = req.getParameter("poDelivery");

				if (poDelivery.equals("�п�ܱH�e�覡")) {
					errorMsgs.add("�п�ܱH�e�覡");
				}

				String deliveryAddress = req.getParameter("deliveryAddress");
				System.out.println("deliveryAddress :�@" + deliveryAddress);

				try {

					if (poDelivery.indexOf("10") != -1) {
						if (deliveryAddress.trim().length() == 0) {
							errorMsgs.add("�п�J�a�}");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				Double pStock = Double.parseDouble(req.getParameter("pStock"));
				System.out.println("�w�s��ܤ�! : " + pStock);
				Double quantity = Double.parseDouble(req.getParameter("qty"));
				System.out.println("quantity : " + quantity);
				Double afterBuyStock = pStock - quantity;

				String poStatus = "10"; // �q���U�w�]��10-�ݥI�� �p�G�����f�I�ګh�O����30-�ݥX�f
				if (poPayment.equals("30")) {
					poStatus = "30";
				}

				String purchaseDetail = null;

				purchaseDetail = req.getParameter("purchaseDetail");
				System.out.println("purchaseDetail : " + purchaseDetail);
				String pName = req.getParameter("pName");
				////
//				productVO = new ProductVO();
//				productVO.setpStock(afterBuyStock);
//				productVO.setProductNo(productNo);

				purchaseOrderVO = new PurchaseOrderVO();
				purchaseOrderVO.setPoPayment(poPayment);
				purchaseOrderVO.setPoDelivery(poDelivery);
				purchaseOrderVO.setDeliveryAddress(deliveryAddress);
				purchaseOrderVO.setQuantity(quantity);
				purchaseOrderVO.setProductNo(productNo);
				purchaseOrderVO.setPoStatus(poStatus);
				purchaseOrderVO.setPurchaseDetail(purchaseDetail);
				purchaseOrderVO.setBuyerName(buyerName);
				purchaseOrderVO.setBuyerPhone(buyerPhone);
//				purchaseOrderVO.setCreditCardNumber(creditCardNumber);
//				purchaseOrderVO.setLastThreeNumber(lastThreeNumber);

				// ���հϰ�//���հϰ�//���հϰ�//���հϰ�//���հϰ�//���հϰ�
				System.out.println(poPayment);
				System.out.println(poDelivery);
				System.out.println(deliveryAddress);
				System.out.println(quantity);
				System.out.println(productNo);
				System.out.println(purchaseDetail);
//				System.out.println(creditCardNumber);
//				System.out.println(lastThreeNumber);
				// ���հϰ�//���հϰ�//���հϰ�//���հϰ�//���հϰ�//���հϰ�

				PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
				ProductService productService = new ProductService();
				// Send the use back to the form, if there were errors

				// �U�q��-�H�H�q��
//				MailService mailservice = new MailService();
//				String subject = "INGYM�ӫ�-�ӫ~:" + pName + "�w��X";
//				String messageText ="��a�s��:" +  memIdSeller + "�ӫ~:" + pName + "�w��X!" + "�R�a�m�W:" + buyerName + "�A�нT�{�X�f�C";
//				mailservice.sendMail("wedy5678@gmail.com", subject, messageText);

				if (!errorMsgs.isEmpty()) {
//					purchaseOrderVO = purchaseOrderService.updateQuantity(quantity);
//					req.setAttribute("purchaseOrderVO", purchaseOrderVO);
					productVO = productService.getOneProduct(productNo);
					req.setAttribute("productVO", productVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					req.setAttribute("purchaseOrderVO", purchaseOrderVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/purchase-order/add-order.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				purchaseOrderService = new PurchaseOrderService();
				productService = new ProductService();

				// ���հϰ�//���հϰ�//���հϰ�//���հϰ�//���հϰ�//���հϰ�
				System.out.println("�ӫ~�s��: " + productNo);
				System.out.println("�ʶR��w�s: " + afterBuyStock);

				// ���հϰ�//���հϰ�//���հϰ�//���հϰ�//���հϰ�//���հϰ�
				productVO = productService.afterBuyStock(productNo, afterBuyStock);// �B�z�q��w�s �`��-�ʶR�ƶq
				purchaseOrderVO = purchaseOrderService.addOrder(poPayment, poDelivery, deliveryAddress, quantity,
						productNo, poStatus, purchaseDetail, buyerName, buyerPhone, memId);

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/

				String url = "/front-end/purchase-order/list-order-by-memId-buyer.jsp";
				System.out.println(url);
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/

			} catch (Exception e) {
				req.setAttribute("purchaseOrderVO", purchaseOrderVO);
				req.setAttribute("productVO", productVO);
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/purchase-order/add-order.jsp");
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
				String poNo = req.getParameter("poNo");
				System.out.println(poNo);
				/*************************** 2.�}�l�R����� ***************************************/
				PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
				purchaseOrderService.deleteProduct(poNo);
				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
				String url = "/purchase_order/listAllOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/purchase_order/listAllOrder.jsp");
				failureView.forward(req, res);
			}
		}

		if ("sendEmail".equals(action)) {
			try {
				MemService memService = new MemService();

				String buyerName = null;
				try {
					buyerName = req.getParameter("buyerName");
				} catch (Exception e) {
					System.out.println("buyerName��null");
				}
				String pName = req.getParameter("pName");
				String memAcc = null;

				String memIdBuyer = null;
				try {
					memIdBuyer = req.getParameter("memIdBuyer");
					MemVO memVO = memService.getOneMem(memIdBuyer);
					memAcc = memVO.getMem_email();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("PurchaseOrderServlet.java:684");
				}

				String memIdSeller = null;
				try {
					memIdSeller = req.getParameter("memIdSeller");
					MemVO memVO = memService.getOneMem(memIdSeller);
					memAcc = memVO.getMem_email();
				} catch (Exception e) {
					System.out.println("memIdSeller��null");
				}

				System.out.println("sendEmail�w����");

				MailService mailservice = new MailService();
				if (buyerName == null && memIdSeller == null && memIdBuyer != null) {
					System.out.println("�ӫ~�w�H�X-�H�ǰe�ܶR�a�b��: " + memAcc);
					String subject = "INGYM�ӫ�-�ӫ~:" + pName + "�w�X�f";
					String messageText_selled = "INGYM�ӫ�-�ӫ~:" + pName + "�w�X�f" + "\n" + "�нЯd�N���f";
					mailservice.sendMail(memAcc, subject, messageText_selled);
				} else {
					String subject = "INGYM�ӫ�-�ӫ~:" + pName + "�w��X";
					String messageText_selled = "��a�b��:" + memAcc + "\n" + "�ӫ~:" + pName + " �w��X" + "\n" + "�R�a�m�W:"
							+ buyerName + "\n" + "�нT�{�X�f";
					System.out.println("�H�󥿦b�ǰe��: " + memAcc);
					mailservice.sendMail(memAcc, subject, messageText_selled);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
