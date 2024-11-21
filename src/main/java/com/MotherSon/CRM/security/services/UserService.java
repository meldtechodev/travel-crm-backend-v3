package com.MotherSon.CRM.security.services;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.dto.SignupRequestDTO;
import com.MotherSon.CRM.dto.SignupRequestSuper;
import com.MotherSon.CRM.models.User;
import com.MotherSon.CRM.repository.RoleRepository;
import com.MotherSon.CRM.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;

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
        

        if(userRepository.existsById(1)) {
        	return "Super Admin already exist";
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
        user.setCompanyId(1);
        user.setDesignationId(1);
        user.setDepartmentId(1);
        user.setRoleId(1);
        user.setIsdelete(false);
        
 
        // Save user to the database
        userRepository.save(user);
 
        return "super admin registered successfully!";
    }
    
 
}
