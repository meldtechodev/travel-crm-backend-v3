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

//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.MotherSon.CRM.models.Destination;
import com.MotherSon.CRM.security.services.DestinationService;

import jakarta.validation.Valid;
//import com.ms.jwt.destinationservice.DestinationService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("Motherson/crm/v1/destination")
public class DestinationController {
	@Autowired
	private DestinationService destinationservice;
	
//	@Autowired
//	private CountryRepository countryRepository;
//	
//	@Autowired
//	private StateRepository staterepository;
	
	
	
//	@PostMapping("/create")
//	public ResponseEntity <Destination> created(@Valid @RequestBody Destination destination)
//	{
//		
//		System.out.print("--------------------xxxxxxxxxxxxxxxxxxxxxxxxxxx--------------");
//		 // Process each key attraction to add a master k	       Destination created= destinationservice.createddestination(destination);
//		return ResponseEntity.ok(created);
//		
//	}
	private String timestamp;
	 
	public static String uploadDirectory=System.getProperty("user.dir") + "/src/main/image/Dimages";
		
		@PostMapping("/create")
		 public ResponseEntity<?> saveDestination (@Valid @ModelAttribute Destination destination,BindingResult result,
	             @RequestParam("image") MultipartFile[] files) throws IOException {
			
			
			
			 if (result.hasErrors()) {
			        Map<String, String> errors = new HashMap<>();
			        result.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
			        System.out.print("Not corrextsssssssssssss");
			        return ResponseEntity.badRequest().body(errors);
			    }
			 if(destinationservice.existsByDestinationName(destination.getDestinationName()))
			 {
				 return ResponseEntity.status(HttpStatus.CONFLICT)
						 .body("destination with the name " + destination.getDestinationName() + " already exists.");
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
	Path fileNameAndPath = Paths.get(uploadDirectory, uniqueFilename);
	Files.write(fileNameAndPath, file.getBytes());
 
 
	String imageUrl = "/image/" +  uniqueFilename;;
	imageUrls.add(imageUrl);
		}
		
	destination.setD_image(imageUrls);
 
	return ResponseEntity.ok( this.destinationservice.createddestination(destination));
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


//	@GetMapping("/getall")
//	public ResponseEntity<List<Destination>> getAlldestination() {
//		List<Destination> destinationdt = destinationservice.getAllDestination();
//		return ResponseEntity.ok(destinationdt);
//
//	}
	
	
	@GetMapping("/getall")
	public Page<Destination> getDestination(
			@RequestParam(value = "page" , defaultValue = "0") int page,
			@RequestParam(value = "size" , defaultValue = "10") int size,
			@RequestParam(value = "sortDirection" , defaultValue = "asc") String sortDirection
			){
		return destinationservice.getDestination(page, size , sortDirection);
	}
	
	
	

	@GetMapping("/getbyid/{id}")
	public Optional<Destination> getDestinationById(@PathVariable Long id) {
		Optional<Destination> destinationid = destinationservice.getDestinationsById(id);

		return destinationid;

	}

//	@GetMapping("/getmasterkey/{masterkey}")
//	public Optional<Destination> getMasterKey(@PathVariable String masterkey) {
//		Optional<Destination> destinationmaster = destinationservice.getDestinationByMasterKey(masterkey);
//		return destinationmaster;
//
//	}

	@DeleteMapping("/deletebyid/{id}")
	public void deleteById(@PathVariable Long id) {
		destinationservice.deleteDestination(id);
	}

//	@PutMapping("/updateby/{id}")
//	public Destination updated(@PathVariable Long id, @RequestBody Destination dts) {
//
//		Destination updatedestination = destinationservice.updateDestinationByid(id, dts);
//
//		return updatedestination;
//
//	}
	
	@PutMapping("/updatebyid/{id}")
    public Destination updateDestination(@PathVariable Long id,
                                  @ModelAttribute Destination destination,
                                  @RequestParam(value = "image", required = false) MultipartFile[] files) throws IOException {
        // First, fetch the existing country
        Destination existingDestination = destinationservice.getDestinationById(id);
        
        // Delete old images from the file system
        if (existingDestination.getD_image() != null) {
            for (String oldImageUrl : existingDestination.getD_image()) {
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
 
        destination.setD_image(imageUrls);
        return destinationservice.updateDestinationById(id, destination);
    }
 
    private boolean isValidImage1(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && (contentType.equals("image/jpeg") || contentType.equals("image/png"));
    }
 
    private String generateUniqueFilename1(String originalFilename) {
        return System.currentTimeMillis() + "_" + originalFilename; // Unique filename based on current time
    }
	


}
