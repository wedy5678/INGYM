package com.chatbox.fileTool;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

import com.chatimg.model.ChatimgService;
import com.chatimg.model.ChatimgVO;

@WebServlet("/Fileupload.do")
@MultipartConfig()
// ��ƾڶq�j��fileSizeThreshold�ȮɡA���e�N�Q�g�J�Ϻ�
// �W�ǹL�{���L�׬O��Ӥ��W�LmaxFileSize�ȡA�Ϊ̤W�Ǫ��`�q�j��maxRequestSize �ȳ��|�ߥXIllegalStateException ���`
public class Fileupload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String saveDirectory = "/images_uploaded"; // �W���ɮת��ئa�ؿ�;
											   // �N�ѩ��U����26~30��� java.io.File �� ContextPath ���U, �۰ʫإߥئa�ؿ�

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8"); // �B�z�����ɦW
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		String action = req.getParameter("action");
		System.out.println("ContentType="+req.getContentType()); // ���ե�

		String realPath = getServletContext().getRealPath(saveDirectory);
		System.out.println("realPath="+realPath); // ���ե�
		File fsaveDirectory = new File(realPath);
		if (!fsaveDirectory.exists())
			 fsaveDirectory.mkdirs(); // �� ContextPath ���U,�۰ʫإߥئa�ؿ�
		
		if ("chatfile".equals(action)) {
			System.out.println("ServletController-chatfile in");
			Part part = req.getPart("file");
			String filename = getFileNameFromPart(part);
			File f = new File(fsaveDirectory, filename);
			
			part.write(f.toString());
		}
	}

	// ���X�W�Ǫ��ɮצW�� (�]��API������method,�ҥH�����ۦ漶�g)
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		System.out.println("header=" + header); // ���ե�
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		System.out.println("filename=" + filename); // ���ե�
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}