package com.groupclass.model;

import java.util.List;


public interface GroupClassDAO_interface {
	public void insert(GroupClassVO groupClassVO);
    public void update(GroupClassVO groupClassVO);
    public byte[] getG_picByG_class_no(String g_class_no);
    public GroupClassVO findByPrimaryKey(String g_class_no);  
    public List<GroupClassVO> getAllByPro_id(String pro_id);  //�нm�Ҧ��ν�
    public List<GroupClassVO> getAll();					//�Ҧ��ν�
    public GroupClassVO getNewGClassNoByPro_id(String pro_id);

}
