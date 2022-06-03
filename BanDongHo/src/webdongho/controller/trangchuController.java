package webdongho.controller;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import webdongho.bean.Mailer;
import webdongho.entity.*;
import webdongho.model.*;

@Transactional
@Controller
@RequestMapping("index")
public class trangchuController extends QuanTriController{
	@Autowired
	SessionFactory factory;

	@Autowired
	Mailer mailer;

	@RequestMapping()
	public String giaodien(HttpSession ss, @CookieValue(value = "hitCounter", defaultValue = "0") long hitCounter, HttpServletResponse response) {
		// lay so lan truy cap trang web  -- maxAge - trong khoang thoi gian check -- 
		hitCounter++;
		Cookie cookie = new Cookie("hitCounter", hitCounter + "");
		cookie.setMaxAge(30*24*60*60);
		response.addCookie(cookie);
		
		
		Session session = factory.getCurrentSession();
		String hql = "FROM DONGHO";
		Query query = session.createQuery(hql);
		List<DONGHO> list = query.list();
		ss.setAttribute("dh", list);	
		List<DONGHO> cart = (List<DONGHO>) ss.getAttribute("cart");
		if(cart == null) {
			cart = new ArrayList<>();
			ss.setAttribute("cart", cart);
		}
		return "trangchu/index";
	}
	
	@RequestMapping(params =  "timkiem", method = RequestMethod.POST)
	public String timkiem(HttpServletRequest request, HttpSession ss) {
		String TenDH = request.getParameter("tendh");
		Session session = factory.getCurrentSession();
		String hql = "FROM DONGHO dh WHERE dh.TENDH LIKE :tdh";
		Query query = session.createQuery(hql);
		query.setParameter("tdh", "%" + TenDH + "%");
		List<DONGHO> list = query.list();
		System.out.println(list.size() + "size dong ho");
		ss.setAttribute("dh", list);
		return "trangchu/index";
	}
	
	@RequestMapping("/log-out")
	public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		List<DONGHO> cart = (List<DONGHO>) session.getAttribute("cart");
		TAIKHOAN tk = (TAIKHOAN) session.getAttribute("tk");
		if(tk!=null) {
			String tentk = tk.getTENTK().trim();
			if(cart.size() == 0 || cart == null) {
				Cookie cookie = new Cookie(tentk, "");
				cookie.setMaxAge(0);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
			else {
				String str = new String("");
				for(DONGHO dh:cart) 
					str = new String(str + dh.toString() + "--");
				str = new String(str.replaceAll(" ", "/s"));
				Cookie cookie = new Cookie(tentk, str);
				cookie.setMaxAge(2*24*60*60);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		}
		
		session.removeAttribute("cart");
		session.removeAttribute("tk");
		session.removeAttribute("myNhanVien");
		session.removeAttribute("tkAdmin");
		return "redirect: /BanDongHo/index.htm";
	}
	

	

	@RequestMapping("/forgotpassword")
	public String forgotpassword(@ModelAttribute("newpass") newpass newpass) {
		return "trangchu/forgot-password";
	}
	@RequestMapping("change-pass")
	public String changepass(HttpSession ss)
	{
		if(ss.getAttribute("tk") == null && ss.getAttribute("tkAdmin") == null)
		{
			return "redirect:/index/sign-in.htm";
		}
		return "trangchu/change-pass";
	}
	@RequestMapping(value = "change-pass", method = RequestMethod.POST)
	public String changepass(HttpSession ss, ModelMap model,HttpServletRequest request)
	{
		if(request.getParameter("newpass").isBlank() || request.getParameter("renewpass").isBlank() || request.getParameter("pass").isBlank())
		{
			model.addAttribute("message", "Vui long nhap day du thong tin");
			return "trangchu/change-pass";
		}
		if(request.getParameter("newpass").trim().equals(request.getParameter("renewpass").trim()) == false)
		{
			model.addAttribute("message", "Mat khau moi khong khop");
			return "trangchu/change-pass";
		}
		String pass = request.getParameter("pass").trim(); 
		String newpass = request.getParameter("newpass").trim();
		TAIKHOAN tk = (TAIKHOAN) ss.getAttribute("tk");
		TAIKHOANADMIN tkAdmin = (TAIKHOANADMIN) ss.getAttribute("tkAdmin");
		if(tk == null)
		{
			if(tkAdmin.getMATKHAU().trim().equals(pass))
			{
				Session session = factory.getCurrentSession(); 
				String hql = "FROM TAIKHOANADMIN TK WHERE TK.TENTK = :TenTK"; 
				Query query = session.createQuery(hql); 
				query.setParameter("TenTK", tkAdmin.getTENTK().trim()); 
				tkAdmin = (TAIKHOANADMIN) query.uniqueResult();
				
				tkAdmin.setMATKHAU(newpass);
				model.addAttribute("message", "Doi mat khau thanh cong");
				ss.removeAttribute("tkAdmin");
				ss.removeAttribute("myNhanVien");
				ss.setAttribute("tkAdmin", tkAdmin);
				ss.setAttribute("myNhanVien", tkAdmin.getMANV_TK());
				return "trangchu/change-pass";
			}
			else
			{
				model.addAttribute("message", "Mat khau sai");
				return "trangchu/change-pass";
			}
		}
		else
		{
			if(tk.getMATKHAU().trim().equals(pass))
			{
				try
				{
					
					Session session = factory.getCurrentSession(); 
					String hql = "FROM TAIKHOAN TK WHERE TK.TENTK = :TenTK"; 
					Query query = session.createQuery(hql); 
					query.setParameter("TenTK", tk.getTENTK().trim()); 
					tk = (TAIKHOAN) query.uniqueResult();
					 
					tk.setMATKHAU(newpass);
					model.addAttribute("message", "Doi mat khau thanh cong");
					ss.removeAttribute("tk");
					ss.setAttribute("tk", tk);
				}
				catch(Exception e)
				{
					model.addAttribute("message", "Doi mat khau khong thanh cong" + e);
				}
				
				return "trangchu/change-pass";
			}
			else
			{
				model.addAttribute("message", "Mat khau sai" + pass +"  " + tk.getMATKHAU());
				return "trangchu/change-pass";
			}
			
		}
		
		
	}
	@RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
	public String sendEmail1(@ModelAttribute("newpass") newpass newpass, ModelMap model,
			BindingResult errors) {
		String email = newpass.getEmail().trim();
		if (email.isBlank()) {
			errors.rejectValue("email", "forgotpass", "Email cannot be empty");
		} else if (getKhachHangByEmail(email).isEmpty() == true) {
			errors.rejectValue("email", "forgotpass", "Email is incorrect");
		} else {
			
			try {
				Double random = Math.random() * 1000000;
				String pass = String.valueOf(random).substring(0, 5);
				Session session = factory.getCurrentSession();
				String hql1 = "FROM TAIKHOAN";
				Query query = session.createQuery(hql1);
				List<TAIKHOAN> list = query.list();
				KHACHHANG kh= new KHACHHANG();
				if(list.isEmpty() == false)
				{
					kh = getKhachHangByEmail(email).get(0);
				}

				TAIKHOAN tk1 = new TAIKHOAN();
				if(kh != null)
					for(TAIKHOAN tk:list)
						if(tk.getMAKH_TK().getMAKH().equals(kh.getMAKH())) {
							tk.setMATKHAU(pass);
							break;
						}
				mailer.send("hieuhdhk@gmail.com", email, "New password", "New password: " + pass );
				
			} catch (Exception e) {
				errors.rejectValue("email", "forgotpass", "");
				model.addAttribute("message", "Email sending failed " + e );
				return "trangchu/forgot-password";
			}
			errors.rejectValue("email", "forgotpass", "");
			model.addAttribute("message", "Mail has been sent. Please check your email for the new password");
		}
		return "trangchu/forgot-password";
	}
	
	@RequestMapping("/sign-in")
	public String signin(@ModelAttribute("account") account account) {
		return "trangchu/sign-in";
	}
	@RequestMapping(value = "/sign-in", method = RequestMethod.POST)
	public String checksignin(@ModelAttribute("account") account account, ModelMap model, BindingResult errors, HttpServletRequest request) {
		String userName = account.getUsername().trim().toString();
		String pass = account.getPassword().trim().toString();

		if (userName.isBlank()) {
			errors.rejectValue("username", "account", "Username cannot be empty");
			return "trangchu/sign-in";
		}
		if (pass.isBlank()) {
			errors.rejectValue("password", "account", "Password cannot be empty");
			return "trangchu/sign-in";
		}
		List<TAIKHOAN> list = this.getAccount(userName);
		TAIKHOANADMIN admin = this.getAdmin(userName);
		if(admin != null ) {
			if(admin.getMATKHAU().trim().equals(pass) == false ) {
				errors.rejectValue("password", "account", "Password incorrect!!");
				return "trangchu/sign-in";
			}
			else {
				
				NHANVIEN nv = this.getAccountAdmin(userName).getMANV_TK();
				
				HttpSession session = request.getSession();
				session.setAttribute("myNhanVien", nv);
				session.setAttribute("tkAdmin", admin);
				session.removeAttribute("tk");
				
				return "redirect:/index.htm";
			}
		}
		
		if ((list.isEmpty() || ((list.isEmpty() == false) && (list.get(0).getMATKHAU().trim().equals(pass) == false)))) {
			errors.rejectValue("username", "account", "Username or Password incorrect");
			errors.rejectValue("password", "account", "Username or Password incorrect");

		} else if (list.isEmpty() == false && list.get(0).getMATKHAU().trim().equals(pass)) {
			
			HttpSession ss = request.getSession();
			TAIKHOAN taikhoan = this.getAccount(userName).get(0);
			ss.removeAttribute("tkAdmin");
			ss.removeAttribute("myNhanVien");
			ss.removeAttribute("tk");
			
			ss.setAttribute("tk", taikhoan);
			Session session = factory.getCurrentSession();
			String hql = "FROM TAIKHOAN TK WHERE TK.TENTK = :TenTK AND TK.MATKHAU = :MatKhau";
			Query query = session.createQuery(hql);
			query.setParameter("TenTK", list.get(0).getTENTK());
			query.setParameter("MatKhau", list.get(0).getMATKHAU());
			TAIKHOAN tk1 =(TAIKHOAN) query.uniqueResult();
			List<DONGHO> list1 = new ArrayList<>();
			if (tk1!=null) {
				ss.setAttribute("tk", tk1);
				Cookie arr[] = request.getCookies();
				if(arr != null) {
					for(Cookie o:arr) {
						if(o.getName().trim().equals(tk1.getTENTK().trim()) && !o.getValue().equals("")) {
							String str = o.getValue();
							String str1[] = str.replaceAll("/s", " ").split("--");
							for(String s:str1) {
								String[] str2= s.split(":");
								DONGHO dh = new DONGHO();
								dh.setMADH(str2[0]);
								dh.setTENDH(str2[1]);
								dh.setGIA(Integer.parseInt(str2[2]));
								dh.setSOLUONGTON(Integer.parseInt(str2[3]));
								dh.setLOAIDH(str2[4]);
								dh.setMOTA(str2[5]);
								dh.setHINHANH(str2[6]);
								list1.add(dh);
							}
						}
					}
				}
				ss.setAttribute("cart", list1);
			}
			return "redirect:/index.htm";
		}
		
		
		
		return "trangchu/sign-in";
	}    
	@RequestMapping("/log-out-admin")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("tk");
		session.removeAttribute("myNhanVien");
		session.removeAttribute("tkAdmin");
		return "redirect: /BanDongHo/index.htm";
	}
	
