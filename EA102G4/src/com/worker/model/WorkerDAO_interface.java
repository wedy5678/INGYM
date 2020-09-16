package com.worker.model;

import java.util.*;

public interface WorkerDAO_interface {
          public void insert(WorkerVO workVO);
          public void update(WorkerVO workVO);
          public void updatePw(String work_id, String w_pw);
          public void delete(String work_id);
          public WorkerVO findByPrimaryKey(String work_id);
          public WorkerVO findByAcc(String w_acc);
          public List<WorkerVO> getAll();
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
