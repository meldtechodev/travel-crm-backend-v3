package com.MotherSon.CRM.dto;

public class RoomTypesDTO {

	 private Long room_type_id;
	    private String roomName;
	    private String bedSize;
	    
		public RoomTypesDTO(Long room_type_id, String roomName, String bedSize) {
			super();
			this.room_type_id = room_type_id;
			this.roomName = roomName;
			this.bedSize = bedSize;
		}
		public Long getRoom_type_id() {
			return room_type_id;
		}
		public void setRoom_type_id(Long room_type_id) {
			this.room_type_id = room_type_id;
		}
		public String getRoomName() {
			return roomName;
		}
		public void setRoomName(String roomName) {
			this.roomName = roomName;
		}
		public String getBedSize() {
			return bedSize;
		}
		public void setBedSize(String bedSize) {
			this.bedSize = bedSize;
		}	    
}