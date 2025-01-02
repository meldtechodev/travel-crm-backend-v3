package com.MotherSon.CRM.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

//import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.MotherSon.CRM.models.Destination;
import com.MotherSon.CRM.models.Inclusion;
import com.MotherSon.CRM.models.Pkg;
import com.MotherSon.CRM.models.User;
import com.MotherSon.CRM.repository.DestinationRepository;
import com.MotherSon.CRM.repository.ExclusionRepository;
import com.MotherSon.CRM.repository.InclusionRepository;
import com.MotherSon.CRM.repository.PackageThemeRepository;
import com.MotherSon.CRM.repository.UserRepository;
import com.MotherSon.CRM.repository.VendorRepository;
import com.MotherSon.CRM.security.services.DestinationService;
import com.MotherSon.CRM.security.services.InclusionService;
import com.MotherSon.CRM.security.services.PkgService;
import com.MotherSon.CRM.security.services.QueryBookService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("Motherson/crm/v1/packages")
public class PkgController {
		
		
		@Autowired
		private PkgService pkgService;
		
		@Autowired
		private UserRepository userRepository;
		
		@Autowired
		private DestinationService destinationservice;
		
		@Autowired 
		private DestinationRepository destinationRepository;
		
		@Autowired
		private  PackageThemeRepository pkgthemrepository;
		
		@Autowired
		private VendorRepository vendorrepository;
		
		@Autowired 
		private InclusionRepository inclusionrepository;
		
		@Autowired
		private QueryBookService queryBookService;
		
		@Autowired
		private ExclusionRepository exclusionrepositiry;
		
		@GetMapping("/getby/{id}")
	    public ResponseEntity<Pkg> getPkgsById(@PathVariable Long id) {
	    Optional<Pkg> pkg = pkgService.getPkgsById(id);
	        return (ResponseEntity<Pkg>) pkg.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
	                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	    }
		
		
		@GetMapping("/getAllPkg")
	    public List<Pkg> getAllPkg(@RequestParam(required = false) Long id) {
	        return pkgService.getAllPkg(id);
	    }

		
		
		@GetMapping("/getAll")
		public Page<Pkg> getPkg(
				@RequestParam(value = "page" , defaultValue = "0") int page,
				@RequestParam(value = "size" , defaultValue = "10") int size,
				@RequestParam(value = "sortDirection" , defaultValue = "asc") String sortDirection
				){
			return pkgService.getPkg(page , size , sortDirection);
		}
		
		
		
		private String timestamp;
		
		public static String uploadDirectory=System.getProperty("user.dir") + "/src/main/upload/images";
			
