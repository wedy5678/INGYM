package com.product_category.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.product.model.ProductVO;

public class Product_categoryJDBCDAO implements Product_categoryDAO_interface {
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USERID = "INGYM";
	private static final String PASSWD = "123456";

	private static final String INSERT_STMT = "INSERT INTO PRODUCT_CATEGORY VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM PRODUCT_CATEGORY";
	private static final String GET_ONE_STMT = "SELECT * FROM PRODUCT_CATEGORY WHERE CATEGORY_NO=?";
//		private static final String DELETE_FROM_FRONT = 
//			"UPDATE PRODUCT SET P_STATUS=? WHERE PRODUCT_NO=?";
	private static final String UPDATE = "UPDATE PRODUCT_CATEGORY SET CATEGORY_NAME=? WHERE CATEGORY_NO = ?";
	private static final String GET_Emps_ByDeptno_STMT = "SELECT * FROM PRODUCT where CATEGORY_NO = ? ";
//	private static final String GET_Emps_ByDeptno_STMT = "SELECT * FROM PRODUCT where CATEGORY_NO = ? order by PRODUCT_NO";

	@Override
	public void insert(Product_categoryVO product_categoryVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			System.out.println("���J���\");
			con = DriverManager.getConnection(url, USERID, PASSWD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, product_categoryVO.getCategoryName());
			pstmt.setString(2, product_categoryVO.getCategoryNo());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(Product_categoryVO product_categoryVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, USERID, PASSWD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, product_categoryVO.getCategoryNo());
			pstmt.setString(2, product_categoryVO.getCategoryName());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
//		public void deleteFromFront(Product_categoryVO product_categoryVO) {
//
//			Connection con = null;
//			PreparedStatement pstmt = null;
//
//			try {
//
//				Class.forName(driver);
//				con = DriverManager.getConnection(url, userid, passwd);
//				pstmt = con.prepareStatement(DELETE_FROM_FRONT);
//				
//				pstmt.setString(1, product_categoryVO.getP_status());
//				pstmt.setString(2, product_categoryVO.getProduct_no());
//
//				pstmt.executeUpdate();
//
//				// Handle any driver errors
//			} catch (ClassNotFoundException e) {
//				throw new RuntimeException("Couldn't load database driver. "
//						+ e.getMessage());
//				// Handle any SQL errors
//			} catch (SQLException se) {
//				throw new RuntimeException("A database error occured. "
//						+ se.getMessage());
//				// Clean up JDBC resources
//			} finally {
//				if (pstmt != null) {
//					try {
//						pstmt.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//				if (con != null) {
//					try {
//						con.close();
//					} catch (Exception e) {
//						e.printStackTrace(System.err);
//					}
//				}
//			}
//
//		}

	public Product_categoryVO findByPrimaryKey(String category_no) {

		Product_categoryVO product_categoryVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, USERID, PASSWD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, category_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				product_categoryVO = new Product_categoryVO();
				product_categoryVO.setCategoryNo(rs.getString("CATEGORY_NO"));
				product_categoryVO.setCategoryName(rs.getString("CATEGORY_NAME"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return product_categoryVO;
	}

//		@Override
	public List<Product_categoryVO> getAll() {
		List<Product_categoryVO> list = new ArrayList<Product_categoryVO>();
		Product_categoryVO product_categoryVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);

			con = DriverManager.getConnection(url, USERID, PASSWD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVO �]�٬� Domain objects
				product_categoryVO = new Product_categoryVO();
				product_categoryVO.setCategoryNo(rs.getString("CATEGORY_NO"));
				product_categoryVO.setCategoryName(rs.getString("CATEGORY_NAME"));

				list.add(product_categoryVO); // Store the row in the list
//					System.out.println("while");
			}
			System.out.println("���J���\");
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public Set<ProductVO> getProductsByCategory(String category_no) {
		Set<ProductVO> set = new LinkedHashSet<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, USERID, PASSWD);
			pstmt = con.prepareStatement(GET_Emps_ByDeptno_STMT);

			pstmt.setString(1, category_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				productVO = new ProductVO();
				productVO.setProductNo(rs.getString("PRODUCT_NO"));
				productVO.setpName(rs.getString("P_NAME"));
				productVO.setMemId(rs.getString("MEM_ID"));
				productVO.setCategoryNo(rs.getString("CATEGORY_NO"));
				productVO.setpPrice(rs.getDouble("P_PRICE"));
				productVO.setpStock(rs.getDouble("P_STOCK"));
				productVO.setpDetail(rs.getString("P_DETAIL"));
				productVO.setpUploadTime(rs.getTimestamp("P_UPLOAD_TIME"));
				productVO.setpRating(rs.getDouble("P_RATING"));
				productVO.setpStatus(rs.getString("P_STATUS"));
				set.add(productVO); // Store the row in the list
//				System.out.println("while");
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return set;
	}

	public static void main(String[] args) {
		Product_categoryJDBCDAO dao = new Product_categoryJDBCDAO();
//		 �ק�
//			Product_categoryVO product_categoryVO2 = new Product_categoryVO();
//			product_categoryVO2.setCategory_no("C003");;
//			product_categoryVO2.setCategory_name("�ק����O");;
//
//			dao.update(product_categoryVO2);
//			System.out.println("�ק令�\");
		// �s�W
//		Product_categoryVO productVO1 = new Product_categoryVO();
//		productVO1.setCategory_name("��������");
//
//		dao.insert(productVO1);
//////			��ܷs�W�ӫ~���s��
//		List<Product_categoryVO> list = dao.getAll();// ��d�ߥ����@��
//		String newestProductNum = null;
//		Iterator<Product_categoryVO> iterator = list.iterator();
//		while (iterator.hasNext()) {
//			newestProductNum = iterator.next().getCategory_no();
//		}
//		System.out.println("�s�W���\!!�����s���� : " + newestProductNum);
////			��ܷs�W�ӫ~���s��

//			// �d��
//		Product_categoryVO product_categoryVO4 = dao.findByPrimaryKey("C004");
//		System.out.println("--------------------------------");
//		System.out.println("���O�s�� : " + "\t" + product_categoryVO4.getCategoryName());
//		System.out.println("���O�W�� : " + "\t" + product_categoryVO4.getCategoryNo());
//		System.out.println("--------------------------------");
		//
//			// �d�ߥ���

//				System.out.println("List�إߦ��\");

//		for (Product_categoryVO aProduct_category : list) {
//			System.out.println("--------------------------------");
//			System.out.println("���O�s�� : " + "\t" + aProduct_category.getCategory_no());
//			System.out.println("���O�W�� : " + "\t" + aProduct_category.getCategory_name());
//
//		}
		// �d�߬Y���O���ӫ~
		String category = "C001";
		System.out.println("�H�U�����O�s��" + category + "���Ҧ��ӫ~");
		Set<ProductVO> set = dao.getProductsByCategory(category);
		for (ProductVO aProduct : set) {
			Timestamp time = aProduct.getpUploadTime();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String timeStr = df.format(time);
			System.out.println("--------------------------------");
			System.out.println("�ӫ~�s�� : " + "\t" + aProduct.getProductNo());
			System.out.println("�ӫ~�W�� : " + "\t" + aProduct.getpName());
			System.out.println("�|���s�� : " + "\t" + aProduct.getMemId());
			System.out.println("���O�s�� : " + "\t" + aProduct.getCategoryNo());
			System.out.println("��� : " + "\t" + aProduct.getpPrice());
			System.out.println("�w�s : " + "\t" + aProduct.getpStock());
			System.out.println("�ӫ~�Ա� : " + "\t" + aProduct.getpDetail());
			System.out.println("�W�[��� : " + "\t" + timeStr);
			System.out.println("�`���� : " + "\t" + aProduct.getpRating());
			System.out.println("�����H�� : " + "\t" + aProduct.getNumberOfRating());
			System.out.println("���A : " + "\t" + aProduct.getpStatus());
			System.out.println();
		}
		System.out.println("--------------------------------");
		System.out.println("��ܦ��\");
	}
}
