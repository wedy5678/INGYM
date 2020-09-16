package com.product_tracking.model;

import java.util.List;

public class ProductTrackingService {

	private ProductTrackingDAO_interface dao;

	public ProductTrackingService() {
		dao = new ProductTrackingJDBCDAO();
	}

	public void addProductTracking(String memId, String productNo) {
		ProductTrackingVO productTrackingVO = new ProductTrackingVO();
		productTrackingVO.setMemId(memId);
		productTrackingVO.setProductNo(productNo);
		dao.insert(productTrackingVO);
		System.out.println("�s�W�l��-���\");
	}

	public List<ProductTrackingVO> getAllProductTracking() {
		System.out.println("�d�ߥ����l��-���\");
		return dao.getAll();
	}

	public List<ProductTrackingVO> getAllProductTrackingByMemId(String memId) {
		System.out.println("�d�ߥ����ھڷ|���s��-���\");
		return dao.getAllByMemId(memId);
	}

	public void deleteProductTracking(String memId, String productNo) {
		ProductTrackingVO productTrackingVO = new ProductTrackingVO();
		productTrackingVO.setMemId(memId);
		productTrackingVO.setProductNo(productNo);
		dao.delete(productTrackingVO);
		System.out.println("�R���l��-���\");
	}

}
