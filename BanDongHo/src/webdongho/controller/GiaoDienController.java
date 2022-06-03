package webdongho.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import webdongho.entity.DONGHO;
import webdongho.entity.TAIKHOAN;
@Controller
@RequestMapping("/bandongho/")
@Transactional

public class GiaoDienController {
	@Autowired
	SessionFactory factory;
	@Transactional
//	@RequestMapping("giaodien")
//	public String giaodien(HttpSession ss) {
//		Session session = factory.getCurrentSession();
//		String hql = "FROM DONGHO";
//		Query query = session.createQuery(hql);
//		List<DONGHO> list = query.list();
//		ss.setAttribute("dh", list);	
//		List<DONGHO> cart = (List<DONGHO>) ss.getAttribute("cart");
//		if(cart == null) {
//			cart = new ArrayList<>();
//			ss.setAttribute("cart", cart);
//		}
//		return "bandongho/giaodien";
//	}
	
	@RequestMapping("giaodien1")
	public String giaodien1(HttpSession ss) {
		List<DONGHO> list = new ArrayList<>();
		list = (List<DONGHO>) ss.getAttribute("dh");
		if(list == null) {
			Session session = factory.getCurrentSession();
			String hql = "FROM DONGHO";
			Query query = session.createQuery(hql);
			list = query.list();
			ss.setAttribute("dh", list);
		}
		List<DONGHO> cart = (List<DONGHO>) ss.getAttribute("cart");
		if(cart == null) {
			cart = new ArrayList<>();
			ss.setAttribute("cart", cart);
		}
		return "redirect:/index.htm";
	}
	
	@RequestMapping(value = "them/{productId}", method = RequestMethod.GET)
	public String them(@PathVariable("productId")String productId, HttpSession ss) {
		Session session = factory.getCurrentSession();
		String hql = "FROM DONGHO dh WHERE dh.MADH = :mdh";
		Query query = session.createQuery(hql);
		query.setParameter("mdh", productId);
		DONGHO dh = (DONGHO) query.uniqueResult();
		DONGHO dh1 = new DONGHO();
		dh1.setMADH(dh.getMADH());
		dh1.setTENDH(dh.getTENDH());
		dh1.setGIA(dh.getGIA());
		dh1.setSOLUONGTON(1);
		dh1.setMOTA(dh.getMOTA());
//		dh1.setTrangThai(dh.getTR());
		dh1.setLOAIDH(dh.getLOAIDH());
//		dh1.setHangDH(dh.getHangDH());
		dh1.setHINHANH(dh.getHINHANH());
		List<DONGHO> cart = (List<DONGHO>) ss.getAttribute("cart");
		int kt = timDONGHO(cart, dh.getMADH());
//		for(DONGHO d:cart) d.toString();
		if(kt == -1 || cart.size() == 0 ) {
			cart.add(dh1);
			ss.setAttribute("cart", cart);
			
		}
		else if(kt >= 0) {
			cart.get(kt).setSOLUONGTON(cart.get(kt).getSOLUONGTON() + 1);
			ss.setAttribute("cart", cart);
		}
		return "redirect:/bandongho/giaodien1.htm";
	}
	
	@RequestMapping(value = "timkiem", method = RequestMethod.POST)
	public String timkiem(HttpServletRequest request, HttpSession ss) {
		String TenDH = request.getParameter("tendh");
		Session session = factory.getCurrentSession();
		String hql = "FROM DONGHO dh WHERE dh.TENDH LIKE :tdh";
		Query query = session.createQuery(hql);
		query.setParameter("tdh", "%" + TenDH + "%");
		List<DONGHO> list = query.list();
		ss.setAttribute("dh", list);
		return "bandongho/giaodien";
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session, HttpServletResponse response, HttpServletRequest request) {
		List<DONGHO> cart = (List<DONGHO>) session.getAttribute("cart");
		TAIKHOAN tk = (TAIKHOAN) session.getAttribute("tk");
		String tentk = tk.getTENTK().trim();
		Cookie arr[] = request.getCookies();
		if(cart.size() == 0 || cart == null) {
//			for(Cookie o:arr)
//				if(o.getName().equals(tentk)) {
//					o.setValue("");
//					o.setMaxAge(0);
//					System.out.println(o.getValue() + "abc");
//					return "redirect:/bandongho/sign-in.htm";
//				}
			Cookie cookie = new Cookie(tentk, "");
			cookie.setMaxAge(2*24*60*60);
			response.addCookie(cookie);
		}
		else {
			String str = new String("");
			for(DONGHO dh:cart) 
				str = new String(str + dh.toString() + "--");
			str = new String(str.replaceAll(" ", "/s"));
			Cookie cookie = new Cookie(tentk, str);
			cookie.setMaxAge(2*24*60*60);
			response.addCookie(cookie);
		}
		return "redirect:/bandongho/sign-in.htm";
	}
	
	public int timDONGHO(List<DONGHO> dsdh, String MaDH) {
		for(int i = 0; i < dsdh.size(); i++)
//		for(DONGHO dh:dsdh)
			if(dsdh.get(i).getMADH().trim().equals(MaDH.trim()))
				return i;
		return -1;
	}
	
	
}
