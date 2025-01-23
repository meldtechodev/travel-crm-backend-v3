package com.MotherSon.CRM.security.services;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.dto.LoginRequest;
import com.MotherSon.CRM.dto.LoginResponse;
import com.MotherSon.CRM.dto.SignupRequestDTO;
import com.MotherSon.CRM.dto.SignupRequestSuper;
import com.MotherSon.CRM.models.Company;
import com.MotherSon.CRM.models.Departments;
import com.MotherSon.CRM.models.Designations;
import com.MotherSon.CRM.models.Role;
import com.MotherSon.CRM.models.User;
import com.MotherSon.CRM.repository.BookingRepository;
import com.MotherSon.CRM.repository.CustomerRepository;
import com.MotherSon.CRM.repository.PkgRepository;
import com.MotherSon.CRM.repository.QueryBookRepository;
import com.MotherSon.CRM.repository.UserRepository;
import com.MotherSon.CRM.utils.JwtUtil;

import jakarta.transaction.Transactional;

@Service
public class UserService implements UserDetailsService  {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private QueryBookRepository queryBookRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private BookingRepository bookingRepository;
    
    @Autowired
    private PkgRepository pkgRepository;
 
    
    
    
    @Autowired
    private JwtUtil jwtutil;
    

    public UserService(UserRepository userRepository, JwtUtil jwtutil) {
		super();
		this.userRepository = userRepository;
		this.jwtutil = jwtutil;
	}


	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private ModelMapper modelMapper;  // Inject ModelMapper

    public String registerUser(SignupRequestDTO signupRequestDTO) {
        // Check if email already exists
        if (userRepository.existsByEmail(signupRequestDTO.getEmail())) {
            return "Email is already in use.";
        }

        // Map DTO to User entity using ModelMapper
        User user = modelMapper.map(signupRequestDTO, User.class);

        // Encrypt the password
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // Set additional fields
        //user.setCreatedDate(LocalDateTime.now());
       // user.setStatus(true);  // Assume the user is active by default

        // Save user to the database
        userRepository.save(user);

        return "User registered successfully!";
    }
    
    public String registersuperadmin(SignupRequestSuper signupRequestsuper) {
        // Check if email already exists
        if (userRepository.existsByEmail(signupRequestsuper.getEmail())) {
            return "Email is already in use.";
        }
 
        // Map DTO to User entity using ModelMapper
        User user = modelMapper.map(signupRequestsuper, User.class);
 
        // Encrypt the password
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
 
        // Set additional fields
        user.setCreatedDate(LocalDateTime.now());
        user.setStatus(true);  // Assume the user is active by default
        user.setCreatedBy("Narender");
        user.setModifiedBy("Narender");
        // Set hardcoded company object
        Company company = new Company();
        company.setId(1L); // Hardcoded ID
        user.setCompany(company);
 
        // Set hardcoded department object
        Departments department = new Departments();
        department.setId(1L); // Hardcoded ID
        user.setDepartment(department);
        
        Designations designation=new Designations();
        designation.setId(1L);
        user.setDesignation(designation);
        // Set hardcoded role object (optional, if needed)
        Role role = new Role();
        role.setId(1L); // Hardcoded ID
        user.setRole(role);
        
      //  user.setDepartmentId(user.getDepartmentId());
        user.setIsdelete(false);
        
 
        // Save user to the database
        userRepository.save(user);
 
        return "super admin registered successfully!";
    }
     
    
 
    
    public LoginResponse login(LoginRequest request) {
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                // Generate JWT token
                String token = jwtutil.generateToken(request.getEmail());
                return new LoginResponse(token);
            }
        }

        throw new RuntimeException("Invalid Email or Password");
    }
    public List<User>getalluser()
    {
    	 List<User>getuserser=userRepository.findAll();
		return getuserser;
    	
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		  User user = userRepository.findByEmail(username)
	                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

	        return org.springframework.security.core.userdetails.User
	                .withUsername(user.getEmail())
	                .password(user.getPassword())
	                .authorities("Super_Admin")
	                .build();
	    }
	
	
	public User getNameFromEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
        return user; // Assuming your User entity has a `name` field
    }
	
	
	
	@Transactional
    public String updateUserById(Long userId, SignupRequestDTO signupRequestDTO) {
        // Check if user exists
        User existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser == null) {
            return "User not found";
        }
 
        // Map the updated fields from DTO to the existing User entity
        modelMapper.map(signupRequestDTO, existingUser);
 
        // If the password is updated, re-encode it
        if (signupRequestDTO.getPassword() != null && !signupRequestDTO.getPassword().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(signupRequestDTO.getPassword());
            existingUser.setPassword(encodedPassword);
        }
 
        // Save updated user to the database
        userRepository.save(existingUser);
 
        return "User updated successfully";
    }
	
	public long getTotalUser() {
        return userRepository.countTotalUser();
    }
 
    // Get the total number of active bookings where status = true
    public long getActiveUser() {
        return userRepository.countActiveUser();
    }

