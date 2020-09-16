package com.coin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.*;
import javax.servlet.http.*;

import com.coin.model.*;
import com.mem.model.MemService;
import com.mem.model.MemVO;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutOneTime;


public class CoinServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static AllInOne all;
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("coin_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�I�ƭq��s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/coin/select_coin_order.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
					
				String coin_id = null;				
					coin_id = str.trim();				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/coin/select_coin_order.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				CoinService coinSvc = new CoinService();
				CoinVO coinVO = coinSvc.getOneCoinOreder(coin_id);
				if (coinVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/coin/select_coin_order.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("coinVO", coinVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front-end/coin/listOneCoinOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);
				return;
				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/coin/select_coin_order.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getMem_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("mem_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�|���s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/coin/select_coin_order.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
					
				String mem_id = null;				
					mem_id = str.trim();				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/coin/select_coin_order.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				CoinService coinSvc = new CoinService();
				List<CoinVO> listMem = coinSvc.getMemCoinOrder(mem_id);
				if (listMem == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/coin/select_coin_order.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("listMem", listMem); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front-end/coin/listMemCoinOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/coin/select_coin_order.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String coin_id = req.getParameter("coin_id").trim();
				
				/***************************2.�}�l�d�߸��****************************************/
				CoinService coinSvc = new CoinService();
				CoinVO coinVO = coinSvc.getOneCoinOreder(coin_id);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("coinVO", coinVO);         // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front-end/coin/update_coin_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			}catch (NullPointerException ne){
				errorMsgs.add("�q��s�����o���� 3064:" + ne.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/coin/listAllCoinOrder.jsp");
				failureView.forward(req, res);
			}
			catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/coin/listAllCoinOrder.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String coin_id = req.getParameter("coin_id").trim();
				Integer amount=null;
				Integer deposit_coin=null;
				try {
					amount = new Integer(req.getParameter("amount").trim()); 
				}catch (NumberFormatException e) {
	            	errorMsgs.add("�x�Ȫ��B: �п�J�Ʀr");
	            }
				if (amount == null) {
					errorMsgs.add("�x�Ȫ��B: �ФŪť�");
				}else if(amount>999999||amount<100) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�x�Ȫ��B: �u��b100~999999");
	            }
				
				try {
					deposit_coin = new Integer(req.getParameter("deposit_coin").trim());
				}catch(NumberFormatException e){
					errorMsgs.add("�x���I��: �п�J�Ʀr");
				}
				if (deposit_coin == null) {
					errorMsgs.add("�x���I��: �ФŪť�");
				} else if(amount>999999||amount<=100) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�x���I��: �u��b100~999999");
	            }
//				if(deposit_coin!=amount)
//					errorMsgs.add("�x���I�ƻP�x�Ȫ��B���ŦX");
				CoinVO coinVO = new CoinVO();
				coinVO.setAmount(amount);
				coinVO.setDeposit_coin(deposit_coin);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println(1);
					req.setAttribute("coinVO", coinVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/coin/update_coin_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				CoinService coinSvc = new CoinService();
				coinVO = coinSvc.updateCoinOrder(deposit_coin, amount, coin_id,1);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("coinVO", coinVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				System.out.println(2);
				String url = "/front-end/coin/showReqOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/coin/update_coin_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			HttpSession session = req.getSession();
			CoinVO coinVO = new CoinVO();
			try {
				MemVO memVO = null;
				String mem_id =null;
				
				memVO = (MemVO)session.getAttribute("memVOLogin");
				if(memVO==null) {
					throw new RuntimeException("memVO=null");
				}
				mem_id = memVO.getMem_id();
				if(mem_id==null) {
					throw new RuntimeException("mem_id=null");
				}
				
				Integer amount=null;
				try {	
					amount = new Integer(req.getParameter("amount").trim());
				}catch(NumberFormatException e){
					errorMsgs.add("�п�J�Ʀr");
				}
				if (amount>=150000||amount<100)
					errorMsgs.add("�x�Ȫ��B�u��b100~150000����");
				Integer deposit_coin = amount;
				
				
				
				coinVO.setMem_id(mem_id);
				coinVO.setDeposit_coin(deposit_coin);
				coinVO.setAmount(amount);
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("coinVO", coinVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/coin/addCoinOrder.jsp");
					failureView.forward(req, res);
					return;
				}
//				
				CoinService coinSvc = new CoinService();
				coinVO = coinSvc.addCoinOrder(mem_id, deposit_coin, amount);
				/******************************�ͦ�ECPAY�q�檫��****************************************/
				initial();
				AioCheckOutOneTime  obj = new AioCheckOutOneTime();
				SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
				Date date = new Date();
				String strDate = sdFormat.format(date);
//				AioCheckOutOneTime obj = new AioCheckOutOneTime();
				SimpleDateFormat sdfMMdd = new SimpleDateFormat("MMddss");
				System.out.println(req.getContextPath());
				
				obj.setMerchantTradeNo(coinVO.getCoin_id()+sdfMMdd.format(date));

				res.setContentType("text/html; charset=utf-8");
			    res.setCharacterEncoding("utf-8");
				obj.setMerchantTradeDate(strDate);
				obj.setTotalAmount(amount.toString());
				obj.setTradeDesc("detail");
				obj.setItemName("INGYM�I��  " + deposit_coin +"   �I");
//				obj.setItemName("INGYM"+ deposit_coin);
				obj.setNeedExtraPaidInfo("N");
				obj.setRedeem("Y");
				obj.setReturnURL(
						req.getRequestURL()
						+ "?ResultCoin_id="+coinVO.getCoin_id()
						+"&mem_id="+memVO.getMem_id()
						);
//
//								
//								obj.setClientBackURL(req.getRequestURL()
//										+ "?action=getOne_For_Display&coin_id="+coinVO.getCoin_id());  //�Ȥ�ݸ���   �O�o�]�w  
//				
								obj.setClientBackURL(	req.getRequestURL()
										+ "?ResultCoin_id="+coinVO.getCoin_id()
										+"&mem_id="+memVO.getMem_id());   //�S��ssl�� ���Ȥ�ݦۤvĲ�o��s
				String form = all.aioCheckOut(obj, null);
				PrintWriter out = res.getWriter();
				 out.println("<!DOCTYPE html>");
			        out.println("<html>");
			        out.println("<body>");
			        out.println(form);
			        out.println("</ul>");
			        out.println("</body>");
			        out.println("</html>");
//				String url = "/front-end/coin/showReqOrder.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
//				successView.forward(req, res);				
				
			} catch (RuntimeException e) { 
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/index.jsp");
				failureView.forward(req, res);
				return;
			}catch (Exception e) {
				req.setAttribute("coinVO", coinVO); // �t����J�榡���~��empVO����,�]�s�Jreq
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/coin/addCoinOrder.jsp");
				failureView.forward(req, res);
				return;
			}
		}
//        String payResult = req.getParameter("RtnMsg");
//		if(payResult!=null) {
//			if(payResult.equals("������\")){
//				System.out.println(payResult);
//				Integer numResult = null;
//				try {
//				numResult = new Integer(req.getParameter("RtnCode"));
//				}catch(NumberFormatException ne){
//					System.out.println("numResult/RtnCode"+"  �^�ǭ��૬����");
//				}
//				
//				if(numResult==1) {
				String coin_id=  req.getParameter("ResultCoin_id");
				if(coin_id!=null) {
				String mem_id = req.getParameter("mem_id");
				CoinService coSvc = new CoinService();
				MemService memSvc = new MemService();
				CoinVO coinVO =coSvc.getOneCoinOreder(coin_id);
				MemVO memVO = memSvc.getOneMem(mem_id);
				System.out.println(memVO.getCoin()+"�ק�e");
				int deposit_coin =coinVO.getDeposit_coin();
				int memCoin = memVO.getCoin()+deposit_coin;
				if(coinVO.getCo_status()!=1) {
				coSvc.updateCoinOrder(coinVO.getDeposit_coin(),coinVO.getAmount(), coinVO.getCoin_id(),1);
				memVO = memSvc.updateMem(memVO.getMem_name(), memVO.getMem_psw(),memVO.getMem_bir(), memVO.getSex()
						, memVO.getMem_addr(),memVO.getMem_email(),memVO.getMem_phone(), memVO.getMem_absent(),
						memCoin, memVO.getMem_resume(),memVO.getM_reg_date(),memVO.getSel_auth(),
						memVO.getArt_auth(),memVO.getCom_auth(), memVO.getMem_id());
				System.out.println(memVO.getCoin()+"�ק��");
				}
				RequestDispatcher sucView = req
						.getRequestDispatcher("/front-end/coin/listMemCoinOrder.jsp");
				sucView.forward(req, res);
				}
//				}else if(numResult!=0) {
//					System.out.println("���~���,�N�X��:"+numResult);
//				}
				
				
//			}
//		}
		
	}

	private static void initial(){
		all = new AllInOne("");
	}
	public static String genAioCheckOutOneTime(){
		AioCheckOutOneTime obj = new AioCheckOutOneTime();
		obj.setMerchantTradeNo("INGYM0008");
		obj.setMerchantTradeDate("2017/01/01 08:05:23");
		obj.setTotalAmount("50");
		obj.setTradeDesc("test Description");
		obj.setItemName("TestItem");
		obj.setReturnURL("http://211.23.128.214:5000");
		obj.setNeedExtraPaidInfo("N");
		obj.setRedeem("Y");
		String form = all.aioCheckOut(obj, null);
		return form;
	}

}

