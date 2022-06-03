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
@Table (name = "TAIKHOAN")
public class TAIKHOAN {

	@Id
	@Column(name = "TENTK")
	private String TENTK;
	
	@Column(name = "MATKHAU" )
	private String MATKHAU;
	
	@OneToOne
	@JoinColumn(name = "MAKH")
	private KHACHHANG MAKH_TK;

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

	public KHACHHANG getMAKH_TK() {
		return MAKH_TK;
	}

	public void setMAKH_TK(KHACHHANG mAKH_TK) {
		MAKH_TK = mAKH_TK;
	}
	

}
