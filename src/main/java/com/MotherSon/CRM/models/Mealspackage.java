package com.MotherSon.CRM.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "Mealspackage_Master")
public class Mealspackage {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "mealstype_code")
	private String mealstypeCode;
	
	private String mealstype_name;
	
	private boolean status;
	
	private boolean isdelete;
	
	private String ipaddress;
	
	private String createdby;
	
	private String modifiedby;
	
	private LocalDateTime createddate;
	
	private LocalDateTime modifieddate;
	
	
	
//	@ManyToMany(mappedBy = "mealPackages")
//    private Set<PackageitineraryDetails> packageitineraryDetails = new HashSet<>();
	
	
	
	@PrePersist
	protected void onCreate() {
		createddate = LocalDateTime.now();
		modifieddate = LocalDateTime.now();
		
	}
		
		@PreUpdate
		protected void onUpdate() {
			modifieddate = LocalDateTime.now();
			
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

//		public String getMealstype_code() {
//			return mealstype_code;
//		}
//
//		public void setMealstype_code(String mealstype_code) {
//			this.mealstype_code = mealstype_code;
//		}

		public String getMealstype_name() {
			return mealstype_name;
		}

		public void setMealstype_name(String mealstype_name) {
			this.mealstype_name = mealstype_name;
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

		public String getIpaddress() {
			return ipaddress;
		}

		public void setIpaddress(String ipaddress) {
			this.ipaddress = ipaddress;
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

		public String getMealstypeCode() {
			return mealstypeCode;
		}

		public void setMealstypeCode(String mealstypeCode) {
			this.mealstypeCode = mealstypeCode;
		}


		

}