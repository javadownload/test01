package service;

import java.util.List;

import vo.DeptVO;

public interface DeptService {
	public void updateDept(DeptVO vo);
	public  void deleteDept(int deptno);
	public  int insertDept(DeptVO vo);
	public  int insertDept(String dname, String loc);
	public  DeptVO getDept(int deptno);
	public  List<DeptVO> getDeptAll();
}
