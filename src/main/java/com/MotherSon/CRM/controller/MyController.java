package com.MotherSon.CRM.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
//import java.util.Optional;
import java.util.UUID;

//import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.MotherSon.CRM.dto.Response;
import com.MotherSon.CRM.models.Hotel;
import com.MotherSon.CRM.security.services.HotelService;

import jakarta.validation.Valid;

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RestController
	@RequestMapping("Motherson/crm/v1/hotel")
	public class MyController {
		
		@Autowired
		private HotelService hotelService;
		
		
		
		@GetMapping("/getAll")
		public List<Hotel> getAllHotel(){
			return hotelService.getAllHotel();
		}
		
		
//		@GetMapping("/destination/{id}")
//		public List<Hotel> getHotelByDestinationId(@PathVariable Long id){
//			return hotelService.getHotelByDestinationId(id);
//		}
		
		@GetMapping("/gethotelby/{id}")
	    public ResponseEntity<Hotel> getHotelsById(@PathVariable Long id) {
	        Optional<Hotel> hotel = hotelService.getHotelsById(id);
	        return hotel.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
	                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	    }
		
        
		
		private String timestamp;

	public static String uploadDirectory=System.getProperty("user.dir") + "/src/main/we/image";
		
		@PostMapping("/create")
		 public ResponseEntity<?> saveHotel(@Valid @ModelAttribute Hotel hotel,BindingResult result,
	             @RequestParam("image") MultipartFile[] files) throws IOException {
			
			
			if (result.hasErrors()) {
		        Map<String, String> errors = new HashMap<>();
		        result.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		        System.out.print("Not corrextsssssssssssss");
		        return ResponseEntity.badRequest().body(errors);
		    }
			 if (hotelService.existsByhname(hotel.getHname())) {
		            return ResponseEntity.status(HttpStatus.CONFLICT)
		                    .body("hotel Name with the name " + hotel.getHname() + " already exists.");
		        }
			
			List<String> imageUrls = new ArrayList<>();
			
			
			 Path uploadPath = Paths.get(uploadDirectory);
		        if (!Files.exists(uploadPath)) {
		            Files.createDirectories(uploadPath);
		        
		        }
			for(MultipartFile  file : files) {
				
	// Validate file type
	if (!isValidImage(file)) {
	throw new IllegalArgumentException("File must be a JPEG or PNG image.");
	}


	// Generate a unique file name


	String uniqueFilename = generateUniqueFilename(file.getOriginalFilename());
	//String uniqueFilename = uploadDate + "_" + originalFilename; // e.g., "14-12-2024_image.jpg"
	Path fileNameAndPath = Paths.get(uploadDirectory, uniqueFilename);
	Files.write(fileNameAndPath, file.getBytes());


	String imageUrl = "/image/" +  uniqueFilename;; 
	imageUrls.add(imageUrl);
		}
		
	hotel.setHimage(imageUrls);

	 return ResponseEntity.ok(this.hotelService.addHotel(hotel));
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
	    
	    // Set the current time stamp
	    
	  String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH-mm-ss"));
	  //DateTimeFormatter timestamp = DateTimeFormatter.ofPattern("HHmm");

	    // Generate a UUID
	    String uniqueID = UUID.randomUUID().toString();

	    // Create a unique filename
	    
	    
	    return timestamp + "_" + uniqueID + extension;
	}



	@PutMapping("/update/{id}")
	public Hotel updateHotel(@PathVariable Long id,
	                              @ModelAttribute Hotel hotel,
	                              @RequestParam(value = "image", required = false) MultipartFile[] files) throws IOException {
	    // First, fetch the existing country
	    Hotel existingHotel = hotelService.getHotelById(id);
	    
	    // Delete old images from the file system
	    if (existingHotel.getHimage() != null) {
	        for (String oldImageUrl : existingHotel.getHimage()) {
	            Path oldImagePath = Paths.get(uploadDirectory, oldImageUrl.substring(oldImageUrl.lastIndexOf("/") + 1));
	            Files.deleteIfExists(oldImagePath);
	        }
	    }
 
	    List<String> imageUrls = new ArrayList<>();
 
	    if (files != null) {
	        for (MultipartFile file : files) {
	            if (!isValidImage(file)) {
	                throw new IllegalArgumentException("File must be a JPEG or PNG image.");
	            }
 
	            String uniqueFilename = generateUniqueFilename(file.getOriginalFilename());
	            Path fileNameAndPath = Paths.get(uploadDirectory, uniqueFilename);
	            Files.write(fileNameAndPath, file.getBytes());
 
	            String imageUrl = "/uploads/" + uniqueFilename;
	            imageUrls.add(imageUrl);
	        }
	    }
 
	    hotel.setHimage(imageUrls);
	    return hotelService.updateHotelById(id, hotel);
	}
 
	private boolean isValidImage1(MultipartFile file) {
	    String contentType = file.getContentType();
	    return contentType != null && (contentType.equals("image/jpeg") || contentType.equals("image/png"));
	}
 
	private String generateUniqueFilename1(String originalFilename) {
	    return System.currentTimeMillis() + "_" + originalFilename; // Unique filename based on current time
	}
 
		

	//@PutMapping("/updateHotel/{id}")
	//public ResponseEntity<Hotel> updateHotel(@PathVariable(value = "id") long id,
//	                                          @ModelAttribute Hotel hotelDetails,
//	                                          @RequestParam(value = "image", required = false) MultipartFile[] files) throws IOException {
//	    if (files != null) {
//	        List<String> imageUrls = new ArrayList<>();
//	        for (MultipartFile file : files) {
//	            if (!isValidImage(file)) {
//	                throw new IllegalArgumentException("File must be a JPEG or PNG image.");
//	            }
//	            String uniqueFilename = generateUniqueFilename(file.getOriginalFilename());
//	            Path fileNameAndPath = Paths.get(uploadDirectory, uniqueFilename);
//	            Files.write(fileNameAndPath, file.getBytes());
//	            String imageUrl = "/image/" + uniqueFilename;
//	            imageUrls.add(imageUrl);
//	        }
//	        hotelDetails.setHimage(imageUrls);
//	    }
	//    
//	    Optional<Hotel> updatedHotel = hotelService.updateHotel(id, hotelDetails);
//	    return updatedHotel.map(ResponseEntity::ok)
//	                       .orElseGet(() -> ResponseEntity.notFound().build());
	//}



		
//		@PutMapping("/update/{id}")
//		public ResponseEntity<Hotel> updateHotels(@PathVariable long id , @RequestBody Hotel hotel){
//			if(hotel !=null)
//			{
//				Hotel h=new Hotel();
//				
//			h.setId(id);	
//			h.setHname(hotel.getHname());
//			h.setHdescription(hotel.getHdescription());
//			h.setHimage(hotel.getHimage());
//			h.setRoomtype(hotel.getRoomtype());
//			h.setRoomsize(hotel.getRoomsize());
//			h.setBedtype(hotel.getBedtype());
//			h.setBedsize(hotel.getBedsize());
//			h.setHcontactname(hotel.getHcontactname());
//			h.setHcontactnumber(hotel.getHcontactnumber());
//			h.setHcontactemail(hotel.getHcontactemail());
//			h.setHamenities(hotel.getHamenities());
//			h.setHaddress(hotel.getHaddress());
//			h.setHpincode(hotel.getHpincode());
//			h.setHrating(hotel.getHrating());
//			h.setHstatus(hotel.getHstatus());
//			
//			
//			hotelService.updateHotels(h);
//			return ResponseEntity.ok(h);
//			
//			}
//			
//			else
//			{
//				return ResponseEntity.notFound().build();
//			}}
//			
			@DeleteMapping("/delete/{id}")
			public ResponseEntity<Hotel> deleteHotels(@PathVariable  long id){
				
				try
				{
					hotelService.findById(id);
					hotelService.deleteById(id);
					return ResponseEntity.noContent().build();
				}
				finally
				{
					return ResponseEntity.notFound().build();
				}
				
			}
			
			@GetMapping("/getallHotel")
			public Response<Object> getAllHotel(
			        @RequestParam(required = false) Long stateId,
			        @RequestParam(required = false) Long countryId,
			        @RequestParam(required = false) Long destinationId,
			        @RequestParam(required = false) Long hotelId
			) {
			    return hotelService.getHotel(stateId, countryId, destinationId, hotelId);
			}

	}

