package es.jemerino.springboot.rest.skeleton.constant;

public class Mapping {

	public static final String API_VERSION_1 = "/api/v1";
	
	public static final String GET_HELLO_WORLD = "/helloWorld";

	public static final String H2_CONSOLE = "/h2console/*";
	public static final String H2_CONSOLE_SEC = "/h2console/**";
	public static final String LOGOUT = "/oauth/logout";
	public static final String AUTHORIZE = "/oauth/authorize";
	public static final String SECURE_PATH = "/**";
	
}
