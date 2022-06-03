package webdongho.controller;


import java.util.*;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import webdongho.entity.*;
import webdongho.model.*;
@Transactional
@Controller
@RequestMapping("/nhaphang/")
public class NhapDongHoController extends QuanTriController {

	@Autowired 
	SessionFactory factory;
	
	
	
	@RequestMapping(value = "insertPN", method = RequestMethod.GET)
	public String insertPN(ModelMap model, HttpServletRequest request) {
		
		this.loadDataDongHo(request,model);
		model.addAttribute("donghos",this.getDongHos());
		PHIEUNHAP pn = this.getExistPN();
		CTPN ctpn = new CTPN();
		if(pn != null) {
			model.addAttribute("maphieunhap",pn.getMAPN());
			model.addAttribute("phieunhap", pn);
			model.addAttribute("CTPNS",pn.getCTPNS_PN());
			model.addAttribute("ctpnn",ctpn);
			model.addAttribute("add",1);
			model.addAttribute("submit", "Thêm");
		}
		else {
			model.addAttribute("add",0);
			model.addAttribute("submit", "Thêm");
		}
		
		return "quantri/nhapdongho";
	}
	@RequestMapping(value = "insertPN",params = "addPN" , method = RequestMethod.GET)
	public String addPN(ModelMap model, HttpServletRequest request) {
		
		this.loadDataDongHo(request,model);
		model.addAttribute("donghos",this.getDongHos());
		
		PHIEUNHAP pn = this.makePhieuNhap();
		
		CTPN ctpn = new CTPN();
		//ctpn.setDH_CTPN(this.getDongHo("DH5"));
		ctpn.setPN_CTPN(pn);
		
		model.addAttribute("maphieunhap",pn.getMAPN());
		model.addAttribute("phieunhap", pn);
		model.addAttribute("CTPNS",pn.getCTPNS_PN());
		model.addAttribute("ctpnn",ctpn);
		model.addAttribute("add",1);
		model.addAttribute("submit", "Thêm");
		
		return "quantri/nhapdongho";
	}
	@RequestMapping(value = "insertPN/{MAPN}.htm", params = "addProduct")
	public String addProduct(ModelMap model, HttpServletRequest request,
				@PathVariable("MAPN") String mapn ,
				@ModelAttribute("ctpnn") CTPN ctpnNew,
				BindingResult errors
				){
		this.loadDataDongHo(request,model);
		model.addAttribute("donghos",this.getDongHos());
		PHIEUNHAP pn = this.getPhieuNhap(mapn);
		ctpnNew.setPN_CTPN(pn);
		
		//================= ghi ctpn vaof 	co so du lieu ///
		if(ctpnNew.getDONGIA() < 0) {
			errors.rejectValue("DONGIA", "ctpnn", "Đơn giá không hợp lệ!!");
			model.addAttribute("maphieunhap",pn.getMAPN());
			model.addAttribute("phieunhap", pn);
			model.addAttribute("CTPNS",pn.getCTPNS_PN());
			model.addAttribute("ctpnn",ctpnNew);
			model.addAttribute("add",1);
			return "quantri/nhapdongho";
		}
		if(ctpnNew.getSOLUONG() < 1) {
			errors.rejectValue("SOLUONG", "ctpnn", "Số lượng không hợp lệ!!");
			model.addAttribute("maphieunhap",pn.getMAPN());
			model.addAttribute("phieunhap", pn);
			model.addAttribute("CTPNS",pn.getCTPNS_PN());
			model.addAttribute("ctpnn",ctpnNew);
			model.addAttribute("add",1);
			return "quantri/nhapdongho";
		}
		for (CTPN ct : pn.getCTPNS_PN()) {
			if(ct.getDH_CTPN().getMADH().equals(ctpnNew.getDH_CTPN().getMADH())) {
				errors.rejectValue("DH_CTPN", "ctpnn", "Đồng hồ đã có trong phiếu nhập này!!");
				model.addAttribute("maphieunhap",pn.getMAPN());
				model.addAttribute("phieunhap", pn);
				model.addAttribute("CTPNS",pn.getCTPNS_PN());
				model.addAttribute("ctpnn",ctpnNew);
				model.addAttribute("add",1);
				return "quantri/nhapdongho";
			}
		}
		
		if(ctpnNew != null) {
				this.insertCTPN(ctpnNew);
		}
		Collection<CTPN> ctpns = pn.getCTPNS_PN();
		ctpns.add(ctpnNew);
		pn.setCTPNS_PN(ctpns);
		System.out.println(pn.getCTPNS_PN().isEmpty());
		model.addAttribute("CTPNS",pn.getCTPNS_PN());
		model.addAttribute("maphieunhap", mapn);
		model.addAttribute("submit", "Thêm");
		model.addAttribute("ctpnn",new CTPN());
		model.addAttribute("add",1);
		model.addAttribute("message","Thêm product thành công!");
		return "quantri/nhapdongho";
	}
	
