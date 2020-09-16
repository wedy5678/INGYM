package com.sports.model;

import java.util.*;


public interface SportsDAO_interface {
          public void insert(SportsVO sportsVO);
          public void update(SportsVO sportsVO);
          public void delete(String sport_no);
          public SportsVO findByPrimaryKey(String sport_no);
          public List<SportsVO> getAll();
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<SportsVO> getAll(Map<String, String[]> map); 
}