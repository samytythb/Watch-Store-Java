package webdongho.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table (name = "CTPD")
public class CTPD {

	@EmbeddedId
	private Pk id;

//	@Id
//	@ManyToOne
//	@JoinColumn(name = "MAPD")
//	private PHIEUDAT PD_CTPD;
//	
//	@Id
//	@ManyToOne
//	@JoinColumn(name = "MADH")
//	private DONGHO DH_CTPD;
	
//	
	@Column(name="SOLUONG")
	private int SOLUONG;
	
	
	@Column(name="DONGIA")
	private int DONGIA;


	public int getDONGIA() {
		return DONGIA;
	}

	public void setDONGIA(int dONGIA) {
		DONGIA = dONGIA;
	}

	public int getSOLUONG() {
		return SOLUONG;
	}

	public void setSOLUONG(int sOLUONG) {
		SOLUONG = sOLUONG;
	}
	
	@Embeddable
	  public static class Pk implements Serializable {
	    private static final long serialVersionUID = 1L;
	    
	    @Column(name = "MAPD")
		Integer MaPD;
		
		@Column(name = "MaDH")
		String MaDH;

		public Integer getMaPD() {
			return MaPD;
		}

		public void setMaPD(Integer maPD) {
			MaPD = maPD;
		}

		public String getMaDH() {
			return MaDH;
		}

		public void setMaDH(String maDH) {
			MaDH = maDH;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		@Override
		public String toString() {
			return "Pk [MaPD=" + MaPD + ", MaDH=" + MaDH + "]";
		}
	  }
	
	public Integer getSoLuong() {
		return SOLUONG;
	}

	public void setSoLuong(Integer soLuong) {
		SOLUONG = soLuong;
	}

	public Pk getId() {
		return id;
	}

	public void setId(Pk id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ChiTietPhieuDat [id=" + id + ", SoLuong=" + SOLUONG + "]";
	}

//	public PHIEUDAT getPD_CTPD() {
//		return PD_CTPD;
//	}
//
//	public void setPD_CTPD(PHIEUDAT pD_CTPD) {
//		PD_CTPD = pD_CTPD;
//	}
//
//	public DONGHO getDH_CTPD() {
//		return DH_CTPD;
//	}
//
//	public void setDH_CTPD(DONGHO dH_CTPD) {
//		DH_CTPD = dH_CTPD;
//	}
//
//	public Double getDONGIA() {
//		return DONGIA;
//	}


//	public void setDONGIA(Double dONGIA) {
//		DONGIA = dONGIA;
//	}
	
}

