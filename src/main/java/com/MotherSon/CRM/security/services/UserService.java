package com.MotherSon.CRM.security.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
import com.MotherSon.CRM.models.User;
import com.MotherSon.CRM.repository.UserRepository;
import com.MotherSon.CRM.utils.JwtUtil;

import jakarta.transaction.Transactional;

@Service
public class UserService implements UserDetailsService  {

    @Autowired
    private UserRepository userRepository;
    
    
    
    
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
    
//    public String registersuperadmin(SignupRequestSuper signupRequestsuper) {
//        // Check if email already exists
//        if (userRepository.existsByEmail(signupRequestsuper.getEmail())) {
//            return "Email already exists!";
//        }
//
//        // Map DTO to User entity using ModelMapper
//        User user = modelMapper.map(signupRequestsuper, User.class);
//
//        // Encrypt the password
//        String encodedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);
//
//        // Set additional fields
//        user.setCreatedDate(LocalDateTime.now());
//        user.setStatus(true);  // Assume the user is active by default
//        user.setCreatedBy("Narender");
//        user.setModifiedBy("Narender");
//      //  user.setDepartmentId(user.getDepartmentId());
//        user.setIsdelete(false);
//        
//
//        // Save user to the database
//        userRepository.save(user);
//
//        return "Super Admin Registered Successfully!";
//    }
    
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
	
	}
    
    
    
    

