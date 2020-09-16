package com.art_comment.model;

import java.sql.*;

public class Art_CommentVO implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;

	private String com_no; // �d���s��
	private String article_no; // �峹�s��
	private String mem_id; // �|���s��(�^��)
	private String mes_content; // �d�����e
	private Timestamp com_release; // �o�G�ɶ�
	private String com_status; // �d�����A
	
	public Art_CommentVO() {
		
	}
	
	public Art_CommentVO(String com_no, String article_no, String mem_id, String mes_content, Timestamp com_release,
			String com_status) {
		super();
		this.com_no = com_no;
		this.article_no = article_no;
		this.mem_id = mem_id;
		this.mes_content = mes_content;
		this.com_release = com_release;
		this.com_status = com_status;
	}
	
	public String getCom_no() {
		return com_no;
	}

	public void setCom_no(String com_no) {
		this.com_no = com_no;
	}

	public String getArticle_no() {
		return article_no;
	}

	public void setArticle_no(String article_no) {
		this.article_no = article_no;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getMes_content() {
		return mes_content;
	}

	public void setMes_content(String mes_content) {
		this.mes_content = mes_content;
	}

	public Timestamp getCom_release() {
		return com_release;
	}

	public void setCom_release(Timestamp com_release) {
		this.com_release = com_release;
	}

	public String getCom_status() {
		return com_status;
	}

	public void setCom_status(String com_status) {
		this.com_status = com_status;
	}

}
