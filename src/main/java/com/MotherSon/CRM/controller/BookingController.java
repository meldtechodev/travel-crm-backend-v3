package com.MotherSon.CRM.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.MotherSon.CRM.models.User;
import com.MotherSon.CRM.models.booking.Booking;
import com.MotherSon.CRM.security.services.BookingService;
import com.MotherSon.CRM.security.services.DestinationService;
import com.MotherSon.CRM.security.services.UserService;




@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/Motherson/crm/v1/booking")
public class BookingController {
	
	
	
	@Autowired
	private BookingService bookingService;
	
	
	@Autowired
	private DestinationService destinationservice;
	
	
	
	@GetMapping("/getallBooking")
	public ResponseEntity<List<Booking>> getAllBooking(){
		List<Booking> bookinglist = bookingService.getAllBooking();
		return new ResponseEntity<>(bookinglist, HttpStatus.OK);
	}
	
	
	
	@GetMapping("/getbyuserid/{userId}")
    public List<Booking> getBookingsByUser(@PathVariable Long userId) {
        return bookingService.findBookingsByUserId(userId);
	
	}
	
	
//	@GetMapping("/top-destinations/{userId}")
//    public Map<Object, Object> getTopConfirmedDestinations(@PathVariable Long userId,
//                                                          @RequestParam(defaultValue = "Error") int limit) {
//        return bookingService.getTopConfirmedDestinations(userId, limit);
//	}

	
	
	private String timestamp;

	public static String uploadDirectory=System.getProperty("user.dir") + "/src/main/upload/bookingimages";
		
		@PostMapping("/create")
		 public Booking saveBooking(@ModelAttribute Booking booking,
	             @RequestParam("image") MultipartFile[] files) throws IOException {
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

	//String uniqueFilename = generateUniqueFilename(uploadDate, originalFilename, timestamp);
	String uniqueFilename = generateUniqueFilename(file.getOriginalFilename());
	//String uniqueFilename = uploadDate + "_" + originalFilename; // e.g., "14-12-2024_image.jpg"
	Path fileNameAndPath = Paths.get(uploadDirectory, uniqueFilename);
	Files.write(fileNameAndPath, file.getBytes());



	
	String imageUrl = "/image/" +  uniqueFilename;; 
	imageUrls.add(imageUrl);
		}
		
	booking.setCustomerIdProof(imageUrls);

	return this.bookingService.addBooking(booking);
	}

	//private String generateUniqueFilename(String uploadDate, String originalFilename) {
			// TODO Auto-generated method stub
			//return null;
		//}

	//private boolean isValidImage(List<MultipartFile> file) {
		
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
	
	
	
	// For Update 
	
	@PutMapping("/update/{id}")
    public Booking updateBooking(@PathVariable Long id,
                                  @ModelAttribute Booking booking,
                                  @RequestParam(value = "image", required = false) MultipartFile[] files) throws IOException {
        // First, fetch the existing booking
        Booking existingBooking = bookingService.getBookingById(id);
        
        // Delete old images from the file system
        if (existingBooking.getCustomerIdProof() != null) {
            for (String oldImageUrl : existingBooking.getCustomerIdProof()) {
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

        booking.setCustomerIdProof(imageUrls);
        return bookingService.updateBookingById(id, booking);
    }

    private boolean isValidImage1(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && (contentType.equals("image/jpeg") || contentType.equals("image/png"));
    }

    private String generateUniqueFilename1(String originalFilename) {
        return System.currentTimeMillis() + "_" + originalFilename; // Unique filename based on current time
    }
	
	
	
	
	@PutMapping("/deleteby/{id}")
	public ResponseEntity<?> deleteBooking(@PathVariable Long id){
		bookingService.deleteBooking(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}



	@GetMapping("/byUser/{userId}")
	public ResponseEntity<List<Booking>> getBookingByuserIdAndStatus(
	        @PathVariable Long userId, 
	        @RequestParam String status) {
	    if (!status.equals("CONFIRMED") && !status.equals("CANCELED") && !status.equals("PENDING")) {
	        return ResponseEntity.badRequest().body(null);
	    }
	    List<Booking> bookings = bookingService.getBookingsByStatus(userId, status);
	    return ResponseEntity.ok(bookings);
	}

	
	@GetMapping("/top-destinations/{userId}")
    public Map<String, Long> getTopConfirmedDestinations_Id(@PathVariable Long userId,
                                                          @RequestParam(defaultValue = "Error") int limit) {
        return bookingService.getTopConfirmedDestinations_Id(userId, limit);
	}
}