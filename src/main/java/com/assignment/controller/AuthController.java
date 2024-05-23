package com.assignment.controller;
import com.assignment.config.JwtHelper;
import com.assignment.entities.UserCredentials;
import com.assignment.response.JwtResponse;
import com.assignment.response.UnAuthorizedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private JwtHelper jwtHelper;

        @Autowired
        private UserDetailsService userDetailsService;
private boolean authenticatedUser=false;
        @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody UserCredentials userCredentials) {
            try {
                Authentication authenticate = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(userCredentials.getEmail(), userCredentials.getPassword())
                );
                authenticatedUser=true;
               if(authenticatedUser){
                   UserDetails userDetails = userDetailsService.loadUserByUsername(userCredentials.getEmail());
                   System.out.println("userDetails : "+userDetails);
                   String jwt = jwtHelper.generateToken(userDetails);
                   System.out.println("meraj : "+jwt);
                   JwtResponse jwtResponse= new JwtResponse();
                   jwtResponse.setJwt(jwt);
                   jwtResponse.setMessage("Login Succefully");
                   return new ResponseEntity<>(jwtResponse,HttpStatus.OK);
               }
            } catch (AuthenticationException e) {
                UnAuthorizedResponse unAuthorizedResponse = new UnAuthorizedResponse();
                unAuthorizedResponse.setMessage("UnAuthorized User Enter correct username or password");
                return new ResponseEntity<>(unAuthorizedResponse,HttpStatus.UNAUTHORIZED);
            }
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }


