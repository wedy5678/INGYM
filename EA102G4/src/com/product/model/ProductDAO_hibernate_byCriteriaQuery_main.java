package com.product.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.product_photo.model.PhotoVO;

import hibernate.util.HibernateUtil;


public class ProductDAO_hibernate_byCriteriaQuery_main  {
	
	public void insertProductAndPhoto(ProductVO productVO,List<PhotoVO> list) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(productVO);
			
			String key =(String) session.getIdentifier(productVO);
			System.out.println("getIdentifier: " + key);
			
			for(PhotoVO aPhotoVO : list) {
				PhotoVO photoVO = new PhotoVO();
				photoVO.setProductNo(key);
				photoVO.setpPhoto(aPhotoVO.getpPhoto());
				System.out.println(key + "一張圖片insert");
				session.saveOrUpdate(photoVO);
			}
			
			tx.commit();
		} catch (RuntimeException ex) {
			if (tx != null)
				tx.rollback();
			throw ex; //System.out.println(ex.getMessage());
		} 
		
	}


	public static void main(String[] args) {
		ProductDAO_hibernate_byCriteriaQuery_main dao = new ProductDAO_hibernate_byCriteriaQuery_main();
		
		List<PhotoVO> list = new ArrayList<PhotoVO>();
		list.add(new PhotoVO());
		list.add(new PhotoVO());
		list.add(new PhotoVO());
		ProductVO productVO = new ProductVO();
		productVO.setpName("測試h商品");
		productVO.setMemId("MEM0000001");
		dao.insertProductAndPhoto(productVO, list);
		System.out.println("hibernate-新增成功");
	}
}