//    public Map<String, Long> getAllStats(Long userId) {
//        
//        Map<String, Long> stats = new LinkedHashMap<>();
// 
//        // For Super Admin (userId = 1) - Aggregated Stats for All Users
//        if (userId == 1) {
//            stats.put("totalBookings", bookingRepository.count());
//            stats.put("activeBookings", bookingRepository.countByStatusTrue());
//            stats.put("totalQueryBooks", queryBookRepository.count());
//            stats.put("activeQueryBooks", queryBookRepository.countByLeadStatusTrue());
//            stats.put("totalCustomers", customerRepository.count());
//            stats.put("activeCustomers", customerRepository.countByStatusTrue());
//            stats.put("totalUsers", userRepository.countByIsdeleteFalse());
//            stats.put("activeUsers", userRepository.countByStatusTrueAndIsdeleteFalse());
//        } else {
//            // For Regular User (userId != 1) - Stats Specific to the Logged-in User
//            stats.put("totalBookings", bookingRepository.countByBookingByuserId_UserId(userId));
//            stats.put("activeBookings", bookingRepository.countByBookingByuserId_UserIdAndStatusTrue(userId));
//            stats.put("totalQueryBooks", queryBookRepository.countByUseridUserId(userId));
//            stats.put("activeQueryBooks", queryBookRepository.countByUseridUserIdAndLeadStatusTrue(userId));
//            stats.put("totalCustomers", customerRepository.countByUser_UserId(userId));
//            stats.put("activeCustomers", customerRepository.countByUser_UserIdAndStatusTrue(userId));
//            stats.put("totalUsers", 1L); // Only the logged-in user
//            stats.put("activeUsers", 1L); // Assuming logged-in user is active
//        }
// 
//        return stats;
//    }
    
    
    public Map<String, Object> getStats(Long userId, Long superAdminId) {
        Map<String, Object> stats = new LinkedHashMap<>();
 
        // If superAdminId is provided, get the superAdmin stats
        if (superAdminId != null) {
            stats.putAll(getSuperAdminStats());  // Directly add superAdmin stats to the map
        }
        
        else if (userId != null) {
            stats.putAll(getUserStats(userId));  // Directly add user stats to the map
        }
 
        return stats;
    }
 
    // Fetch stats specific to the superAdmin
    private Map<String, Object> getSuperAdminStats() {
        Map<String, Object> stats = new LinkedHashMap<>();
 
        stats.put("totalBookings", bookingRepository.count());
        stats.put("activeBookings", bookingRepository.countByBookingStatus("confirmed"));
        stats.put("totalQuery", queryBookRepository.count());
        stats.put("activeQuery", queryBookRepository.countByLeadStatusTrue());
        stats.put("totalCustomers", customerRepository.count());
        stats.put("activeCustomers", customerRepository.countByStatusTrue());
        stats.put("totalLeads", queryBookRepository.countByLeadSourceIsNotNull());
 
        // Fetch lead source counts for superAdmin
        Map<String, Integer> leadSourceCounts = getLeadSourceCountsForSuperAdmin();
        stats.putAll(leadSourceCounts);  // Add lead source counts to the stats
 
        return stats;
    }
 
    // Fetch lead source counts for superAdmin
