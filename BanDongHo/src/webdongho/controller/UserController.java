package webdongho.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import webdongho.entity.KHACHHANG;
import webdongho.entity.NHANVIEN;
import webdongho.entity.TAIKHOAN;
import webdongho.entity.TAIKHOANADMIN;
import webdongho.bean.User;


@Transactional
@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	SessionFactory factory;
	@RequestMapping(value = "info" , method = RequestMethod.GET)
	public String user_info(ModelMap model, HttpServletRequest request, @ModelAttribute("user") User myUser)
	{
		HttpSession ss = request.getSession();
		TAIKHOAN tk = (TAIKHOAN) ss.getAttribute("tk");
		TAIKHOANADMIN myNhanVien = (TAIKHOANADMIN) ss.getAttribute("tkAdmin");
		if(tk == null && myNhanVien == null) {
			
			return "redirect:/index/sign-in.htm";
		}
		if(tk != null) {
			model.addAttribute("user",tk.getMAKH_TK());
		}
		else {
			model.addAttribute("user",myNhanVien.getMANV_TK());
		}
		//================
		Session session=factory.getCurrentSession();
		return "user/info";
	}
	@RequestMapping(value = "info" , method = RequestMethod.POST)
	public String save_info(HttpServletRequest request,ModelMap model,@ModelAttribute("user") User myUser,BindingResult errors)
	{
		
		HttpSession ss = request.getSession();
		TAIKHOAN tk = (TAIKHOAN) ss.getAttribute("tk");
		TAIKHOANADMIN myNhanVien = (TAIKHOANADMIN) ss.getAttribute("tkAdmin");
		
		if(myUser.getHOTEN().trim().length() == 0)
		{
			errors.rejectValue("HOTEN", "user", "Vui long nhap ho ten !");
		}
		if(myUser.getSDT().trim().length() == 0)
		{
			errors.rejectValue("SDT", "user", "Vui long nhap SDT !");
		}
		if(myUser.getDIACHI().trim().length() == 0)
		{
			errors.rejectValue("DIACHI", "user", "Vui long nhap dia chi !");
		}



		if(errors.hasErrors())
		{
			System.out.print(errors);
			return "user/info";
		}
		if(tk != null) {
			
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			try {
				KHACHHANG kh = this.tranferToKhachHang(tk.getMAKH_TK(), myUser);
				session.update(kh);
				t.commit();
			}
			catch(Exception e)
			{

			}
			finally {
				session.close();
			}
			return "redirect:info.htm";
		}
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(this.tranferToNhanVien(myNhanVien.getMANV_TK(), myUser));
			t.commit();
		}
		catch(Exception e)
		{

		}
		finally {
			session.close();
		}
		return "redirect:info.htm";
	}
	
	public NHANVIEN tranferToNhanVien(NHANVIEN n,User u)  {
		n.setDIACHI(u.getDIACHI());
		n.setEMAIL(u.getEMAIL());
		n.setHOTEN(u.getHOTEN());
		n.setGIOITINH(u.getGIOITINH());
		n.setSDT(u.getSDT());
		
		
		return n;
	}
	public KHACHHANG tranferToKhachHang(KHACHHANG n,User u) {
		n.setDIACHI(u.getDIACHI());
		n.setEMAIL(u.getEMAIL());
		n.setHOTEN(u.getHOTEN());
		n.setGIOITINH(u.getGIOITINH());
		n.setSDT(u.getSDT());
		
		
		return n;
	}
	
}
