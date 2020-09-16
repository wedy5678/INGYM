 package com.article.model;

import java.util.*;

import com.art_comment.model.Art_CommentVO;
import com.article_rep.model.Article_RepVO;


public interface ArticleDAO_interface {
	public void insert(ArticleVO articleVO);
	public void insertwork(ArticleVO articleVO);
	
	public void update(ArticleVO articleVO);
	
	public void update2(ArticleVO articleVO);
	
	public void delete(String article_no);
	public ArticleVO findByPrimaryKey(String article_no);
	public List<ArticleVO> getAll();
	
	 //�d�߬Y�峹���d��(�@��h)(�^�� Set)
    public Set<Art_CommentVO> getArt_CommentsByArticle_no(String article_no);
    
    //�d�߬Y�峹�����|(�@��h)(�^�� Set)
    public Set<Article_RepVO> getArticle_RepsByArticle_no(String article_no);
    
    public List getRepArt();
    
//    public void update_rep(String article_no);
    
    public void updateToNoShow(String article_no);
	
    
    
}
