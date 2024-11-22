package com.MotherSon.CRM.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MotherSon.CRM.dto.LoginRequest;
import com.MotherSon.CRM.dto.LoginResponse;
import com.MotherSon.CRM.dto.SignupRequestDTO;
import com.MotherSon.CRM.dto.SignupRequestSuper;
import com.MotherSon.CRM.models.User;
import com.MotherSon.CRM.security.services.UserService;
import com.MotherSon.CRM.utils.JwtUtil;

@RestController
@RequestMapping("Motherson/crm/v1")
public class UserController {

    @Autowired
    private UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
		super();
		this.userService = userService;
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}

	// Signup endpoint - open for all (no authentication required)
    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequestDTO signupRequestDTO) {
        return userService.registerUser(signupRequestDTO);
    }
    
//    @PostMapping("/signupsuperadmin")
//    public String signupsuperadimncon(@RequestBody SignupRequestSuper signupRequestsuper) {
//        return userService.registersuperadmin(signupRequestsuper);
//    }
    
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtUtil.generateToken(request.getEmail());
    }

    
    @GetMapping("/usergetall")
    public List<User> getalluser()
    {
    	List<User>getuserco=userService.getalluser();
		return getuserco;
    	
    }
    
    
    
    
    
}
