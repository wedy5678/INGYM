package com.mem.model;

import java.sql.Connection;
import java.util.*;


public interface MemDAO_interface {
          public void insert(MemVO memVO);
          public void update(MemVO memVO);
          public void delete(String mem_id);
          public void updateMemAuth(MemVO memVO);
          public void updateMemCoin(MemVO memVO, Connection con);
          public MemVO findByPrimaryKey(String mem_id);
          public MemVO findByPrimaryKeyByMemAcc(String mem_email);
          public List<String> getAllEmail();
          public List<MemVO> getAll();
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}