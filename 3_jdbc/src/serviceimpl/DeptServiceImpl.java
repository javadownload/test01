package serviceimpl;

import java.util.List;

import dao.DeptDAO;
import service.DeptService;
import vo.DeptVO;

public class DeptServiceImpl implements DeptService{
	
	private DeptDAO dao =null;

	public DeptServiceImpl() {}
	public DeptServiceImpl(DeptDAO dao) {
		this.dao = dao;
	}
	public DeptDAO getDao() {
		return dao;
	}
	public void setDao(DeptDAO dao) {
		this.dao = dao;
	}
	public void updateDept(DeptVO vo) {
		dao.updateDept(vo);
	}
	public void deleteDept(int deptno) {
		dao.deleteDept(deptno);
	}
	public int insertDept(DeptVO vo) {
		return dao.insertDept(vo);
	}
	public int insertDept(String dname, String loc) {
		return dao.insertDept(dname,loc);
	}
	public DeptVO getDept(int deptno) {
		return dao.getDept(deptno);
	}
	public List<DeptVO> getDeptAll() {
		return dao.getDeptAll();
	}
}