	@RequestMapping("insertPN/updatectpn")
	public String updatethisctpn(
				@RequestParam("DH_CTPN_OLD") String dhOld,
				ModelMap model,
				HttpServletRequest request,
				@RequestParam("PN_CTPN") String pn,
				@ModelAttribute("ctpn") CTPN thisctpn ) {
		
			this.loadDataDongHo(request,model);
			
			model.addAttribute("donghos",this.getDongHos());
			DONGHO thisDHOld = this.getDongHo(dhOld);
			DONGHO thisDHNew = thisctpn.getDH_CTPN();
			PHIEUNHAP thisPN = this.getPhieuNhap(pn);
			CTPN thisCTPN = this.getCTPN(thisDHOld, thisPN);
			
			System.out.println("thisctpn == " + thisctpn.toString());
			System.out.println("thisCTPN == " + thisCTPN.toString());
			
			//=== KIEM TRA CHINH SUA HOP LE =========================================
			if(!thisctpn.getDH_CTPN().getMADH().equals(dhOld)) {
				if( this.getCTPN(thisDHNew, thisPN) != null) {
				
					System.out.println("Dong ho tron phieu nhap nay da ton tai!!!!");
					model.addAttribute("message","Mã đồng hồ đã có trong phiếu nhập này!!");
				}
				else {
					
					this.deleteCTPN(thisCTPN);
					thisCTPN.setDH_CTPN(thisDHNew);
					this.insertCTPN(thisctpn);
					model.addAttribute("message","Chỉnh sửa thành công!");
				}
			}
			//=== KIEM TRA CHINH SUA HOP LE =========================================
			else {
				thisCTPN.setDONGIA(thisctpn.getDONGIA());
				thisCTPN.setSOLUONG(thisctpn.getSOLUONG());
				this.updateCTPN(thisCTPN);
				model.addAttribute("message","Chỉnh sửa thành công!");
			}
//			System.out.println("so ctpn tron pn new " + this.getPhieuNhap(thisPN.getMAPN()).getCTPNS_PN().size());
//		//	System.out.println("so ctpn tron pn ole " + thisPN.getCTPNS_PN().size());
//			
//			for (CTPN ct : this.getPhieuNhap(thisPN.getMAPN()).getCTPNS_PN()) {
//				System.out.println(ct.toString());
//			}
//			
			model.addAttribute("CTPNS",this.getExistPN().getCTPNS_PN());
			
			model.addAttribute("maphieunhap", thisPN.getMAPN());
			model.addAttribute("submit", "Thêm");
			CTPN newCTPN = new CTPN();
			model.addAttribute("ctpnn", newCTPN);
			model.addAttribute("add",1);
			
			return "quantri/nhapdongho";
//			return this.insertPN(model, request);

		}
//	@RequestMapping("insertPN/updatectpn")
//	public String updatethisctpn(
//			@RequestParam("PN_CTPN") String pn,
//			@RequestParam("DH_CTPN") String dh,
//			@RequestParam("DH_CTPN_OLD") String dhOld,
//			@RequestParam("SOLUONG") int sl,
//			@RequestParam("DONGIA") int dg,
//			ModelMap model,
//			HttpServletRequest request,
//			@ModelAttribute("ctpn") CTPN ctpn) {
//		//========  NEU DOI DH THI XOA CTPN CÅ¨ THÃŠM Má»šI CHá»¨ KO UPDATE Ä�Æ¯á»¢C ++++++++++++++++++++++++++ 
//		System.out.println("DH = " + dh + "---");
//		System.out.println("DH = " + dhOld + "---");
//		System.out.println("DH = " + pn);
//		System.out.println("DH = " + sl);
//		System.out.println("DH = " + dg);
//		System.out.println("ctpn " + ctpn.getDONGIA());
//		this.loadDataDongHo(request,model);
//		model.addAttribute("donghos",this.getDongHos());
//		DONGHO thisDHOld = this.getDongHo(dhOld);
//		DONGHO thisDHNew = this.getDongHo(dh);
//		PHIEUNHAP thisPN = this.getPhieuNhap(pn);
//		CTPN thisCTPN = this.getCTPN(thisDHOld, thisPN);
//		//=== KIEM TRA CHINH SUA HOP LE =========================================
//		if(!dh.equals(dhOld) && this.getCTPN(thisDHNew, thisPN) != null) {
//			System.out.println("Dong ho tron phieu nhap nay da ton tai!!!!");
//			
//		}
//		//=== KIEM TRA CHINH SUA HOP LE =========================================
//		else {
//			thisCTPN.setDH_CTPN(thisDHNew);
//			thisCTPN.setDONGIA(dg);
//			thisCTPN.setSOLUONG(sl);
//			this.updateCTPN(thisCTPN);
//		}
//		
//		model.addAttribute("CTPNS",this.getPhieuNhap(pn).getCTPNS_PN());
//		model.addAttribute("maphieunhap", pn);
//		model.addAttribute("submit", "Thêm");
//		model.addAttribute("ctpnn",new CTPN());
//		model.addAttribute("add",1);
//		return "quantri/nhapdongho";
//		
//	}
	
