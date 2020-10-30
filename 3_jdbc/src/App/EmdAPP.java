package App;

import java.util.List;

import dao.DeptDAO;
import service.DeptService;
import serviceimpl.DeptServiceImpl;
import vo.DeptVO;

public class EmdAPP {
	public static void main(String[] args) {
       
		DeptDAO dao = new DeptDAO();
		DeptService service = new DeptServiceImpl(dao);
		
		//System.out.println("--- 목록 LIST ----");
//		for(DeptVO data :service.getDeptAll()) {
//			System.out.println(data);
//		}
		
		System.out.println("--- 목록 LIST ----");
		List<DeptVO> list = service.getDeptAll();
		list.forEach(i->{System.out.println(i.getLoc());});
	}
}



