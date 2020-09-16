package com.product_tracking.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.closeHandle.CloseHandle;

public class ProductTrackingJDBCDAO implements ProductTrackingDAO_interface {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USERID = "INGYM";
	private static final String PASSWD = "123456";

	private static final String INSERT_STMT = "INSERT INTO  PRODUCT_TRACKING VALUES(?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM PRODUCT_TRACKING";
	private static final String GET_ALL_STMT_BY_MEMID = "SELECT * FROM PRODUCT_TRACKING WHERE MEM_ID=?";
	private static final String DELETE = "DELETE FROM PRODUCT_TRACKING WHERE MEM_ID=? AND PRODUCT_NO=?";

	CloseHandle closeHandle = new CloseHandle();

	@Override
	public void insert(ProductTrackingVO productTrackingVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, productTrackingVO.getMemId());
			pstmt.setString(2, productTrackingVO.getProductNo());

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeHandle.close(pstmt, con);
		}

	}

	@Override
	public void delete(ProductTrackingVO productTrackingVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, productTrackingVO.getMemId());
			pstmt.setString(2, productTrackingVO.getProductNo());

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeHandle.close(pstmt, con);
		}
	}

	@Override
	public List<ProductTrackingVO> getAll() {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ProductTrackingVO productTrackingVO = null;
		List<ProductTrackingVO> list = new ArrayList<ProductTrackingVO>();
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				productTrackingVO = new ProductTrackingVO();
				productTrackingVO.setMemId(rs.getString("MEM_ID"));
				productTrackingVO.setProductNo(rs.getString("PRODUCT_NO"));
				list.add(productTrackingVO);
			}

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeHandle.close(rs, pstmt, con);
		}
		return list;
	}

	
	 public List<ProductTrackingVO> getAllByMemId(String memId){
			// TODO Auto-generated method stub

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			ProductTrackingVO productTrackingVO = null;
			List<ProductTrackingVO> list = new ArrayList<ProductTrackingVO>();
			try {
				Class.forName(DRIVER);
				con = DriverManager.getConnection(URL, USERID, PASSWD);
				pstmt = con.prepareStatement(GET_ALL_STMT_BY_MEMID);
				pstmt.setString(1, memId);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					productTrackingVO = new ProductTrackingVO();
					productTrackingVO.setMemId(rs.getString("MEM_ID"));
					productTrackingVO.setProductNo(rs.getString("PRODUCT_NO"));
					list.add(productTrackingVO);
				}

				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeHandle.close(rs, pstmt, con);
			}
			return list;
	 }
	 
	 
	public static void main(String[] args) {
		ProductTrackingJDBCDAO dao = new ProductTrackingJDBCDAO();

		// �s�W�l��
//		ProductTrackingVO productTrackingVO = new ProductTrackingVO();
//		productTrackingVO.setMemId("MEM0000001");
//		productTrackingVO.setProductNo("P000001");
//		dao.insert(productTrackingVO);
//		System.out.println("�s�W�l��-���\");
		// �R���l��
//		ProductTrackingVO productTrackingVO2 = new ProductTrackingVO();
//		productTrackingVO2.setMemId("MEM0000002");
//		productTrackingVO2.setProductNo("P000001");
//		dao.delete(productTrackingVO2);
//		System.out.println("�R���l��-���\");
		// �d�ߥ����l��
//		List<ProductTrackingVO> list = dao.getAll();
//		for (ProductTrackingVO aProductTrackingVO : list) {
//			System.out.println("--------------------------------");
//			System.out.println(aProductTrackingVO.getMemId());
//			System.out.println(aProductTrackingVO.getProductNo());
//		}
//		System.out.println("�d�ߥ����l��-���\");
		// �d�ߥ����ھڷ|���s��
		List<ProductTrackingVO> list2 = dao.getAllByMemId("MEM0000002");
		for (ProductTrackingVO aProductTrackingVO : list2) {
			System.out.println("--------------------------------");
			System.out.println(aProductTrackingVO.getMemId());
			System.out.println(aProductTrackingVO.getProductNo());
		}
		System.out.println("�d�ߥ����ھڷ|���s��-���\");
	}
}