	@RequestMapping(value = "insertPN/{MAPN}.htm", params = "delProduct")
	public String delProduct(ModelMap model, HttpServletRequest request,
			@PathVariable("MAPN") String str){
		this.loadDataDongHo(request,model);
		model.addAttribute("donghos",this.getDongHos());
		if(str.length() >= 9) {
			String mapn = str.substring(0, 6);
			String madh = str.substring(6, str.length());
			
			System.out.println(mapn + " sdfjgsf === " + madh);
			PHIEUNHAP pn = this.getPhieuNhap(mapn);
			CTPN ctpn = this.getCTPN(this.getDongHo(madh), pn);
			
			if(ctpn != null) {
				this.deleteCTPN(ctpn);
				Collection<CTPN> ctpns = pn.getCTPNS_PN();
				ctpns.remove(ctpn);
				pn.setCTPNS_PN(ctpns);
			}
			
			//System.out.println(pn.getCTPNS_PN().isEmpty());
			model.addAttribute("CTPNS",pn.getCTPNS_PN());
			model.addAttribute("maphieunhap", mapn);
			model.addAttribute("submit", "Thêm");
			model.addAttribute("ctpnn",new CTPN());
			model.addAttribute("add",1);
			model.addAttribute("message","Xóa product thành công!");
		}
		return "quantri/nhapdongho";
	}

	@RequestMapping(value = "huyphieunhap/${maphieunhap}.htm")
	public String delPhieuNhap(@PathVariable("maphieunhap") String mapn, ModelMap model, HttpServletRequest request) {
		
		PHIEUNHAP pn = this.getPhieuNhap(mapn);
		if(pn != null) {
			for (CTPN ctnp : pn.getCTPNS_PN()) {
				this.deleteCTPN(ctnp);
			}
			this.deletePN(pn);
		}
		model.addAttribute("message","Hủy phiếu nhập thành công!");
		return insertPN(model, request);
	}
	
	@RequestMapping(value = "insertPN/{MAPN}.htm", params = "success")
	public String success(ModelMap model, HttpServletRequest request,
			@PathVariable("MAPN") String mapn ,@ModelAttribute("ctpnn") CTPN ctpnNew){

		HttpSession session = request.getSession();
		NHANVIEN nv = (NHANVIEN) session.getAttribute("myNhanVien");
		if(nv == null) {
			return "trangchu/sign-in";
		}
		PHIEUNHAP pn = this.getPhieuNhap(mapn);
		pn.setMANV_PN(nv);
		//this.insertPN(pn);
		int kt = 0;
		for (CTPN ctpn : pn.getCTPNS_PN()) {
			System.out.println(ctpn.getDH_CTPN().getMADH() + "  __________________________");
			kt = this.updateSLT(ctpn.getDH_CTPN().getMADH(), ctpn.getSOLUONG());
			if(kt == 1) {
				model.addAttribute("message", "Successfully!");
			}
			if(kt == 0) {
				model.addAttribute("message", "FAIL!");
				//Neues loi giua chung thi se dan den loi du lieu
				//return addProduct(model, request, mapn, ctpnNew);
			}
		}
		
		
		model.addAttribute("add", 0);
		model.addAttribute("message","Thêm phiếu nhập thành công!");
		return insertPN(model, request);
	}
	
	

	
	public int updateSLT(String madh, int soluong) {
		DONGHO dongho = getDongHo(madh);
		if(dongho == null)
			return 0;
		dongho.setSOLUONGTON(dongho.getSOLUONGTON() + soluong);
		
		return 1;
	}
	
	public int updateDongHo(DONGHO dongho) {
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		try {
			System.out.println(dongho.getMADH() + "   _SA@@@@@@@@@@@@@@@@@@@@@");
			session.update(dongho);
			t.commit();
			System.out.println("cap nhat DH thanh cong!!");
		} catch (Exception e) {
			t.rollback();
			System.out.println("cap nhat DH that bai!!  " + e.getMessage());
			return 0;
		}
		finally {
			session.close();
		}
		
		return 1;
	}
	
