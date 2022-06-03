package webdongho.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import webdongho.entity.CTPD;
import webdongho.entity.DONGHO;
import webdongho.entity.KHACHHANG;
import webdongho.entity.PHIEUDAT;
import webdongho.entity.TAIKHOAN;

@Controller
@RequestMapping("/bandongho/")
@Transactional
public class CartController {
	@Autowired
	SessionFactory factory;
	@Transactional
	@RequestMapping("cart")
	public String cart(HttpSession ss, HttpServletRequest request) {
		List<DONGHO> cart =  (List<DONGHO>) ss.getAttribute("cart");
		Session session = factory.getCurrentSession();
		String hql = "FROM KHACHHANG WHERE MAKH = :makh";
		Query query = session.createQuery(hql);
		TAIKHOAN tk = (TAIKHOAN) ss.getAttribute("tk");
		query.setParameter("makh", tk.getMAKH_TK().getMAKH());
		KHACHHANG KH = (KHACHHANG) query.uniqueResult();
		ss.setAttribute("KH", KH);
		
		int TongTien = 0;
		if(cart != null) {
			for(DONGHO dh:cart) 
				TongTien = TongTien + dh.getGIA()*dh.getSOLUONGTON();
			request.setAttribute("TongTien", TongTien);
		}
		return "bandongho/cart";
	}
	@RequestMapping("log-out")
	public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		List<DONGHO> cart = (List<DONGHO>) session.getAttribute("cart");
		TAIKHOAN tk = (TAIKHOAN) session.getAttribute("tk");
		String tentk = tk.getTENTK().trim();
		Cookie arr[] = request.getCookies();
		if(cart.size() == 0 || cart == null) {
			Cookie cookie = new Cookie(tentk, "");
			cookie.setMaxAge(2*24*60*60);
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
		return "redirect:/index.htm";
	}
	
	
	@RequestMapping(value="cart", method = RequestMethod.POST)
	public String chinhSua(HttpSession ss,HttpServletRequest request) {
		List<DONGHO> cart = (List<DONGHO>) ss.getAttribute("cart");
		int sl = Integer.parseInt(request.getParameter("soluong"));
		String MaDH = request.getParameter("MaDH");
		DONGHO dh = timDONGHO1(cart, MaDH);
		if(sl < 0)
			dh.setSOLUONGTON(1);
		else
			dh.setSOLUONGTON(sl);
		int TongTien = 0;
		for(DONGHO d :cart) 
			TongTien = TongTien + d.getGIA()*d.getSOLUONGTON();
		request.setAttribute("TongTien", TongTien);

		return "bandongho/cart";
	}
	
	@RequestMapping(value="cart",params="btnTinhTien", method = RequestMethod.POST)
	public String tinhTien(HttpSession ss,HttpServletRequest request) {
		List<DONGHO> cart = (List<DONGHO>) ss.getAttribute("cart");
		List<DONGHO> list = (List<DONGHO>) ss.getAttribute("dh");
		List<DONGHO> cart1 = new ArrayList<>();

		
		for(DONGHO d:cart)
			cart1.add(new DONGHO(d));
		for(DONGHO dh1:cart) {
			for(DONGHO dh2:list) {
				if(dh1.getMADH().trim().equals(dh2.getMADH().trim())) {
					System.out.println(dh1.getSOLUONGTON() + " " + dh2.getSOLUONGTON());
					if(dh1.getSOLUONGTON() <= dh2.getSOLUONGTON())
						dh1.setSOLUONGTON(dh2.getSOLUONGTON()-dh1.getSOLUONGTON());
					else {
						request.setAttribute("message","Số lượng đồng hồ " + dh1.getTENDH() + 
								" chỉ còn lại là " + dh2.getSOLUONGTON() + "!");
						return "bandongho/cart";
					}
				}
			}
		}
		Session session = factory.openSession();
		String hql = "FROM KHACHHANG WHERE MAKH = :makh";
		Query query = session.createQuery(hql);
		TAIKHOAN tk = (TAIKHOAN) ss.getAttribute("tk");
		query.setParameter("makh", tk.getMAKH_TK().getMAKH());
		KHACHHANG KH1 = (KHACHHANG) query.uniqueResult();
		KHACHHANG KH =(KHACHHANG) ss.getAttribute("KH");
		PHIEUDAT PD = themPD(KH.getHOTEN(), KH.getDIACHI(), KH.getSDT(), KH1);
		session.close();
		session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(PD);
			for(DONGHO dh:cart)
				session.update(dh);
			for(DONGHO dh:cart1)
				session.save(themCTPD(dh, PD, dh.getSOLUONGTON(), dh.getGIA()));
			cart = new ArrayList<>();
			ss.setAttribute("cart", cart);
			request.setAttribute("message", "Mua thành công!");
			t.commit();
		} catch (Exception e) {
			t.rollback();
			System.out.println("abc");
		}
		return "redirect: /BanDongHo/index.htm";
	}

	public PHIEUDAT themPD(String Hoten, String DiaChi, String SDT, KHACHHANG KH) {
		PHIEUDAT PD = new PHIEUDAT();
		Date date = new Date();
		PD.setNGAYDAT(date);
		PD.setHOTENNGUOINHAN(Hoten);
		PD.setDIACHI(DiaChi);
		PD.setSDT(SDT);
		PD.setMAKH_PD(KH);
		PD.setTRANGTHAI(0);
        Calendar today = Calendar.getInstance();
        today.add(Calendar.DAY_OF_MONTH, 5);
        Date date1 = today.getTime();
		PD.setNGAYGiAO(date1);
		return PD;
	}
	
	public CTPD themCTPD(DONGHO dh, PHIEUDAT PD, int SoLuong, int DonGia) {
		CTPD ctpd = new CTPD();
		CTPD.Pk pk = new CTPD.Pk();
		pk.setMaDH(dh.getMADH());
		pk.setMaPD(PD.getMAPD());
		ctpd.setId(pk);
		ctpd.setSOLUONG(SoLuong);
		ctpd.setDONGIA(DonGia);
		return ctpd;
	}
	
	@RequestMapping(value = "xoa/{productId}", method = RequestMethod.GET)
	public String add(@PathVariable("productId")String productId, HttpSession ss) {
		List<DONGHO> cart = (List<DONGHO>) ss.getAttribute("cart");
		int kt = timDONGHO(cart, productId);
		if(kt != -1) {
			cart.remove(kt);
			ss.setAttribute("cart", cart);
//			for(DONGHO dh:cart) System.out.println(dh.toString());
		}
		return "redirect:/bandongho/cart.htm";
	}
	
	public int timDONGHO(List<DONGHO> dsdh, String MaDH) {
		for(int i =0; i < dsdh.size(); i++)
			if(dsdh.get(i).getMADH().equals(MaDH))
				return i;
		return -1;
	}
	
	public DONGHO timDONGHO1(List<DONGHO> dsdh, String MaDH) {
		for(DONGHO dh:dsdh)
			if(dh.getMADH().equals(MaDH))
				return dh;
		return null;
	}
	
}
