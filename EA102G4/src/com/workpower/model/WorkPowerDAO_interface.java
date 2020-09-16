package com.workpower.model;

import java.util.*;

public interface WorkPowerDAO_interface {
          public void insert(WorkPowerVO workerpowerVO);
          public void update(WorkPowerVO workerpowerVO);
          public void deleteAllByWork(String work_id);
          public List<WorkPowerVO> getByWorker(String work_id);
          public List<WorkPowerVO> getByPower(String power_id);
          public List<WorkPowerVO> getAll();
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
