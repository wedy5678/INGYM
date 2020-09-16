package com.memphoto.model;

	import java.util.List;

	public interface MemPhotoDAO_interface {
	          public void insert(MemPhotoVO memPhotoVO);
	          public void update(MemPhotoVO memPhotoVO);
	          public void delete(String photo_no);
	          public List<MemPhotoVO> findByPrimaryKey(String mem_id);
	          public MemPhotoVO findByPrimaryKeyByStatus(String mem_id);
	          public MemPhotoVO findByPrimaryKeyPhotoNo(String photo_no);
	          public List<MemPhotoVO> getAll();
	          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//	        public List<BodyRecordVO> getAll(Map<String, String[]> map); 
	}

