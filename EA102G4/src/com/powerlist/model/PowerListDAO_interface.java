package com.powerlist.model;

import java.util.*;

import com.powerlist.model.PowerListVO;

public interface PowerListDAO_interface {
          public void insert(PowerListVO powerlistVO);
          public void update(PowerListVO powerlistVO);
          public void delete(PowerListVO powerlistVO);   
          public PowerListVO findByPrimaryKey(String power_id);
          public List<PowerListVO> getAll();
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
