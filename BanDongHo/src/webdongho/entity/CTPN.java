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
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Entity
@Table (name = "CTPN")
public class CTPN implements Serializable {
	

	@Id
	@ManyToOne
	@JoinColumn(name = "MAPN")
	private PHIEUNHAP PN_CTPN;
		
	@Id
	@ManyToOne
	@JoinColumn(name = "MADH")
	private DONGHO DH_CTPN;
	
	
	@Column(name="SOLUONG")
//	@NotNull(message = "Số lượng không thể trống!")
//	@DecimalMin(value = "1", message = "Số lượng không hợp lệ!!")
	private int SOLUONG;
	
	
	@Column(name="DONGIA")
//	@NotNull(message = "Đơn giá không thể trống!")
//	@DecimalMin(value = "0", message = "Đơn giá không hợp lệ!!")
	private int DONGIA;


	public int getSOLUONG() {
		return SOLUONG;
	}


	public PHIEUNHAP getPN_CTPN() {
		return PN_CTPN;
	}


	public void setPN_CTPN(PHIEUNHAP pN_CTPN) {
		PN_CTPN = pN_CTPN;
	}


	public DONGHO getDH_CTPN() {
		return DH_CTPN;
	}


	public void setDH_CTPN(DONGHO dH_CTPN) {
		DH_CTPN = dH_CTPN;
	}


	public void setSOLUONG(int sOLUONG) {
		SOLUONG = sOLUONG;
	}


	public int getDONGIA() {
		return DONGIA;
	}


	public void setDONGIA(int dONGIA) {
		DONGIA = dONGIA;
	}

	@Override
	public String toString() {
		return "maDH = " + DH_CTPN.getMADH() + "; maPN = " + PN_CTPN.getMAPN() + "; soluong = " + SOLUONG+ "; dongia = " + DONGIA;
	}
}

	

