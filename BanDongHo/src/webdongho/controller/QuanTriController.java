package webdongho.controller;


import java.util.*;
import java.util.concurrent.TimeUnit;
import java.io.File;
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
import webdongho.model.newaccount;

@Transactional
@Controller
@RequestMapping("/quantri/")
public class QuanTriController {
	@Autowired
	SessionFactory factory;
	

	
	@RequestMapping("index")
	public String index(HttpServletRequest request,ModelMap model) {
		this.loadDataDongHo(request,model);
		int doanhthu = this.getDoanhThu();
		int loinhuan = this.getLoiNhuan();
		int sodondat = this.getPhieuDats(1).size();
		
		List<DONGHO> donghostonkho = this.getDSDongHoTonKho();
		List<DONGHO> donghosbanchay= this.getDSDongHoBanChay();
		
		model.addAttribute("doanhthu", doanhthu);
		model.addAttribute("loinhuan", loinhuan);
		model.addAttribute("sodondat", sodondat);
		model.addAttribute("donghostonkho", donghostonkho);
		model.addAttribute("donghosbanchay", donghosbanchay);
		return "quantri/index";
	}
	
	@RequestMapping("duyetdon")
	public String duyetdon(ModelMap model) {
		List<PHIEUDAT__> list = this.getPhieuDats(0);
		model.addAttribute("phieudats", list);
		model.addAttribute("enableSearch","0");
		return "quantri/dondathang";
	}
	@RequestMapping("hoadon")
	public String hoadon(ModelMap model) {
		List<PHIEUDAT__> list = this.getPhieuDats(1);
		
		model.addAttribute("phieudats", list);
		model.addAttribute("enableSearch","0");
		return "quantri/dondathang";
	}
	@RequestMapping("hoadon/{MAPD}.htm")
	public String inHoaDon(ModelMap model, @PathVariable("MAPD") String mapd) {
		PHIEUDAT__ pd = this.getPhieuDat(mapd);
		System.out.println(pd.getMAKH_PD().getEMAIL());
		//List<PHIEUDAT> list = this.getPhieuDats(1);
		model.addAttribute("phieudat", pd);
		return "quantri/hoadon";
	}
	
