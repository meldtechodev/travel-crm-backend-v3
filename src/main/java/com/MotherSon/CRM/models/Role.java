package com.MotherSon.CRM.models;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name="Roles_Master")
@JsonIgnoreProperties(value= {"user"})
public class Role {
       
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String roleName;
	private String ipaddress;
	
	private boolean status;
	
	private boolean isdelete;
	
	

	@Column(name="created_by")
	private String createdby;
	
	@Column(name="modified_by")
	private String modifiedby;
	
	@Column(name="created_date")
	private LocalDateTime createddate;
	
	@Column(name="modified_date")
	private LocalDateTime modifieddate;
	
	
	
	@OneToMany(mappedBy="role",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JsonManagedReference
	private List<User> user;
	
	
	 @PrePersist
	    protected void onCreate() {
			createddate = LocalDateTime.now();
			modifieddate = LocalDateTime.now();

		}

	    @PreUpdate
		protected void onUpdate() {
			modifieddate = LocalDateTime.now();
		}
	    
	    

	public Role() {
			super();
			// TODO Auto-generated constructor stub
		}

	public String getIpaddress() {
			return ipaddress;
		}

		public void setIpaddress(String ipaddress) {
			this.ipaddress = ipaddress;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	
	
}