	@RequestMapping(value = "/sign-up", method = RequestMethod.GET )
	public String signup(@ModelAttribute("newaccount") newaccount newaccount) {
		return "trangchu/sign-up";
	}
	@RequestMapping(value = "/sign-up", method = RequestMethod.POST)
	public String checksignup(@ModelAttribute("newaccount") newaccount newaccount, ModelMap model, BindingResult errors)
			throws Exception {
		if (newaccount.getUsername().isBlank()) {
			errors.rejectValue("username", "newaccount", "Username cannot be empty");
		}
		if (newaccount.getPassword().isBlank()) {
			errors.rejectValue("password", "newaccount", "Password cannot be empty");
		}
		if (newaccount.getRepassword().isBlank()) {
			errors.rejectValue("repassword", "newaccount", "Repeat Password cannot be empty");

		} else if (!(newaccount.getPassword().equals(newaccount.getRepassword()))) {
			errors.rejectValue("repassword", "newaccount", "Does not match the password");
		}
		if (newaccount.getEmail().isBlank()) {
			errors.rejectValue("email", "newaccount", "Email cannot be empty");
		}
		

		if(errors.hasErrors())
		{
			return "trangchu/sign-up";
		}
		if (getKhachHangByEmail(newaccount.getEmail()).isEmpty() == false) {
			model.addAttribute("message", "Email already exists");
			return "trangchu/sign-up";
		}
		if (getAccount(newaccount.getUsername()).isEmpty() == false) {
			model.addAttribute("message", "Username already exists");
			return "trangchu/sign-up";
		}
		
		KHACHHANG newkhachhang = new KHACHHANG();
		newkhachhang.setEMAIL(newaccount.getEmail());
		newkhachhang.setMAKH(newaccount.getUsername());
		newkhachhang.setHOTEN(newaccount.getUsername());
		newkhachhang.setGIOITINH(true);

		
		TAIKHOAN newaccountKH = new TAIKHOAN();
		newaccountKH.setTENTK(newaccount.getUsername());
		newaccountKH.setMATKHAU(newaccount.getPassword());
		newaccountKH.setMAKH_TK(newkhachhang);
		try
		{
			this.addKhachHang(newkhachhang);
			this.addAccount(newaccountKH);
			model.addAttribute("message", "Success");
		}
		catch(Exception e)
		{
			model.addAttribute("message", "Fail");
		}
		

		return "trangchu/sign-up";
	}
	@RequestMapping(value = "shopping/{MADH}.htm")
	public String shoppingWatch(HttpServletRequest request, ModelMap model,
			@PathVariable("MADH") String maDH) {
		
		DONGHO dh = this.getDongHo(maDH);
		
		
		model.addAttribute("dongho", dh);
		return "trangchu/shopping";
	}
	
