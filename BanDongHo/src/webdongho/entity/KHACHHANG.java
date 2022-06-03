package webdongho.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import webdongho.bean.User;



@Entity
@Table (name = "KHACHHANG")
public class KHACHHANG {
	
	@Id
	@Column(name = "MAKH")
	private String MAKH;
	
	@Column(name = "SOCMND" )
	private String SOCMND;
	
	@Column(name = "HOTEN",columnDefinition = "nvarchar(50)")
	private String HOTEN;
	
	@Column(name = "GIOITINH")
	private Boolean GIOITINH;
	
	@Column(name = "NGAYSINH")
	private Date NGAYSINH;
	
	@Column(name = "DIACHI", columnDefinition = "nvarchar(100)")
	private String DIACHI;
	
	@Column(name = "SDT")
	private String SDT;
	
	@Column(name = "EMAIL")
	private String EMAIL;
	
	@OneToMany(mappedBy = "MAKH_PD",fetch = FetchType.EAGER)
	private Collection<PHIEUDAT__> PHIEUDATS;
//	
//	@OneToOne
//	@JoinColumn(name = "TENTK")
//	private TAIKHOAN TENTK_KH;
	
//	public KHACHHANG(User user) {
//		MAKH = user.getMA();
//		HOTEN = user.getHOTEN();
//		GIOITINH = user.getGIOITINH();
//		EMAIL = user.getEMAIL();
//		NGAYSINH = nv.getNGAYSINH();
//	//	PHIEUDATS = nv.getPHIEUDATS();
//		SDT = nv.getSDT();
//		DIACHI = nv.getDIACHI();
//		
//	}

	public KHACHHANG() {
		super();
	}

	public String getMAKH() {
		return MAKH;
	}

	public void setMAKH(String mAKH) {
		MAKH = mAKH;
	}

	public String getSOCMND() {
		return SOCMND;
	}

	public void setSOCMND(String sOCMND) {
		SOCMND = sOCMND;
	}

	public String getHOTEN() {
		return HOTEN;
	}

	public void setHOTEN(String hOTEN) {
		HOTEN = hOTEN;
	}

	public Boolean getGIOITINH() {
		return GIOITINH;
	}

	public void setGIOITINH(Boolean gIOITINH) {
		GIOITINH = gIOITINH;
	}

	public Date getNGAYSINH() {
		return NGAYSINH;
	}

	public void setNGAYSINH(Date nGAYSINH) {
		NGAYSINH = nGAYSINH;
	}

	public String getDIACHI() {
		return DIACHI;
	}

	public void setDIACHI(String dIACHI) {
		DIACHI = dIACHI;
	}

	public String getSDT() {
		return SDT;
	}

	public void setSDT(String sDT) {
		SDT = sDT;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public Collection<PHIEUDAT__> getPHIEUDATS() {
		return PHIEUDATS;
	}

	public void setPHIEUDATS(Collection<PHIEUDAT__> pHIEUDATS) {
		PHIEUDATS = pHIEUDATS;
	}

//	public TAIKHOAN getTENTK_KH() {
//		return TENTK_KH;
//	}
//
//	public void setTENTK_KH(TAIKHOAN tENTK_KH) {
//		TENTK_KH = tENTK_KH;
//	}
	
	
}
