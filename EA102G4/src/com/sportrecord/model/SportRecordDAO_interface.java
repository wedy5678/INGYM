package com.sportrecord.model;

	import java.util.List;

	public interface SportRecordDAO_interface {
	          public SportRecordVO insert(SportRecordVO sportRecordVO);
	          public void update(SportRecordVO sportRecordVO);
	          public void delete(String record_no);
	          public List<SportRecordVO> findByPrimaryKeyMemId(String mem_id);
	          public SportRecordVO findByPrimaryKeyMemIdLast(String mem_id);
	          public SportRecordVO findByPrimaryKeyRecordNo(String record_no);
	          public List<SportRecordVO> getAll();
	          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//	        public List<SportRecordVO> getAll(Map<String, String[]> map); 
	}

