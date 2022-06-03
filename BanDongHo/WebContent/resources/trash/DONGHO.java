package webdongho.entity;

import java.io.Serializable;

//done

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table (name = "DONGHO")
public class DONGHO implements Serializable{
	@Id
	@Column (name="MADH")
	private String MADH;
	
	@Column (name="TENDH", columnDefinition = "nvarchar(100)")
	private String TENDH;
	
	@Column (name = "GIA")
	private int GIA;
	
	@Column (name = "SOLUONGTON")
	private int SOLUONGTON;
	
	@Column (name = "LOAIDH")
	private String LOAIDH;
	
	@Column (name = "MOTA", columnDefinition = "nvarchar(500)")
	private String MOTA ;
	
	@OneToMany(mappedBy = "DH_CTPN", fetch = FetchType.EAGER)
	private Collection<CTPN> CTPNS;
	
	@OneToMany(mappedBy = "DH_CTPD", fetch = FetchType.EAGER)
	private Collection<CTPD__> CTPDS;
	
	@Column (name = "HINHANH")
	private String HINHANH;
	
	public DONGHO() {
		super();
	}

	public DONGHO(DONGHO d) {
		MADH = d.getMADH();
		TENDH = d.getTENDH();
		GIA = d.getGIA();
		SOLUONGTON = d.getSOLUONGTON();
		LOAIDH = d.getLOAIDH();
		MOTA = d.getMOTA();
		CTPNS = d.getCTPNS();
//		CTPDs = d.getCTPDs();
		HINHANH = d.getHINHANH();
	}

	public DONGHO(String maDH) {
		this.MADH = maDH;
	}

	public String getMADH() {
		return MADH;
	}

	public Collection<CTPD__> getCTPDS() {
		return CTPDS;
	}

	public void setCTPDS(Collection<CTPD__> cTPDS) {
		CTPDS = cTPDS;
	}

	public void setMADH(String mADH) {
		MADH = mADH;
	}

	public String getTENDH() {
		return TENDH;
	}

	public void setTENDH(String tENDH) {
		TENDH = tENDH;
	}

	public int getGIA() {
		return GIA;
	}

	public void setGIA(int gIA) {
		GIA = gIA;
	}

	public int getSOLUONGTON() {
		return SOLUONGTON;
	}

	public void setSOLUONGTON(int sOLUONGTON) {
		SOLUONGTON = sOLUONGTON;
	}

	public String getLOAIDH() {
		return LOAIDH;
	}

	public void setLOAIDH(String lOAIDH) {
		LOAIDH = lOAIDH;
	}

	public String getMOTA() {
		return MOTA;
	}

	public void setMOTA(String mOTA) {
		MOTA = mOTA;
	}

	public String getHINHANH() {
		return HINHANH;
	}

	public void setHINHANH(String hINHANH) {
		HINHANH = hINHANH;
	}

	public Collection<CTPN> getCTPNS() {
		return CTPNS;
	}

	public void setCTPNS(Collection<CTPN> cTPNS) {
		CTPNS = cTPNS;
	}
	
	@Override
	public String toString() {
		return MADH.trim() + ":" + TENDH + ":" + GIA + ":" + SOLUONGTON + ":"
				+ LOAIDH.trim() + ":" + MOTA + ":" + HINHANH.trim();
	}

//	public Collection<CTPD> getCTPDs() {
//		return CTPDs;
//	}
//
//	public void setCTPDs(Collection<CTPD> cTPDs) {
//		CTPDs = cTPDs;
//	}
}
