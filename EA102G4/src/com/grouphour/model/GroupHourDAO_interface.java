package com.grouphour.model;

import java.util.HashSet;
import java.util.List;

public interface GroupHourDAO_interface {
	public void insert(GroupHourVO groupHourVO);
    public void update(GroupHourVO groupHourVO);
    public GroupHourVO findByPrimaryKey(String g_time_no);  
    public HashSet<GroupHourVO> getAllTimeByGroupClassNo(String g_class_no);  //�νҩҦ��ɶ�
    public List<GroupHourVO> getAll();					//�Ҧ��ν�
    public void delete(String g_time_no);
}
