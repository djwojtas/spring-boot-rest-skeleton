package es.jemerino.springboot.rest.skeleton.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomLogoutSuccessHandler 
		extends AbstractAuthenticationTargetUrlRequestHandler 
		implements LogoutSuccessHandler {
	
	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String TOKEN_BEARER = "Bearer ";
	
	@Autowired
	private TokenStore tokenStore;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, 
			Authentication authentication) throws IOException, ServletException {
		String token = request.getHeader(AUTHORIZATION_HEADER);
		
		if(token != null && token.startsWith(TOKEN_BEARER)) {
			OAuth2AccessToken accessToken = tokenStore.readAccessToken(token.split(" ")[1]);
			
			if(accessToken != null) {
				tokenStore.removeAccessToken(accessToken);
			}
		}
		
		response.setStatus(HttpServletResponse.SC_OK);
	}

}
