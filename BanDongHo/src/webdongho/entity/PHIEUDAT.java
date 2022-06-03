package webdongho.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "PHIEUDAT")
public class PHIEUDAT {
	@Id 
	@GeneratedValue
	@Column(name = "MAPD")
	private Integer MAPD;
	
	@Column(name = "NGAYDAT")
	private Date NGAYDAT;
	
	@Column(name = "HOTENNGUOINHAN", columnDefinition = "nvarchar(50)")
	private String HOTENNGUOINHAN;
	
	@Column(name = "DIACHI", columnDefinition = "nvarchar(100)")
	private String DIACHI;
	
	@Column(name = "SDT")
	private String SDT;
	
	@Column(name = "NGAYGIAO")
	private Date NGAYGiAO;
	
	@Column(name = "TRANGTHAI")
	private int TRANGTHAI;
	
//	@ManyToOne
//	@JoinColumn(name = "MANV")
//	private NHANVIEN MANV_PD;
//
	@ManyToOne
	@JoinColumn(name = "MAKH")
	private KHACHHANG MAKH_PD;
	

//	@OneToMany(mappedBy = "PD_CTPD",fetch = FetchType.EAGER)
//	private Collection<CTPD> CTPDS_PD;
	

	public Integer getMAPD() {
		return MAPD;
	}

	public void setMAPD(Integer mAPD) {
		MAPD = mAPD;
	}

	public Date getNGAYDAT() {
		return NGAYDAT;
	}

	public void setNGAYDAT(Date nGAYDAT) {
		NGAYDAT = nGAYDAT;
	}

	public String getHOTENNGUOINHAN() {
		return HOTENNGUOINHAN;
	}

	public void setHOTENNGUOINHAN(String hOTENNGUOINHAN) {
		HOTENNGUOINHAN = hOTENNGUOINHAN;
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

	public Date getNGAYGiAO() {
		return NGAYGiAO;
	}

	public void setNGAYGiAO(Date nGAYGiAO) {
		NGAYGiAO = nGAYGiAO;
	}

	public int getTRANGTHAI() {
		return TRANGTHAI;
	}

	public void setTRANGTHAI(int tRANGTHAI) {
		TRANGTHAI = tRANGTHAI;
	}

	

	public KHACHHANG getMAKH_PD() {
		return MAKH_PD;
	}

	public void setMAKH_PD(KHACHHANG mAKH_PD) {
		MAKH_PD = mAKH_PD;
	}

//	public Collection<CTPD> getCTPDS_PD() {
//		return CTPDS_PD;
//	}
//
//	public void setCTPDS_PD(Collection<CTPD> cTPDS_PD) {
//		CTPDS_PD = cTPDS_PD;
//	}

	
//	public NHANVIEN getMANV_PD() {
//		return MANV_PD;
//	}
//
//	public void setMANV_PD(NHANVIEN mANV_PD) {
//		MANV_PD = mANV_PD;
//	}

	
	

}
