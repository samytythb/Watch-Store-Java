package webdongho.controller;

import java.net.http.HttpRequest;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import webdongho.entity.*;
import webdongho.model.*;

@Transactional
@Controller
@RequestMapping("index")
public class trangchuController {
	@Autowired
	SessionFactory factory;

	@Autowired
	public JavaMailSender mailer;

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
		session.removeAttribute("cart");
		session.removeAttribute("tk");
		session.removeAttribute("myNhanVien");
		session.removeAttribute("tkAdmin");
		return "redirect: /BanDongHo/index.htm";
	}
	@RequestMapping("/sign-in")
	public String signin(@ModelAttribute("account") account account) {
		return "trangchu/sign-in";
	}

	@RequestMapping("/sign-up")
	public String signup(@ModelAttribute("newaccount") newaccount newaccount) {
		return "trangchu/sign-up";
	}

	@RequestMapping("/forgotpassword")
	public String forgotpassword(@ModelAttribute("newpass") newpass newpass) {
		return "trangchu/forgot-password";
	}

	@RequestMapping(value = "/sign-in", method = RequestMethod.POST)
	public String checksignin(@ModelAttribute("account") account account, ModelMap model, BindingResult errors, HttpServletRequest request) {
		String userName = account.getUsername().trim().toString();
		String pass = account.getPassword().trim().toString();
		boolean checkusername = true, checkpassword = true;

		if (userName.isBlank()) {
			errors.rejectValue("username", "account", "Username cannot be empty");
			checkusername = false;
		}
		if (pass.isBlank()) {
			errors.rejectValue("password", "account", "Password cannot be empty");
			checkpassword = false;
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
		
		if ((list.isEmpty() || ((list.isEmpty() == false) && (list.get(0).getMATKHAU().trim().equals(pass) == false)))
				&& checkusername && checkpassword) {
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
	
	@RequestMapping(value = "/sign-up", method = RequestMethod.POST)
	public String checksignup(@ModelAttribute("newaccount") newaccount newaccount, ModelMap model, BindingResult errors)
			throws Exception {
		String newusername = newaccount.getUsername().trim();
		String newpass = newaccount.getPassword().trim();
		String newrepass = newaccount.getRepassword().trim();
		String newemail = newaccount.getEmail().trim();
		boolean checkusername = true, checkpass = true, checkrepass = true, checkemail = true;
		if (newusername.isBlank()) {
			errors.rejectValue("username", "newaccount", "Username cannot be empty");
			checkusername = false;
		}
		if (newpass.isBlank()) {
			errors.rejectValue("password", "newaccount", "Password cannot be empty");
			checkpass = false;
		}
		if (newrepass.isBlank()) {
			errors.rejectValue("repassword", "newaccount", "Repeat Password cannot be empty");
			checkpass = false;

		} else if (!(newrepass.equals(newpass))) {
			errors.rejectValue("repassword", "newaccount", "Does not match the password");
		}
		if (newemail.isBlank()) {
			errors.rejectValue("email", "newaccount", "Email cannot be empty");
			checkemail = false;
		}
		if (checkusername && checkpass && checkrepass && checkemail) {
			KHACHHANG newkhachhang = new KHACHHANG();
			newkhachhang.setMAKH(newusername);
			newkhachhang.setEMAIL(newemail);
			newkhachhang.setSOCMND(newusername);
			Integer temp0 = this.addKhachHang(newkhachhang);
			TAIKHOAN newaccountEntity = new TAIKHOAN();
			newaccountEntity.setTENTK(newusername);
			newaccountEntity.setMATKHAU(newpass);
			newaccountEntity.setMAKH_TK(newkhachhang);
			System.out.println(newaccountEntity.getTENTK());
			System.out.println(newaccountEntity.getMATKHAU());
			Integer temp = this.addAccount(newaccountEntity);
			System.out.println(temp);
			if (getKhachHangByEmail(newemail).isEmpty() == false) {
				model.addAttribute("message2", "Email already exists");
			}
			if (getAccount(newusername).isEmpty() == false) {
				model.addAttribute("message1", "Username already exists");
			}
			if (temp != 0 && temp0 != 0) {
				model.addAttribute("message", "Success");
				model.addAttribute("message1", "");
				model.addAttribute("message2", "");
			} else {
				model.addAttribute("message", "Fail");
			}

		}
		return "trangchu/sign-up";
	}

	@RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
	public String sendEmail1(@ModelAttribute("newpass") newpass newpass, ModelMap model,
			BindingResult errors) {
		System.out.println(newpass.getEmail());
		String email = newpass.getEmail().trim();
		if (email.isBlank()) {
			errors.rejectValue("email", "forgotpass", "Email cannot be empty");
		} else if (getKhachHangByEmail(email).isEmpty() == true) {
			errors.rejectValue("email", "forgotpass", "Email is incorrect");
		} else {
			MimeMessage mail = mailer.createMimeMessage();
			try {
				MimeMessageHelper helper = new MimeMessageHelper(mail, true);
				helper.setFrom("leminhduc2821@gmail.com", "PTIT WATCH");
				helper.setTo(email);
				helper.setReplyTo("leminhduc2821@gmail.com");
				double randomDouble = Math.random();
				randomDouble = randomDouble * 1000000;
				int newpassword = (int) randomDouble;
				System.out.println(newpassword);
				helper.setSubject("New password");
				helper.setText("New password: " + newpassword);
				List<KHACHHANG> kh= getKhachHangByEmail(email);
				String makh=kh.get(0).getMAKH().trim();
				List<TAIKHOAN> tk=getAccount(makh);
				TAIKHOAN tk0=tk.get(0);
				TAIKHOAN newaccountEntity = new TAIKHOAN();
				newaccountEntity.setTENTK(tk0.getTENTK());
				newaccountEntity.setMATKHAU(String.valueOf(newpassword));
				newaccountEntity.setMAKH_TK(tk0.getMAKH_TK());
				int temp=updateAccount(newaccountEntity);
				mailer.send(mail);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				errors.rejectValue("email", "forgotpass", "");
				model.addAttribute("message", "Email sending failed");
			}
			errors.rejectValue("email", "forgotpass", "");
			model.addAttribute("message", "Mail has been sent. Please check your email for the new password");
		}
		return "trangchu/forgot-password";
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
	public int updateAccount(TAIKHOAN tk) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.update(tk);
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