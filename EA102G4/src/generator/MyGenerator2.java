//�H�U�Q���Ѫ���9��B15��B28�欰��Hibernate3-4�����k

package generator;

import java.io.*;
import java.sql.*;

import org.hibernate.HibernateException;
//import org.hibernate.engine.SessionImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class MyGenerator2 implements IdentifierGenerator {

//	public Serializable generate(SessionImplementor session, Object object)
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

		String photoNo = null;
		Connection con = session.connection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT PHOTO_NO_SEQ.NEXTVAL as nextval FROM product_photo where rownum <= 1");
			rs.next();
			photoNo = Integer.toString(rs.getInt("nextval"));
//			con.close();   //��Hibernate 5 ���i�����s�u
		} catch (SQLException e) {
			throw new HibernateException("Unable to generate Sequence");
		}
		System.out.println("photoNo: " + photoNo);
		return photoNo;
	}
}