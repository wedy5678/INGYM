package com.product.model;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.closeHandle.CloseHandle;
import com.product_photo.model.PhotoVO;

public class ProductJDBCDAO implements ProductDAO_interface {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USERID = "INGYM";
	private static final String PASSWD = "123456";


	private static final String INSERT_STMT = "INSERT INTO PRODUCT(product_no,p_name, mem_id, category_no, p_price, p_stock, p_detail , PO_PAYMENT , PO_DELIVERY) VALUES ('P'||LPAD(PRODUCT_NO_SEQ.NEXTVAL,6,'0'), ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM PRODUCT";
	private static final String GET_ONE_STMT = "SELECT * FROM PRODUCT WHERE PRODUCT_NO=?";
	private static final String GET_ONE_BY_MEMID = "SELECT * FROM PRODUCT WHERE MEM_ID=?";
	private static final String DELETE_FROM_FRONT = "UPDATE PRODUCT SET P_STATUS=? WHERE PRODUCT_NO=?";
	
	private static final String UPDATE = "UPDATE PRODUCT SET P_NAME=?, CATEGORY_NO=?, P_PRICE=?, P_STOCK=?, P_DETAIL=?,PO_PAYMENT=?,PO_DELIVERY=?, P_UPLOAD_TIME=SYSTIMESTAMP WHERE PRODUCT_NO = ?";
	
	private static final String INSERT_PHOTO = "INSERT INTO PRODUCT_PHOTO(PHOTO_NO,PRODUCT_NO,P_PHOTO) VALUES (PHOTO_NO_SEQ.NEXTVAL,?, ?)";
	private static final String UPDATE_STOCK ="UPDATE PRODUCT SET P_STOCK = ? WHERE PRODUCT_NO = ?";
	private static final String RATING ="UPDATE PRODUCT SET NUMBER_OF_RATING = NUMBER_OF_RATING + 1,P_RATING = P_RATING + ? WHERE PRODUCT_NO = ?";
	CloseHandle closeHandle = new CloseHandle();
	
	public ProductVO updateRating(double pRating, ProductVO productVo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWD);
			pstmt = con.prepareStatement(RATING);
			
