package com.tool.hong;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class myFakePic {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "INGYM";
	private static final String PASSWORD = "123456";
	private static final String SQL = "UPDATE GROUP_CLASS SET G_PIC=? WHERE G_CLASS_NO=?";

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			

			// 1. setBlob
//			pstmt.setInt(1, 1);
//			pstmt.setString(2, "�����}����");
//			Blob blob = con.createBlob();  //�Ū��e��,�ΨӸ˦줸��ƥ�
//			byte[] pic2 = getPictureByteArray("items/FC_Bayern.png");
//			blob.setBytes(1, pic2); // �o�䪺1�����O��m(���ޭ�),�q�o��blob�e�����Ĥ@�Ӧ�m�}�l�s��줸���
//			pstmt.setBlob(3, blob);
//			pstmt.executeUpdate();

			// �M�Ÿ̭��ѼơA���ШϥΤw���o��PreparedStatement����
//			pstmt.clearParameters();

			// 2. setBytes
			for(int i =0; i<13 ;i++) {
			pstmt = con.prepareStatement(SQL);
			byte[] pic = getPictureByteArray("C:\\fakepic\\"+i+".jpg");
			pstmt.setBytes(1, pic);
			if(i<10)
				pstmt.setString(2, "GC100000"+i);
			else
				pstmt.setString(2, "GC10000"+i);
			pstmt.executeUpdate();
			pstmt.clearParameters();
			}

			// �M�Ÿ̭��ѼơA���ШϥΤw���o��PreparedStatement����
			

			// 3. setBinaryStream
//			pstmt.setInt(1, 3);
//			pstmt.setString(2, "�Ӯa���w��");
//			InputStream is = getPictureStream("items/FC_Real_Madrid.png");
//			pstmt.setBinaryStream(3, is, is.available());
//			pstmt.executeUpdate();
//
//			System.out.println("�s�W���\");

		} catch (ClassNotFoundException ce) {
			System.out.println(ce);
		} catch (SQLException se) {
			System.out.println(se);
		} catch (IOException ie) {
			System.out.println(ie);
		} finally {
			// �̫إ߶��������귽 (�V�߫إ߶V������)
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}
		}
	}

	// �ϥ�InputStream��Ƭy�覡
	public static InputStream getPictureStream(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		return fis;
	}

	// �ϥ�byte[]�覡
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}
}
