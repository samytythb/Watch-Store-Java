package webdongho.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "CTPD")
public class CTPD__ implements Serializable{


	@Id
	@ManyToOne
	@JoinColumn(name = "MAPD")
	private PHIEUDAT__ PD_CTPD;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "MADH")
	private DONGHO DH_CTPD;
	
//	
	@Column(name="SOLUONG")
	private int SOLUONG;
	
	
	@Column(name="DONGIA")
	private int DONGIA;


	public int getSOLUONG() {
		return SOLUONG;
	}

	public void setSOLUONG(int sOLUONG) {
		SOLUONG = sOLUONG;
	}

	public PHIEUDAT__ getPD_CTPD() {
		return PD_CTPD;
	}

	public void setPD_CTPD(PHIEUDAT__ pD_CTPD) {
		PD_CTPD = pD_CTPD;
	}

	public DONGHO getDH_CTPD() {
		return DH_CTPD;
	}

	public void setDH_CTPD(DONGHO dH_CTPD) {
		DH_CTPD = dH_CTPD;
	}

	public int getDONGIA() {
		return DONGIA;
	}


	public void setDONGIA(int dONGIA) {
		DONGIA = dONGIA;
	}
	
}

