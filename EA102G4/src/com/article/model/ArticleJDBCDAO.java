package com.article.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.art_comment.model.Art_CommentVO;
import com.article_rep.model.Article_RepVO;






public class ArticleJDBCDAO implements ArticleDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "INGYM";
	String passwd = "123456";
	
	private static final String INSERT_STMT = "INSERT INTO ARTICLE (ARTICLE_NO, MEM_ID, TITLE, ART_CONTENT) VALUES ('ART'||LPAD( SEQ_ARTICLE_NO.NEXTVAL,3,'0'), ?, ?, ?)"; //�|���峹�s�W
	private static final String INSERT_WORK_STMT = "INSERT INTO ARTICLE (ARTICLE_NO, WORK_ID, TITLE, ART_CONTENT) VALUES ('ART'||LPAD( SEQ_ARTICLE_NO.NEXTVAL,3,'0'), ?, ?, ?)"; //�|���峹�s�W
	
	private static final String GET_ALL_STMT = "SELECT ARTICLE_NO , MEM_ID, WORK_ID, TITLE, ART_CONTENT, A_RELEASE, LINK_COUNT, COM_COUNT, A_STATUS FROM ARTICLE";    //�d�ߥ����|���峹
	
	private static final String GET_ONE_STMT = "SELECT * FROM ARTICLE WHERE Article_NO = ?";
	
	
	private static final String GET_ARTICLE_REPsByARTICLE_NO_STMT = "SELECT ART_REP_NO,MEM_ID,REP_CONTENT,COM_STATUS,ARTICLE_NO FROM ARTICLE_REP WHERE ARTICLE_NO =? ORDER BY ART_REP_NO";

	
	private static final String GET_ART_COMMENTSByARTICLE_NO_STMT = "SELECT COM_NO,MEM_ID,MES_CONTENT,COM_RELEASE,COM_STATUS,ARTICLE_NO FROM ART_COMMENT WHERE ARTICLE_NO =? ORDER BY COM_NO";

	
