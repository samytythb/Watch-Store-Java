package webdongho.entity;


import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "PHIEUNHAP")
public class PHIEUNHAP {
	@Id 
	@Column(name = "MAPN")
	private String MAPN;
	
	@Column(name = "NGAYNHAP")
	private String NGAYNHAP;
	
	@ManyToOne
	@JoinColumn(name = "MANV")
	private NHANVIEN MANV_PN;

	@OneToMany(mappedBy = "PN_CTPN",fetch = FetchType.EAGER)
	private Collection<CTPN> CTPNS_PN;
	
	@Override
	public String toString() {
		return MAPN + "/" +"/" + NGAYNHAP + "/" ;
	}
	public PHIEUNHAP() {
		
	}
	public PHIEUNHAP(String mAPN) {
		this.MAPN = mAPN;
	}
	
	public Collection<CTPN> getCTPNS_PN() {
		return CTPNS_PN;
	}

	public void setCTPNS_PN(Collection<CTPN> cTPNS_PN) {
		CTPNS_PN = cTPNS_PN;
	}

	public String getMAPN() {
		return MAPN;
	}

	public void setMAPN(String mAPN) {
		MAPN = mAPN;
	}

	public String getNGAYNHAP() {
		

		return NGAYNHAP;
	}

	public void setNGAYNHAP(String nGAYNHAP) {
		NGAYNHAP = nGAYNHAP;
	}

	public NHANVIEN getMANV_PN() {
		return MANV_PN;
	}

	public void setMANV_PN(NHANVIEN mANV_PN) {
		MANV_PN = mANV_PN;
	}

	
	
}
