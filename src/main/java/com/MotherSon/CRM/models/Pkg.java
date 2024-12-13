package com.MotherSon.CRM.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonValue;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
	@Table(name = "Package_master")
	public class Pkg {
			
			
			

			@Id
			@GeneratedValue(strategy = GenerationType.IDENTITY)
			private Long id;
			
			
			@NotBlank(message = "PkName is required")
			@Column(name = "pkName", nullable = false)
			private String pkName;
			
			 
			

			
			@Column(name= "fromCityId")
			private Long fromCityId;
			
			@Column(name="toCityId")
			private Long toCityId;
			
			@Column(name="SupplierId")

			private Long SupplierId;

		

//			@ManyToOne
//		    @JoinColumn(name = "from_city_id", referencedColumnName = "id")
//		    private Destination fromCity;
//			
//			 
//
//			@ManyToOne
//			    @JoinColumn(name = "to_city_id", referencedColumnName = "id")
//			    private Destination toCity;
//			  
			
			//@NotBlank(message = "cityCoverdid is required")
			
			
			public Long getSupplierId() {
				return SupplierId;
			}

			public void setSupplierId(Long supplierId) {
				SupplierId = supplierId;
			}

			public Long getFromCityId() {
				return fromCityId;
			}

			public void setFromCityId(Long fromCityId) {
				this.fromCityId = fromCityId;
			}

			public Long getToCityId() {
				return toCityId;
			}

			public void setToCityId(Long toCityId) {
				this.toCityId = toCityId;
			}

			@Column(name = "description", nullable = false)
			private String description;
			
			@NotBlank(message = "pkCategory is required")
			@Column(name = "Pk_Category", nullable = false)
			private String pkCategory;
			
			
			@Column(name = "pkSpecifications", nullable = false)
			private String pkSpecifications;
			
			
//			@NotBlank(message = "Image is required")
			@Column(name = "pk_image", nullable = false)
			//@ElementCollection
			private List<String> pimage;
			
			//@NotBlank(message = "Days is required")
			@Column(name = "days", nullable = false)
			private Integer days;
			
			//@NotBlank(message = "Nights is required")
			@Column(name = "nights", nullable = false)
			private Integer nights;
			
			private Long s_id;
			
			private Long c_id;
 
			
			//@NotBlank(message = "is_fixed_departure is required")
			@Column(name = "is_fixed_departure", nullable = false)
			private boolean is_fixed_departure;
			
			//@NotBlank(message = "fixed_departure_destinations is requird")
			@Column(name = "fixed_departure_destinations", nullable = false)
			private String fixed_departure_destinations;
			
			@NotBlank(message = "PackageType is requird")
			@Column(name = "package_Type", nullable = false)
			private String packageType;
			
			@NotBlank(message = "Created By is requird")
			@Column(name = "created_by", nullable = false)
			private String created_by;
			
			private String modified_by;
			
			
			@NotBlank(message = "IpAddress is requird")
			@Column(name = "ipAddress", nullable = false)
			private String ipaddress;
			
			//@NotBlank(message = "Status is requird")
			@Column(name = "status", nullable = false)
			private boolean status;
			
			private boolean isdelete;
			
			private LocalDateTime created_date;
			
			private LocalDateTime modified_date;
			
			 @Column(name = "destination_covered_id")
			    private String destinationCoveredId;
			 
			 
			 public String getDestinationCoveredId() {
				return destinationCoveredId;
			}

			public void setDestinationCoveredId(String destinationCoveredId) {
				this.destinationCoveredId = destinationCoveredId;
			}

			@Column(name="pkThemid",nullable=false)
			 private String pkthem;
			 
			 @Column(name="inclusionids",nullable=false)
            private String inclusionid;
			 
			 @Column(name="exclusionids",nullable=false)
			 private String exclusionid;
			
			public String getExclusionid() {
				return exclusionid;
			}

			public void setExclusionid(String exclusionid) {
				this.exclusionid = exclusionid;
			}
			
			public void setexclusionids(List<Long> exclusionids) {
		        this.inclusionid = exclusionids.stream()
		                .map(String::valueOf)
		                .collect(Collectors.joining(","));
		    }

		    public List<Long> getexclusionids() {
		        return List.of(exclusionid.split(","))
		                .stream()
		                .map(Long::valueOf)
		                .collect(Collectors.toList());
		    }

			public String getInclusionid() {
				return inclusionid;
			}

			public void setInclusionid(String inclusionid) {
				this.inclusionid = inclusionid;
			}

			public String getPkthem() {
				return pkthem;
			}

			public void setPkthem(String pkthem) {
				this.pkthem = pkthem;
			}

		
			public void inclusionids(List<Long> inclusionids) {
		        this.inclusionid = inclusionids.stream()
		                .map(String::valueOf)
		                .collect(Collectors.joining(","));
		    }

		    public List<Long> getinclusionids() {
		        return List.of(inclusionid.split(","))
		                .stream()
		                .map(Long::valueOf)
		                .collect(Collectors.toList());
		    }
			// Methods to manage destination IDs
		    public void setpkthemIds(List<Long> pkthemIds) {
		        this.pkthem = pkthemIds.stream()
		                .map(String::valueOf)
		                .collect(Collectors.joining(","));
		    }

		    public List<Long> getpkthemIds() {
		        return List.of(pkthem.split(","))
		                .stream()
		                .map(Long::valueOf)
		                .collect(Collectors.toList());
		    }

			@PrePersist
			protected void onCreate() {
				created_date = LocalDateTime.now();
				modified_date = LocalDateTime.now();
			}
			
			@PreUpdate
			protected void onUpdate() {
				
				modified_date = LocalDateTime.now();
			}

			
		
			public Long getId() {
				return id;
			}

			public void setId(Long id) {
				this.id = id;
			}

			public String getPkName() {
				return pkName;
			}

			public void setPkName(String pkName) {
				this.pkName = pkName;
			}

			

			

			

			public String getDescription() {
				return description;
			}

			public void setDescription(String description) {
				this.description = description;
			}

			public String getPkCategory() {
				return pkCategory;
			}

			public void setPkCategory(String pkCategory) {
				this.pkCategory = pkCategory;
			}


			public String getPkSpecifications() {
				return pkSpecifications;
			}

			public void setPkSpecifications(String pkSpecifications) {
				this.pkSpecifications = pkSpecifications;
			}

			

			

			

			

			

			

			public List<String> getPimage() {
				return pimage;
			}

			public void setPimage(List<String> pimage) {
				this.pimage = pimage;
			}

			public int getDays() {
				return days;
			}

			public void setDays(int days) {
				this.days = days;
			}

			public int getNights() {
				return nights;
			}

			public void setNights(int nights) {
				this.nights = nights;
			}

			public boolean isIs_fixed_departure() {
				return is_fixed_departure;
			}

			public void setIs_fixed_departure(boolean is_fixed_departure) {
				this.is_fixed_departure = is_fixed_departure;
			}

			public String getFixed_departure_destinations() {
				return fixed_departure_destinations;
			}

			public void setFixed_departure_destinations(String fixed_departure_destinations) {
				this.fixed_departure_destinations = fixed_departure_destinations;
			}

			

			public String getPackageType() {
				return packageType;
			}

			public void setPackageType(String packageType) {
				this.packageType = packageType;
			}

			public String getCreated_by() {
				return created_by;
			}

			public void setCreated_by(String created_by) {
				this.created_by = created_by;
			}

			public String getModified_by() {
				return modified_by;
			}

			public void setModified_by(String modified_by) {
				this.modified_by = modified_by;
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

			public LocalDateTime getCreated_date() {
				return created_date;
			}

			public void setCreated_date(LocalDateTime created_date) {
				this.created_date = created_date;
			}

			public LocalDateTime getModified_date() {
				return modified_date;
			}

			public void setModified_date(LocalDateTime modified_date) {
				this.modified_date = modified_date;
			}

			public void setDays(Integer days) {
				this.days = days;
			}

			public void setNights(Integer nights) {
				this.nights = nights;
			}

			public Long getS_id() {
				return s_id;
			}

			public void setS_id(Long s_id) {
				this.s_id = s_id;
			}

			public Long getC_id() {
				return c_id;
			}

			public void setC_id(Long c_id) {
				this.c_id = c_id;
			}

			
			

			
			
			
			
			
			
		}

