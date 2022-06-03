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

@Entity
@Table (name = "NHANVIEN")
public class NHANVIEN {

	@Id
	@Column(name = "MANV")
	private String MANV;
	
	@Column(name = "HOTEN", columnDefinition = "nvarchar(50)")
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
	
	@OneToMany(mappedBy = "MANV_PN", fetch = FetchType.EAGER)
	private Collection<PHIEUNHAP> PHIEUNHAPS;
	
//	@OneToMany(mappedBy = "MANV_PD", fetch = FetchType.EAGER)
//	private Collection<PHIEUDAT> PHIEUDATS;
	

	public String getMANV() {
		return MANV;
	}

	public void setMANV(String mANV) {
		MANV = mANV;
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

	public Collection<PHIEUNHAP> getPHIEUNHAPS() {
		return PHIEUNHAPS;
	}

	public void setPHIEUNHAPS(Collection<PHIEUNHAP> pHIEUNHAPS) {
		PHIEUNHAPS = pHIEUNHAPS;
	}

//	public Collection<PHIEUDAT> getPHIEUDATS() {
//		return PHIEUDATS;
//	}
//
//	public void setPHIEUDATS(Collection<PHIEUDAT> pHIEUDATS) {
//		PHIEUDATS = pHIEUDATS;
//	}

//	public TAIKHOANADMIN getTENTK_NV() {
//		return TENTK_NV;
//	}
//
//	public void setTENTK_NV(TAIKHOANADMIN tENTK_NV) {
//		TENTK_NV = tENTK_NV;
//	}

	
	
	
	
	
}