			@PostMapping("/create")
			 public Pkg savePkg( @ModelAttribute Pkg pkg,
				        
		             @RequestParam("image") MultipartFile[] files,
		             @RequestParam("destinationCoveredId") String destinationCoveredId,
		             @RequestParam("pkthem") String pkthem,
		             @RequestParam("inclusionid") String inclusionid,
		             @RequestParam("exclusionid") String exclusionid) throws IOException {
				
				
		        // Convert comma-separated IDs to List<Long>
//		        List<Long> destinationIdList = Arrays.stream(destinationCoveredId.split(","))
//		                                             .map(Long::valueOf)
//		                                             .collect(Collectors.toList());
		        
		        // Fetch fromCity and toCity as Destination entities from the database
		        // Validate fromCityId
//		        if (pkg.getFromCityId() == null || !destinationRepository.existsById(pkg.getFromCityId())) {
//		            throw new IllegalArgumentException("From City ID does not exist.");
//		        }
//		        else
//		        {
//		        	pkg.setFromCityId(pkg.getFromCityId());
//		        }
              
		        if(pkg.getSupplierId()== null || !vendorrepository.existsById(pkg.getSupplierId()))
		        {
		        	throw new IllegalArgumentException("supplierId does not exist");
		        }
		        else
		        {
		        	pkg.setSupplierId(pkg.getSupplierId());
		        }
		        // Validate toCityId
//		        if (pkg.getToCityId() == null || !destinationRepository.existsById(pkg.getToCityId())) {
//		            throw new IllegalArgumentException("To City ID does not exist.");
//		        }
//		        else {
//		        
//		        // Set fromCity and toCity in Package
//		        
//		       pkg.setToCityId(pkg.getToCityId());
//
//		        }
		        
		        // Set the destinationCoveredId in pkg
		       // pkg.setDestinationCoveredId(destinationCoveredId);
 
		        // Validate each destinationCoveredId against the Destination table
//		        for (Long destinationId : pkg.getDestinationCoveredIds()) {
//		            if (!destinationRepository.existsById(destinationId)) {
//		                throw new IllegalArgumentException("Destination ID " + destinationId + " does not exist.");
//		            }
//		            else
//		            {
//		            	pkg.setDestinationCoveredId(destinationCoveredId);
//		            }
//		        }
		        
		        for(Long inclusionidi : pkg.getinclusionids())
		        {
		        	if(!inclusionrepository.existsById(inclusionidi))
		        	{
		        		throw new IllegalArgumentException("inclusion id"+inclusionidi+"does not exist");
		        	}
		        	else
		        	{
		        		pkg.setInclusionid(inclusionid);
		        	}
		        }
		        
		        for(Long exclusionidi : pkg.getexclusionids())
		        {
		        	if(!exclusionrepositiry.existsById(exclusionidi))
		        	{
		        		throw new IllegalArgumentException("inclusion id"+exclusionidi+"does not exist");
		        	}
		        	else
		        	{
		        		pkg.setExclusionid(exclusionid);
		        	}
		        }
		        
		        
		        for (Long pkgthemid : pkg.getpkthemIds()) {
		            if (!pkgthemrepository.existsById(pkgthemid)) {
		                throw new IllegalArgumentException("Destination ID " + pkgthemid + " does not exist.");
		            }
		            else
		            {
		            	 pkg.setPkthem(pkthem);
		            }
		           
		        }
			
		      //  pkg.setPkthem(pkthem);
		        
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
				
				  
				
			pkg.setPimage(imageUrls);
	//	Pkg pkgsave=	pkgService.addPkg(pkg);
		//return new ResponseEntity<>("pkg is created",HttpStatus.CREATED);
		return this.pkgService.addPkg(pkg);
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
 
		    // Generate a UUID
		    String uniqueID = UUID.randomUUID().toString();
 
		    
		    // Create a unique filename
		    return timestamp + "_" + uniqueID + extension;
		}		

		@PutMapping("/update/{id}")
		public Pkg updateHotel(@PathVariable Long id,
		                              @ModelAttribute Pkg pkg,
		                              @RequestParam(value = "image", required = false) MultipartFile[] files) throws IOException {
		    // First, fetch the existing country
		    Pkg existingPkg = pkgService.getPkgById(id);
		    
		    // Delete old images from the file system
		    if (existingPkg.getPimage() != null) {
		        for (String oldImageUrl : existingPkg.getPimage()) {
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

		    pkg.setPimage(imageUrls);
		    return pkgService.updatePkgById(id, pkg);
		}

		private boolean isValidImage1(MultipartFile file) {
		    String contentType = file.getContentType();
		    return contentType != null && (contentType.equals("image/jpeg") || contentType.equals("image/png"));
		}

		private String generateUniqueFilename1(String originalFilename) {
		    return System.currentTimeMillis() + "_" + originalFilename; // Unique filename based on current time
		}
		
		
		@GetMapping("/top-five-packages")
	    public ResponseEntity<?> getTopFivePackages(@RequestParam Long userId) {
	        Optional<User> userOpt = userRepository.findById(userId);
	 
	        if (userOpt.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body(Collections.singletonMap("error", "User not found"));
	        }
	 
	        User user = userOpt.get();
	 
			// Fetch the top 5 packages from the service based on user role (Super Admin or Normal User)
	        Map<String, Integer> topPackages = queryBookService.getTopFivePackages(user);
	 
	       
	        return ResponseEntity.ok(topPackages);
	    }
	 



	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Pkg> deletePkg(@PathVariable  long id){
		
		try
		{
			pkgService.findById(id);
			pkgService.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		finally
		{
			return ResponseEntity.notFound().build();
		}
		
	}
	
	
	
}