//    private Map<String, Integer> getLeadSourceCountsForSuperAdmin() {
//        List<Object[]> result = queryBookRepository.findLeadSourceCounts();
// 
//        Map<String, Integer> leadSourceCountMap = new HashMap<>();
//        for (Object[] row : result) {
//            String leadSource = (String) row[0];
//            Long count = (Long) row[1];
//            leadSourceCountMap.put(leadSource, count.intValue());
//        }
// 
//        return leadSourceCountMap;
//    }
// 
    // Fetch stats specific to the user
    private Map<String, Object> getUserStats(Long userId) {
        Map<String, Object> stats = new LinkedHashMap<>();
 
        stats.put("totalBookings", bookingRepository.countByBookingByuserId_UserId(userId));
        stats.put("activeBookings", bookingRepository.countByBookingByuserId_UserIdAndBookingStatus(userId, "confirmed"));
        stats.put("totalQuery", queryBookRepository.countByUseridUserId(userId));
        stats.put("activeQuery", queryBookRepository.countByUseridUserIdAndLeadStatusTrue(userId));
        stats.put("totalCustomers", customerRepository.countByUser_UserId(userId));
        stats.put("activeCustomers", customerRepository.countByUser_UserIdAndStatusTrue(userId));
        stats.put("totalLeads", queryBookRepository.countByUseridUserIdAndLeadSourceIsNotNull(userId));
 
        // Fetch lead source counts for the user
        Map<String, Integer> leadSourceCounts = getLeadSourceCountsForUser(userId);
        stats.putAll(leadSourceCounts);  // Add lead source counts to the stats
 
        return stats;
    }
 
    // Fetch lead source counts for the user
