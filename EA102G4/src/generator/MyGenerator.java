//�H�U�Q���Ѫ���9��B15��B28�欰��Hibernate3-4�����k

package generator;

import java.io.*;
import java.sql.*;

import org.hibernate.HibernateException;
//import org.hibernate.engine.SessionImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class MyGenerator implements IdentifierGenerator {

//	public Serializable generate(SessionImplementor session, Object object)
	public Serializable generate(SharedSessionContractImplementor session, Object object)
			throws HibernateException {

		String prefix = "P";
		String productNo = null;
		Connection con = session.connection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT PRODUCT_NO_SEQ.NEXTVAL as nextval FROM PRODUCT");
			rs.next();
			int nextval = rs.getInt("nextval");
			productNo = prefix;
			while(true) {
				productNo = productNo + "0";
				if((productNo + nextval).length() > 6) {
					productNo = productNo + nextval;
					System.out.println(productNo);
					break;
				}
				System.out.println(productNo);
			}
//			con.close();   //��Hibernate 5 ���i�����s�u
		} catch (SQLException e) {
			throw new HibernateException("Unable to generate Sequence");
		}
		System.out.println(productNo.length());
		return productNo;
	}
}