package com.MotherSon.CRM.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity 
@Table(name="packageprice")
public class PackagePrice {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
      private Long id;
	 private double markup;
	 private  double basiccost;
	 private double gst;
	 private double totalcost;
	 private String createdby;

		private String modifiedby;
		
		private String ipaddress;

		private LocalDateTime createddate;

		private LocalDateTime modifieddate;

		private boolean status;

		private boolean isdelete;
		
		@Column(name="pkid")
		private Long packid;
public Long getPackid() {
			return packid;
		}

		public void setPackid(Long packid) {
			this.packid = packid;
		}

		//		@OneToOne
//		@JoinColumn(name="pkId")
//		private Pkg pkid;
		@PrePersist
		protected void oncreate() {
			createddate = LocalDateTime.now();
			modifieddate = LocalDateTime.now();
		}

		@PreUpdate
		protected void onupdate() {
			modifieddate = LocalDateTime.now();

		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public double getMarkup() {
			return markup;
		}

		public void setMarkup(double markup) {
			this.markup = markup;
		}

		public double getBasiccost() {
			return basiccost;
		}

		public void setBasiccost(double basiccost) {
			this.basiccost = basiccost;
		}

		public double getGst() {
			return gst;
		}

		public void setGst(double gst) {
			this.gst = gst;
		}

		public double getTotalcost() {
			return totalcost;
		}

		public void setTotalcost(double totalcost) {
			this.totalcost = totalcost;
		}

		public String getCreatedby() {
			return createdby;
		}

		public void setCreatedby(String createdby) {
			this.createdby = createdby;
		}

		public String getModifiedby() {
			return modifiedby;
		}

		public void setModifiedby(String modifiedby) {
			this.modifiedby = modifiedby;
		}

		public String getIpaddress() {
			return ipaddress;
		}

		public void setIpaddress(String ipaddress) {
			this.ipaddress = ipaddress;
		}

		public LocalDateTime getCreateddate() {
			return createddate;
		}

		public void setCreateddate(LocalDateTime createddate) {
			this.createddate = createddate;
		}

		public LocalDateTime getModifieddate() {
			return modifieddate;
		}

		public void setModifieddate(LocalDateTime modifieddate) {
			this.modifieddate = modifieddate;
		}

		public boolean isStatus() {
			return status;
		}

		public void setStatus(boolean status) {
			this.status = status;
		}

		public boolean isIsdelete() {
			return isdelete;
		}

		public void setIsdelete(boolean isdelete) {
			this.isdelete = isdelete;
		}

	
		
		
		
		
}
