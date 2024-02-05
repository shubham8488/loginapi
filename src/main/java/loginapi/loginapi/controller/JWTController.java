package loginapi.loginapi.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import loginapi.loginapi.helper.JWTUtil;
import loginapi.loginapi.model.JWTRequest;
import loginapi.loginapi.model.JWTResponse;
import loginapi.loginapi.service.CustomUserDetailService;

@RestController
@CrossOrigin
public class JWTController {
	
	@Autowired
	JWTUtil jwtutil;
	
	@Autowired
	CustomUserDetailService cud;
	
	@Autowired
	AuthenticationManager am;
	
	@PostMapping("/generateToken")
	public ResponseEntity<?> generateToken(@RequestBody JWTRequest req) {
		
		System.out.println("generateToken");
		System.out.println(req.getUsername());
		try {
			System.out.println("in try of generateToken");
			am.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
		}
		catch(Exception e) {
			System.out.println("something went wrong !!");
			//throw new Exception("Bad credential !!");
			return new ResponseEntity<>(Map.of("Message","Bad credential !!")  , HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		UserDetails user = cud.loadUserByUsername(req.getUsername());
		
		String token=jwtutil.generateToken(user);
		
		System.out.println("Token : "+token);
		//return ResponseEntity.ok(  );
		
		return new ResponseEntity<>(new JWTResponse(token), HttpStatus.OK);
	}

}
