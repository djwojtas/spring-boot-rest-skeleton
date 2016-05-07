package es.jemerino.springboot.rest.skeleton.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import es.jemerino.springboot.rest.skeleton.constant.Mapping;
import es.jemerino.springboot.rest.skeleton.constant.Properties;
import es.jemerino.springboot.rest.skeleton.security.Authorities;
import es.jemerino.springboot.rest.skeleton.security.CustomAuthenticationEntryPoint;
import es.jemerino.springboot.rest.skeleton.security.CustomLogoutSuccessHandler;

@Configuration
public class OAuth2Configuration {

	@Configuration
	@EnableResourceServer
	protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

		@Autowired
		private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

		@Autowired
		private CustomLogoutSuccessHandler customLogoutSuccessHandler;

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http
				.exceptionHandling()
				.authenticationEntryPoint(customAuthenticationEntryPoint)
				.and()
				.logout()
				.logoutUrl(Mapping.LOGOUT)
				.logoutSuccessHandler(customLogoutSuccessHandler)
				.and()
				.csrf()
				.requireCsrfProtectionMatcher(new AntPathRequestMatcher(Mapping.AUTHORIZE))
				.disable()
				.headers()
				.frameOptions()
				.disable()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				.antMatchers(Mapping.SECURE_PATH).authenticated();
		}

	}

	@Configuration
	@EnableAuthorizationServer
	protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

		@Value(Properties.PROP_CLIENTID)
		private String clientId;

		@Value(Properties.PROP_SECRET)
		private String clientSecret;

		@Value(Properties.PROP_GRANT_TYPES)
		private String[] grantTypes;

		@Value(Properties.PROP_SCOPES)
		private String[] scopes;

		@Value(Properties.PROP_TOKEN_VALIDITY_SECONDS)
		private Integer tokenValiditySeconds;

		@Autowired
		private DataSource dataSource;

		@Bean
		public TokenStore tokenStore() {
			return new JdbcTokenStore(dataSource);
		}

		@Autowired
		private AuthenticationManager authenticationManager;

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager);
		}

		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			clients
				.inMemory()
				.withClient(clientId)
				.scopes(scopes)
				.authorities(Authorities.ROLE_USER.name())
				.authorizedGrantTypes(grantTypes)
				.secret(clientSecret)
				.accessTokenValiditySeconds(tokenValiditySeconds);
		}

	}

}
