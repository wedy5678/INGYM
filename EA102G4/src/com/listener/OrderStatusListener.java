package com.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class OrderStatusListener implements ServletContextListener{
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();		
		Map<String,String> poStatusListener = new HashMap<>();
//		10--�B�z�� 20--�q��w�T�{ 30--�f���B�e�� 40--�w�e�F 50--�w���f 60--�w���� 70--�w����
//		poStatusListener.put("10", "�B�z��");
//		poStatusListener.put("20", "�q��w�T�{");
//		poStatusListener.put("30", "�f���B�e��");
//		poStatusListener.put("40", "�w�e�F");
//		poStatusListener.put("50", "�w���f");
//		poStatusListener.put("60", "�w����");
//		poStatusListener.put("70", "�w����");
//		context.setAttribute("poStatusListener", poStatusListener);
		
		Map<String,String> poPaymentListener = new HashMap<>();
		poPaymentListener.put("10", "�H�Υd");
		poPaymentListener.put("20", "��b�I��");
		poPaymentListener.put("30", "���f�I��");
		context.setAttribute("poPaymentListener", poPaymentListener);
		
		Map<String,String> poDeliveryListener = new HashMap<>();
		poDeliveryListener.put("10", "�v�t�쩲");
		poDeliveryListener.put("20", "���f�I��");
		context.setAttribute("poDeliveryListener", poDeliveryListener);
		
		Map<String,String> listProductByMemId = new HashMap<>();
		listProductByMemId.put("10", "�W�[��");
		listProductByMemId.put("20", "�w�U�[");
		context.setAttribute("listProductByMemId", listProductByMemId);
		
		Map<String,String> OrderStatusListener = new HashMap<>();
		OrderStatusListener.put("10", "�ݥI��");
//		OrderStatusListener.put("20", "�ݥI��");
		OrderStatusListener.put("30", "�ݥX�f");
		OrderStatusListener.put("40", "�w�X�f");
		OrderStatusListener.put("50", "�w�e�F");
//		OrderStatusListener.put("60", "�w���f");
		OrderStatusListener.put("70", "�q��w����");
		OrderStatusListener.put("711", "���-���X�ק�q�椤");
		OrderStatusListener.put("712", "�R��-���X�ק�q�椤");
		OrderStatusListener.put("721", "���-�q��ק襤");
		OrderStatusListener.put("722", "�R��-�q��ק襤");
		OrderStatusListener.put("731", "���-���X�������");
		OrderStatusListener.put("732", "�R��-���X�������");
		OrderStatusListener.put("80", "�������");//���襼����
		OrderStatusListener.put("81", "�������");//��a�w����
		OrderStatusListener.put("82", "�������");//�R�a�w����
		OrderStatusListener.put("83", "�������");//����ҵ���
	
		context.setAttribute("OrderStatusListener", OrderStatusListener);
	}
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}
}