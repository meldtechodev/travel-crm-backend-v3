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
import org.springframework.security.access.prepost.PreAuthorize;
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

//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import java.util.UUID;
//
////import javax.validation.Valid;
//
////import javax.naming.Binding;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;

import com.MotherSon.CRM.models.Country;
//import com.ms.jwt.service.country.CountryService;
import com.MotherSon.CRM.security.services.CountryService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("Motherson/crm/v1/country")
public class Controller {

	@Autowired
	private CountryService countryService;

//	@GetMapping("/getall")
//	public ResponseEntity<List<Country>> getAllCountries() {
//		List<Country> countries = countryService.getAllCountries();
//		return new ResponseEntity<>(countries, HttpStatus.OK);
//	}
	
	@GetMapping("/getall")
	public Page<Country> getCountry(
			@RequestParam(value = "page" , defaultValue = "0") int page,
			@RequestParam(value = "size" , defaultValue = "10") int size,
			@RequestParam(value = "sortDirection" , defaultValue = "asc") String sortDirection
			){
		return countryService.getCountry(page, size, sortDirection);
	}
 

	    @GetMapping("/getid/{id}")
	    public ResponseEntity<Country> getCountrysById(@PathVariable Long id) {
	        Optional<Country> country = countryService.getCountrysById(id);
	        return country.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
	                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	    }

//	   
	private String timestamp;

	public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/image/countryimages";
//	 @PreAuthorize("hasAuthority('Super_Admin')")
	@PostMapping("/create")
	public ResponseEntity<?> saveCountry(@Valid @ModelAttribute Country country,BindingResult result, @RequestParam("image") MultipartFile[] files )
			throws IOException {
		      
		//handle validator error
		 if (result.hasErrors()) {
		        Map<String, String> errors = new HashMap<>();
		        result.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		        System.out.print("Not corrextsssssssssssss");
		        return ResponseEntity.badRequest().body(errors);
		    }
		 
		 if (countryService.existsByCountryName(country.getCountryName())) {
	            return ResponseEntity.status(HttpStatus.CONFLICT)
	                    .body("Country with the name " + country.getCountryName() + " already exists.");
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

			//// Get the upload date formatted
			// String uploadDate = LocalDate.now().format(formatter);
			// String originalFilename = file.getOriginalFilename();

			// Generate a unique file name

			// String uniqueFilename = generateUniqueFilename(uploadDate, originalFilename,
			// timestamp);
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

		country.setCimage(imageUrls);
//		 Country savedCountry = countryService.addCountry(country);
//	        return ResponseEntity.status(HttpStatus.CREATED).body(savedCountry);
		return ResponseEntity.ok(this.countryService.addCountry(country));
		//return this.countryService.addCountry(country);
	}

	// private String generateUniqueFilename(String uploadDate, String
	// originalFilename) {
	// TODO Auto-generated method stub
	// return null;
	// }

	// private boolean isValidImage(List<MultipartFile> file) {

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

		// Create a unique filename

		// return timestamp + "_" + uniqueID + extension;
		// return uploadDate + "_" + uniqueID + extension; // e.g.,
		// "14-12-2024_123e4567-e89b-12d3-a456-426614174000.jpg"
		return timestamp + "_" + uniqueID + extension;
	}

	@PutMapping("/updatebyid/{id}")
	public Country updateCountry(@PathVariable Long id, @ModelAttribute Country country,
			@RequestParam(value = "image", required = false) MultipartFile[] files) throws IOException {
		// First, fetch the existing country
		Country existingCountry = countryService.getCountryById(id);

		// Delete old images from the file system
		if (existingCountry.getCimage() != null) {
			for (String oldImageUrl : existingCountry.getCimage()) {
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

		country.setCimage(imageUrls);
		return countryService.updateCountryById(id, country);
	}

	private boolean isValidImage1(MultipartFile file) {
		String contentType = file.getContentType();
		return contentType != null && (contentType.equals("image/jpeg") || contentType.equals("image/png"));
	}

	private String generateUniqueFilename1(String originalFilename) {
		return System.currentTimeMillis() + "_" + originalFilename; // Unique filename based on current time
	}

	@DeleteMapping("/deleteby/{id}")
	public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
		countryService.deleteCountry(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