//		private static final String GET_Artic_ByArticleno_STMT = "SELECT ARTICLE_NO , MEM_ID, WORK_ID, TITLE, ART_CONTENT, A_RELEASE, LINK_COUNT, COM_COUNT, A_STATUS FROM ARTICLE  ORDER BY ARTICLE_NO";		
	
	
	private static final String DELETE = "DELETE FROM ARTICLE WHERE ARTICLE_NO = ?";
	
	private static final String UPDATE = "UPDATE ARTICLE SET  TITLE=?, ART_CONTENT=? WHERE ARTICLE_NO=?  ";

	private static final String UPDATE2 = "UPDATE ARTICLE SET MEM_ID=?, WORK_ID=?, TITLE=?, ART_CONTENT=? WHERE ARTICLE_NO=? ";

	private static final String GET_REP_ART ="SELECT * "+ " FROM ARTICLE WHERE A_STATUS = 'A0' ORDER BY ARTICLE_NO ";
	
	private static final String UPDATE_TO_NO_SHOW = "UPDATE ARTICLE SET A_STATUS = 'A0' WHERE ARTICLE_NO = ?";
	
	@Override
	public void insert(ArticleVO articleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setString(1, articleVO.getMem_id());
			pstmt.setString(2, articleVO.getTitle());
			pstmt.setString(3, articleVO.getArt_content());
			
			pstmt.executeUpdate();
			
			

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void insertwork(ArticleVO articleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;


		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_WORK_STMT);
			
			pstmt.setString(1, articleVO.getWork_id());
			pstmt.setString(2, articleVO.getTitle());
			pstmt.setString(3, articleVO.getArt_content());
			
			pstmt.executeUpdate();
			
			

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(ArticleVO articleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(1, articleVO.getTitle());
			pstmt.setString(2, articleVO.getArt_content());
			pstmt.setString(3, articleVO.getArticle_no());
			

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update2(ArticleVO articleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE2);

			pstmt.setString(1, articleVO.getMem_id());
			pstmt.setString(2, articleVO.getWork_id());
			pstmt.setString(3, articleVO.getTitle());
			pstmt.setString(4, articleVO.getArt_content());
			pstmt.setInt(5, articleVO.getLink_count());
			pstmt.setInt(6, articleVO.getCom_count());
			pstmt.setString(7, articleVO.getA_status());
			pstmt.setString(8, articleVO.getArticle_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void delete(String article_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, article_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public ArticleVO findByPrimaryKey(String article_no) {

		ArticleVO articleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, article_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				articleVO = new ArticleVO();
				articleVO.setArticle_no(rs.getString("Article_no"));
				articleVO.setMem_id(rs.getString("Mem_id"));
				articleVO.setWork_id(rs.getString("Work_id"));
				articleVO.setTitle(rs.getString("Title"));
				articleVO.setArt_content(rs.getString("Art_content"));
				articleVO.setA_release(rs.getTimestamp("A_release"));
				articleVO.setLink_count(rs.getInt("Link_count"));
				articleVO.setCom_count(rs.getInt("Com_count"));
				articleVO.setA_status(rs.getString("A_status"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return articleVO;
	}

	@Override
	public List<ArticleVO> getAll() {
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				articleVO = new ArticleVO();
				articleVO.setArticle_no(rs.getString("Article_no"));
				articleVO.setMem_id(rs.getString("Mem_id"));
				articleVO.setWork_id(rs.getString("Work_id"));
				articleVO.setTitle(rs.getString("Title"));
				articleVO.setArt_content(rs.getString("Art_content"));
				articleVO.setA_release(rs.getTimestamp("A_release"));
				articleVO.setLink_count(rs.getInt("Link_count"));
				articleVO.setCom_count(rs.getInt("Com_count"));
				articleVO.setA_status(rs.getString("A_status"));
				list.add(articleVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public Set<Art_CommentVO> getArt_CommentsByArticle_no(String article_no) {
		Set<Art_CommentVO> set = new LinkedHashSet<Art_CommentVO>();
		Art_CommentVO art_commentVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ART_COMMENTSByARTICLE_NO_STMT);
			pstmt.setString(1, article_no);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				art_commentVO = new Art_CommentVO();
				art_commentVO.setCom_no(rs.getString("com_no"));
				art_commentVO.setMem_id(rs.getString("mem_id"));
				art_commentVO.setMes_content(rs.getString("mes_content"));
				art_commentVO.setCom_release(rs.getTimestamp("com_release"));
				art_commentVO.setCom_status(rs.getString("com_status"));
				art_commentVO.setArticle_no(rs.getString("article_no"));
				set.add(art_commentVO); // Store the row in the vector
			}
	
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	
	@Override
	public Set<Article_RepVO> getArticle_RepsByArticle_no(String article_no) {
		Set<Article_RepVO> set = new LinkedHashSet<Article_RepVO>();
		Article_RepVO aricle_repVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ARTICLE_REPsByARTICLE_NO_STMT);
			pstmt.setString(1, article_no);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				aricle_repVO = new Article_RepVO();
				aricle_repVO.setArt_rep_no(rs.getString("art_rep_no"));
				aricle_repVO.setMem_id(rs.getString("mem_id"));
				aricle_repVO.setRep_content(rs.getString("rep_content"));
				aricle_repVO.setRep_time(rs.getTimestamp("rep_time"));
				aricle_repVO.setCom_status(rs.getString("com_status"));
				aricle_repVO.setArticle_no(rs.getString("article_no"));
				set.add(aricle_repVO); // Store the row in the vector
			}
	
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	
	@Override
	public List getRepArt() {
		List<ArticleVO> reparticlelist = new ArrayList<ArticleVO>();
		ArticleVO aVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_REP_ART);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				aVO = new ArticleVO();
				aVO.setArticle_no(rs.getString("article_no"));
				reparticlelist.add(aVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return reparticlelist;
	}
		@Override
		public void updateToNoShow(String article_no) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE_TO_NO_SHOW);

				pstmt.setString(1, article_no);
				
				pstmt.executeUpdate();

			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
	
	
	public static void main(String[] args) {

		ArticleJDBCDAO art = new ArticleJDBCDAO();

		// �s�W�|��
		ArticleVO articleVO1 = new ArticleVO();
		articleVO1.setMem_id("MEM0000002");
		articleVO1.setTitle("����������");
		articleVO1.setArt_content("<p><img src=/EA102G4/front-end/article/assets/img/blog/01.jpg alt=\"\">" + 
				"<br />\r\n" + 
				"�����W�j�٦ת����Z�G<br />\r\n" + 
				"<br />\r\n" + 
				"�j���q�B�C���ơB�h�ռơB���첾�B�C�t�סB���K�סB���ʤ@�P�B���p���Y�B�����i�B�ն���Q�B�h�m�j�ٸs�B�V�m��i���J�ս�B��48�p�ɡB�维�Ű��C<br />\r\n" + 
				"<br />\r\n" + 
				"1.�j���q�B�C���ơG�����z�פ���RM��ܬY�ӭt���q��s�򰵪��̰����_���ơC��p�A�m�ߪ̹�@�ӭ��q�u��s���|�_5���A�h�ӭ��q�N�O5RM�C��s����G1-5RM���t���V�m��Ϧ٦׼W�ʡA�o�i�O�q�M�t�סF6-10RM���t���V�m��Ϧ٦ײʤj�A�O�q�t�״����A���@�O�W��������F10-15RM���t���V�m���ֺ��W�ʤ�����A���O�q�B�t�סB�@�O�������i�F30RM���t���V�m�٦פ���Ӧ�޼W�h�A�@�[�O�����A���O�q�B�t�״���������C�i���A5-10RM���t�����q�A�Ω�W�j�٦���n�������V�m�C<br />\r\n" + 
				"<br />\r\n" + 
				"2.�h�ռơG����ɭԷQ�_�ӭn��ҤF�A�N���W2��3�աA�o���O���O�ɶ��A�ڥ�������٦סC�����M����X60��90�������ɶ�������ҬY�ӳ���A�C�Ӱʧ@����8��10�աA�~��R����E�٦סA�P�ɦ٦׻ݭn����_�ɶ��V���C�@������٦׹��M����A&ldquo;���M��&rdquo;�n�ۧڷP���A��A�ת��Э�O�G�ġB�ȡB�o�¡B���B�����B�X�i�A�H�Φ٦ץ~�ΤW������ʧ����C<br />\r\n" + 
				"<br />\r\n" + 
				"3.���첾�G���ެO����B�ױ��B���|�B�s�|�A���n������׹a��o�ɶq�C�A�H�R���Ԧ��٦סA�A�|�o�ɶq���C�o�@���P&ldquo;�����i&rdquo;���ɷ|�٬ޡA�ѨM��k�O�ֳt�a�q�L&ldquo;��w&rdquo;���A�C���L�A�ڨä��_�{�j���q���b�{�B�ʪ��@�ΡC<br />\r\n" + 
				"<br />\r\n" + 
				"4.�C�t�סG�C�C�a�|�_�A�b�C�C�a��U�A��٦ת���E��`�C�S�O�O�A�b��U�׹a�ɡA�n����n�t�סA���h���ʽm�ߡA����R����E�٦סC�ܦh�H�����F�h���ʽm�ߡA��׹a�|�_�ӴN�⧹���F���ȡA�ܧ֦a��U�A���O�F�W�j�٦ת��j�n�ɾ��C<br />\r\n" + 
				"<br />\r\n" + 
				"5.���K�סG&ldquo;�K��&rdquo;�����O��դ������𮧮ɶ��A�u��1�����Χ�֮ɶ��٬����K�סC�n�Ϧ٦׶����t�W�j�A�N�n�֥𮧡A�W�c�a��E�٦סC&ldquo;�h�ռ�&rdquo;�]�O�إߦb&ldquo;���K��&rdquo;����¦�W���C��ҮɡA�n�����M�@�ˡA�����e�`�a��J�V�m�A���h�Q�O���ơC<br />\r\n" + 
				"<br />\r\n" + 
				"6.���ʤ@�P�G�٦ת��u�@�O�����g��t���A�`�N�O�K�׶����N��ʭ���h�����ֺ��ѥ[�u�@�C�m�Y�@�ʧ@�ɡA�N�����N�Ѧa�ϷN���M�ʧ@�@�P�_�ӡA�Y�m����N�Q����٦פu�@�C�Ҧp�G�m�ߦ��s�|�A�N�n�C�Y�������`���ۤw�����u�A�ݪФG�Y�٦b�C�C�a���Y�C<br />\r\n" + 
				"<br />\r\n" + 
				"7.���p���Y�G�o�O�Ϧ٦׽u���m�o�Q�����㪺�@���D�n�k�h�C���n�D��Y�Ӱʧ@����٦צ��Y�̺�i����m�ɡA�O���@�U�o�ئ��Y�̺�i�����A�A���R�O�ʽm�ߡA�M��C�C�^�_��ʧ@���}�l��m�C�ڪ���k�O�Pı�٦׳̺�i�ɡA��1��6�A�A��U�ӡC<br />\r\n" + 
				"<br />\r\n" + 
				"8.�����i�G���b��Ӥ@�դ��O���٦׫����i�A���צb�ʧ@���}�Y�٬O�����A�����n�����Q���]���B��&ldquo;��w&rdquo;���A�^�A�`�O�F������O�ܡC<br />\r\n" + 
				"<br />\r\n" + 
				"9.�ն���Q�G�C�����@�հʧ@���n���i��Q�C�o�˯�W�[�٦ת���y�q�A�٦��U��ư��I�n�b�٦׸̪��o���A�[�֦٦ת���_�A���t�ɥR��i�C<br />\r\n" + 
				"<br />\r\n" + 
				"10.�h�m�j�ٸs�G�h�m�ݡB�I�B�y�v�B�L�����j�ٸs�A���ȯ�Ϩ���j���A�ٯ���P�i��L����٦ת��ͪ��C�����H���F��޻K�m�ʡA�u�m�޻K�Ӥ��m��L����A�Ϧӷ|�ϤG�Y�٪��ͪ��Q���w�C�C��ĳ�A�w�Ƥ@�ǨϥΤj���q���j���_�X�ʧ@�m�ߡA�p�j���q���`�۽m�ߡA���̯�P�i�Ҧ���L����٦ת��ͪ��C�o�@�I���䭫�n�A�i�d���O�ܤ֦�90�H���H���S�����������A�H�P����F����檺�ĪG�C�]���A�b�V�m�p���̭n�h�w�Ƶw�|�B�`�ۡB�ױ��B���|�B����V�W(�Գ�b)�o5�Ӹg��_�X�ʧ@�C<br />\r\n" + 
				"<br />\r\n" + 
				"11.�V�m��i���J�ս�G�b�V�m�᪺30��90�����̡A�J�ս誺�ݨD�F���p���A���ɸɥR�J�ս�ĪG�̨ΡC<br />\r\n" + 
				"<br />\r\n" + 
				"12.��48�p�ɡG�����٦װV�m�@����ݭn��48��72�p�ɤ~��i��ĤG���V�m�C�p�G�i�氪�j�פO�q�V�m�A�h�����٦ר⦸�V�m�����j72�p�ɤ]�����A�ר�O�j�٦׶��C���L���٨ҥ~�A���٤��P���L�ٸs�A�����g�`���i���E�A�C�P���ܤ֭n�m4���A�C����15�����F��T�ӹ�A�̦��Ī��m�ߡA�u��3�աA�C��20&mdash;25���A������O�ܡF�C�ն��j�ɶ��n�u�A����W�L1�����C<br />\r\n" + 
				"<br />\r\n" + 
				"13.�维�Ű��G�o�O�@�Ӥ��O���Z�����Z�C�\�h��ǰ������H�S�O�����m�߭��q�M�ʧ@���ơA���Ӫ`�N�ʧ@�O�_�ܧΡC�����V�m���ĪG���ȶȨ��M��t�������q�M�ʧ@���ơA�ӥB�٭n�ݩҽm�٦׬O�_�������O�M����E���{�סC�p�G�ʧ@�ܧΩΤ����A�n�m���٦רS���Υu�O�������O�A�V�m�ĪG�N���j�A�ƦܥX���t�C�ƹ�W�A�b�Ҧ����k�h���A�ʧ@�����T�ʥû��O�Ĥ@���n���C��i�Υ��T���ʧ@�|�_����������q�A�]���n�Τ��Э㪺�ʧ@�|�_�󭫪����q�C���n�P�H�k��A�]���n�ⰷ���Ъ��J�����b�ߤW</p>");	
		art.insert(articleVO1);
		System.out.println("�s�W���\");
		
		
		articleVO1.setMem_id("MEM0000003");
		articleVO1.setTitle("��������");
		articleVO1.setArt_content("<p>�j�h�ƤH�|���u���U�Z�~���ťh�����СC�U�Z���^�a�A���}���C�Y�����A�𮧤@�Ӥp�ɥ��k�~������C����[���񳣨�Ӥp�ɥH�W�A�V�m���^�a���ߤW�E&mdash;�Q�I�F�C�ҥH�j�����H�V�m�᳣�O���I�J�կ��B�Y�I���G���򪺡A�̦h�[�I�W�ٯ��A�N��ı�F�C�@�O�S�ɶ��A�G�O�Ȫ��D�����h�Y�C���V�m��O�������i����ݨD�̩������ɭԡC�o�Ӯɬq����N�³̧֡A���e���x�s�תաA�ݭn�ɥR�j�q���Ҥ��M�J�ս�C</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p><img src=/EA102G4/front-end/article/assets/img/blog/e01.jpg alt=\"\">" + 
				"<br />\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p>�J�կ��M²��Ҥ��u�ມ���V�m��1&mdash;2�Ӥp�ɪ��ݭn�A���q�̫�@�\��ĤG�Ѧ��W�_�ɫ�i�\�A�������Q�X�Ӥp�ɤ���i���C�����}���x�Ʊo����R���ɥR�|�v�T�ĤG�Ѫ���બ�A�A�ӥB����V�m�}�a���٦��ֺ����L�F��i�l�����̨ήɾ��A���ɶ��o����R������i�ɥR�״_�]�|���ѡC�o�ˤ��������F�٦סA�����p���ٷ|��i���}�ɭP�V�m�V�G�Ϊ̨����z�C</p>\r\n" + 
				"\r\n" + 
				"<p>�ڪ��W�Z�ڰ��������߱o�N�O�G���q��V�m�ɶ����e�A�����󪺳̦n���W�Τ��ȰV�m�A�o�˱q�V�m���ߤW��ı�e������h�Y�X�y�A�R���a�ɥR����һݭn����i�A�P�ɤ]�i�H�קK��ı�e�Y�j�q�Ҥ��ӾɭP�תչL�h����n�C�p�G�A�u���U�Z��~���ɶ��A�����ĳ�A�V�m�e�����n�Y�������߶��C�U�Z�e�ΤU�Z��Y�@�I�ѱ��B�C�Y�B�����ѥ]�����ƦX�Ҥ�(���n�Ӧh)�A�N�ണ�Ѩ�������q�i��j�j�׾���V�m�F�C</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p><img src=/EA102G4/front-end/article/assets/img/blog/e02.jpg alt=\"\">" + 
				"<br />\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p>��軡�A�U�Ȥ��I�ɥR�@�I�ƦX�Ҥ��A����U�Ȥ��I�b�Τ��I�N�i�H�찷���ж}�l����V�m�C����V�m���ɶ��A���Ĳv�a�����V�m���Ȥ����(�̦h�@�p��)�C�V�m��ߧY�Y������²��Ҥ��[�W�ٯ��A�o�Ӯɭ����Ӥ��W�L�ߤW�C�I�ΤC�I�b�A�Ϊ̧󦭡C�C�I�b�e�^�a�Y�������\(���צ���������)�Q�I���k�i�H�A�ɥR�@���A�H�C�װ��J�խ������D(�תէt�q����h���H�N�i�H�h���ƦX�Ҥ�)�C�p�G��ı�ɶ�����ߡA��軡�ߤW�Q�G�I�έ��@�I�~�Ϊ��H�A�Ϋe�A�ܤ@�M�J�կ��C�Ъ`�N�A���M�V�m��ݭn�j�q�����ɥR�A���]�n�`�N�O�֦Y�h�\�A���ҭJ�Y����C</p>");	
		art.insert(articleVO1);
		System.out.println("�s�W���\");
		
		articleVO1.setMem_id("MEM0000004");
		articleVO1.setTitle("�������n�B");
		articleVO1.setArt_content("<p><strong><img alt=\"�B�ʡA�����A��|�A���V�C \" src=\"https://www.pro360.com.tw/wp-content/uploads/2019/04/iStock-960658902-1.jpg\" width=\"600\" /></strong></p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p><strong>���٦׶q�M�w���骺�N�²v�A�٦פ�_�תթҮ��Ӫ����q�h�F5~10���C</strong></p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p>�H�۹B�ʰ���������V�ӶV�����A�]�V�ӶV�h�H�o���Q�n�n�����N�n�u�W�ٴ�סv�C�����W����զ��p��ܪ��u�٦׶q�v���Ҥ��P�A�A���D�A�q�X�Ӫ��ƭȥN����ܡH�٦׼W�[�ٯର�A�a�Ӥ���˪��n�B�H�Q�W�٤��u�n�{�u�����A�٦���L���n�]���v�T�٦ת��ͪ��I�N�� Cofit ��i�v�@�@���A�ѵ��a�C</p>\r\n" + 
				"\r\n" + 
				"<p><strong>�٦׶q�O����H</strong></p>\r\n" + 
				"\r\n" + 
				"<p>�j�����a��²����׭p�W���٦׶q�A�O����٦סA�]�t���f�١B�ߦ١B���Ʀ٪��`�M�F�٦׶q�h�O�n�H�u�����`�����ϳJ�ս譫�v�ӭp��C�����ڸs�q�`�һ����u�W�١v�ұj�ժ��O�u���f�١v�A�W�ٴ����A�i�H�z�L�w���q���h�K���[��f�٭����W���ܤơI</p>\r\n" + 
				"\r\n" + 
				"<p><strong>�٦ת����n��</strong></p>\r\n" + 
				"\r\n" + 
				"<p>�j�a�`ť��٦׶q�M�w�F����N�²v�A���]�O���f�٦����Y��C�u�����f�٥i�H�z�L�V�m�W�[�A�b�餺����V���A�]�|���N�²v�W�[�C</p>\r\n" + 
				"\r\n" + 
				"<p><img alt=\"�����]�ϡ����ۧK�O�ϮwPexels�^ \" src=\"https://pics.ettoday.net/images/4035/d4035628.jpg\" width=\"600\" /></p>\r\n" + 
				"\r\n" + 
				"<p><strong>���~�������H��W�١A�קK���w�٤֯g�ΰ��貨�P�g�C</strong></p>\r\n" + 
				"\r\n" + 
				"<p>�٦׮��Ӫ���q�O�תժ�5~10���A�Ҧ�����n�o�u���תժ�1/4�A�O���A�~��ݰ_�Ӻ��B�餺�N�¨}�n�����䤧�@�C�ܩ�٦ת���L�n�B�]�t�O�@���`�A�b�i�次�ʮɪ������i�H��ֶˮ`�F���H�ۦ~�֪��W���A�Y�O�A�S���������T���B�ʲߺD�A�٦׶q�|�@���y���B�N�²v�]�|�v���U���A�Y�O�����٤֯g�w�̡A��O�|�y���ͬ��W�����K�P��ê�I</p>\r\n" + 
				"\r\n" + 
				"<p>�H�U�O�z�L�٤O�V�m���٦׶q�W�[���n�B�G</p>\r\n" + 
				"\r\n" + 
				"<p><strong>1.�W�[��¦�N�²v�]BMR)�A�����餣����n�תաC</strong></p>\r\n" + 
				"\r\n" + 
				"<p><strong>2.���ɹB�ʯ�O�A�קK�B�ʶˮ`�C</strong></p>\r\n" + 
				"\r\n" + 
				"<p><strong>3.�W�i����K�סA�ϰ��f�j���B���w�ѤơC</strong></p>\r\n" + 
				"\r\n" + 
				"<p><strong>4.�ϥͬ���֦]�[���Τ[���y�����٦ׯh�ҡC</strong></p>\r\n" + 
				"\r\n" + 
				"<p><strong>5.���C�ߦ�޵��C�ʯe�f�����I�C</strong></p>\r\n" + 
				"\r\n" + 
				"<p><strong>6.�קK���貨�P�ο��w�٤֯g�C</strong></p>\r\n" + 
				"\r\n" + 
				"<p><strong>7.���νu����n�ݡC</strong><br />\r\n" + 
				"&nbsp;</p>");	
		art.insert(articleVO1);
		System.out.println("�s�W���\");
		
		articleVO1.setMem_id("MEM0000005");
		articleVO1.setTitle("�����e�������ߺD");
		articleVO1.setArt_content("<p>�b����&ldquo;��i&ldquo;�o�Ӹ��D���ɭԡA�ڭ̤j�h�ƤH�����D�B�ʫ�ݭn�ɥR��q�A<strong>���A���D�����e���ӫ��Y�ܡH�h�����Цh�[�e�Ӷi���B�Y����B�Y�h��</strong>�A�o�ǳ��|�v�T�A�b����ɪ���{�M���骺�Pı�C���е��j�a�@�ǹB�ʫe��������¦���ѡA���U�A�󰪮Ħa�B�ʡC</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h2>�h�����Ф��e���ӦY����H</h2>\r\n" + 
				"\r\n" + 
				"<p>���M���ǤH���w�Ÿ�����A���b�h�����Ф��e�A��������������q�����q��i���]�ڬ��������`����&ldquo;MACROS&rdquo;�^�������n�C</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p><img alt=\"�����e�Y���� �����e���Y\" src=\"https://blogscdn.thehut.net/app/uploads/sites/510/2019/10/700x385-04-1_1585231139.jpg\" width=\"700\" /></p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h3><strong>�Ҥ��ƦX��</strong></h3>\r\n" + 
				"\r\n" + 
				"<hr />\r\n" + 
				"<p><a href=\"https://www.myprotein.tw/nutrition/carbohydrates.list\"><strong>�Ҥ��ƦX��</strong></a>�O�٦ת��D�n��q�ӷ��C�b��������A�ڭ̪��٦ױq���������Ҥ��ƦX����������q�]����}�^�H�ߧY�ϥΡA�Ϊ̱q�٦פ��B�~����q�s�x�]�}��^�������q�C���\���A���G�M���椤�A�ڭ̳��i�H����Ҥ��ƦX���A�ӺҤ��J�i�H���ڭ̥��Ѭ��ʡ]�]�A�j���^���Ҧ��٦״��ѯ�q�A�]������U����̤j�{�צa�W�[�}�쪺�x�s�A�]���A�B�ʫe����A�q�Ҥ��A�ڭ̥i�H��V�O����[�a�B�ʡA�åB�Ҥ����U�ɥR�}��A���U��[�ֹB�ʫ᪺�٦׫�_�C</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h3><strong>�J�ս�</strong></h3>\r\n" + 
				"\r\n" + 
				"<hr />\r\n" + 
				"<p>�j�h�ƹB�ʭ������D<strong>�J�ս�</strong>�O�����W���٦ת�����A�������e����J�ս�]���n�B�C��s����A�b�����e�����ӳJ�ս�A���U�������٦ת��W���M��_�C�b�����e�����ӳJ�ս�A�o�N�ϧA�����馳�ɶ����ƳJ�ս�ñN����Ѭ����ġA�q�Ӧ��U��٦ת��إߩM�״_�C</p>\r\n" + 
				"\r\n" + 
				"<p>�t�@����s����A���׬O�b�����e�άO������A�ɥR�ۦP���q���J�ս賣�㦳�@�˪��q�B�C�w������������J�ս�]�i�H���U�A�q�W�@�����夤��_�A�Ϩ��鰵�n�ǳ�, �H�F��̨Ϊ��A�A���i������C</p>\r\n" + 
				"\r\n" + 
				"<p>Myprotein ���Ѥ@�t�C�u��<a href=\"https://www.myprotein.tw/nutrition/protein.list\"><strong>�J�կ�</strong></a>��ܡA���U�z�b�����e��K�ֱ����Ħa�ɥR�J�ս�C</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p><img alt=\"�J�կ� �J�ս�\" sizes=\"(max-width: 1024px) 100vw, 1024px\" src=\"https://blogscdn.thehut.net/app/uploads/sites/510/2019/10/Post-Workout-Shake-1800x1200-min_1200x672_acf_cropped_1200x672_acf_cropped_1200x672_acf_cropped-1024x573_1585231345.jpg\" srcset=\"https://blogscdn.thehut.net/app/uploads/sites/510/2019/10/Post-Workout-Shake-1800x1200-min_1200x672_acf_cropped_1200x672_acf_cropped_1200x672_acf_cropped-1024x573_1585231345.jpg 1024w, https://blogscdn.thehut.net/app/uploads/sites/510/2019/10/Post-Workout-Shake-1800x1200-min_1200x672_acf_cropped_1200x672_acf_cropped_1200x672_acf_cropped-1024x573_1585231345-300x168.jpg 300w, https://blogscdn.thehut.net/app/uploads/sites/510/2019/10/Post-Workout-Shake-1800x1200-min_1200x672_acf_cropped_1200x672_acf_cropped_1200x672_acf_cropped-1024x573_1585231345-768x430.jpg 768w, https://blogscdn.thehut.net/app/uploads/sites/510/2019/10/Post-Workout-Shake-1800x1200-min_1200x672_acf_cropped_1200x672_acf_cropped_1200x672_acf_cropped-1024x573_1585231345-700x392.jpg 700w\" width=\"1024\" /></p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h3><strong>�ת�</strong></h3>\r\n" + 
				"\r\n" + 
				"<hr />\r\n" + 
				"<p>�����j�a���S���o�{�A�����ɡA�����W�i��|��� &ldquo;���b�U�N���d����&rdquo;�H�A�i�H��o�ǹB�ʵ����j�׸��C�B�ɶ��������B�ʡA�Ҧp�C�] 20�����C�b�B�ʤ��A��ڭ̪��٦צ����������h�ϥίתաA�תմN�Q���ӿU�N�H���ѯ�q�C�åB���q��Ŧ���תզb���鰷�d����t���n������A�]���W��������d���תիD�`�ܭ��n�C</p>\r\n" + 
				"\r\n" + 
				"<p><a href=\"https://www.myprotein.tw/nutrition/fibre-essential-fats.list\"><strong>���d�ת�&nbsp;</strong></a>���t�@�Ӧn�B�O�����P &ndash; �����U���C���Ƴt�סA�õ��H�a�ӧ���[�������P�A�o�i�H����ɶ��ɭ��C�@�ر`�������d�תմN�OMCT �o�A���O������ƪ��תոɥR�~�C</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h3><strong>�����ɥR�~</strong></h3>\r\n" + 
				"\r\n" + 
				"<hr />\r\n" + 
				"<p><strong>�����e����ɥR�~&nbsp;</strong>�i�H��n�a���U�A�F��V�m�ؼСC�p�G�A�Q�b�|�����V�m�������O�q�M��{�A����ٻĳo�@�����ɾ��Q�ҩ��O���ĥB�w�����C</p>\r\n" + 
				"\r\n" + 
				"<p>�p�G�A�Q�b�B�ʫe�b�p�ɸɥR�@�I��q�A����t���@�ئ]�M���ͯ�B�����������O�ܦn����ܡC�o������e�ɥR���i�H���w�h�ҷP�ô����B�ʮɪ���{�A�a�ӧ�n�������ĪG�C</p>\r\n" + 
				"\r\n" + 
				"<p>�L�צb����e�A�Τ���ɥR���A���n�T�O���Ӳ��~�����i��ާ@�A�Ҧp�O�H�\�٬O�Ÿ��A�ΡA�ܤ��Ψ�L���Ʒf�t���A�H��o�z�Q���ĪG�C</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p><img alt=\" �����e���Y\" sizes=\"(max-width: 700px) 100vw, 700px\" src=\"https://blogscdn.thehut.net/app/uploads/sites/510/2019/10/Blog-Drinkingwater-Male-700x385_1585231431.jpg\" srcset=\"https://blogscdn.thehut.net/app/uploads/sites/510/2019/10/Blog-Drinkingwater-Male-700x385_1585231431.jpg 700w, https://blogscdn.thehut.net/app/uploads/sites/510/2019/10/Blog-Drinkingwater-Male-700x385_1585231431-300x165.jpg 300w\" width=\"700\" /></p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h3>�����e�h�[�Y�F��H</h3>\r\n" + 
				"\r\n" + 
				"<hr />\r\n" + 
				"<p>��X�H�W�ݭn�Ҽ{���غئ]���A�h�������e�A�ڭ̸Ӷ��j�h�[�i���O�H</p>\r\n" + 
				"\r\n" + 
				"<p><strong>��ĳ�b����e2-3�p��</strong>�A�ýT�O�o�@�\�]�t���T�� Macros ���q��i���]�Ҥ��ƦX���A�R�����J�ս�M���d���תա^�C</p>");	
		art.insert(articleVO1);
		System.out.println("�s�W���\");
		
		articleVO1.setMem_id("MEM0000006");
		articleVO1.setTitle("���ݦ׵��");
		articleVO1.setArt_content("<p><strong>���ݦ�</strong>�O��ΡB�i�٪̨̳��w�諸�������@�A�C�קC���q�B�I�t�J�ս�A�S���ݾ�ߵo�D�C���A���ݦײi�N��B�ܮe���㱼�A�ЧA�p�󰵥X�F�`�B�h�Ī��������ݦ׮Ʋz�A�Ѥѳ��n�Y�n�G�C</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p>���ݦר��w�諸�D�n��]�A��_��L�������L�B���ͳ��Ӫ��C�סA�֦����J�ս�q���S�ʡA�w�����W��<a href=\"https://blog.worldgymtaiwan.com/diet-how-to-avoid-fat-storage\" target=\"_blank\">���</a>���n����C���]�]���C�סA�i�ծɤ@���p�ߡA���ݦ״N�|�ܮ����ߡC<br />\r\n" + 
				"&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p>�]���A�Q�n���פf�P�n�A�u���ݦ���ġv��k�A�@�w�n�ʰO�b�ߡA���A�N�@���B�N�W��C</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h2><strong>���ݦ����5�J�B</strong></h2>\r\n" + 
				"\r\n" + 
				"<p>���ݦצn�Y��5�J�B�A������1�����4���A��ܤ@�ؤ�k�i�H�I</p>\r\n" + 
				"\r\n" + 
				"<h3><br />\r\n" + 
				"1.�N�᪺���סA�C�C�h�B�A�i�N�C</h3>\r\n" + 
				"\r\n" + 
				"<p>���ݦפ��n�b�N�᪬�A���ɭԥߧY��J��l�εN�A�άO�ηL�i�l�ѭ�C<br />\r\n" + 
				"���T���k�A�e�@�ѱq�N���J�N�áA�n�Y30�����e�A���X�Ӧ^��ǷšC�o�˦A�i�N���ɭԡA�׽褣�|���Y�L�֡A�������]�����C</p>\r\n" + 
				"\r\n" + 
				"<h3><br />\r\n" + 
				"2.�w�Q��</h3>\r\n" + 
				"\r\n" + 
				"<p>���M�Q�ڪ����=100�@�ɤ��G5.5���J�Q�ڡA�Q�������z���@�ΡA���צY�i�����A����10%�t���q�A�ҥH�|�����C�o�O�ڦۤv�̷R�Ϊ���k�A�ܤ־M�@�ӱߤW�A�ĪG�̦n�C<br />\r\n" + 
				"<br />\r\n" + 
				"�p�s�O���Ʊ��A���w�t�~�[�J�j�q������(�κ����U�l��)�A���O����ʨ����B���԰��B�������B����׮۱�...���A�s�P���פ@�_�w�b�Q���A�j�Ѯ��X�Ӳi�N�A�|�ܭ���I</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h3>3.�g�J��</h3>\r\n" + 
				"\r\n" + 
				"<p>�J�եi�H�Φ��O�@�h�A����٦פ����y���C<br />\r\n" + 
				"�o��k�S�O�A�X�N�����p����A�M�J�G�B�ը��Ƥ@�_�դáA�U��ΡC�A�[�J���檣�@���A�W�n�Y�C</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h3>4.�[�J�u��ӾM�s</h3>\r\n" + 
				"\r\n" + 
				"<p>�u�ġv��Ϧפ����J�ս貣���ܤơA���׽��ܹ�C<br />\r\n" + 
				"�o�]�O�`�Ϊ���k�A�e�@�ѱN�N�ê����סB�u��B���ƾM�b�@�_�A�n�Y���ɭԡA����񽭵�A�@�_��J�N�c�e�N�A�A��S�h��(�y�f��)�I</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h3>5.�Ρu�e�v����k�i�N</h3>\r\n" + 
				"\r\n" + 
				"<p>�O��@�ӭ�h�A���ݦפ@�w�n�δe�����������F�L�קA�ιq��B�η���B�Τ��N�άO�N�c�A�n���ξl�Ŵe�N���n�B�I<br />\r\n" + 
				"<br />\r\n" + 
				"�i�q��k�j�@�����ݦץ[�b�M���A�q����_�ӫ�A�e10����<br />\r\n" + 
				"�i�e�Ϊk�j�示��o�A�����[���A���ݦ������U��3������������A�����\�W��\�A�e��7�����N�i�H�o�I<br />\r\n" + 
				"�i���N�k�j���u����A��J���ݦסA���@�u�ߧY�����A�e10�����C<br />\r\n" + 
				"�i�e�N�k�j�N�c�w��170��c�A��J���ݦׯN5�����A�N�c�����A�ξl�Ŵe�N10�����C</p>\r\n" + 
				"\r\n" + 
				"<p><br />\r\n" + 
				"�e�m�@�~�@�w�n��(�p�G�ѰO���A�ܤ�����+�Q�ھM5����)�A�Ӳ�5���O�i�էޥ��A�Ƿ|�F�������P�W��I</p>\r\n" + 
				"\r\n" + 
				"<p><br />\r\n" + 
				"���Y���ӤF�A��B�W�٪��H���ֵ��O�C</p>\r\n" + 
				"\r\n" + 
				"<p><br />\r\n" + 
				"�ﰷ�d�����B�������ɦV�A�[�W�ɱ`�����A�ݭn��i�������A�ר�O�J�ս�A�ҥH�X�G���O�C�Ѧۤv�a�K��F�w��u���ݦסv�o�D�D�����ܤơA���U�ӷ|����5�D�u����K���\�v�H�Τ���2�D�u���ݦצ����\�v�A���A���W�ٴ���\�A���ȦY���C</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p>�o�X�D���]�p�A�h�H�u�������ơv���ը��A��W�K��������B�]���ȼW�[�h�l���q�F�t�~�A�|���Υt�@�ӶW�ŭ����w�u�Գ��ζǲοP���v�A���A�W�[�f�P�M�����P�C</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h2><strong>���ݦ�7��Ʋz���</strong></h2>\r\n" + 
				"\r\n" + 
				"<h3>���1�i���N�������ݦצ��P���j</h3>\r\n" + 
				"\r\n" + 
				"<p>�m�����n���ݦ�1���B�ᷦ��1�M�B�ǲ�<a href=\"https://blog.worldgymtaiwan.com/%E7%87%95%E9%BA%A5%E6%80%8E%E9%BA%BC%E5%90%83%E6%89%8D%E4%B8%8D%E6%9C%83%E8%83%96\" target=\"_blank\">�P��</a>����40g�B�ը���(��������ơB���Ԥ��B�J�ԡB�Q)<br />\r\n" + 
				"<br />\r\n" + 
				"�m�e�m�@�~�n�e�@�ѡA���ݦתw�Q��<br />\r\n" + 
				"<br />\r\n" + 
				"�m�@�k�n<br />\r\n" + 
				"Step1.���N�k�i�N���ݦשM�ᷦ��C�ǲοP��+��1�M�A�q��]15�����C<br />\r\n" + 
				"Step2.���������F�A��i�K���A���W�ը��ơA�N�i�H�a�X���F�C<br />\r\n" + 
				"<br />\r\n" + 
				"Tip�G�o�ӫK��i�H�Y�u�`�šv�A���ݭn�A�[���A�ܤ�K�C</p>\r\n" + 
				"\r\n" + 
				"<p><img alt=\"���N�������ݦצ��Գ�\" sizes=\"(max-width: 700px) 100vw, 700px\" src=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E6%B0%B4%E7%85%AE%E9%A6%99%E6%96%99%E9%9B%9E%E8%83%B8%E8%82%89%E4%BD%90%E8%97%9C%E9%BA%A5.jpg?width=700&amp;name=%E6%B0%B4%E7%85%AE%E9%A6%99%E6%96%99%E9%9B%9E%E8%83%B8%E8%82%89%E4%BD%90%E8%97%9C%E9%BA%A5.jpg\" srcset=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E6%B0%B4%E7%85%AE%E9%A6%99%E6%96%99%E9%9B%9E%E8%83%B8%E8%82%89%E4%BD%90%E8%97%9C%E9%BA%A5.jpg?width=350&amp;name=%E6%B0%B4%E7%85%AE%E9%A6%99%E6%96%99%E9%9B%9E%E8%83%B8%E8%82%89%E4%BD%90%E8%97%9C%E9%BA%A5.jpg 350w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E6%B0%B4%E7%85%AE%E9%A6%99%E6%96%99%E9%9B%9E%E8%83%B8%E8%82%89%E4%BD%90%E8%97%9C%E9%BA%A5.jpg?width=700&amp;name=%E6%B0%B4%E7%85%AE%E9%A6%99%E6%96%99%E9%9B%9E%E8%83%B8%E8%82%89%E4%BD%90%E8%97%9C%E9%BA%A5.jpg 700w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E6%B0%B4%E7%85%AE%E9%A6%99%E6%96%99%E9%9B%9E%E8%83%B8%E8%82%89%E4%BD%90%E8%97%9C%E9%BA%A5.jpg?width=1050&amp;name=%E6%B0%B4%E7%85%AE%E9%A6%99%E6%96%99%E9%9B%9E%E8%83%B8%E8%82%89%E4%BD%90%E8%97%9C%E9%BA%A5.jpg 1050w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E6%B0%B4%E7%85%AE%E9%A6%99%E6%96%99%E9%9B%9E%E8%83%B8%E8%82%89%E4%BD%90%E8%97%9C%E9%BA%A5.jpg?width=1400&amp;name=%E6%B0%B4%E7%85%AE%E9%A6%99%E6%96%99%E9%9B%9E%E8%83%B8%E8%82%89%E4%BD%90%E8%97%9C%E9%BA%A5.jpg 1400w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E6%B0%B4%E7%85%AE%E9%A6%99%E6%96%99%E9%9B%9E%E8%83%B8%E8%82%89%E4%BD%90%E8%97%9C%E9%BA%A5.jpg?width=1750&amp;name=%E6%B0%B4%E7%85%AE%E9%A6%99%E6%96%99%E9%9B%9E%E8%83%B8%E8%82%89%E4%BD%90%E8%97%9C%E9%BA%A5.jpg 1750w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E6%B0%B4%E7%85%AE%E9%A6%99%E6%96%99%E9%9B%9E%E8%83%B8%E8%82%89%E4%BD%90%E8%97%9C%E9%BA%A5.jpg?width=2100&amp;name=%E6%B0%B4%E7%85%AE%E9%A6%99%E6%96%99%E9%9B%9E%E8%83%B8%E8%82%89%E4%BD%90%E8%97%9C%E9%BA%A5.jpg 2100w\" width=\"700\" /></p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h3>���2�i��İ_�q�N���ݡj</h3>\r\n" + 
				"\r\n" + 
				"<p>�m�����n���ݦ�1���B<a href=\"https://blog.worldgymtaiwan.com/tag/%E9%A3%B2%E9%A3%9F/%E8%98%86%E7%AD%8D7%E5%A4%A7%E5%A5%BD%E8%99%95\" target=\"_blank\">Ī��</a>�B�h��1�������B�����˰��T�B�ը���(���ԯ��B�J�ԡB�Q)<br />\r\n" + 
				"<br />\r\n" + 
				"�m�e�m�@�~�n�e�@�ѡA���ݦתw�Q��<br />\r\n" + 
				"<br />\r\n" + 
				"�m�@�k�n<br />\r\n" + 
				"Step1.���ݦק��ûq�W�ը���(���ԯ��i�H��h�@�I)�C�N�L�ѤU�ӤW���|�h���&gt;���ݦ�&gt;�@���h��A�O�@�I�o�AĪ�������C<br />\r\n" + 
				"Step2. �δe�N�k�A�N���A���W<a href=\"https://blog.worldgymtaiwan.com/tag/%E9%A3%B2%E9%A3%9F/10%E7%A8%AE%E5%81%A5%E5%BA%B7%E8%B5%B7%E5%8F%B8\" target=\"_blank\">�����˰_�q</a>�C<br />\r\n" + 
				"<br />\r\n" + 
				"Tip�G�o�ӫK��ݭn�u�[���A�Y(�j���L�i120��)�v�A�����˷|�[���ɿĤơC</p>\r\n" + 
				"\r\n" + 
				"<p><img alt=\"��İ_�q�N����\" sizes=\"(max-width: 700px) 100vw, 700px\" src=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E6%A9%99%E6%B1%81%E8%B5%B7%E5%8F%B8%E7%83%A4%E9%9B%9E%E8%83%B8.jpg?width=700&amp;name=%E6%A9%99%E6%B1%81%E8%B5%B7%E5%8F%B8%E7%83%A4%E9%9B%9E%E8%83%B8.jpg\" srcset=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E6%A9%99%E6%B1%81%E8%B5%B7%E5%8F%B8%E7%83%A4%E9%9B%9E%E8%83%B8.jpg?width=350&amp;name=%E6%A9%99%E6%B1%81%E8%B5%B7%E5%8F%B8%E7%83%A4%E9%9B%9E%E8%83%B8.jpg 350w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E6%A9%99%E6%B1%81%E8%B5%B7%E5%8F%B8%E7%83%A4%E9%9B%9E%E8%83%B8.jpg?width=700&amp;name=%E6%A9%99%E6%B1%81%E8%B5%B7%E5%8F%B8%E7%83%A4%E9%9B%9E%E8%83%B8.jpg 700w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E6%A9%99%E6%B1%81%E8%B5%B7%E5%8F%B8%E7%83%A4%E9%9B%9E%E8%83%B8.jpg?width=1050&amp;name=%E6%A9%99%E6%B1%81%E8%B5%B7%E5%8F%B8%E7%83%A4%E9%9B%9E%E8%83%B8.jpg 1050w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E6%A9%99%E6%B1%81%E8%B5%B7%E5%8F%B8%E7%83%A4%E9%9B%9E%E8%83%B8.jpg?width=1400&amp;name=%E6%A9%99%E6%B1%81%E8%B5%B7%E5%8F%B8%E7%83%A4%E9%9B%9E%E8%83%B8.jpg 1400w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E6%A9%99%E6%B1%81%E8%B5%B7%E5%8F%B8%E7%83%A4%E9%9B%9E%E8%83%B8.jpg?width=1750&amp;name=%E6%A9%99%E6%B1%81%E8%B5%B7%E5%8F%B8%E7%83%A4%E9%9B%9E%E8%83%B8.jpg 1750w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E6%A9%99%E6%B1%81%E8%B5%B7%E5%8F%B8%E7%83%A4%E9%9B%9E%E8%83%B8.jpg?width=2100&amp;name=%E6%A9%99%E6%B1%81%E8%B5%B7%E5%8F%B8%E7%83%A4%E9%9B%9E%E8%83%B8.jpg 2100w\" width=\"700\" /></p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h3>���3�i�Aۣ�Գ��N���ݦסj</h3>\r\n" + 
				"\r\n" + 
				"<p>�m�����n���ݦ�1���BĪ���B�E��ۣ(�������w��ۣ�����i�H�A�Ҧp�G<a href=\"https://blog.worldgymtaiwan.com/healthy-food-pleurotus-eryngii-meal\" target=\"_blank\">���jۣ</a>)�B<a href=\"https://blog.worldgymtaiwan.com/%E8%97%9C%E9%BA%A5%E9%A3%9F%E8%AD%9C\" target=\"_blank\">�Գ�</a>40g�B�ը���(�f�X��B���ԯ��B�J�ԡB�Q)<br />\r\n" + 
				"<br />\r\n" + 
				"�m�e�m�@�~�n�e�@�ѡA���ݦתw�Q��<br />\r\n" + 
				"<br />\r\n" + 
				"�m�@�k�n<br />\r\n" + 
				"Step1.�Գ�+1/2�M���A�q��]15�����C���ݦק��ûq�W�ը���(�f�X��֤@�I�A���ԯ��i�H��h�@�I)�C�N�L���\�����ݦסA�O�W�@�I�o�AĪ�������C<br />\r\n" + 
				"Step2. �δe�N�k�A���ׯN���A�|�����ġA�i�H�ΨӪ�ۣ�C�M��N�i�H�˶i�K���o�I<br />\r\n" + 
				"<br />\r\n" + 
				"�mTip�n�o�ӫK��i�H�u�Y�`�šv�A�[���Y(�j���L�i120��)�]�����C</p>\r\n" + 
				"\r\n" + 
				"<p><img alt=\"�Aۣ�Գ��N���ݦ�\" sizes=\"(max-width: 700px) 100vw, 700px\" src=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E9%AE%AE%E8%8F%87%E8%97%9C%E9%BA%A5%E7%83%A4%E9%9B%9E%E8%83%B8%E8%82%89.jpg?width=700&amp;name=%E9%AE%AE%E8%8F%87%E8%97%9C%E9%BA%A5%E7%83%A4%E9%9B%9E%E8%83%B8%E8%82%89.jpg\" srcset=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E9%AE%AE%E8%8F%87%E8%97%9C%E9%BA%A5%E7%83%A4%E9%9B%9E%E8%83%B8%E8%82%89.jpg?width=350&amp;name=%E9%AE%AE%E8%8F%87%E8%97%9C%E9%BA%A5%E7%83%A4%E9%9B%9E%E8%83%B8%E8%82%89.jpg 350w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E9%AE%AE%E8%8F%87%E8%97%9C%E9%BA%A5%E7%83%A4%E9%9B%9E%E8%83%B8%E8%82%89.jpg?width=700&amp;name=%E9%AE%AE%E8%8F%87%E8%97%9C%E9%BA%A5%E7%83%A4%E9%9B%9E%E8%83%B8%E8%82%89.jpg 700w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E9%AE%AE%E8%8F%87%E8%97%9C%E9%BA%A5%E7%83%A4%E9%9B%9E%E8%83%B8%E8%82%89.jpg?width=1050&amp;name=%E9%AE%AE%E8%8F%87%E8%97%9C%E9%BA%A5%E7%83%A4%E9%9B%9E%E8%83%B8%E8%82%89.jpg 1050w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E9%AE%AE%E8%8F%87%E8%97%9C%E9%BA%A5%E7%83%A4%E9%9B%9E%E8%83%B8%E8%82%89.jpg?width=1400&amp;name=%E9%AE%AE%E8%8F%87%E8%97%9C%E9%BA%A5%E7%83%A4%E9%9B%9E%E8%83%B8%E8%82%89.jpg 1400w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E9%AE%AE%E8%8F%87%E8%97%9C%E9%BA%A5%E7%83%A4%E9%9B%9E%E8%83%B8%E8%82%89.jpg?width=1750&amp;name=%E9%AE%AE%E8%8F%87%E8%97%9C%E9%BA%A5%E7%83%A4%E9%9B%9E%E8%83%B8%E8%82%89.jpg 1750w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E9%AE%AE%E8%8F%87%E8%97%9C%E9%BA%A5%E7%83%A4%E9%9B%9E%E8%83%B8%E8%82%89.jpg?width=2100&amp;name=%E9%AE%AE%E8%8F%87%E8%97%9C%E9%BA%A5%E7%83%A4%E9%9B%9E%E8%83%B8%E8%82%89.jpg 2100w\" width=\"700\" /></p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h3>���4�i�����u�����ݡj</h3>\r\n" + 
				"\r\n" + 
				"<p>�m�����n���ݦ�1���BĪ���B���ڽ�(����)�B�ǲοP��40g�B�ը���(<a href=\"https://blog.worldgymtaiwan.com/%E5%B8%8C%E8%87%98%E5%84%AA%E6%A0%BC%E6%B8%9B%E8%82%A5%E9%A3%9F%E8%AD%9C\" target=\"_blank\">�u��</a>�B���ԯ��B�J�ԡB�Q)<br />\r\n" + 
				"<br />\r\n" + 
				"�m�e�m�@�~�n�e�@�ѡA���ק��ûq�W�ը��ơA�u��i�H�h�@�I�A��B�c�M�s�C<br />\r\n" + 
				"<br />\r\n" + 
				"�m�@�k�n<br />\r\n" + 
				"Step1.�ǲοP��+��1�M�A�q��]15�����C���ݦץδe�Ϊk�A�M�������i�h�示�ΡC<br />\r\n" + 
				"Step2.Ī��+���ڽ��A�O�W�@�Ǫo�A��i�N�c10����(���w��170��c)�A�Ҧ��������F�A�N��˽L�C<br />\r\n" + 
				"<br />\r\n" + 
				"�mTip�n�o�ӫK��i�H�u�Y�`�šv�A�Υ[���Y(�j���L�i120��)�C<br />\r\n" + 
				"<img alt=\"�����u������\" sizes=\"(max-width: 700px) 100vw, 700px\" src=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E9%A6%99%E7%85%8E%E5%84%AA%E6%A0%BC%E9%9B%9E%E8%83%B8.jpg?width=700&amp;name=%E9%A6%99%E7%85%8E%E5%84%AA%E6%A0%BC%E9%9B%9E%E8%83%B8.jpg\" srcset=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E9%A6%99%E7%85%8E%E5%84%AA%E6%A0%BC%E9%9B%9E%E8%83%B8.jpg?width=350&amp;name=%E9%A6%99%E7%85%8E%E5%84%AA%E6%A0%BC%E9%9B%9E%E8%83%B8.jpg 350w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E9%A6%99%E7%85%8E%E5%84%AA%E6%A0%BC%E9%9B%9E%E8%83%B8.jpg?width=700&amp;name=%E9%A6%99%E7%85%8E%E5%84%AA%E6%A0%BC%E9%9B%9E%E8%83%B8.jpg 700w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E9%A6%99%E7%85%8E%E5%84%AA%E6%A0%BC%E9%9B%9E%E8%83%B8.jpg?width=1050&amp;name=%E9%A6%99%E7%85%8E%E5%84%AA%E6%A0%BC%E9%9B%9E%E8%83%B8.jpg 1050w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E9%A6%99%E7%85%8E%E5%84%AA%E6%A0%BC%E9%9B%9E%E8%83%B8.jpg?width=1400&amp;name=%E9%A6%99%E7%85%8E%E5%84%AA%E6%A0%BC%E9%9B%9E%E8%83%B8.jpg 1400w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E9%A6%99%E7%85%8E%E5%84%AA%E6%A0%BC%E9%9B%9E%E8%83%B8.jpg?width=1750&amp;name=%E9%A6%99%E7%85%8E%E5%84%AA%E6%A0%BC%E9%9B%9E%E8%83%B8.jpg 1750w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E9%A6%99%E7%85%8E%E5%84%AA%E6%A0%BC%E9%9B%9E%E8%83%B8.jpg?width=2100&amp;name=%E9%A6%99%E7%85%8E%E5%84%AA%E6%A0%BC%E9%9B%9E%E8%83%B8.jpg 2100w\" width=\"700\" /></p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h3>���5�iۣۣ�����Գ��j</h3>\r\n" + 
				"\r\n" + 
				"<p>�m�����n���ݦ�1���BĨۣ�����B�Գ�40g�B�ը���(���ԯ��B�J�ԡB�Q)<br />\r\n" + 
				"<br />\r\n" + 
				"�m�e�m�@�~�n�e�@�ѡA���ݦתw�Q��<br />\r\n" + 
				"<br />\r\n" + 
				"�m�@�k�n<br />\r\n" + 
				"Step1.�Գ�+1/2�M���A�q��]15�����C���ݦ׻q�W�ը��ơA�δe�Ϊk�C<br />\r\n" + 
				"Step2.�����ݦת���ġA�A�~�򪣬vۣ�C���������˽L�N�i�H�o�I<br />\r\n" + 
				"<br />\r\n" + 
				"�mTip�n�o�ӫK��i�H�u�Y�`�šv�A�Υ[���Y(�j���L�i120��)�C</p>\r\n" + 
				"\r\n" + 
				"<p><img alt=\"ۣۣ�����Գ�\" sizes=\"(max-width: 700px) 100vw, 700px\" src=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E8%8F%87%E8%8F%87%E9%9B%9E%E4%BD%90%E8%97%9C%E9%BA%A5.jpg?width=700&amp;name=%E8%8F%87%E8%8F%87%E9%9B%9E%E4%BD%90%E8%97%9C%E9%BA%A5.jpg\" srcset=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E8%8F%87%E8%8F%87%E9%9B%9E%E4%BD%90%E8%97%9C%E9%BA%A5.jpg?width=350&amp;name=%E8%8F%87%E8%8F%87%E9%9B%9E%E4%BD%90%E8%97%9C%E9%BA%A5.jpg 350w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E8%8F%87%E8%8F%87%E9%9B%9E%E4%BD%90%E8%97%9C%E9%BA%A5.jpg?width=700&amp;name=%E8%8F%87%E8%8F%87%E9%9B%9E%E4%BD%90%E8%97%9C%E9%BA%A5.jpg 700w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E8%8F%87%E8%8F%87%E9%9B%9E%E4%BD%90%E8%97%9C%E9%BA%A5.jpg?width=1050&amp;name=%E8%8F%87%E8%8F%87%E9%9B%9E%E4%BD%90%E8%97%9C%E9%BA%A5.jpg 1050w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E8%8F%87%E8%8F%87%E9%9B%9E%E4%BD%90%E8%97%9C%E9%BA%A5.jpg?width=1400&amp;name=%E8%8F%87%E8%8F%87%E9%9B%9E%E4%BD%90%E8%97%9C%E9%BA%A5.jpg 1400w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E8%8F%87%E8%8F%87%E9%9B%9E%E4%BD%90%E8%97%9C%E9%BA%A5.jpg?width=1750&amp;name=%E8%8F%87%E8%8F%87%E9%9B%9E%E4%BD%90%E8%97%9C%E9%BA%A5.jpg 1750w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E8%8F%87%E8%8F%87%E9%9B%9E%E4%BD%90%E8%97%9C%E9%BA%A5.jpg?width=2100&amp;name=%E8%8F%87%E8%8F%87%E9%9B%9E%E4%BD%90%E8%97%9C%E9%BA%A5.jpg 2100w\" width=\"700\" /></p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h3>���6�i���������R�S����j</h3>\r\n" + 
				"\r\n" + 
				"<p>�m�����n��������֡B���ݦסB�f�X�k�B�_�q�B�o�{mozzarella�_�q(�i�Ω��İ_�q�B���V�o�^�B�͵�C<br />\r\n" + 
				"<br />\r\n" + 
				"�m�@�k�n<br />\r\n" + 
				"Step1. �N�c170��c�w���C���ݦפ��p���[�Q�դáA�ƥΡC<br />\r\n" + 
				"Step2. ��l����@�I�o�A�����[���A���פU���3�����A�[�J�f�X�k�A�ժ������ץ����_��A�ƥΡC<br />\r\n" + 
				"Step3. ��֤W�\�񭹧��G�_�q���B�f�X���סBmozzarella�_�q�A�t�[�@�I�v�����ը��A��J�N�c5�����A�X�l��i�H�O�@�Ǿ��V�o�M�͵�A�W��C<br />\r\n" + 
				"<br />\r\n" + 
				"�mTip�n��ֳ̥~��w�d2�������n�g��ơA�N��i�H�O���p�ܡC</p>\r\n" + 
				"\r\n" + 
				"<p><img alt=\"���������R�S����\" sizes=\"(max-width: 700px) 100vw, 700px\" src=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E5%AB%A9%E9%9B%9E%E7%91%AA%E6%A0%BC%E9%BA%97%E7%89%B9%E8%96%84%E9%A4%85.jpg?width=700&amp;name=%E5%AB%A9%E9%9B%9E%E7%91%AA%E6%A0%BC%E9%BA%97%E7%89%B9%E8%96%84%E9%A4%85.jpg\" srcset=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E5%AB%A9%E9%9B%9E%E7%91%AA%E6%A0%BC%E9%BA%97%E7%89%B9%E8%96%84%E9%A4%85.jpg?width=350&amp;name=%E5%AB%A9%E9%9B%9E%E7%91%AA%E6%A0%BC%E9%BA%97%E7%89%B9%E8%96%84%E9%A4%85.jpg 350w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E5%AB%A9%E9%9B%9E%E7%91%AA%E6%A0%BC%E9%BA%97%E7%89%B9%E8%96%84%E9%A4%85.jpg?width=700&amp;name=%E5%AB%A9%E9%9B%9E%E7%91%AA%E6%A0%BC%E9%BA%97%E7%89%B9%E8%96%84%E9%A4%85.jpg 700w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E5%AB%A9%E9%9B%9E%E7%91%AA%E6%A0%BC%E9%BA%97%E7%89%B9%E8%96%84%E9%A4%85.jpg?width=1050&amp;name=%E5%AB%A9%E9%9B%9E%E7%91%AA%E6%A0%BC%E9%BA%97%E7%89%B9%E8%96%84%E9%A4%85.jpg 1050w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E5%AB%A9%E9%9B%9E%E7%91%AA%E6%A0%BC%E9%BA%97%E7%89%B9%E8%96%84%E9%A4%85.jpg?width=1400&amp;name=%E5%AB%A9%E9%9B%9E%E7%91%AA%E6%A0%BC%E9%BA%97%E7%89%B9%E8%96%84%E9%A4%85.jpg 1400w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E5%AB%A9%E9%9B%9E%E7%91%AA%E6%A0%BC%E9%BA%97%E7%89%B9%E8%96%84%E9%A4%85.jpg?width=1750&amp;name=%E5%AB%A9%E9%9B%9E%E7%91%AA%E6%A0%BC%E9%BA%97%E7%89%B9%E8%96%84%E9%A4%85.jpg 1750w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E5%AB%A9%E9%9B%9E%E7%91%AA%E6%A0%BC%E9%BA%97%E7%89%B9%E8%96%84%E9%A4%85.jpg?width=2100&amp;name=%E5%AB%A9%E9%9B%9E%E7%91%AA%E6%A0%BC%E9%BA%97%E7%89%B9%E8%96%84%E9%A4%85.jpg 2100w\" width=\"700\" /></p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h3>���7�i�p�ܦR�q���ױ��j</h3>\r\n" + 
				"\r\n" + 
				"<p>�m�����n���ݦ�1���BĨۣ�B�R�q�B�ϥ��ŹT�B������԰_�q���B�v�����B�����˰_�q�B�q��������(�εf�X��)�C<br />\r\n" + 
				"<br />\r\n" + 
				"�m�@�k�n<br />\r\n" + 
				"Step1. ���שMĨۣ���p���A���O�����A�ƥΡC<br />\r\n" + 
				"Step2.�R�q�h��A�쥭�C�s�@�R�q���A�ѤU�ӤW�G�R�q�ֺ�����԰_�q���ֽ���������ס�Ĩۣ�֦ϥ��ŹT�֬v�����A�N�R�q�C�C�����J�Ҧ����`�A�}�f�¤U�A�ƥΡC<br />\r\n" + 
				"Step3.�����礤���[���A��J�����˰_�q�A�R�q���}�f�¤U�A��b�����˰_�q�W�A�����˰_�q�Φܪ�����A½���ΦR�q�A��4�ӭ��N�����o�I<br />\r\n" + 
				"<br />\r\n" + 
				"�mTip�n<br />\r\n" + 
				"�R�q�n�Τj���@�I���A����A���w���f�����i�H�C<br />\r\n" + 
				"�����˰_�q�D�n�O�y�ʤf�z�@�ΡA�Q��ּ��q�A���[�]ok�C<br />\r\n" + 
				"<img alt=\"�p�ܦR�q���ױ�\" sizes=\"(max-width: 700px) 100vw, 700px\" src=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E9%85%A5%E8%84%86%E5%90%90%E5%8F%B8%E9%9B%9E%E8%82%89%E6%8D%B2.jpg?width=700&amp;name=%E9%85%A5%E8%84%86%E5%90%90%E5%8F%B8%E9%9B%9E%E8%82%89%E6%8D%B2.jpg\" srcset=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E9%85%A5%E8%84%86%E5%90%90%E5%8F%B8%E9%9B%9E%E8%82%89%E6%8D%B2.jpg?width=350&amp;name=%E9%85%A5%E8%84%86%E5%90%90%E5%8F%B8%E9%9B%9E%E8%82%89%E6%8D%B2.jpg 350w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E9%85%A5%E8%84%86%E5%90%90%E5%8F%B8%E9%9B%9E%E8%82%89%E6%8D%B2.jpg?width=700&amp;name=%E9%85%A5%E8%84%86%E5%90%90%E5%8F%B8%E9%9B%9E%E8%82%89%E6%8D%B2.jpg 700w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E9%85%A5%E8%84%86%E5%90%90%E5%8F%B8%E9%9B%9E%E8%82%89%E6%8D%B2.jpg?width=1050&amp;name=%E9%85%A5%E8%84%86%E5%90%90%E5%8F%B8%E9%9B%9E%E8%82%89%E6%8D%B2.jpg 1050w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E9%85%A5%E8%84%86%E5%90%90%E5%8F%B8%E9%9B%9E%E8%82%89%E6%8D%B2.jpg?width=1400&amp;name=%E9%85%A5%E8%84%86%E5%90%90%E5%8F%B8%E9%9B%9E%E8%82%89%E6%8D%B2.jpg 1400w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E9%85%A5%E8%84%86%E5%90%90%E5%8F%B8%E9%9B%9E%E8%82%89%E6%8D%B2.jpg?width=1750&amp;name=%E9%85%A5%E8%84%86%E5%90%90%E5%8F%B8%E9%9B%9E%E8%82%89%E6%8D%B2.jpg 1750w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E9%85%A5%E8%84%86%E5%90%90%E5%8F%B8%E9%9B%9E%E8%82%89%E6%8D%B2.jpg?width=2100&amp;name=%E9%85%A5%E8%84%86%E5%90%90%E5%8F%B8%E9%9B%9E%E8%82%89%E6%8D%B2.jpg 2100w\" width=\"700\" /></p>");	
		art.insert(articleVO1);
		System.out.println("�s�W���\");
		
		articleVO1.setMem_id("MEM0000007");
		articleVO1.setTitle("���������M����");
		articleVO1.setArt_content("<p>�j�h�ƤH�|���u���U�Z�~���ťh�����СC�U�Z���^�a�A���}���C�Y�����A�𮧤@�Ӥp�ɥ��k�~������C����[���񳣨�Ӥp�ɥH�W�A�V�m���^�a���ߤW�E&mdash;�Q�I�F�C�ҥH�j�����H�V�m�᳣�O���I�J�կ��B�Y�I���G���򪺡A�̦h�[�I�W�ٯ��A�N��ı�F�C�@�O�S�ɶ��A�G�O�Ȫ��D�����h�Y�C���V�m��O�������i����ݨD�̩������ɭԡC�o�Ӯɬq����N�³̧֡A���e���x�s�תաA�ݭn�ɥR�j�q���Ҥ��M�J�ս�C</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p><img src=/EA102G4/front-end/article/assets/img/blog/G1.jpg alt=\"\">" + 
				"<br />\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p>�J�կ��M²��Ҥ��u�ມ���V�m��1&mdash;2�Ӥp�ɪ��ݭn�A���q�̫�@�\��ĤG�Ѧ��W�_�ɫ�i�\�A�������Q�X�Ӥp�ɤ���i���C�����}���x�Ʊo����R���ɥR�|�v�T�ĤG�Ѫ���બ�A�A�ӥB����V�m�}�a���٦��ֺ����L�F��i�l�����̨ήɾ��A���ɶ��o����R������i�ɥR�״_�]�|���ѡC�o�ˤ��������F�٦סA�����p���ٷ|��i���}�ɭP�V�m�V�G�Ϊ̨����z�C</p>\r\n" + 
				"\r\n" + 
				"<p>�ڪ��W�Z�ڰ��������߱o�N�O�G���q��V�m�ɶ����e�A�����󪺳̦n���W�Τ��ȰV�m�A�o�˱q�V�m���ߤW��ı�e������h�Y�X�y�A�R���a�ɥR����һݭn����i�A�P�ɤ]�i�H�קK��ı�e�Y�j�q�Ҥ��ӾɭP�תչL�h����n�C�p�G�A�u���U�Z��~���ɶ��A�����ĳ�A�V�m�e�����n�Y�������߶��C�U�Z�e�ΤU�Z��Y�@�I�ѱ��B�C�Y�B�����ѥ]�����ƦX�Ҥ�(���n�Ӧh)�A�N�ണ�Ѩ�������q�i��j�j�׾���V�m�F�C</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p><img src=/EA102G4/front-end/article/assets/img/blog/e01.jpg alt=\"\">" + 
				"<br />\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p>��軡�A�U�Ȥ��I�ɥR�@�I�ƦX�Ҥ��A����U�Ȥ��I�b�Τ��I�N�i�H�찷���ж}�l����V�m�C����V�m���ɶ��A���Ĳv�a�����V�m���Ȥ����(�̦h�@�p��)�C�V�m��ߧY�Y������²��Ҥ��[�W�ٯ��A�o�Ӯɭ����Ӥ��W�L�ߤW�C�I�ΤC�I�b�A�Ϊ̧󦭡C�C�I�b�e�^�a�Y�������\(���צ���������)�Q�I���k�i�H�A�ɥR�@���A�H�C�װ��J�խ������D(�תէt�q����h���H�N�i�H�h���ƦX�Ҥ�)�C�p�G��ı�ɶ�����ߡA��軡�ߤW�Q�G�I�έ��@�I�~�Ϊ��H�A�Ϋe�A�ܤ@�M�J�կ��C�Ъ`�N�A���M�V�m��ݭn�j�q�����ɥR�A���]�n�`�N�O�֦Y�h�\�A���ҭJ�Y����C</p>");	
		art.insert(articleVO1);
		System.out.println("�s�W���\");
		
		articleVO1.setMem_id("MEM0000008");
		articleVO1.setTitle("��׵��");
		articleVO1.setArt_content("<p>�����A�@���[���A�N�O�u���&ne;��v�A���²��A�@�Ѥ֦Y2�\�N����魫�F�A���O�Ҵ_�D���֡I�]���������O��h�餺�������A�Ʀ���i��������<a href=\"https://blog.worldgymtaiwan.com/disease-prevention-six-ways-causes-muscle-loss\" target=\"_blank\">��h�٦�(�S���٦׭D��ְڡI</a>??)�A�A�H���G�F�A���S���I</p>\r\n" + 
				"\r\n" + 
				"<p><img alt=\"�ݹL�ӡI �M�ݥ~���ڡu��׵��v���Z�j���}�i2020��s�j\" sizes=\"(max-width: 700px) 100vw, 700px\" src=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%914.png?width=700&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%914.png\" srcset=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%914.png?width=350&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%914.png 350w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%914.png?width=700&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%914.png 700w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%914.png?width=1050&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%914.png 1050w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%914.png?width=1400&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%914.png 1400w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%914.png?width=1750&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%914.png 1750w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%914.png?width=2100&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%914.png 2100w\" width=\"700\" /></p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p>���򬰦�{�b��׵��o��y��H�Q�Q�ݡA�W�Z�g�`�[���B���B�ʡB�������e����&hellip;�A�o���a�ߺD�A�ɭP�A<a href=\"https://blog.worldgymtaiwan.com/tag/%E9%A3%B2%E9%A3%9F/20%E7%A8%AE%E5%BC%B7%E5%8A%9B%E6%8E%92%E6%AF%92%E9%A3%9F%E7%89%A9\" target=\"_blank\">�Y�i�@��r��</a>�M�W�[�תաA�תլO�]�t�ݪ��쪺�֤U�תթM�ݤ��쪺��Ŧ�תաC�S�O�����A<a href=\"https://blog.worldgymtaiwan.com/tag/%E5%81%A5%E5%BA%B7/%E7%BE%8E%E9%AB%94/4%E6%8B%9B%E6%B6%88%E9%99%A4%E5%85%A7%E8%87%9F%E8%84%82%E8%82%AA\" target=\"_blank\">��Ŧ�תշ|�M�`�찷�d</a>�A�ܦh�H�o���M�����C</p>\r\n" + 
				"\r\n" + 
				"<p>�~���گu�������Y�짹��������\�A�ҥH���j�a��z��Ϊ���׵��A�T�\�U3���\�I�H�A�f�t�C�аȥ��O�o�A��������O�j�{�l�A�ӬO�Y����n����i�A���x�~��Q�ή��ӱ����q��I</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h2><strong>�u��׵��v�����ȩ񶺮ɶ� �����~���ڪ�����</strong></h2>\r\n" + 
				"\r\n" + 
				"<h3>1.���\����(����\�]�i�H��I)</h3>\r\n" + 
				"\r\n" + 
				"<p>??�ɶq�����n�u����v�B���~�H�L�}���D�B����M�s�L�����C</p>\r\n" + 
				"\r\n" + 
				"<ul>\r\n" + 
				"	<li><a href=\"https://blog.worldgymtaiwan.com/tag/%E9%A3%B2%E9%A3%9F/%E8%B1%86%E6%BC%BF%E6%B8%9B%E8%82%A5\" target=\"_blank\">����</a>+��������+���]�J1~2��</li>\r\n" + 
				"	<li>�©@��+�׳J�R�q(������)+����F��(���[���D��)�Τ��G�@���C</li>\r\n" + 
				"	<li>������(�Ψ�L�\����)+�C���J��+����F��(���[���D��)�Τ��G�@���C</li>\r\n" + 
				"</ul>\r\n" + 
				"\r\n" + 
				"<p>�©@��+���ٳJ����T���v�C�]���զR�q�ݩ��o�������A�֦Y�@�I����n�A�ҥH�u�Y�@���C</p>\r\n" + 
				"\r\n" + 
				"<p><img alt=\"��צ��\-1\" sizes=\"(max-width: 700px) 100vw, 700px\" src=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E6%B8%9B%E8%84%82%E6%97%A9%E9%A4%90-1.jpg?width=700&amp;name=%E6%B8%9B%E8%84%82%E6%97%A9%E9%A4%90-1.jpg\" srcset=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E6%B8%9B%E8%84%82%E6%97%A9%E9%A4%90-1.jpg?width=350&amp;name=%E6%B8%9B%E8%84%82%E6%97%A9%E9%A4%90-1.jpg 350w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E6%B8%9B%E8%84%82%E6%97%A9%E9%A4%90-1.jpg?width=700&amp;name=%E6%B8%9B%E8%84%82%E6%97%A9%E9%A4%90-1.jpg 700w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E6%B8%9B%E8%84%82%E6%97%A9%E9%A4%90-1.jpg?width=1050&amp;name=%E6%B8%9B%E8%84%82%E6%97%A9%E9%A4%90-1.jpg 1050w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E6%B8%9B%E8%84%82%E6%97%A9%E9%A4%90-1.jpg?width=1400&amp;name=%E6%B8%9B%E8%84%82%E6%97%A9%E9%A4%90-1.jpg 1400w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E6%B8%9B%E8%84%82%E6%97%A9%E9%A4%90-1.jpg?width=1750&amp;name=%E6%B8%9B%E8%84%82%E6%97%A9%E9%A4%90-1.jpg 1750w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E6%B8%9B%E8%84%82%E6%97%A9%E9%A4%90-1.jpg?width=2100&amp;name=%E6%B8%9B%E8%84%82%E6%97%A9%E9%A4%90-1.jpg 2100w\" width=\"700\" /></p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h3>2.�K�Q�ө���</h3>\r\n" + 
				"\r\n" + 
				"<ul>\r\n" + 
				"	<li>�P����+<a href=\"https://blog.worldgymtaiwan.com/%E7%A9%BA%E8%85%B9%E5%90%83%E9%A6%99%E8%95%89-%E5%BC%8A%E5%A4%9A%E6%96%BC%E5%88%A9\" target=\"_blank\">����</a>�@��+�A���N���T���v</li>\r\n" + 
				"	<li>�L�}<a href=\"https://blog.worldgymtaiwan.com/%E5%84%AA%E6%A0%BC-%E5%84%AA%E9%85%AA%E4%B9%B3-%E5%B8%8C%E8%87%98%E5%84%AA%E6%A0%BC%E5%B7%AE%E5%88%A5%E5%9C%A8%E5%93%AA%E8%A3%A1\" target=\"_blank\">�u�T��</a>+��X���G��+�A���N������</li>\r\n" + 
				"	<li>�Ŭu�J�@�](2��)+<a href=\"https://blog.worldgymtaiwan.com/%E5%9C%B0%E7%93%9C%E6%B8%9B%E8%82%A5%E6%96%B9%E6%B3%95\" target=\"_blank\">�a��</a>+����F��</li>\r\n" + 
				"</ul>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p>�o�O�`�Y���զX�A���j�a�Ѧ�</p>\r\n" + 
				"\r\n" + 
				"<p><img alt=\"�K�Q�ө�����\\" sizes=\"(max-width: 700px) 100vw, 700px\" src=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E4%BE%BF%E5%88%A9%E5%95%86%E5%BA%97%E6%B8%9B%E8%84%82%E9%A4%90.jpg?width=700&amp;name=%E4%BE%BF%E5%88%A9%E5%95%86%E5%BA%97%E6%B8%9B%E8%84%82%E9%A4%90.jpg\" srcset=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E4%BE%BF%E5%88%A9%E5%95%86%E5%BA%97%E6%B8%9B%E8%84%82%E9%A4%90.jpg?width=350&amp;name=%E4%BE%BF%E5%88%A9%E5%95%86%E5%BA%97%E6%B8%9B%E8%84%82%E9%A4%90.jpg 350w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E4%BE%BF%E5%88%A9%E5%95%86%E5%BA%97%E6%B8%9B%E8%84%82%E9%A4%90.jpg?width=700&amp;name=%E4%BE%BF%E5%88%A9%E5%95%86%E5%BA%97%E6%B8%9B%E8%84%82%E9%A4%90.jpg 700w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E4%BE%BF%E5%88%A9%E5%95%86%E5%BA%97%E6%B8%9B%E8%84%82%E9%A4%90.jpg?width=1050&amp;name=%E4%BE%BF%E5%88%A9%E5%95%86%E5%BA%97%E6%B8%9B%E8%84%82%E9%A4%90.jpg 1050w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E4%BE%BF%E5%88%A9%E5%95%86%E5%BA%97%E6%B8%9B%E8%84%82%E9%A4%90.jpg?width=1400&amp;name=%E4%BE%BF%E5%88%A9%E5%95%86%E5%BA%97%E6%B8%9B%E8%84%82%E9%A4%90.jpg 1400w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E4%BE%BF%E5%88%A9%E5%95%86%E5%BA%97%E6%B8%9B%E8%84%82%E9%A4%90.jpg?width=1750&amp;name=%E4%BE%BF%E5%88%A9%E5%95%86%E5%BA%97%E6%B8%9B%E8%84%82%E9%A4%90.jpg 1750w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E4%BE%BF%E5%88%A9%E5%95%86%E5%BA%97%E6%B8%9B%E8%84%82%E9%A4%90.jpg?width=2100&amp;name=%E4%BE%BF%E5%88%A9%E5%95%86%E5%BA%97%E6%B8%9B%E8%84%82%E9%A4%90.jpg 2100w\" width=\"700\" /></p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h3>3.�ۧU�\�K����</h3>\r\n" + 
				"\r\n" + 
				"<ul>\r\n" + 
				"	<li><a href=\"https://blog.worldgymtaiwan.com/tag/%E9%A3%B2%E9%A3%9F/%E9%AB%98%E9%BA%97%E8%8F%9C1/6%E6%B8%9B%E8%82%A5%E5%AD%B8\" target=\"_blank\">���R��</a>+�Ťߵ�+��ۣ��+����J+�M�]����+�W�̶�</li>\r\n" + 
				"	<li>�p����+�C�Բ���+����+�f�X���J+�[�d�զ�+<a href=\"https://blog.worldgymtaiwan.com/%E7%B4%AB%E7%B1%B3%E9%BB%91%E7%B1%B3%E5%AF%8C%E5%90%AB%E8%8A%B1%E9%9D%92%E7%B4%A0\" target=\"_blank\">����</a>��</li>\r\n" + 
				"	<li><a href=\"https://blog.worldgymtaiwan.com/tag/%E9%A3%B2%E9%A3%9F/%E8%8A%B1%E6%A4%B0%E8%8F%9C%E6%B8%9B%E9%87%8D\" target=\"_blank\">�ᷦ��</a>+<a href=\"https://blog.worldgymtaiwan.com/tag/%E9%A3%B2%E9%A3%9F/%E7%8E%89%E7%B1%B3%E7%AD%8D%E4%BD%8E%E7%86%B1%E9%87%8F%E5%8F%88%E5%8A%A9%E7%98%A6\" target=\"_blank\">�ɦ̵�</a>���ڽ�+���a<a href=\"https://blog.worldgymtaiwan.com/tag/%E9%A3%B2%E9%A3%9F/%E9%BB%91%E6%9C%A8%E8%80%B3%E9%80%99%E6%A8%A3%E5%90%83%E4%B8%8D%E6%98%AF%E6%AF%92\" target=\"_blank\">���</a>+�]�J+�����L+�a�ʶ�</li>\r\n" + 
				"</ul>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p><a href=\"https://blog.worldgymtaiwan.com/%E8%87%AA%E5%8A%A9%E9%A4%90%E6%B8%9B%E8%82%A5%E6%B3%95\" target=\"_blank\">�ۧU�\�����h</a></p>\r\n" + 
				"\r\n" + 
				"<ul>\r\n" + 
				"	<li>�t�����ɶq���W�����A�H���קK���X�l�B���t�|�u���A�άO��ƤӦh�����A�o�������q�����A�B�l���j�q�o�סC</li>\r\n" + 
				"	<li>�t�~�A���ƩΥ[�u���~�A�ण�I�h���I�A�]���[�u���~���K�[���t�q���B���q�]���A�Ҧp�G���סB�ʭ����G�B���z�C</li>\r\n" + 
				"</ul>\r\n" + 
				"\r\n" + 
				"<p>�D��A�ɶq�O�D��o�����A�p�G�O�L��ܡA����i�H��ܦn�h�֪������L�A�_�h�Y�U�q�����L���~�֡A������o�סB���q�W���I<br />\r\n" + 
				"<br />\r\n" + 
				"���˫ܤ�K��Uber eats �MFood Ponda�~�e�A�ȡA���d�\�Υ~�e�W��K�A�Ʀ��٥i�H�w���q�\�C����ͬ��b21�@���A�u�O�ӴΤF�I</p>\r\n" + 
				"\r\n" + 
				"<p><img alt=\"���d�\-1\" sizes=\"(max-width: 700px) 100vw, 700px\" src=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E5%81%A5%E5%BA%B7%E9%A4%90-1.jpg?width=700&amp;name=%E5%81%A5%E5%BA%B7%E9%A4%90-1.jpg\" srcset=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E5%81%A5%E5%BA%B7%E9%A4%90-1.jpg?width=350&amp;name=%E5%81%A5%E5%BA%B7%E9%A4%90-1.jpg 350w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E5%81%A5%E5%BA%B7%E9%A4%90-1.jpg?width=700&amp;name=%E5%81%A5%E5%BA%B7%E9%A4%90-1.jpg 700w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E5%81%A5%E5%BA%B7%E9%A4%90-1.jpg?width=1050&amp;name=%E5%81%A5%E5%BA%B7%E9%A4%90-1.jpg 1050w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E5%81%A5%E5%BA%B7%E9%A4%90-1.jpg?width=1400&amp;name=%E5%81%A5%E5%BA%B7%E9%A4%90-1.jpg 1400w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E5%81%A5%E5%BA%B7%E9%A4%90-1.jpg?width=1750&amp;name=%E5%81%A5%E5%BA%B7%E9%A4%90-1.jpg 1750w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E5%81%A5%E5%BA%B7%E9%A4%90-1.jpg?width=2100&amp;name=%E5%81%A5%E5%BA%B7%E9%A4%90-1.jpg 2100w\" width=\"700\" /></p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h2><strong>�ֳt�Ʋz�����j �ۤv�N�u�@�P����\�v�̰��d�I</strong></h2>\r\n" + 
				"\r\n" + 
				"<p>�W�Z���ȥ𮧮ɶ��A���@�w�n�ѤѦY�~���A�A�]�i�H�����۳ƫK��A�a�줽�q�[���A�άO��b�O�ųU�̡A���Ȧۤv�a�̰��d�A�ٯ�ٿ��O�I</p>\r\n" + 
				"\r\n" + 
				"<h3>1.�����K��</h3>\r\n" + 
				"\r\n" + 
				"<p>�i�o�N�B���Q�}��A����N�Ӱ��T���v�Υ͵�F�Է���\�a�I���רF�ԡB�C���͵�����T���v�A�άO�έӹq��]�J�]�����A�ܧֳt�N��ѨM�C���L�O�ѤF�A���α�����������n�[�Ӧh�A���O���D�������q�W���ڡI�[�Ӧh�A��έp�e�����}�\�C</p>\r\n" + 
				"\r\n" + 
				"<p><img alt=\"�i�o�N�B���Q�}��A����N�Ӱ��T���v�Υ͵�F�Է���\�a�I\" sizes=\"(max-width: 700px) 100vw, 700px\" src=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%911.png?width=700&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%911.png\" srcset=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%911.png?width=350&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%911.png 350w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%911.png?width=700&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%911.png 700w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%911.png?width=1050&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%911.png 1050w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%911.png?width=1400&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%911.png 1400w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%911.png?width=1750&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%911.png 1750w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%911.png?width=2100&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%911.png 2100w\" width=\"700\" /></p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p>�t�~�A�]�J�t�����T���v�B�F�ԡA�٥i�H��ܱa���G�A�h�h�ɥR���ͯ��A�ܾA�X���R�Y���檺�H�C</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h3>2.�q�j�Q��</h3>\r\n" + 
				"\r\n" + 
				"<p>�ֻ��Y<a href=\"https://blog.worldgymtaiwan.com/tag/%E9%A3%B2%E9%A3%9F/%E7%BE%A9%E5%A4%A7%E5%88%A9%E9%BA%B5%E8%B6%8A%E5%90%83%E8%B6%8A%E7%98%A6\" target=\"_blank\">�q�j�Q��</a>�ܭD�H�����ƴN���D�I���F����P�J�ս誺�f�t�A��ƥi�H��ܲM���εf�X(����)�f���A���q�����B�C��B����C�ܦh�A�]���o����Ƨt���j�q�����o�A�O��δ����j�ҡI���~�A�q�j�Q�Ѥ]�O�[�j�q���_�q�K�N�A�o�u�|���`���q�S���ɤ@�j�h�C</p>\r\n" + 
				"\r\n" + 
				"<p><img alt=\"��׵��-�q�j�Q��\" sizes=\"(max-width: 700px) 100vw, 700px\" src=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%912.png?width=700&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%912.png\" srcset=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%912.png?width=350&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%912.png 350w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%912.png?width=700&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%912.png 700w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%912.png?width=1050&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%912.png 1050w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%912.png?width=1400&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%912.png 1400w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%912.png?width=1750&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%912.png 1750w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%912.png?width=2100&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%912.png 2100w\" width=\"700\" /></p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p>�Ӹq�j�Q�Ѧb�t��譱�A�i�H���N�ᷦ��B�ɦ̵��A�άO��ܱa����F�ԡA�M��t�W���<a href=\"https://blog.worldgymtaiwan.com/%E9%9B%9E%E8%83%B8%E8%82%89%E8%8F%9C%E5%96%AE\" target=\"_blank\">���ݦ�</a>�B���٦סA�N�|�O�@�\���ֺ���������\�F�I</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h3>3.���A��</h3>\r\n" + 
				"\r\n" + 
				"<p>���A���j�h�O���J�ս�B�C�תաA�ӥB�N�k�ֳt�S²��A�Ҧp�G���l�B��ġB�����B����&hellip;���A�O��αڪ��n�٦�A�u�n²����N�����S�A�N�i�H���ΤF�I�������A�Ʋz�|���Q���A�p�Gı�o�S������D�A�i�H�[�I�J���Q�ή��Q�ը��C</p>\r\n" + 
				"\r\n" + 
				"<p><img alt=\"��׵��-���A��\" sizes=\"(max-width: 700px) 100vw, 700px\" src=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%913.png?width=700&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%913.png\" srcset=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%913.png?width=350&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%913.png 350w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%913.png?width=700&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%913.png 700w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%913.png?width=1050&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%913.png 1050w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%913.png?width=1400&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%913.png 1400w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%913.png?width=1750&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%913.png 1750w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%913.png?width=2100&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%913.png 2100w\" width=\"700\" /></p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p>�S�O�O�A���A���@�w�n���ˡu<a href=\"https://blog.worldgymtaiwan.com/healthy-food-salmon\" target=\"_blank\">�D��</a>�v�A�A�|�o�{�\�h���������ȩάO�����̡A�D���`�`��ܷ��D���A�]���D���t���״I��DHA�A�٦��t�����d���o�סuOmega-3�v�A�O���U���H�餺���a�תդ��Ѫ��n�תաA���U��U�N��h�d�����A�ٯ�ﵽ�ߦ�ޯe�f�C�ӥB�D�����Ʋz�覡�W�h�ˡA���ެO�ΡB�N�B�ͳ����A���ܬ����C�t�~�A�J���P�ֳ��]�I�t���d���o�uOmega-3�v�תջġA�A�q�Y�A�@�˯����U��I</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h3>4.�A�����G��</h3>\r\n" + 
				"\r\n" + 
				"<p>�۫H�j�a�����D�A�Q�n��Φ��\�A���G���ܭ��n�I�]���H��C�ѳ̤��i�ίʪ��A�N�O�״I������G�A���L���G���ɶq���<a href=\"https://blog.worldgymtaiwan.com/are-low-gi-foods-good-for-weight-loss\" target=\"_blank\">�CGI</a>�B����i�K�ת��U��A�Ҧp�G<a href=\"https://blog.worldgymtaiwan.com/healthy-food-best-time-to-eat-kiwi-for-weight-loss\" target=\"_blank\">�_���G</a>�B<a href=\"https://blog.worldgymtaiwan.com/tag/%E9%A3%B2%E9%A3%9F/%E4%B8%80%E5%A4%A9%E4%B8%80%E9%A1%86%E8%98%8B%E6%9E%9C-%E9%86%AB%E7%94%9F%E9%81%A0%E9%9B%A2%E6%88%91\" target=\"_blank\">ī�G</a>�B<a href=\"https://blog.worldgymtaiwan.com/tag/%E9%A3%B2%E9%A3%9F/%E8%8A%AD%E6%A8%82%E6%9C%80%E5%8F%97%E6%AD%A1%E8%BF%8E%E7%9A%8415%E5%80%8B%E5%8A%9F%E6%95%88\" target=\"_blank\">�ݼ�</a>&hellip;���F�ܩ󽭵����A���F��⽭��A�]�i�H�f�tۣ���A�]��ۣ�����J�ս�t�q�]�ܰ��A�B�S�O�C���q�B���ֺ����n�����A���ȼW�[�����P�A�]���U�󭰧C�x�T�J�B��֯תէl���C</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h3>5.�إq����</h3>\r\n" + 
				"\r\n" + 
				"<p><a href=\"https://blog.worldgymtaiwan.com/tag/%E9%A3%B2%E9%A3%9F/%E4%B8%80%E5%BC%B5%E8%A1%A8%E9%81%BF%E9%96%8B%E5%A3%BD%E5%8F%B8%E7%86%B1%E9%87%8F%E5%9C%B0%E9%9B%B7\" target=\"_blank\">�إq</a>���ܤƩʤ]�ܤj�A���F�`�������a�����A�٦��Ⱳ�B���إq�B���ֹإq&hellip;���A���I�O�A<a href=\"https://blog.worldgymtaiwan.com/tag/%E9%A3%B2%E9%A3%9F/%E6%8A%97%E6%80%A7%E6%BE%B1%E7%B2%89%E8%AE%93%E4%BD%A0%E8%BC%95%E9%AC%86%E4%BA%AB%E7%98%A6\" target=\"_blank\">�إq���̶��O�ܩʾ���</a>�A�C���q�~�A�Y�F�S�������P�F���`�ƪ������A�i�H��ܷ�u�B<a href=\"https://blog.worldgymtaiwan.com/%E8%9B%8B%E7%99%BD%E8%B3%AA%E4%B8%8D%E8%83%BD%E9%9A%A8%E4%BE%BF%E5%90%83-12%E7%A8%AE%E7%B2%BE%E7%98%A6%E8%9B%8B%E7%99%BD%E8%B3%AA%E5%8A%A9%E5%A2%9E%E8%82%8C%E6%B8%9B%E8%84%82\" target=\"_blank\">�s�A�������A�ɥR�״I���J�ս�</a>�BOmega3�תջġC�Ӯ��a�إq�A�~�h���إq��i���A�תէC�A�����Y�i�H�W�j�K�̤O�B�ܦѡA�B���C�ߦ�ޯe�f�����I�C</p>\r\n" + 
				"\r\n" + 
				"<p><img alt=\"��׵��-�إq����\" sizes=\"(max-width: 700px) 100vw, 700px\" src=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%915.png?width=700&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%915.png\" srcset=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%915.png?width=350&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%915.png 350w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%915.png?width=700&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%915.png 700w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%915.png?width=1050&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%915.png 1050w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%915.png?width=1400&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%915.png 1400w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%915.png?width=1750&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%915.png 1750w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%915.png?width=2100&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%915.png 2100w\" width=\"700\" /></p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p>�إq���n�B�h�A���]�n�`�N�o�X�I�A�p�ߧO��p�I���O���ֹإq�A���֬O�ݩ�Ѭ����A�A�g�L��ĺ��L�A1�����q�N��350�j�d�I�t�~�A���ǹإq�|�դW���D���A�άO�[�W�_�q�B�K�N�A�o�ǰ����q���t�Ʃ���ơA��֦Y�N�֦Y�C�t�ƥi�H��Y�ڽ����B�����A���U����ơA���U�s���N�¡C</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h3>6.���N�\+���d�o��</h3>\r\n" + 
				"\r\n" + 
				"<p>��M�u���N�\�v�A�O�̨嫬��׵��A���L�A�o���٤֥[�@�y�A�����[�I<a href=\"https://blog.worldgymtaiwan.com/tag/%E5%81%A5%E5%BA%B7/%E7%BE%8E%E9%AB%94/%E5%A5%BD%E5%A3%9E%E8%84%82%E8%82%AA%E5%B0%8D%E8%BA%AB%E9%AB%94%E7%9A%84%E5%BD%B1%E9%9F%BF\" target=\"_blank\">�u���d�o�סv</a>�A�|��n�C�]������i�v�ҹ�u�L�צY���N�O�L�k���Ĵ�Ρv�A�֤F�o�סB�P�ɤ]�ʤֹ����P�A�|������Q�Y��h�����A�ӥB�L�o�׵���L�k���z�D��ơA�e�����ͫK�����D�C</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p>�]���A�b���N�\�W�A�i�H�[�J�n�o�A�Ҧp�����M�תջġG����o�B���V�o�A�O�`�`�Q�s�x���Ӧb����\�W�ϥΪ��A�A�q����A����ﵽ����B���x�T�J�A�f�t�������S���d�C</p>");	
		art.insert(articleVO1);
		System.out.println("�s�W���\");
		
		// �s�W���u
//		ArticleVO articleVO01 = new ArticleVO();
//		articleVO01.setWork_id("WORK00080");
//		articleVO01.setTitle("�x��峹�s�W1.1");
//		articleVO01.setArt_content("�x��峹���e2.1...");
//		
//		art.insertwork(articleVO01);
//		System.out.println("�s�W���\");

//		// �ק�|��
//		ArticleVO articleVO2 = new ArticleVO();	
//
//		articleVO2.setTitle("�峹�ק�");
//		articleVO2.setArt_content("�峹���e�ק�");		
//		articleVO2.setArticle_no("ART001");
//		
//		art.update(articleVO2);
//		
//		System.out.println("�ק令�\");
		
//		// �ק�x��
//		ArticleVO articleVO2 = new ArticleVO();	
//
//		articleVO2.setTitle("�峹�ק�");
//		articleVO2.setArt_content("�峹���e�ק�V1");		
//		articleVO2.setArticle_no("ART013");
//		
//		art.update(articleVO2);
//		
//		System.out.println("�ק令�\");

		// �R��
//		art.delete("ART013");
//		System.out.println("�R�����\");

		// ��@�d��
//		ArticleVO articleVO3 = art.findByPrimaryKey("ART009");
//		System.out.println("�峹�s��:"+articleVO3.getArticle_no() );
//		System.out.println("�|���s��:"+articleVO3.getMem_id());
//		System.out.println("�x��s��:"+articleVO3.getWork_id() );
//		System.out.println("�峹���D:"+articleVO3.getTitle() );
//		System.out.println("�峹���e:"+articleVO3.getArt_content() );
//		System.out.println("�W�Ǯɶ�:"+articleVO3.getA_release() );
//		System.out.println("�g��:"+articleVO3.getLink_count() );
//		System.out.println("�d����:"+articleVO3.getCom_count() );
//		System.out.println("�峹���A:"+articleVO3.getA_status());
//		System.out.println("---------------------");

		// �d�ߥ���
//				List<ArticleVO> list = art.getAll();
//				for (ArticleVO aArticle : list) {
//					System.out.println("�峹�s��:"+aArticle.getArticle_no() );
//					System.out.println("�|���s��:"+aArticle.getMem_id() );
//					System.out.println("�x��s��:"+aArticle.getWork_id() );
//					System.out.println("�峹���D:"+aArticle.getTitle() );
//					System.out.println("�峹���e:"+aArticle.getArt_content() );
//					System.out.println("�W�Ǯɶ�:"+aArticle.getA_release() );
//					System.out.println("�g��:"+aArticle.getLink_count() );
//					System.out.println("�d����:"+aArticle.getCom_count() );
//					System.out.println("�峹���A:"+aArticle.getA_status() );
//					System.out.println();
//					System.out.println("---------------------");
//				}
		
		
		// �d�߬Y���������u
//		Set<Art_CommentVO> set = art.getArt_CommentsByArticle_no("ART001");
//		for (Art_CommentVO aart_commentVO : set) {
//			System.out.print("�d���s��:"+aart_commentVO.getCom_no() + ",");
//			System.out.print("�|���s��:"+aart_commentVO.getMem_id() + ",");
//			System.out.print("�d�����e:"+aart_commentVO.getMes_content() + ",");
//			System.out.print("�d���ɶ�:"+aart_commentVO.getCom_release() + ",");
//			System.out.print("�d�����A:"+aart_commentVO.getCom_status() + ",");
//			System.out.print("�峹�s��:"+aart_commentVO.getArticle_no() + ",");
//			System.out.println();								
//	}
				
	}	
	
}


