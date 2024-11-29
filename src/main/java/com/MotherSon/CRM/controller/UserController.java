package com.MotherSon.CRM.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
@CrossOrigin(origins = "*", maxAge = 3600)
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

    // @PostMapping("/signupsuperadmin")
    // public String signupsuperadimncon(@RequestBody SignupRequestSuper
    // signupRequestsuper) {
    // return userService.registersuperadmin(signupRequestsuper);
    // }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtUtil.generateToken(request.getEmail());
    }
    
    @PutMapping("/updatebyid/{userId}")
    public String updateUser(@PathVariable Long userId, @RequestBody SignupRequestDTO signupRequestDTO) {
        return userService.updateUserById(userId, signupRequestDTO);
    }
 

    @GetMapping("/usergetall")
    public List<User> getalluser() {
        List<User> getuserco = userService.getalluser();
        return getuserco;

    }
    

    @GetMapping("/username")
    public User getNameFromToken(@RequestHeader("Authorization") String token) {
        // Remove "Bearer " prefix if present
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        // Extract email from the token
        String email = jwtUtil.extractUsername(token);

//        String user = jwtUtil.extractUsername(token);

        // Retrieve the name associated with the email
        return userService.getNameFromEmail(email);
    }
    
	@GetMapping("/ipAddress")
    public String getIpAddress() {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            return ip.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "Unable to fetch IP Address";
        }
    }

}