	@RequestMapping( value = "/addNV", method = RequestMethod.GET)
	public String addNV(ModelMap model) {
		model.addAttribute("newaccount", new newaccount());
		return "quantri/addNV";
	}
	@RequestMapping(value = "/addNV", method = RequestMethod.POST)
	public String addNVp(@ModelAttribute("newaccount") newaccount newaccount, ModelMap model, BindingResult errors)
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
			return "quantri/addNV";
		}
		if (getKhachHangByEmail(newaccount.getEmail()).isEmpty() == false) {
			model.addAttribute("message", "Email already exists");
			return "quantri/addNV";
		}
		if (getNhanVienByEmail(newaccount.getEmail()).isEmpty() == false) {
			model.addAttribute("message", "Email already exists");
			return "quantri/addNV";
		}
		if (getAccount(newaccount.getUsername()).isEmpty() == false) {
			model.addAttribute("message", "Username already exists");
			return "quantri/addNV";
		}
		if (getAccountAd(newaccount.getUsername()).isEmpty() == false) {
			model.addAttribute("message", "Username already exists");
			return "quantri/addNV";
		}
		
		NHANVIEN newNhanVien = new NHANVIEN();
		newNhanVien.setEMAIL(newaccount.getEmail());
		newNhanVien.setMANV(newaccount.getUsername());
		newNhanVien.setHOTEN(newaccount.getUsername());
		newNhanVien.setGIOITINH(true);

		
		TAIKHOANADMIN newaccountKH = new TAIKHOANADMIN();
		newaccountKH.setTENTK(newaccount.getUsername());
		newaccountKH.setMATKHAU(newaccount.getPassword());
		newaccountKH.setMANV_TK(newNhanVien);
		try
		{
			this.addNV(newNhanVien);
			this.addAccountAd(newaccountKH);
			model.addAttribute("message", "Success");
		}
		catch(Exception e)
		{
			model.addAttribute("message", "Fail");
		}
		

		return "quantri/addNV";
	}
	public int addNV(NHANVIEN nv) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(nv);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}
	public int addAccountAd(TAIKHOANADMIN tk) {
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
	public List<TAIKHOAN> getAccount(String name) {
		Session session = factory.getCurrentSession();
		String hql = "FROM TAIKHOAN WHERE TENTK = :name";
		Query query = session.createQuery(hql);
		query.setParameter("name", name);
		List<TAIKHOAN> user = query.list();
		return user;
	}
	public List<TAIKHOANADMIN> getAccountAd(String name) {
		Session session = factory.getCurrentSession();
		String hql = "FROM TAIKHOANADMIN WHERE TENTK = :name";
		Query query = session.createQuery(hql);
		query.setParameter("name", name);
		List<TAIKHOANADMIN> user = query.list();
		return user;
	}

	
	@RequestMapping("dondathang")
	public String dondathang(ModelMap model, HttpServletRequest request) {
		String searchInput = request.getParameter("searchInput");
		String filter = request.getParameter("filter");
	//	System.out.println(searchInput + " = searchInput");
		
		if(searchInput == null || filter == null || (searchInput.isEmpty() && filter.isEmpty())) {
			List<PHIEUDAT__> list = this.getPhieuDats();
			model.addAttribute("phieudats", list);
		}
		else {
			
			List<PHIEUDAT__> list = this.getPhieuDatsHavingCons(searchInput,filter);
			model.addAttribute("phieudats", list);
			
		}
		model.addAttribute("enableSearch","1");
		request.setAttribute("filter", filter);
		request.setAttribute("searchInput", searchInput);
		return "quantri/dondathang";
	}
	
	
	
	@RequestMapping( value="dondathang/{MAPD}.htm", params = "btnDuyet" )
	private String submitDonDat(ModelMap model, @PathVariable("MAPD") String MAPD ) {
		
		PHIEUDAT__ dondathang = this.getPhieuDat(MAPD);
		dondathang.setTRANGTHAI(1);
		
		System.out.println(dondathang.getTRANGTHAI() + "id don dat" + dondathang.getMAPD() + "sdsds" );
		Integer kt = this.updatePhieuDat(dondathang);
		if(kt == 1) {
			System.out.println("duyet thanh cong");
		}
		else {
			System.out.println("duyet that bai");
		}

		return duyetdon(model);
	}
	@RequestMapping( value="dondathang/{MAPD}.htm", params = "btnHuy" )
	private String huyDonDat(HttpServletRequest request ,ModelMap model, @PathVariable("MAPD") String MAPD ) {
		PHIEUDAT__ dondathang = this.getPhieuDat(MAPD);
		dondathang.setTRANGTHAI(-1);
		
		System.out.println(dondathang.getTRANGTHAI() + "id don dat" + dondathang.getMAPD());
		Integer kt = this.updatePhieuDat(dondathang);
		if(kt == 1) {
			System.out.println("duyet thanh cong");
		}
		else {
			System.out.println("duyet that bai");
		}

		return dondathang(model,request);
	}
	
	
	
	
	public int getDoanhThu() {
		int doanhthu = 0;
		
		List<PHIEUDAT__> phieudats = this.getPhieuDats(1);
		for (PHIEUDAT__ phieudat : phieudats) {
			for (CTPD__ CTPD__ : phieudat.getCTPDS_PD()) {
				doanhthu +=CTPD__.getDONGIA()*CTPD__.getSOLUONG();
			}
		}
		
		return doanhthu;
	}
	public int getLoiNhuan() {
		int loinhuan = 0;
		int doanhthu = this.getDoanhThu();
		int tongVon = 0;
		List<PHIEUNHAP> phieunhaps = this.getPhieuNhaps();
		for (PHIEUNHAP phieunhap : phieunhaps) {
			for (CTPN ctpn : phieunhap.getCTPNS_PN()) {
				tongVon += ctpn.getSOLUONG()*ctpn.getDONGIA();
			}
		}
		
		loinhuan = doanhthu - tongVon;
		
		return loinhuan;
	}

	public List<PHIEUNHAP> getPhieuNhaps() {
		Session session = factory.getCurrentSession();
		String hql = "From PHIEUNHAP";
		Query query = session.createQuery(hql);
		
		List<PHIEUNHAP> list = query.list();
		
		return list;
	}
	
	//  ===============DONGHO======================================================
	@RequestMapping(value = "insertDH",method = RequestMethod.GET)
	public String insertDH(HttpServletRequest request,ModelMap model) {
		this.loadDataDongHo(request,model);
		model.addAttribute("dongho",new DONGHO());
		//model.addAttribute("submit", "Thêm");
		HttpSession session = request.getSession();
		session.setAttribute("submit", "Thêm");
		return "quantri/insertDH";
	}
	
	
	
    @RequestMapping(value = "insertDH",method = RequestMethod.POST)
    public String insertDH(HttpServletRequest request,ModelMap model,@ModelAttribute("dongho") DONGHO dongho, 
    		@RequestParam("HINHANHF") MultipartFile hinhAnh, BindingResult errors) {
    	
    	
    	if(dongho.getMADH().isEmpty()) {
    		errors.rejectValue("MADH", "dongho", "Mã đồng hồ không được trống!!");
    		this.loadDataDongHo(request,model);
    		return "quantri/insertDH";
    	}
    	if(this.getDongHo(dongho.getMADH()) != null) {
    		HttpSession session = request.getSession();
    		String tmp = (String) session.getAttribute("submit");
    		if(tmp.equals("Thêm")) {
    			errors.rejectValue("MADH", "dongho", "Mã đồng hồ đã có!!");
        		this.loadDataDongHo(request,model);
        		return "quantri/insertDH";
    		}
    	}
    	if(dongho.getTENDH().isEmpty()) {
    		errors.rejectValue("TENDH", "dongho", "Tên đồng hồ không được trống!!");
    		this.loadDataDongHo(request,model);
    		return "quantri/insertDH";
    	}
    	if(dongho.getGIA() < 0) {
    		errors.rejectValue("GIA", "dongho", "Giá không hợp lệ!!");
    		this.loadDataDongHo(request,model);
    		return "quantri/insertDH";
    	}
    	if(dongho.getSOLUONGTON() < 0) {
    		errors.rejectValue("SOLUONGTON", "dongho", "Số lượng không hợp lệ!!");
    		this.loadDataDongHo(request,model);
    		return "quantri/insertDH";
    	}
    	if(dongho.getLOAIDH().isEmpty()) {
    		errors.rejectValue("LOAIDH", "dongho", "Loại không được trống!!");
    		this.loadDataDongHo(request,model);
    		return "quantri/insertDH";
    	}
    	if(!hinhAnh.getContentType().substring(0, 5).equals("image") ) {
			System.out.println(" ko hop le!!" + hinhAnh.getContentType().substring(0, 5));
			errors.rejectValue("HINHANH", "dongho", "Hình ảnh không hợp lệ!!");
			this.loadDataDongHo(request,model);
    		return "quantri/insertDH";
		}
    	int kt = this.addEditDongHo(dongho, hinhAnh);
    //	HttpSession ss = request.getSession();
    	if(kt == 1) {
    		System.out.println("acction thanh cong!");
    		model.addAttribute("message", "Thành công!!");
    	}
    	if(kt == 0) {
    		model.addAttribute("message","Thất bại!!");
    		//ss.setAttribute("message", "Thất bại!!");
    	}
    //	return "redirect: /BanDongHo/quantri/insertDH.htm";
    	this.loadDataDongHo(request,model);
		model.addAttribute("dongho",new DONGHO());
		//model.addAttribute("submit", "Thêm");
		HttpSession session = request.getSession();
		session.setAttribute("submit", "Thêm");
    	return "quantri/insertDH";
    }
	@Autowired
	ServletContext context;
    public int addEditDongHo(DONGHO dongho, MultipartFile hinhAnh) {
    	//this.loadData(request,model);
    	boolean kt = this.getDongHo(dongho.getMADH()) == null;
    	//System.out.println(hinhAnh.getOriginalFilename() + "  -----truoc ");
    	if(kt || (!kt && hinhAnh != null && hinhAnh.getOriginalFilename() != "")) {
    		try {
    			//Kiem tra file 
    			if(hinhAnh.getContentType().substring(0, 4) == "image" ) {
    				System.out.println("hop le!!");
    			}
    		
    			
//    			String hinhAnhPath ="D:/ThanNgocQuynh/TNQ_III_HKII/LAP-TRINH-WEB/WEB_JAVA_ECLIPSE/BanDongHo/WebContent/imgweb/dongho/" + hinhAnh.getOriginalFilename() ;//context.getRealPath("/imgweb/dongho/" + hinhAnh.getOriginalFilename());
//    			hinhAnh.transferTo(new File(hinhAnhPath));
    			
    			String hinhAnhPath =context.getRealPath("/imgweb/dongho/" + hinhAnh.getOriginalFilename());
    			hinhAnh.transferTo(new File(hinhAnhPath));
    			
    			dongho.setHINHANH(hinhAnh.getOriginalFilename());
    			System.out.println(hinhAnhPath);
    		} catch (Exception e) {
    			System.out.println("loi hinh anh " + e.getMessage() );
    			return 0;
    		}
    	}
    	if((!kt && hinhAnh == null) || (!kt && hinhAnh.getOriginalFilename() == "")) {
    		DONGHO dh = getDongHo(dongho.getMADH());
    		dongho.setHINHANH(dh.getHINHANH());
    		System.out.println(dongho.getHINHANH() + "sau");
    	}
    	
    	
    	Session session = factory.openSession();
    	Transaction t = session.beginTransaction();
    	try {
    		if(kt)
    			session.save(dongho);
    		else
    			session.update(dongho);
			t.commit();
			if(kt)
				System.out.println("them thanh cong");
			
			if(!kt)
				System.out.println("EDIT thanh cong");
			
			
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			if(kt)
				System.out.println("them that bai " + e.getMessage());
			
			if(!kt)
				System.out.println("EDIT that bai " + e.getMessage());
//			model.addAttribute("message","them moi that bai");
			System.out.println("them that bai" + e.getMessage());
			return 0;
		}finally {
			session.close();
		}

    	return 1;

    }

    
    @RequestMapping(value = "editDH/{MADH}.htm",params = "linkEdit" )
    private String editDH(HttpServletRequest request, ModelMap model, @ModelAttribute("dongho") DONGHO dongho,
    				@PathVariable("MADH") String MADH) {
    	this.loadDataDongHo(request, model);
    	DONGHO dh = this.getDongHo(MADH);
    	
    	if(!dh.getCTPNS().isEmpty() || !dh.getCTPDS().isEmpty()) {
    		model.addAttribute("canEdit", "cannotedit");
    	}
    	model.addAttribute("dongho",dh);
    	HttpSession session = request.getSession();
    	session.setAttribute("submit", "Lưu");
    	return "quantri/insertDH";
    }
    
    @RequestMapping(value = "deleteDH/{MADH}.htm")
    public String deleteDH(HttpServletRequest request,ModelMap model,@ModelAttribute("thisdongho") DONGHO dongho,
    				@PathVariable("MADH") String MADH) {
    	DONGHO dh = dongho;//this.getDongHo(MADH);
    	
    	if(dh.getCTPDS() != null || dh.getCTPNS() != null) {
    		model.addAttribute("message","Không thể xóa đồng hồ này!!");
    		return insertDH(request,model);
    	}
    	
    	int kt = this.deleteDongHo(dh);
    	if( kt == 1) {
    		model.addAttribute("message", "Xóa đồng hồ Thành công!!");
    	}
    	if( kt == 0) {
    		model.addAttribute("message", "Xóa đồng hồ thất bại!!");
    	}
    	
    	return insertDH(request,model);
    }
    // 	==================DONGHO======================
	public List<DONGHO> getDongHos(){
		Session session=factory.getCurrentSession();
    	String hql="From DONGHO";
    	Query query =session.createQuery(hql);
    	List<DONGHO> list= query.list();
    	
    	return list;
	}
	public DONGHO getDongHo( String maDH){
		Session session=factory.getCurrentSession();
		String hql="From DONGHO where MADH =:maDH";
		
		Query query =session.createQuery(hql);
		query.setParameter("maDH", maDH);
		DONGHO dongho ;
		try {
			dongho = (DONGHO) query.list().get(0);
		} catch (Exception e) {
			return null;
		}
		
		return dongho;
	}
	public int deleteDongHo(DONGHO dh) {
		Session session=factory.openSession();
    	Transaction t=session.beginTransaction();
    	try {
			session.delete(dh);
			t.commit();
			System.out.println("xoa thanh cong");
			
		} catch (Exception e) {
			t.rollback();
			System.out.println("xoa that bai -> " + e.getMessage());
			return 0;
			// TODO: handle exception
		}finally {
			session.close();
		}
    	return 1;
	}
	
	
	public void loadDataDongHo(HttpServletRequest request, ModelMap model) {
	
		PagedListHolder pagedListHolder = new PagedListHolder(this.getDongHos());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder",pagedListHolder);
	}
	

	
	public List<CTPD__> getChiTietPD() {
		Session session=factory.getCurrentSession();
		String hql="From CTPD__";
		Query query =session.createQuery(hql);
		List<CTPD__> list= query.list();
		//System.out.println("load list thanh cong");
		return list;
	}
	//== PHIEU DAT===========================================================//
	public List<PHIEUDAT__> getPhieuDats() {
		Session session=factory.getCurrentSession();
		String hql="From PHIEUDAT__";
		Query query =session.createQuery(hql);
		List<PHIEUDAT__> list= query.list();
		return list;
	}
	public List<PHIEUDAT__> getPhieuDatsHavingCons(String SearchInput, String filter){
		
		if(SearchInput.isEmpty() && filter.equals("all")){ 
			return getPhieuDats();
		}
		String sort = "";
		boolean isCons = false;
		if(filter.equals("time")) {
			sort = "NGAYDAT";
		}
		if(filter.equals("id")) {
			sort = "MAPD";
		}
		if(filter.equals("daduyet")) {
			isCons = true;
			sort = "1";
		}
		if(filter.equals("dahuy")) {
			isCons = true;
			sort = "-1";
		}
		if(filter.equals("dangcho")) {
			isCons = true;
			sort = "0";
		}
	//	System.out.println("input =   " + SearchInput + "  " + sort + " ---" + filter );
		
		if(isCons) {
			Session session=factory.getCurrentSession();
			String hql="select PD From PHIEUDAT__ PD where TRANGTHAI =:sort and( HOTENNGUOINHAN like :searchInput or MAPD like :searchInput ) ";
			Query query =session.createQuery(hql);
			query.setParameter("searchInput", "%" + SearchInput + "%");
			query.setParameter("sort",Integer.parseInt(sort));
	//		System.out.println("query = " + hql);
			List<PHIEUDAT__> list= query.list();
			return list;
		}
		else {
			Session session=factory.getCurrentSession();
			String kt = " ORDER BY PD." + sort;
			String hql="Select PD From PHIEUDAT__ PD where HOTENNGUOINHAN like :searchInput or MAPD like :searchInput";
			if(!filter.equals("all"))
				hql = hql + kt;
	//		System.out.println("query = " + hql);
			Query query =session.createQuery(hql);
			query.setParameter("searchInput", "%" + SearchInput + "%");

	//		System.out.println("query = " + hql);
			List<PHIEUDAT__> list= query.list();
			return list;
		}
	}
	
	public List<PHIEUDAT__> getPhieuDats(int kt) {
		Session session=factory.getCurrentSession();
		String hql="From PHIEUDAT__ where TRANGTHAI =:kt";
		Query query =session.createQuery(hql);
		query.setParameter("kt", kt);
		List<PHIEUDAT__> list= query.list();
		return list;
	}
	
	public PHIEUDAT__ getPhieuDat(String mapd) {
		Session session=factory.getCurrentSession();
		System.out.println(mapd);
		String hql="From PHIEUDAT__ where MAPD =:mapd";
		Query query = session.createQuery(hql);
		query.setParameter("mapd", mapd);
		PHIEUDAT__ dondathang = (PHIEUDAT__) query.list().get(0);
//		System.out.println(dondathang.getGHICHU() + "in side get don dat hang");
		return dondathang;
	}
	
	public Integer updatePhieuDat(PHIEUDAT__ dondathang) {
		Session	session=factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(dondathang);
			t.commit();
		}catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			return 0;
		}
		finally {
			session.close();
		}
		return 1;
	}
	
	public List<DONGHO> getDSDongHoTonKho(){
		Session session=factory.getCurrentSession();
		String hql="From DONGHO dh order by dh.SOLUONGTON DESC" ;
		Query query =session.createQuery(hql);
		List<DONGHO> list= query.list();
		return list;
	}
	public List<DONGHO> getDSDongHoBanChay(){

		List<DONGHO> dhBanChays = this.getDongHos();
	
		dhBanChays.removeIf(t -> t.getCTPDS().size() == 0);
		return dhBanChays;
	}
	public List<KHACHHANG> getKhachHangByEmail(String makh) {
		Session session = factory.getCurrentSession();
		String hql = "FROM KHACHHANG WHERE Email = :makh";
		Query query = session.createQuery(hql);
		query.setParameter("makh", makh);
		List<KHACHHANG> kh = query.list();
		return kh;
	}
	public List<NHANVIEN> getNhanVienByEmail(String makh) {
		Session session = factory.getCurrentSession();
		String hql = "FROM NHANVIEN WHERE EMAIL = :makh";
		Query query = session.createQuery(hql);
		query.setParameter("makh", makh);
		List<NHANVIEN> kh = query.list();
		return kh;
	}
}
