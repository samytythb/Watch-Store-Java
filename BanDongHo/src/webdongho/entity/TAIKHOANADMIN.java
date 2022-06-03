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
@Table (name = "TAIKHOANADMIN")
public class TAIKHOANADMIN {
	
	@Id
	@Column(name = "TENTK")
	private String TENTK;
	
	@Column(name = "MATKHAU" )
	private String MATKHAU;
	
	@OneToOne
	@JoinColumn(name = "MANV")
	private NHANVIEN MANV_TK;

	public String getTENTK() {
		return TENTK;
	}

	public void setTENTK(String tENTK) {
		TENTK = tENTK;
	}

	public String getMATKHAU() {
		return MATKHAU;
	}

	public void setMATKHAU(String mATKHAU) {
		MATKHAU = mATKHAU;
	}

	public NHANVIEN getMANV_TK() {
		return MANV_TK;
	}

	public void setMANV_TK(NHANVIEN mANV_TK) {
		MANV_TK = mANV_TK;
	}
	
	
}
