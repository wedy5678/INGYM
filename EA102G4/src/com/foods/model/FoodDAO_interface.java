package com.foods.model;

import java.util.*;
import com.foodrecord.model.FoodRecordVO;

import com.worker.model.WorkerVO;

public interface FoodDAO_interface {
          public void insert(FoodVO foodVO);
          public void update(FoodVO foodVO);
          public void delete(String food_no);
          public FoodVO getFood_no(String food_name);
          public FoodVO findByPrimaryKey(String food_no);
          public List<FoodVO> getAll();
          public Double[] getAllNu(List<FoodRecordVO> list);
//          public double getKcal(String food_no);
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
