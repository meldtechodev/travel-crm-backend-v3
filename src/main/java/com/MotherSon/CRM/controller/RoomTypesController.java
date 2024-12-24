package com.MotherSon.CRM.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.validation.BindingResult;

import com.MotherSon.CRM.models.HotelPrice;
import com.MotherSon.CRM.models.RoomTypes;
import com.MotherSon.CRM.models.Rooms;
import com.MotherSon.CRM.security.services.RoomTypesService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.Valid;


@RestController
@RequestMapping("Motherson/crm/v1/roomtypes")
@CrossOrigin(origins = "*", maxAge = 3600)
//@JsonIgnoreProperties({HotelPrice})
	public class RoomTypesController {
		
		
		@Autowired
		private RoomTypesService roomtypesService;
		
		
//		@GetMapping("/getAll")
//		public List<RoomTypes> getAllRoomTypes(){
//			return roomtypesService.getAllRoomTypes();
//		}
		
		@GetMapping("/getall")
	    public Object getAllRoomTypes(@RequestParam(required = false) Long hotelId,
	    		                       @RequestParam(required = false) Long roomTypeId) {
	        return roomtypesService.getRoomTypes(hotelId, roomTypeId);
	    }
 
		
		
		@GetMapping("/getAll")
		public Page<RoomTypes> getRoomTypes(
				@RequestParam(value = "page" , defaultValue = "0") int page,
				@RequestParam(value = "size" , defaultValue = "10") int size,
				@RequestParam(value = "sortDirection" , defaultValue = "asc") String sortDirection
				){
			return roomtypesService.getRoomTypes(page , size , sortDirection);
		}
		
		
		@GetMapping("/getById/{id}")
		public ResponseEntity<RoomTypes> getRoomTypessById(@PathVariable Long id){
			Optional<RoomTypes> R = roomtypesService.getRoomTypessById(id);
			return R.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
		}
		
		
//		@PostMapping("/create")
//		public RoomTypes addRoomTypes(@RequestBody RoomTypes roomtypes) {
//			return this.roomtypesService.addRoomTypes(roomtypes);
//		}
		
		
		private String timestamp;

		public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/image/rimages";

	    @PostMapping("/create")
	    public ResponseEntity<?> saveRoomTypes(@Valid @ModelAttribute RoomTypes roomtypes,BindingResult result,
	                                    @RequestParam("image") MultipartFile file) throws IOException {
	        
	    	 if (result.hasErrors()) {
			        Map<String, String> errors = new HashMap<>();
			        result.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
			        System.out.print("Not corrextsssssssssssss");
			        return ResponseEntity.badRequest().body(errors);
			    }
			 
	        // Validate the uploaded file
	        if (!isValidImage(file)) {
	            throw new IllegalArgumentException("File must be a JPEG or PNG image.");
	        }

	        // Ensure the upload directory exists
	        Path uploadPath = Paths.get(uploadDirectory);
	        if (!Files.exists(uploadPath)) {
	            Files.createDirectories(uploadPath);
	        }

	        // Generate a unique file name and save the image
	        String uniqueFilename = generateUniqueFilename(file.getOriginalFilename());
	        Path fileNameAndPath = Paths.get(uploadDirectory, uniqueFilename);
	        Files.write(fileNameAndPath, file.getBytes());

	        // Set the image URL in the roomtypes object
	        String imageUrl = "/image/" + uniqueFilename; // Assuming this is how you want to construct the URL
	        roomtypes.setRimage(imageUrl); // Assuming rimage is a String field

	        return ResponseEntity.ok(this.roomtypesService.addRoomTypes(roomtypes));
	    }

	    private boolean isValidImage(MultipartFile file) {
	        // Get the file content type
	        String contentType = file.getContentType();
	        return contentType != null && (contentType.equals("image/jpeg") || contentType.equals("image/png"));
	    }

	    private String generateUniqueFilename(String originalFilename) {
	        // Extract the file extension
	        String extension = "";
	        if (originalFilename != null && originalFilename.lastIndexOf('.') > 0) {
	            extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
	        }

	        // Set the current timestamp
	        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH-mm-ss"));

	        // Generate a UUID
	        String uniqueID = UUID.randomUUID().toString();

	        // Create a unique filename
	        return timestamp + "_" + uniqueID + extension;
	        
	    }

	    
	    
	    @PutMapping("/update/{id}")
	    public RoomTypes updateRoomTypes(@PathVariable Long id,
	                                  @ModelAttribute RoomTypes roomtypes,
	                                  @RequestParam(value = "image", required = false) MultipartFile imageFile) throws IOException {
	        // Fetch the existing booking
	        RoomTypes existingRoomTypes = roomtypesService.getRoomTypesById(id);

	       // Delete old rimage if it exists
	        if (existingRoomTypes.getRimage() != null) {
	            Path oldImagePath = Paths.get(uploadDirectory, existingRoomTypes.getRimage().substring(existingRoomTypes.getRimage().lastIndexOf("/") + 1));
	            Files.deleteIfExists(oldImagePath);
	        }

	        // Handle the new image file
	      if (imageFile != null) {
	            if (!isValidImage(imageFile)) {
	               throw new IllegalArgumentException("File must be a JPEG or PNG image.");
	           }

	           String uniqueFilename = generateUniqueFilename(imageFile.getOriginalFilename());
	            Path filePath = Paths.get(uploadDirectory, uniqueFilename);
	            Files.write(filePath, imageFile.getBytes());

	            String imageUrl = "/uploads/" + uniqueFilename;
	            roomtypes.setRimage(imageUrl); // Set the rimage field
	        }

//	        // Update other fields
	        existingRoomTypes.setBedSize(roomtypes.getBedSize());
	        existingRoomTypes.setMax_person(roomtypes.getMax_person());
	        existingRoomTypes.setRimage(roomtypes.getRimage());
	        existingRoomTypes.setStatus(roomtypes.isStatus());
	        existingRoomTypes.setCreated_by(roomtypes.getCreated_by());
	        existingRoomTypes.setModified_by(roomtypes.getModified_by());
	        existingRoomTypes.setIsdelete(roomtypes.isIsdelete());
	        existingRoomTypes.setIpaddress(roomtypes.getIpaddress());
	        
////	        existingRoomTypes.setCreated_date(LocalDateTime.now()); // or set it from the input if provided
////	        existingRoomTypes.setModified_date(LocalDateTime.now());
	//
	        // Save the updated booking
	       return roomtypesService.updateRoomTypesById(id, existingRoomTypes);
	    }

	    private boolean isValidImage1(MultipartFile file) {
	        String contentType = file.getContentType();
	        return contentType != null && (contentType.equals("image/jpeg") || contentType.equals("image/png"));
	    }

	    private String generateUniqueFilename1(String originalFilename) {
	        return System.currentTimeMillis() + "_" + originalFilename; // Unique filename based on current time
	    }

	    
	    
//	    @PutMapping("/update/{id}")
//	    public RoomTypes updateRoomTypes(@PathVariable Long id,
//	                                  @ModelAttribute RoomTypes roomtypes,
//	                                  @RequestParam(value = "image", required = false) MultipartFile file) throws IOException {
//	        // Fetch the existing booking
//	        RoomTypes existingRoomTypes = roomtypesService.getRoomTypesById(id);
	//
//	        // Delete the old rimage from the file system
//	        if (existingRoomTypes.getRimage() != null) {
//	            Path oldImagePath = Paths.get(uploadDirectory, existingRoomTypes.getRimage().substring(existingRoomTypes.getRimage().lastIndexOf("/") + 1));
//	            Files.deleteIfExists(oldImagePath);
//	        }
	//
//	        // Handle the new rimage file
//	        if (file != null && isValidImage(file)) {
//	            String uniqueFilename = generateUniqueFilename(file.getOriginalFilename());
//	            Path fileNameAndPath = Paths.get(uploadDirectory, uniqueFilename);
//	            Files.write(fileNameAndPath, file.getBytes());
	//
//	            String imageUrl = "/uploads/" + uniqueFilename;
//	            roomtypes.setRimage(imageUrl); // Set the new rimage
//	        } else {
//	            // Retain the old rimage if no new file is provided
//	            roomtypes.setRimage(existingRoomTypes.getRimage());
//	        }
	//
//	        // Update other fields if necessary
//	        roomtypes.setId(id); // Ensure the ID is set
//	        return roomtypesService.updateBookingById(id, roomtypes);
//	    }
	//
//	    private boolean isValidImage1(MultipartFile file) {
//	        String contentType = file.getContentType();
//	        return contentType != null && (contentType.equals("image/jpeg") || contentType.equals("image/png"));
//	    }
	//
//	    private String generateUniqueFilename1(String originalFilename) {
//	        return System.currentTimeMillis() + "_" + originalFilename; // Unique filename based on current time
//	    }
	//}
	    
	    
	    @DeleteMapping("/delete/{id}")
		public ResponseEntity<RoomTypes> deleteRoomTypes(@PathVariable  long id){
			
			try
			{
				roomtypesService.findById(id);
				roomtypesService.deleteById(id);
				return ResponseEntity.noContent().build();
			}
			finally
			{
				return ResponseEntity.notFound().build();
			}
			
		}
	    
	    
	    
	}

