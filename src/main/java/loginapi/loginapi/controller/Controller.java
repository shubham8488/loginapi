package loginapi.loginapi.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import loginapi.loginapi.model.LoginUser;
import loginapi.loginapi.model.SignupRequest;
import loginapi.loginapi.repository.LoginUserRepository;
import loginapi.loginapi.service.CustomUserDetailService;


@RestController
@CrossOrigin
public class Controller {
	
	@Autowired
   LoginUserRepository lur;
	
	@Autowired
	BCryptPasswordEncoder bpe;
	

	@GetMapping("/home")
	public ResponseEntity<?> home(){
		
		System.out.println("Home page api called !");
		return new ResponseEntity<>(Map.of("Message","Home page api called !"), HttpStatus.OK);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody SignupRequest user){
		
		LoginUser lu=new LoginUser();
		lu.setUsername(user.getUsername());
		lu.setPassword(bpe.encode(user.getPassword()));
		lu.setRole("USER");
		
		lur.save(lu);
		
		System.out.println("signup successful !");
		return new ResponseEntity<>(Map.of("Message","signup successful !"), HttpStatus.OK);
	}
}
