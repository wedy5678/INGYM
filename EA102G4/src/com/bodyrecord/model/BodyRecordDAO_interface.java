package com.bodyrecord.model;

	import java.util.List;

	public interface BodyRecordDAO_interface {
	          public BodyRecordVO insert(BodyRecordVO bodyRecordVO);
	          public void update(BodyRecordVO bodyRecordVO);
	          public void delete(String body_no);
	          public List<BodyRecordVO> findByPrimaryKeyMemId(String mem_id);
	          public BodyRecordVO findByPrimaryKeyMemIdLast(String mem_id);
	          public BodyRecordVO findByPrimaryKeyBodyNo(String body_no);
	          public List<BodyRecordVO> getAll();
	          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//	        public List<BodyRecordVO> getAll(Map<String, String[]> map); 
	}

