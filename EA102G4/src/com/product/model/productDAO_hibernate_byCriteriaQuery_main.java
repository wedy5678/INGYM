package com.product.model;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query; //Hibernate 5.2 開始 取代原 org.hibernate.Query 介面

import hibernate.util.HibernateUtil;
import java.util.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public class productDAO_hibernate_byCriteriaQuery_main  {

	public static void main(String[] args) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		try {
			//給查詢用的
//			 CriteriaBuilder builder = session.getCriteriaBuilder();
//			 CriteriaQuery<ProductVO> criteriaQuery = builder.createQuery(ProductVO.class);
//			 Root<ProductVO> root = criteriaQuery.from(ProductVO.class);
			
			//insert
			ProductVO productVO = new ProductVO();
			productVO.setpName("hiber商品1");
			productVO.setMemId("MEM0000001");
			
			session.saveOrUpdate(productVO);
			
			
			tx.commit();
		} catch (RuntimeException ex) {
			if (tx != null)
				tx.rollback();
			throw ex; //System.out.println(ex.getMessage());
		} finally {
			session.close();
			HibernateUtil.getSessionFactory().close();
		}
	}
}
