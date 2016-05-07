package es.jemerino.springboot.rest.skeleton.constant;

public class Properties {
	
	public static final String ENV_OAUTH = "authentication.oauth.";
	public static final String PROP_CLIENTID = ENV_OAUTH + "clientid";
	public static final String PROP_SECRET = ENV_OAUTH + "secret";
	public static final String PROP_TOKEN_VALIDITY_SECONDS = ENV_OAUTH + "tokenValidityInSeconds";
	public static final String PROP_GRANT_TYPES = ENV_OAUTH + "grantTypes";
	public static final String PROP_SCOPES = ENV_OAUTH + "scopes";

}
