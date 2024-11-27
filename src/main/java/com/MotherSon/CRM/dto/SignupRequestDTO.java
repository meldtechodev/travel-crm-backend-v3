package com.MotherSon.CRM.dto;

public class SignupRequestDTO {
	 private String name;
	    private String email;
	    private String password;
	    private String createdBy;
	    private String modifiedBy;
	    private String ipaddress;

	    private boolean status;
	    private boolean isdelete;
	    
	    
	    private Long companyId;
	       private Long departmentId;
	       private Long roleId;
	       private Long designationId;
	    
		
		
		

		public SignupRequestDTO(String name, String email, String password, String createdBy, String modifiedBy,
				String ipaddress, boolean status, boolean isdelete, Long companyId, Long departmentId, Long roleId,
				Long designationId) {
			super();
			this.name = name;
			this.email = email;
			this.password = password;
			this.createdBy = createdBy;
			this.modifiedBy = modifiedBy;
			this.ipaddress = ipaddress;
			this.status = status;
			this.isdelete = isdelete;
			this.companyId = companyId;
			this.departmentId = departmentId;
			this.roleId = roleId;
			this.designationId = designationId;
		}
		
		
		

		public SignupRequestDTO() {
			super();
			// TODO Auto-generated constructor stub
		}




		public String getCreatedBy() {
			return createdBy;
		}

		public void setCreatedBy(String createdBy) {
			this.createdBy = createdBy;
		}

		public String getModifiedBy() {
			return modifiedBy;
		}

		public void setModifiedBy(String modifiedBy) {
			this.modifiedBy = modifiedBy;
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

		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}

		public Long getCompanyId() {
			return companyId;
		}

		public void setCompanyId(Long companyId) {
			this.companyId = companyId;
		}

		public Long getDepartmentId() {
			return departmentId;
		}

		public void setDepartmentId(Long departmentId) {
			this.departmentId = departmentId;
		}

		public Long getRoleId() {
			return roleId;
		}

		public void setRoleId(Long roleId) {
			this.roleId = roleId;
		}

		public Long getDesignationId() {
			return designationId;
		}

		public void setDesignationId(Long designationId) {
			this.designationId = designationId;
		}
		
		
		
		
	    
}
