package loginapi.loginapi.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import loginapi.loginapi.helper.JWTUtil;
import loginapi.loginapi.service.CustomUserDetailService;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JWTUtil jwtutil;

	@Autowired
	private CustomUserDetailService cud;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String requesttokenheader = request.getHeader("Authorization");
		String username = null;
		String jwttoken = null;

		System.out.println("requesttokenheader : " + requesttokenheader);

		if (requesttokenheader != null && requesttokenheader.startsWith("Bearer")) {
			
			UserDetails user=null;
			jwttoken = requesttokenheader.substring(7);

			try {
				username = jwtutil.extractUsername(jwttoken);
				user = cud.loadUserByUsername(username);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Exception in doFilterInternal : " + e.getMessage());
			}

			 
			
			
			
			if(  user!=null && jwtutil.validateToken(jwttoken, user)) {
				if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							user, null, user.getAuthorities());
					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

				} else {
					System.out.println("Username null or SecurityContext null : " + username);
				}
			}
			


		}

		filterChain.doFilter(request, response);

	}

}