	public int updatePN(PHIEUNHAP phieunhap) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		try {
			session.update(phieunhap);
			t.commit();
			System.out.println("cap nhat PN thanh cong!!");
		} catch (Exception e) {
			t.rollback();
			System.out.println("cap nhat PN that bai!!  " + e.getMessage());
			return 0;
		}
		finally {
			session.close();
		}
		return 1;
	}
	
	public int insertPN(PHIEUNHAP phieunhap) {
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		try {
			session.save(phieunhap);
			t.commit();
			System.out.println("them PN thanh cong!!");
		} catch (Exception e) {
			t.rollback();
			System.out.println("them PN that bai!!" + e.getMessage());
			return 0;
		}
		finally {
			session.close();
		}
		return 1;
	}
	public int deletePN(PHIEUNHAP phieunhap) {
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		try {
			session.delete(phieunhap);
			t.commit();
			System.out.println("delete PN thanh cong!!");
		} catch (Exception e) {
			t.rollback();
			System.out.println("delete PN that bai!!" + e.getMessage());
			return 0;
		}
		finally {
			session.close();
		}
		return 1;
	}
	public NHANVIEN getNhanVien(String maNV) {
		
		Session session = factory.getCurrentSession();
		String hql = "From NHANVIEN where MANV=:maNV";
		Query query = session.createQuery(hql);
		query.setParameter("maNV", maNV);
		return (NHANVIEN) query.list().get(0);
	}
	
	public PHIEUNHAP makePhieuNhap() {
		
		int max = 999999;
		int min = 100000;
		int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
		while(this.getPhieuNhap(random_int + "") != null) {
			random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
		}
		PHIEUNHAP phieunhap = new PHIEUNHAP();
		phieunhap.setMAPN(random_int + "");
		phieunhap.setMANV_PN(this.getNhanVien("TMP"));
		LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = now.format(formatter);
		phieunhap.setNGAYNHAP(formatDateTime);
		this.insertPN(phieunhap);
		
		return phieunhap;
	}
	
	public PHIEUNHAP getPhieuNhap(String mapn) {
		Session session = factory.getCurrentSession();
		String hql = "From PHIEUNHAP where MAPN=:mapn";
		Query query = session.createQuery(hql);
		query.setParameter("mapn", mapn);
		
		PHIEUNHAP pn;
		try {
			pn = (PHIEUNHAP) query.list().get(0);
		} catch (Exception e) {
			return null;
		}
		
		return pn;
	}
	
	public int insertCTPN(CTPN ctpn) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		try {
			session.save(ctpn);
			t.commit();
			System.out.println("them CTPN thanh cong!!");
		} catch (Exception e) {
			t.rollback();
			System.out.println("them CTPN that bai!!" + e.getMessage());
			return 0;
		}
		finally {
			session.close();
		}
		return 1;
	}
	
	public int deleteCTPN(CTPN ctpn) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		try {
			session.delete(ctpn);
			t.commit();
			System.out.println("del CTPN thanh cong!!");
		} catch (Exception e) {
			t.rollback();
			System.out.println("del CTPN that bai!!" + e.getMessage());
			return 0;
		}
		finally {
			session.close();
		}
		return 1;
	}
	
	public int insertCTPNs(Collection<CTPN> ctpns) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		for (CTPN ctpn : ctpns) {
			try {
				session.save(ctpn);
				t.commit();
				System.out.println("them CTPN thanh cong!!");
			} catch (Exception e) {
				t.rollback();
				System.out.println("them CTPN that bai!!" + e.getMessage());
				//return 0;
			}
			finally {
				session.close();
			}
		}
		
		return 1;
	}
	public CTPN getCTPN(DONGHO dh, PHIEUNHAP pn) {
		Session session = factory.getCurrentSession();
		String hql = "From CTPN where DH_CTPN=:dh and PN_CTPN =:pn";
		Query query = session.createQuery(hql);
		query.setParameter("dh", dh);
		query.setParameter("pn", pn);
		
		CTPN ctpn;
		try {
			ctpn = (CTPN) query.list().get(0);
		} catch (Exception e) {
			return null;
		}
		return ctpn;
	}
	
	public int updateCTPN(CTPN ctpn) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		try {
			session.update(ctpn);
			t.commit();
			System.out.println("update CTPN thanh cong!!");
		} catch (Exception e) {
			t.rollback();
			System.out.println("update CTPN that bai!!" + e.getMessage());
			return 0;
		}
		finally {
			session.close();
		}
		return 1;
	}
	
	public PHIEUNHAP getExistPN() {
		Session session = factory.getCurrentSession();
		String hql = "From PHIEUNHAP where MANV=:tmp";
		Query query = session.createQuery(hql);
		query.setParameter("tmp", "TMP");
		
		PHIEUNHAP pn;
		try {
			pn = (PHIEUNHAP) query.list().get(0);
		} catch (Exception e) {
			return null;
		}
		return pn;
	}
}

