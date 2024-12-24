package com.MotherSon.CRM.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MotherSon.CRM.dto.HotelPriceDTO;
import com.MotherSon.CRM.dto.Response;
import com.MotherSon.CRM.models.HotelPrice;
import com.MotherSon.CRM.security.services.HotelPriceService;

@RestController
@RequestMapping("/Motherson/crm/v1/hotelprice")
@CrossOrigin(origins = "*", maxAge = 3600)
	public class HotelPriceController {
		
		@Autowired
		private HotelPriceService hotelpriceService;
		
		
		//@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
		@PostMapping("/create")
		public ResponseEntity<?> addHotelPrice(@RequestBody HotelPrice hotelprice) {
			 HotelPrice hotelprices =hotelpriceService.addHotelPrice(hotelprice);
          return new ResponseEntity<>("Hotelprice is created",HttpStatus.CREATED);
	}
		
		
		@GetMapping("/getall")
		public List<HotelPrice> getAllHotelPrice(){
			return hotelpriceService.getAllHotelPrice();
		}
		
		
		@GetMapping("/getby/{id}")
		public ResponseEntity<HotelPrice> getHotelPriceById1(@PathVariable Long id) {
			Optional<HotelPrice> hotelprice = hotelpriceService.getHotelPriceById1(id);
			return hotelprice.map(value -> new ResponseEntity<>(value,  HttpStatus.OK))
					.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
			
		}
		
//		@GetMapping("/getbyid/{id}")
//		public ResponseEntity<Response<HotelPriceDTO>> getHotelPriceById(@PathVariable Long id) {
//		    // Call the service to fetch the HotelPriceDTO
//		    Optional<HotelPriceDTO> hotelPriceDTO = hotelpriceService.getHotelPriceById(id);
//
//		    // Return the appropriate response
//		    return hotelPriceDTO.map(dto -> {
//		        Response<HotelPriceDTO> response = new Response<>(
//		            "success",
//		            "Fetched hotel price details successfully",
//		            dto
//		        );
//		        return ResponseEntity.ok(response);
//		    }).orElseGet(() -> {
//		        Response<HotelPriceDTO> response = new Response<>(
//		            "failure",
//		            "Hotel price details not found",
//		            null
//		        );
//		        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//		    });
//		}
		
//		@GetMapping("/getbyhotelid/{hotelId}")
//		 public ResponseEntity<Response<HotelPriceDTO>> getHotelPriceByHotelId(@PathVariable Long hotelId) {
//		        Optional<HotelPriceDTO> hotelPriceDTO = hotelpriceService.getHotelPriceByHotelId(hotelId);
//
//		        return hotelPriceDTO.map(dto -> {
//		            Response<HotelPriceDTO> response = new Response<>(
//		                "success",
//		                "Fetched hotel price details successfully",
//		                dto
//		            );
//		            return ResponseEntity.ok(response);
//		        }).orElseGet(() -> {
//		            Response<HotelPriceDTO> response = new Response<>(
//		                "failure",
//		                "Hotel price details not found",
//		                null
//		            );
//		            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//		        });
//		    }

 
		@DeleteMapping("/deletebyid/{id}")
		public ResponseEntity<HotelPrice> deleteHotelPriceById(@PathVariable Long id){
			try
			{
				hotelpriceService.findById(id);
				hotelpriceService.deleteById(id);
				return ResponseEntity.noContent().build();
			}
			
			finally
			{
				return ResponseEntity.notFound().build();
			}
		}
		
 
}
