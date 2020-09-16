package com.grouporder.model;

import java.util.List;

import com.godetail.model.GroupOrderDetailVO;
import com.mem.model.MemVO;


public interface GroupOrderDAO_interface {
	public void insert(GroupOrderVO groupOrderVO);
    public GroupOrderVO findByPrimaryKey(String g_order_no);  
    public List<GroupOrderVO> getAllByMem_id(String mem_id);  //�|���Ҧ��νҭq��
    public List<GroupOrderVO> getAll();					//�Ҧ��νҭq��
    public String insertWithDetail(GroupOrderVO goVO, List<GroupOrderDetailVO> list,MemVO memVOLogin);

}
