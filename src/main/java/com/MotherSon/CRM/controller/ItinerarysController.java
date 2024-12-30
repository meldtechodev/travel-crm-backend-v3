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
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.MotherSon.CRM.models.Itinerarys;
import com.MotherSon.CRM.repository.HotelServiceImpl;
import com.MotherSon.CRM.security.services.ItinerarysService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("Motherson/crm/v1/itinerarys")
public class ItinerarysController {
	
	
	@Autowired
	private ItinerarysService itinerarysService;
	@Autowired HotelServiceImpl hotelrepository;
	
	
	
	@GetMapping("/getbyId/{id}")
	public ResponseEntity<Itinerarys> getItinerarysById(@PathVariable Long id){
		Optional<Itinerarys> it = itinerarysService.getItinerarysById(id);
		return it.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	
	
	
	@GetMapping("/getallItineary")
	public List<Itinerarys> getAllItinerarys(){
		return itinerarysService.getAllItinerarys();
	}
	
	
	@GetMapping("/getAll")
	public Page<Itinerarys> getItinerarys(
			@RequestParam(value = "page" , defaultValue = "0") int page,
			@RequestParam(value = "size" , defaultValue = "10") int size,
			@RequestParam(value = "sortDirection" , defaultValue = "asc") String sortDirection
			){
		return itinerarysService.getItinerarys(page , size , sortDirection);
	}
	
	
	
	
	
	//@PostMapping(consumes = "application/json", produces = "application/json")
//	@PostMapping("/create")
//	public ResponseEntity<Itinerarys> createItinerary(@RequestBody Itinerarys itinerary) {
//	    try {
//	        // Handle the incoming itinerary and return a response
//	        return ResponseEntity.ok(itinerary);
//	    } catch (Exception e) {
//	        e.printStackTrace(); // Log the error
//	        return ResponseEntity.badRequest().build();
//	    }
//	}
	
	
	
//	//@PostMapping("/create")
//	@PostMapping(value = "/your-endpoint", consumes = "application/json", produces = "application/json")
//	public Itinerarys addItinerarys(@RequestBody Itinerarys itinerarys) {
//		return this.itinerarysService.addItinerarys(itinerarys);
//	}
	
	
	//@PostMapping(value = "/itineraries", consumes = MediaType.APPLICATION_JSON_VALUE)
	
	
//		@PostMapping("/create")
//		public ResponseEntity<?> addItinerarys(@RequestBody Itinerarys itinerarys) {
//		    // Validate hotelOptionIds
//		    for (Long hotelOptionId : itinerarys.getHotelOptionIds()) {
//		        if (!hotelrepository.existsById(hotelOptionId)) {
//		            throw new IllegalArgumentException("HotelOption ID " + hotelOptionId + " does not exist");
//		        }
//		    }
//
//		    Itinerarys createdItinerary = this.itinerarysService.addItinerarys(itinerarys);
//		    return new ResponseEntity<>("itinarys created sucessfully",HttpStatus.CREATED);
//		}
	
	private String timestamp;
	 
	public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/image/Iteneries";
//	 @PreAuthorize("hasAuthority('Super_Admin')")
	
	@PostMapping("/create")
	public ResponseEntity<?> saveCountry(@Valid @ModelAttribute Itinerarys itinerarys,BindingResult result, @RequestParam("image") MultipartFile[] files )
			throws IOException {
		      
		//handle validator error
		 if (result.hasErrors()) {
		        Map<String, String> errors = new HashMap<>();
		        result.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		        System.out.print("Not corrextsssssssssssss");
		        return ResponseEntity.badRequest().body(errors);
		    }
		
//		 if (itinerarysService.existsByCountryName(country.getCountryName())) {
//	            return ResponseEntity.status(HttpStatus.CONFLICT)
//	                    .body("Country with the name " + country.getCountryName() + " already exists.");
//	        }
		
		
		List<String> imageUrls = new ArrayList<>();
 
		Path uploadPath = Paths.get(uploadDirectory);
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
 
		for (MultipartFile file : files) {
 
			// Validate file type
			if (!isValidImage(file)) {
				throw new IllegalArgumentException("File must be a JPEG or PNG image.");
			}
 
			String uniqueFilename = generateUniqueFilename(file.getOriginalFilename());
			// String uniqueFilename = uploadDate + "_" + originalFilename; // e.g.,
			// "14-12-2024_image.jpg"
			Path fileNameAndPath = Paths.get(uploadDirectory, uniqueFilename);
			Files.write(fileNameAndPath, file.getBytes());
 
			// String originalFilename = file.getOriginalFilename();
			// Path fileNameAndPath = Paths.get(uploadDirectory, originalFilename);
			// Files.write(fileNameAndPath, file.getBytes());
			String imageUrl = "/image/" + uniqueFilename;
			;
			imageUrls.add(imageUrl);
		}
 
		itinerarys.setItineries_image(imageUrls);
 
		return ResponseEntity.ok(this.itinerarysService.addItinerarys(itinerarys));
		
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
		// DateTimeFormatter timestamp = DateTimeFormatter.ofPattern("HHmm");
 
		// Generate a UUID
		String uniqueID = UUID.randomUUID().toString();
 
		return timestamp + "_" + uniqueID + extension;
	}
 
	
	
	
//	@PutMapping("/updateby{id}")
//	public ResponseEntity<Itinerarys> updateItinerarys(@PathVariable long id , @RequestBody Itinerarys itinerarys ){
//		if(itinerarys != null)
//		{
//			Itinerarys it = new Itinerarys();
//			it.setId(id);
//			////it.setDaynumber(itinerarys.getDaynumber());
//			//it.setCityname(itinerarys.getCityname());
//			it.setDaytitle(itinerarys.getDaytitle());
//			it.setProgram(itinerarys.getProgram());
//			it.setMeals(itinerarys.getMeals());
//			//it.setTransportid(itinerarys.getTransportid());
//			it.setCreatedby(itinerarys.getCreatedby());
//			it.setModifiedby(itinerarys.getModifiedby());
//			it.setIpaddress(itinerarys.getIpaddress());
//			it.setStatus(itinerarys.isStatus());
//			it.setIsdelete(itinerarys.isIsdelete());
//			it.setCreateddate(itinerarys.getCreateddate());
//			it.setModifieddate(itinerarys.getModifieddate());
//		
//			
//			itinerarysService.updateItinerarys(it);
//			return ResponseEntity.ok(it);
//			
//		}
//		else
//		{
//			return ResponseEntity.notFound().build();
//			
//		}}
		
		@PutMapping("/updateby/{id}")
	    public ResponseEntity<Itinerarys> updateItinerarys(@PathVariable Long id, @RequestBody @Valid Itinerarys itinerarys) {
	        Itinerarys updatedItinerary = itinerarysService.updateItinerarys(id, itinerarys);

	        if (updatedItinerary != null) {
	            return ResponseEntity.ok(updatedItinerary);
	        } else {
	            return ResponseEntity.notFound().build();  //
	        }
	    }

	
	@DeleteMapping("/deleteby/{id}")
	public ResponseEntity<Itinerarys> deleteItinerarys(@PathVariable Long id){
		
		try
		{
			itinerarysService.findById(id);
			itinerarysService.deleteById(id);
		}
		
		finally
		{
			
			return ResponseEntity.notFound().build();
		}
	}
	

}