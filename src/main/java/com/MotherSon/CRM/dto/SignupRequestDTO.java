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
	    
		
		
		public SignupRequestDTO(String name, String email, String password, String createdBy, String modifiedBy,
				String ipaddress, boolean status, boolean isdelete) {
			super();
			this.name = name;
			this.email = email;
			this.password = password;
			this.createdBy = createdBy;
			this.modifiedBy = modifiedBy;
			this.ipaddress = ipaddress;
			this.status = status;
			this.isdelete = isdelete;
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
	    
}