//    private Map<String, Integer> getLeadSourceCountsForUser(Long userId) {
//        List<Object[]> result = queryBookRepository.findLeadSourceCountsForUser(userId);
// 
//        Map<String, Integer> leadSourceCountMap = new HashMap<>();
//        for (Object[] row : result) {
//            String leadSource = (String) row[0];
//            Long count = (Long) row[1];
//            leadSourceCountMap.put(leadSource, count.intValue());
//        }
// 
//        return leadSourceCountMap;
//    }

    public Map<String, Object> getDashboardStats(Long userId) {
        Map<String, Object> response = new HashMap<>();
 
        // Fetch the user by userId
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            response.put("error", "User not found");
            return response;
        }
 
        User user = userOpt.get();
        Role role = user.getRole();
 
        
        if (role == null) {
            response.put("error", "Role not assigned to user");
            return response;
        }
 
        
        Map<String, Object> stats = new LinkedHashMap<>();
 
        // Check if the role is SuperAdmin (role_name = "SuperAdmin")
        if (role.getRoleName().equalsIgnoreCase("Super Admin")) {
            // Fetch global stats for SuperAdmin
            stats.put("totalBookings", bookingRepository.count());
            stats.put("activeBookings", bookingRepository.countByBookingStatus("confirmed"));
            stats.put("totalQuery", queryBookRepository.count());
            stats.put("activeQuery", queryBookRepository.countByLeadStatusTrue());
            stats.put("totalCustomers", customerRepository.countTotalCustomer());
            stats.put("activeCustomers", customerRepository.countActiveCustomer());
            stats.put("totalLeads", queryBookRepository.countByLeadSourceIsNotNull());
 
            // Fetch lead source counts for SuperAdmin
            Map<String, Integer> leadSourceCounts = getLeadSourceCountsForSuperAdmin();
            stats.putAll(leadSourceCounts);  // Add lead source counts to the stats
 
        } else {
            
        	stats.put("totalBookings", bookingRepository.countByBookingByuserId_UserId(userId));
            stats.put("activeBookings", bookingRepository.countByBookingByuserId_UserIdAndBookingStatus(userId, "confirmed"));
            stats.put("totalQuery", queryBookRepository.countByUseridUserId(userId));
            stats.put("activeQuery", queryBookRepository.countByUseridUserIdAndLeadStatusTrue(userId));
            stats.put("totalCustomers", customerRepository.countByUser_UserId(userId));
            stats.put("activeCustomers", customerRepository.countByUser_UserIdAndStatusTrue(userId));
            stats.put("totalLeads", queryBookRepository.countByUseridUserIdAndLeadSourceIsNotNull(userId));
 
            // Fetch lead source counts for the specific user
            Map<String, Integer> leadSourceCounts = getLeadSourceCountsForUser(userId);
            stats.putAll(leadSourceCounts);  // Add lead source counts to the stats
        }
 
        return stats;
    }
 
    // Fetch lead source counts for SuperAdmin (all users)
    private Map<String, Integer> getLeadSourceCountsForSuperAdmin() {
        // Fetch lead source counts for all users (SuperAdmin)
        List<Object[]> result = queryBookRepository.findLeadSourceCounts();
 
        Map<String, Integer> leadSourceCountMap = new HashMap<>();
 
        
        for (Object[] row : result) {
            String leadSource = (String) row[0];
            Long count = (Long) row[1];
            leadSourceCountMap.put(leadSource, count.intValue());
        }
 
        return leadSourceCountMap;
    }
 
    
    private Map<String, Integer> getLeadSourceCountsForUser(Long userId) {
       
        List<Object[]> result = queryBookRepository.findLeadSourceCountsForUser(userId);
 
        Map<String, Integer> leadSourceCountMap = new HashMap<>();
 
      
        for (Object[] row : result) {
            String leadSource = (String) row[0];
            Long count = (Long) row[1];
            leadSourceCountMap.put(leadSource, count.intValue());
        }
 
        return leadSourceCountMap;
    }
    
    
    public Map<String, Object> getTopPackagesAndDestinations(User user) {
	    Map<String, Object> responseMap = new LinkedHashMap<>();
 
	    
	    Map<String, Integer> topPackages = getTopPackages(user);
	    responseMap.put("topFivePackages", topPackages);
 
	    
	    Map<String, Integer> topDestinations = getTopDestinations(user);
	    responseMap.put("topTenDestinations", topDestinations);
 
	    return responseMap;
	}
 
	public Map<String, Integer> getLeadSources(User user) {
	    Map<String, Integer> leadSourceMap = new LinkedHashMap<>();
 
	    
	    if (user.getRole().getRoleName().equalsIgnoreCase("Super Admin")) {
	        
	        List<Object[]> leadSources = queryBookRepository.findLeadSourcesForSuperAdmin();
	        
	        
	        for (Object[] result : leadSources) {
	            String leadSource = (String) result[0];  
	            Integer count = ((Long) result[1]).intValue();
	            leadSourceMap.put(leadSource, count);
	        }
	    } else {
	        // Fetch lead sources specific to the user
	        List<Object[]> leadSources = queryBookRepository.findLeadSourcesForUser(user.getUserId());
 
	        
	        for (Object[] result : leadSources) {
	            String leadSource = (String) result[0];  
	            Integer count = ((Long) result[1]).intValue();
	            leadSourceMap.put(leadSource, count);
	        }
	    }
 
	    return leadSourceMap;
	}
 
	private Map<String, Integer> getTopPackages(User user) {
	    Map<String, Integer> packageCountMap = new LinkedHashMap<>();
 
	    if (user.getRole().getRoleName().equalsIgnoreCase("Super_Admin")) {
	      
	        List<Object[]> topPackages = queryBookRepository.findTopFivePackagesForSuperAdmin();
 
	      
	        for (int i = 0; i < Math.min(topPackages.size(), 5); i++) {
	            Object[] result = topPackages.get(i);
	            Long packageId = (Long) result[0];  
	            Integer count = ((Long) result[1]).intValue();
	            packageCountMap.put("Package " + packageId, count);
	        }
	    } else {
	       
	        List<Object[]> topPackages = queryBookRepository.findTopFivePackagesForUser(user.getUserId());
 
	        
	        for (int i = 0; i < Math.min(topPackages.size(), 5); i++) {
	            Object[] result = topPackages.get(i);
	            Long packageId = (Long) result[0];  
	            Integer count = ((Long) result[1]).intValue();
	            packageCountMap.put("Package " + packageId, count);
	        }
	    }
 
	    return packageCountMap;
	}
 
	private Map<String, Integer> getTopDestinations(User user) {
	    Map<String, Integer> destinationCountMap = new LinkedHashMap<>();
 
	    if (user.getRole().getRoleName().equalsIgnoreCase("Super_Admin")) {
	       
	        List<Object[]> topDestinations = queryBookRepository.findTopDestinationsForSuperAdmin();
 
	        
	        for (int i = 0; i < Math.min(topDestinations.size(), 10); i++) {
	            Object[] result = topDestinations.get(i);
	            String destinationName = (String) result[0];  // Destination name
	            Integer count = ((Long) result[1]).intValue();
	            destinationCountMap.put(destinationName, count);
	        }
	    } else {
	        
	        List<Object[]> topDestinations = queryBookRepository.findTopDestinationsByUser(user.getUserId());
 
	        
	        for (int i = 0; i < Math.min(topDestinations.size(), 10); i++) {
	            Object[] result = topDestinations.get(i);
	            String destinationName = (String) result[0];  
	            Integer count = ((Long) result[1]).intValue();
	            destinationCountMap.put(destinationName, count);
	        }
	    }
 
	    return destinationCountMap;
	}
	
	public Map<String, Object> getTopStatesForUsers(Long userId) {
        // Find the user by ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
 
        // Check if the user has the "Super_Admin" role
        if (user.getRole().getRoleName().equalsIgnoreCase("Super Admin")) {
            // Fetch and return the top states for users
            return getTopStates();
        }
 
        // If the user is not a Super Admin, return null
        return null;
    }
 
    private Map<String, Object> getTopStates() {
        Map<String, Object> resultMap = new LinkedHashMap<>();
 
        // Fetch the top states grouped by user, state, and country with counts
        List<Object[]> results = queryBookRepository.getTopStatesForUsers();
 
        // Process the results and build the map
        for (Object[] result : results) {
            String userName = (String) result[0];  // User's name from the query
            String stateName = (String) result[1]; // State name from the query
            String countryName = (String) result[2]; // Country name from the query
            Long destinationCount = (Long) result[3]; // Count of destinations in that state and country
 
            // Initialize the user map if not already created
            resultMap.putIfAbsent(userName, new LinkedHashMap<String, Object>());
 
            // Initialize the domestic and international maps if not already created for the user
            Map<String, Object> categories = (Map<String, Object>) resultMap.get(userName);
            if (categories == null) {
                categories = new LinkedHashMap<>();
                resultMap.put(userName, categories);
            }
 
            // Ensure "domestic" and "international" are correctly initialized as Map<String, Integer>
            Map<String, Integer> domesticMap = (Map<String, Integer>) categories.get("domestic");
            if (domesticMap == null) {
                domesticMap = new LinkedHashMap<>();
                categories.put("domestic", domesticMap);
            }
 
            Map<String, Integer> internationalMap = (Map<String, Integer>) categories.get("international");
            if (internationalMap == null) {
                internationalMap = new LinkedHashMap<>();
                categories.put("international", internationalMap);
            }
 
            // If the country is India, it's domestic; otherwise, it's international
            if ("India".equals(countryName)) {
                // Add to the domestic map for the user (state-wise count)
                domesticMap.put(stateName, destinationCount.intValue());
            } else {
                // Add to the international map for the user (country-wise count)
                internationalMap.merge(countryName, destinationCount.intValue(), Integer::sum);
            }
        }
 
        // Aggregating the count of "India" under international from the domestic states
        resultMap.forEach((userName, categories) -> {
            Map<String, Integer> domesticMap = (Map<String, Integer>) ((HashMap<String,Object>) categories).get("domestic");
            int domesticCount = domesticMap.values().stream().mapToInt(Integer::intValue).sum();
            
            // Add the total count of India under international (if not already present)
            Map<String, Integer> internationalMap = (Map<String, Integer>) ((HashMap<String,Object>) categories).get("international");
            internationalMap.put("India", domesticCount);
        });
 
        return resultMap;
    }
    
    
    public Map<String, Map<String, Object>> getSupplierReportsIfSuperAdmin(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getRole().getRoleName().equalsIgnoreCase("Super Admin")) {
            return getPackageDetailsGroupedByVendor();
        }

        throw new RuntimeException("Access denied: User is not a Super Admin");
    }
    public Map<String, Map<String, Object>> getPackageDetailsGroupedByVendor() {
        List<Object[]> results = pkgRepository.getPackageDetailsWithVendorAndDestination();

        Map<String, Map<String, Object>> vendorPackageDetails = new HashMap<>();

        for (Object[] result : results) {
            String vendorName = (String) result[0];
            String packageName = (String) result[1];
            String destinationName = (String) result[2];
            String packageType = (String) result[3];

            
            vendorPackageDetails.putIfAbsent(vendorName, new HashMap<>());
            Map<String, Object> vendorData = vendorPackageDetails.get(vendorName);

            
            vendorData.putIfAbsent("total_package", 0);
            vendorData.putIfAbsent("packages_name", new HashSet<String>());
            vendorData.putIfAbsent("destinations", new HashSet<String>());
            vendorData.putIfAbsent("package_type", new HashSet<String>());

            vendorData.put("total_package", (int) vendorData.get("total_package") + 1);

            Set<String> packages = (Set<String>) vendorData.get("packages_name");
            packages.add(packageName);

           
            Set<String> destinations = (Set<String>) vendorData.get("destinations");
            if (destinationName != null) {
                destinations.add(destinationName);
            }

            
            Set<String> packageTypes = (Set<String>) vendorData.get("package_type");
            packageTypes.add(packageType);
        }

        return vendorPackageDetails;
    }

 
 

 
}
 
 
	
    
    
    
    

