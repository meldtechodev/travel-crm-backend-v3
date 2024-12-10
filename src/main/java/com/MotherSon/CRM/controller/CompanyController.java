package com.MotherSon.CRM.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
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

import com.MotherSon.CRM.models.Company;
import com.MotherSon.CRM.repository.CompanyRepository;
import com.MotherSon.CRM.security.services.CompanyService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("Motherson/crm/v1/company")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CompanyController {
    
	@Autowired
	private CompanyService companyservice;
	
	//@PostMapping("/create")
//	public Company createcontroller(@RequestBody Company company)
//	{
//		
//		    Company finaldatasave=companyservice.createcompanyser(company);
//		     return finaldatasave;
//		
//	}
//	
	
	private String timestamp;
 
	public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/image/countryimages";
	// @PreAuthorize("hasAuthority('Super_Admin')")
	@PostMapping("/create")
	public ResponseEntity<?> saveCountry( @Valid @ModelAttribute Company company,BindingResult bindingResult, @RequestParam("image") MultipartFile[] files )
			throws IOException {
		      
		//handle validator error
		
		
		 if (bindingResult.hasErrors()) {
		        Map<String, String> errors = new HashMap<>();
		        bindingResult.getFieldErrors().forEach(error -> {
		            errors.put(error.getField(), error.getDefaultMessage());
		        });
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		    }
		
		
		
		
		
		
		
		List<String> imageUrls = new ArrayList<>();
 
		// String timestamp =
		// LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HHmm"));
		// String timestamp =
		// LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmm"));
		// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");//
		// for date
 
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
			
			Path fileNameAndPath = Paths.get(uploadDirectory, uniqueFilename);
			Files.write(fileNameAndPath, file.getBytes());
 
			
			String imageUrl = "/image/" + uniqueFilename;
			;
			imageUrls.add(imageUrl);
		}
 
		company.setCompanylogo(imageUrls);
//		
		return ResponseEntity.ok(this.companyservice.createcompanyser(company));
		//return this.countryService.addCountry(country);
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
 
//	@GetMapping("/getall")
//	public List<Company> getallcompanycon()
//	{
//		
//		List<Company>getallcompc=companyservice.getallcompanyser();
//		return getallcompc;
//		
//	}
	
	
	@GetMapping("/getall")
	public Page<Company> getCompany(
			@RequestParam(value = "page" , defaultValue = "0") int page,
			@RequestParam(value = "size" , defaultValue = "10") int size,
			@RequestParam(value = "sortDirection" ,defaultValue = "asc") String sortDirection
			){
		return companyservice.getCompany(page, size, sortDirection);
	}
	
	
	
	
	@GetMapping("/getbyid/{id}")
	public Optional<Company> getcompanybyidcon(@PathVariable Long id)
	{
		
		 Optional<Company> getcompanybyidc=companyservice.getcompanybyid(id);
		
		return getcompanybyidc;
		
	}
	
//	@PutMapping("updatebyid/{id}")
//	public Company updatebyidcon(@RequestBody Company company,@PathVariable Long id)
//	{
//		     Company updatecombyid=companyservice.updatebyid(company, id);
//		return updatecombyid;
//		
//	}
	
	@PutMapping("/updateby/{id}")
	public Company updateCountry(@PathVariable Long id, @ModelAttribute Company company,
			@RequestParam(value = "image", required = false) MultipartFile[] files) throws IOException {
		// First, fetch the existing country
		Company existingCountry = companyservice.getcompanysbyid(id);
 
		// Delete old images from the file system
		if (existingCountry.getCompanylogo() != null) {
			for (String oldImageUrl : existingCountry.getCompanylogo()) {
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
 
		company.setCompanylogo(imageUrls);
		return companyservice.updatebyid(company, id);
	}
 
	private boolean isValidImage1(MultipartFile file) {
		String contentType = file.getContentType();
		return contentType != null && (contentType.equals("image/jpeg") || contentType.equals("image/png"));
	}
 
	private String generateUniqueFilename1(String originalFilename) {
		return System.currentTimeMillis() + "_" + originalFilename; // Unique filename based on current time
	}
	
	  @DeleteMapping("deletebyid/{id}")
	    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
	        try {
	        	companyservice.deleteById(id);
	            return ResponseEntity.ok("Company with ID " + id + " deleted successfully.");
	        } catch (EntityNotFoundException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	        }
	    }
	
	
	
	
	
}