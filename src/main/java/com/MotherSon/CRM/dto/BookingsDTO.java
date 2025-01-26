package com.MotherSon.CRM.dto;
 
import java.time.LocalDateTime;
 
public class BookingsDTO {
	
	private Long id;
    private double discount_requested;
	private double finalPrice;
	private String paymentStatus;
	private boolean emailSent;
	
	private Long customerId;
	private String fName;
	private String lName;
	private String emailId;
	private String 	contactNo;
	
	private Long queryId;
	private String proposalId;
	private String requirementType;
	private LocalDateTime travelDate;
	
	private Long packId;
	private String pkName;
	private String pkCategory;
	
	private Long hotelId;
	private String hname;
	private String hcontactname;
	private String hcontactnumber;
	private String hcontactemail;
	private String haddress;
	public BookingsDTO(Long id, double discount_requested, double finalPrice, String paymentStatus, boolean emailSent,
			Long customerId, String fName, String lName, String emailId, String contactNo, Long queryId,
			String proposalId, String requirementType, LocalDateTime travelDate, Long packId, String pkName,
			String pkCategory, Long hotelId, String hname, String hcontactname, String hcontactnumber,
			String hcontactemail, String haddress) {
		super();
		this.id = id;
		this.discount_requested = discount_requested;
		this.finalPrice = finalPrice;
		this.paymentStatus = paymentStatus;
		this.emailSent = emailSent;
		this.customerId = customerId;
		this.fName = fName;
		this.lName = lName;
		this.emailId = emailId;
		this.contactNo = contactNo;
		this.queryId = queryId;
		this.proposalId = proposalId;
		this.requirementType = requirementType;
		this.travelDate = travelDate;
		this.packId = packId;
		this.pkName = pkName;
		this.pkCategory = pkCategory;
		this.hotelId = hotelId;
		this.hname = hname;
		this.hcontactname = hcontactname;
		this.hcontactnumber = hcontactnumber;
		this.hcontactemail = hcontactemail;
		this.haddress = haddress;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getDiscount_requested() {
		return discount_requested;
	}
	public void setDiscount_requested(double discount_requested) {
		this.discount_requested = discount_requested;
	}
	public double getFinalPrice() {
		return finalPrice;
	}
	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public boolean isEmailSent() {
		return emailSent;
	}
	public void setEmailSent(boolean emailSent) {
		this.emailSent = emailSent;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public Long getQueryId() {
		return queryId;
	}
	public void setQueryId(Long queryId) {
		this.queryId = queryId;
	}
	public String getProposalId() {
		return proposalId;
	}
	public void setProposalId(String proposalId) {
		this.proposalId = proposalId;
	}
	public String getRequirementType() {
		return requirementType;
	}
	public void setRequirementType(String requirementType) {
		this.requirementType = requirementType;
	}
	public LocalDateTime getTravelDate() {
		return travelDate;
	}
	public void setTravelDate(LocalDateTime travelDate) {
		this.travelDate = travelDate;
	}
	public Long getPackId() {
		return packId;
	}
	public void setPackId(Long packId) {
		this.packId = packId;
	}
	public String getPkName() {
		return pkName;
	}
	public void setPkName(String pkName) {
		this.pkName = pkName;
	}
	public String getPkCategory() {
		return pkCategory;
	}
	public void setPkCategory(String pkCategory) {
		this.pkCategory = pkCategory;
	}
	public Long getHotelId() {
		return hotelId;
	}
	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}
	public String getHname() {
		return hname;
	}
	public void setHname(String hname) {
		this.hname = hname;
	}
	public String getHcontactname() {
		return hcontactname;
	}
	public void setHcontactname(String hcontactname) {
		this.hcontactname = hcontactname;
	}
	public String getHcontactnumber() {
		return hcontactnumber;
	}
	public void setHcontactnumber(String hcontactnumber) {
		this.hcontactnumber = hcontactnumber;
	}
	public String getHcontactemail() {
		return hcontactemail;
	}
	public void setHcontactemail(String hcontactemail) {
		this.hcontactemail = hcontactemail;
	}
	public String getHaddress() {
		return haddress;
	}
	public void setHaddress(String haddress) {
		this.haddress = haddress;
	}
	
}