	@RequestMapping(value = "myCart/{MAKH}.htm")
	public String myCart(HttpServletRequest request, ModelMap model,
			@PathVariable("MAKH") String maKH) {
		
		KHACHHANG kh = this.getKhachHang(maKH);
		
		
		model.addAttribute("phieudats", kh.getPHIEUDATS());
		return "trangchu/dondathang";
	}
	@RequestMapping("myhoadon/{MAPD}.htm")
	public String myHoaDon(ModelMap model, @PathVariable("MAPD") String mapd) {
		PHIEUDAT__ pd = this.getPhieuDat(mapd);
		System.out.println(pd.getMAKH_PD().getEMAIL());
		//List<PHIEUDAT> list = this.getPhieuDats(1);
		model.addAttribute("phieudat", pd);
		return "trangchu/hoadon";
	}
	public KHACHHANG getKhachHang(String maKH) {
		Session session = factory.getCurrentSession();
		String hql = "FROM KHACHHANG WHERE MAKH = :maKH";
		Query query = session.createQuery(hql);
		query.setParameter("maKH", maKH);
		KHACHHANG user = (KHACHHANG) query.list().get(0);
		return user;
	}
	public TAIKHOANADMIN getAccountAdmin(String name) {
		Session session = factory.getCurrentSession();
		String hql = "FROM TAIKHOANADMIN WHERE TENTK = :name";
		Query query = session.createQuery(hql); 
		query.setParameter("name", name);
		TAIKHOANADMIN user = (TAIKHOANADMIN) query.list().get(0);
		return user;
	}

	
	public NHANVIEN getNhanVien(String maNV) {
			
			Session session = factory.getCurrentSession();
			String hql = "From NHANVIEN where MANV=:maNV";
			Query query = session.createQuery(hql);
			query.setParameter("maNV", maNV);
			return (NHANVIEN) query.list().get(0);
		}
	



	public List<TAIKHOAN> getAccount(String name) {
		Session session = factory.getCurrentSession();
		String hql = "FROM TAIKHOAN WHERE TENTK = :name";
		Query query = session.createQuery(hql);
		query.setParameter("name", name);
		List<TAIKHOAN> user = query.list();
		return user;
	}
	public TAIKHOANADMIN getAdmin(String name) {
		Session session = factory.getCurrentSession();
		String hql = "FROM TAIKHOANADMIN WHERE TENTK = :name";
		Query query = session.createQuery(hql);
		query.setParameter("name", name);
		TAIKHOANADMIN user = null;
		try {
			user = (TAIKHOANADMIN) query.list().get(0);
		} catch (Exception e) {
			return null;
		}
		return user;
	}

	public List<KHACHHANG> getKhachHangByEmail(String makh) {
		Session session = factory.getCurrentSession();
		String hql = "FROM KHACHHANG WHERE Email = :makh";
		Query query = session.createQuery(hql);
		query.setParameter("makh", makh);
		List<KHACHHANG> kh = query.list();
		return kh;
	}

	public int addAccount(TAIKHOAN tk) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(tk);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	public int addKhachHang(KHACHHANG kh) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(kh);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}
}