			pstmt.setDouble(1, pRating);
			pstmt.setString(2, productVo.getProductNo());
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			closeHandle.close(pstmt,con);
		}
		return null;
	}
	
	@Override
	public ProductVO updateStock(ProductVO productVo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWD);
			pstmt = con.prepareStatement(UPDATE_STOCK);
			
			pstmt.setDouble(1, productVo.getpStock());
			pstmt.setString(2, productVo.getProductNo());
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			closeHandle.close(pstmt,con);
		}
		return productVo;
	}
	@SuppressWarnings("resource")
	public void insertProductAndPhoto(ProductVO productVO,List<PhotoVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(DRIVER);
			System.out.println("���J���\");
			con = DriverManager.getConnection(URL, USERID, PASSWD);
			con.setAutoCommit(false);
			// TODO�s�W�ӫ~
			String[] str = {"product_no"};
			pstmt = con.prepareStatement(INSERT_STMT,str);

			pstmt.setString(1, productVO.getpName());
			pstmt.setString(2, productVO.getMemId());
			pstmt.setString(3, productVO.getCategoryNo());
			pstmt.setDouble(4, productVO.getpPrice());
			pstmt.setDouble(5, productVO.getpStock());
			pstmt.setString(6, productVO.getpDetail());
			pstmt.setString(7, productVO.getPoPayment());
			pstmt.setString(8, productVO.getPoDelivery());
			
			

			pstmt.executeUpdate();
			rs= pstmt.getGeneratedKeys(); //���o�ۼW�D��
			String generatedProductNo = null;
			if (rs.next()) {
				generatedProductNo = rs.getString(1);
				// ����Ȧ��@�C,�G����Ĥ@�C
				System.out.println("-----�w�w�qSQL�Ҧ�-----id = " + generatedProductNo);
			}
			// TODO�s�W�Ϥ�
			pstmt = con.prepareStatement(INSERT_PHOTO);
			Iterator<PhotoVO> it = list.iterator();
			while(it.hasNext()) {
				PhotoVO itNext = it.next();
				pstmt.setString(1, generatedProductNo);
				pstmt.setBytes(2, itNext.getpPhoto());
				System.out.println("�@�i�Ϥ��i�ӤF!");
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			
			con.commit();
		
		}catch (Exception ee) {
			ee.printStackTrace();
			try {
				System.out.println("���~�o�� rollback");
				con.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}finally {
			closeHandle.close(rs, pstmt, con);
		}
	}
	
	@Override
	public void insert(ProductVO productVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			System.out.println("���J���\");
			con = DriverManager.getConnection(URL, USERID, PASSWD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, productVO.getpName());
			pstmt.setString(2, productVO.getMemId());
			pstmt.setString(3, productVO.getCategoryNo());
			pstmt.setDouble(4, productVO.getpPrice());
			pstmt.setDouble(5, productVO.getpStock());
			pstmt.setString(6, productVO.getpDetail());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			closeHandle.close(pstmt,con);
		}

	}

	@SuppressWarnings("resource")
	@Override
	public void update(ProductVO productVO,List<PhotoVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWD);
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, productVO.getpName());
			pstmt.setString(2, productVO.getCategoryNo());
			pstmt.setDouble(3, productVO.getpPrice());
			pstmt.setDouble(4, productVO.getpStock());
			pstmt.setString(5, productVO.getpDetail());
			pstmt.setString(6, productVO.getPoPayment());
			pstmt.setString(7, productVO.getPoDelivery());
			pstmt.setString(8, productVO.getProductNo());

			pstmt.executeUpdate();
			
			pstmt = con.prepareStatement(INSERT_PHOTO);
			Iterator<PhotoVO> it = list.iterator();
			while(it.hasNext()) {
				PhotoVO itNext = it.next();
				pstmt.setString(1, productVO.getProductNo());
				pstmt.setBytes(2, itNext.getpPhoto());
				System.out.println("�@�i�Ϥ��i�ӤF!");
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			con.commit();

			// Handle any driver errors
		} catch (Exception e) {
			System.out.println("�o�Ͱ��Drollback����");
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			closeHandle.close(pstmt,con);
		}

	}

	@Override
	public void deleteFromFront(ProductVO productVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWD);
			pstmt = con.prepareStatement(DELETE_FROM_FRONT);

			pstmt.setString(1, productVO.getpStatus());
			pstmt.setString(2, productVO.getProductNo());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			closeHandle.close(pstmt,con);
		}

	}

	/****************************************************************findByMemId-start****************************************************************/
	@Override
	public List<ProductVO> findByMemId(String memId) {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWD);
			pstmt = con.prepareStatement(GET_ONE_BY_MEMID);

			pstmt.setString(1, memId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
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
				productVO.setNumberOfRating(rs.getDouble("number_of_rating"));
				list.add(productVO);
			}
			System.out.println("���J���\DAO-findByMemId");
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			closeHandle.close(rs,pstmt,con);
		}
		return list;
	}
	/****************************************************************findByMemId-end****************************************************************/
	
	@Override
	public ProductVO findByPrimaryKey(String product_no) {

		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, product_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
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
				productVO.setNumberOfRating(rs.getDouble("number_of_rating"));
				productVO.setPoPayment(rs.getString("PO_PAYMENT"));
				productVO.setPoDelivery(rs.getString("PO_DELIVERY"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			closeHandle.close(rs,pstmt,con);
		}
		return productVO;
	}

//	@Override
	@SuppressWarnings("null")
	public List<ProductVO> getAll() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		InputStream inputStream = null;
		ByteArrayOutputStream outputStream = null;
		try {

			Class.forName(DRIVER);

			con = DriverManager.getConnection(URL, USERID, PASSWD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
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
				productVO.setPoPayment(rs.getString("PO_PAYMENT"));
				productVO.setPoDelivery(rs.getString("PO_DELIVERY"));
				
					list.add(productVO); // Store the row in the list
			}
			
			System.out.println("���J���\");
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} 
//		catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
		 finally {
			closeHandle.close(outputStream,inputStream,rs,pstmt,con);
		}
		
		return list;
	}

	public static void main(String[] args) {
		ProductJDBCDAO dao = new ProductJDBCDAO();

		// �s�W
//		ProductVO productVO1 = new ProductVO();
//		productVO1.setpName("�����Ϋ~");
//		productVO1.setMemId("MEM000001");
//		productVO1.setpPrice(2000.0);
//		productVO1.setpStock(100.0);
//		productVO1.setCategoryNo("C001");
//		productVO1.setpDetail("�ӶR��");
//		dao.insert(productVO1);
////		��ܷs�W�ӫ~���s��
//		List<ProductVO> list = dao.getAll();// ��d�ߥ����@��
//		String newestProductNum = null;
//		Iterator<ProductVO> iterator = list.iterator();
//		while(iterator.hasNext()) {
//			newestProductNum = iterator.next().getProduct_no();
//		}
//		System.out.println("�s�W���\!!�ӫ~�s���� : " + newestProductNum);
//		��ܷs�W�ӫ~���s��

		// �ק�
//		Timestamp time = new Timestamp(System.currentTimeMillis());
//		ProductVO productVO2 = new ProductVO();
//		productVO2.setProductNo("P000001");
//		productVO2.setMem_id("M000001");
//		productVO2.setP_name("d");
//		productVO2.setP_price(2000.0);
//		productVO2.setpStock(100.0);
//		productVO2.setCategory_no("C001");
//		productVO2.setP_rating(1.0);
//		productVO2.setP_status("ddd");
//		productVO2.setP_detail("ddd");
//		productVO2.setP_upload_time(null);
//		dao.updateStock(productVO2);
//		System.out.println("�ק令�\");
//
		// �R��(�ק窱�A)
//		ProductVO productVO3 = new ProductVO();
//		productVO3.setP_status("�w�U�[");
//		productVO3.setProduct_no("P000005");
//		System.out.println(productVO3.getProduct_no() + "�w�U�[");
//		dao.deleteFromFront(productVO3);
//		System.out.println("�U�[���\");
//		// �d��
//		ProductVO ProductVO4 = dao.findByPrimaryKey("P000004");
//		Timestamp time= ProductVO4.getP_upload_time();
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		String timeStr = df.format(time); 
//		System.out.println("--------------------------------");
//		System.out.println("�ӫ~�s�� : " + "\t" + ProductVO4.getProduct_no());
//		System.out.println("�ӫ~�W�� : " + "\t" + ProductVO4.getP_name());
//		System.out.println("�|���s�� : " + "\t" + ProductVO4.getMem_id());
//		System.out.println("���O�s�� : " + "\t" + ProductVO4.getCategory_no());
//		System.out.println("��� : " + "\t" + ProductVO4.getP_price());
//		System.out.println("�w�s : " +"\t" + ProductVO4.getP_stock());
//		System.out.println("�ӫ~�Ա� : " + "\t" + ProductVO4.getP_detail());
//		System.out.println("�W�[��� : " + "\t" + timeStr);
//		System.out.println("�`���� : " +"\t" + ProductVO4.getP_rating());
//		System.out.println("�����H�� : " + "\t" + ProductVO4.getNumber_of_rating());
//		System.out.println("���A : " + "\t" + ProductVO4.getP_status());

//
//		// �d�ߥ���

//			System.out.println("List�إߦ��\");

//		for (ProductVO aProduct : list) {
//			Timestamp time = aProduct.getpUploadTime();
//			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//			String timeStr = df.format(time);
//			System.out.println("--------------------------------");
//			System.out.println("�ӫ~�s�� : " + "\t" + aProduct.getProductNo());
//			System.out.println("�ӫ~�W�� : " + "\t" + aProduct.getpName());
//			System.out.println("�|���s�� : " + "\t" + aProduct.getMemId());
//			System.out.println("���O�s�� : " + "\t" + aProduct.getCategoryNo());
//			System.out.println("��� : " + "\t" + aProduct.getpPrice());
//			System.out.println("�w�s : " + "\t" + aProduct.getpStock());
//			System.out.println("�ӫ~�Ա� : " + "\t" + aProduct.getpDetail());
//			System.out.println("�W�[��� : " + "\t" + timeStr);
//			System.out.println("�`���� : " + "\t" + aProduct.getpRating());
//			System.out.println("�����H�� : " + "\t" + aProduct.getNumberOfRating());
//			System.out.println("" + "���A : " + "\t" + aProduct.getpStatus());
//
//		}
//		System.out.println("--------------------------------");
		
		//�s�W�ӫ~�M�Ϥ�
//		ProductVO productVO5 = new ProductVO();
//		PhotoVO photoVO5 = new PhotoVO();
//		List<PhotoVO> list5 = new ArrayList<PhotoVO>();
//		productVO5.setpName("�����Ϋ~");
//		productVO5.setMemId("MEM0000001");
//		productVO5.setpPrice(2000.0);
//		productVO5.setpStock(100.0);
//		productVO5.setCategoryNo("C001");
//		productVO5.setpDetail("�ӶR��");
//		productVO5.setPoPayment("20");
//		productVO5.setPoDelivery("20");
//		String s = "cccc";
//		s.getBytes();
//		photoVO5.setpPhoto(s.getBytes());
//		list5.add(photoVO5);
//		photoVO5.setpPhoto(s.getBytes());
//		list5.add(photoVO5);
//		photoVO5.setpPhoto(s.getBytes());
//		list5.add(photoVO5);
//		photoVO5.setpPhoto(s.getBytes());
//		list5.add(photoVO5);
//		dao.insertProductAndPhoto(productVO5,list5);

		//�q�|���s����M�ӫ~
//		List<ProductVO> list6 =  dao.findByMemId("MEM0000001");
//		for(ProductVO aProduct : list6) {
//			Timestamp time = aProduct.getpUploadTime();
//			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//			String timeStr = df.format(time);
//			System.out.println("--------------------------------");
//			System.out.println("�ӫ~�s�� : " + "\t" + aProduct.getProductNo());
//			System.out.println("�ӫ~�W�� : " + "\t" + aProduct.getpName());
//			System.out.println("�|���s�� : " + "\t" + aProduct.getMemId());
//			System.out.println("���O�s�� : " + "\t" + aProduct.getCategoryNo());
//			System.out.println("��� : " + "\t" + aProduct.getpPrice());
//			System.out.println("�w�s : " + "\t" + aProduct.getpStock());
//			System.out.println("�ӫ~�Ա� : " + "\t" + aProduct.getpDetail());
//			System.out.println("�W�[��� : " + "\t" + timeStr);
//			System.out.println("�`���� : " + "\t" + aProduct.getpRating());
//			System.out.println("�����H�� : " + "\t" + aProduct.getNumberOfRating());
//			System.out.println("" + "���A : " + "\t" + aProduct.getpStatus());
//		}
		//��������
		ProductVO productVO7 = new ProductVO();
		productVO7.setProductNo("P000001");
		dao.updateRating(5, productVO7);
		
		System.out.println("��ܦ��\");
	}
		

}