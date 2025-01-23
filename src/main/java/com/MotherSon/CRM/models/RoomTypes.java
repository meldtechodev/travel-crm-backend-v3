package com.MotherSon.CRM.models;
 
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
 
@Entity
@Table(name = "room_types_master")
public class RoomTypes {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_id", nullable = true)
    private Hotel hotel;
 
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rooms_id", nullable = true)
    private Rooms rooms;
 
    @Column(name = "bed_size", nullable = true)
    private String bedSize;
 
    @Column(name = "max_person", nullable = true)
    private Integer max_person;
 
    @Column(nullable = true)
    private String rimage;
 
    @Column(nullable = true)
    private boolean status;
 
    @Column(name = "created_date", nullable = true)
    private LocalDateTime created_date;
 
    @Column(name = "modified_date", nullable = true)
    private LocalDateTime modified_date;
 
    @Column(name = "created_by", nullable = true)
    private String created_by;
 
    @Column(name = "modified_by", nullable = true)
    private String modified_by;
 
    @Column(nullable = true)
    private boolean isdelete;
 
    @Column(nullable = true)
    private String ipaddress;
 
    @PrePersist
    protected void onCreate() {
        created_date = LocalDateTime.now();
        modified_date = LocalDateTime.now();
    }
 
    @PreUpdate
    protected void onUpdate() {
        modified_date = LocalDateTime.now();
    }
 
    // Getters and setters
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public Hotel getHotel() {
        return hotel;
    }
 
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
 
    public Rooms getRooms() {
        return rooms;
    }
 
    public void setRooms(Rooms rooms) {
        this.rooms = rooms;
    }
 
    public String getBedSize() {
        return bedSize;
    }
 
    public void setBedSize(String bedSize) {
        this.bedSize = bedSize;
    }
 
    public Integer getMax_person() {
        return max_person;
    }
 
    public void setMax_person(Integer max_person) {
        this.max_person = max_person;
    }
 
    public String getRimage() {
        return rimage;
    }
 
    public void setRimage(String rimage) {
        this.rimage = rimage;
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
}
