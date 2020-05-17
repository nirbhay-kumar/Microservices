* Create a copy of **09.Microservices-Creating-A-Resource-Server-And-Routing-Tokens-To-Services** project

* Create a class CustomeUserInfoTokenServices



		public class CustomUserInfoTokenServices implements ResourceServerTokenServices {
		protected final Log logger = LogFactory.getLog(getClass());
		private static final String[] PRINCIPAL_KEYS = new String[] { "user", "username",
				"userid", "user_id", "login", "id", "name" };
		private final String userInfoEndpointUrl;
		private final String clientId;
		private OAuth2RestOperations restTemplate;
		private String tokenType = DefaultOAuth2AccessToken.BEARER_TYPE;
		private AuthoritiesExtractor authoritiesExtractor = new FixedAuthoritiesExtractor();
		public CustomUserInfoTokenServices(String userInfoEndpointUrl, String clientId) {
			this.userInfoEndpointUrl = userInfoEndpointUrl;
			this.clientId = clientId;
		}
		public void setTokenType(String tokenType) {
			this.tokenType = tokenType;
		}
		public void setRestTemplate(OAuth2RestOperations restTemplate) {
			this.restTemplate = restTemplate;
		}
		public void setAuthoritiesExtractor(AuthoritiesExtractor authoritiesExtractor) {
			this.authoritiesExtractor = authoritiesExtractor;
		}
		@Override
		public OAuth2Authentication loadAuthentication(String accessToken)
				throws AuthenticationException, InvalidTokenException {
			Map<String, Object> map = getMap(this.userInfoEndpointUrl, accessToken);
			if (map.containsKey("error")) {
				this.logger.debug("userinfo returned error: " + map.get("error"));
				throw new InvalidTokenException(accessToken);
			}
			return extractAuthentication(map);
		}

		private OAuth2Authentication extractAuthentication(Map<String, Object> map) {
			Object principal = getPrincipal(map);
			OAuth2Request request = getRequest(map);
			List<GrantedAuthority> authorities = this.authoritiesExtractor
					.extractAuthorities(map);
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
					principal, "N/A", authorities);
			token.setDetails(map);
			return new OAuth2Authentication(request, token);
		}

		private Object getPrincipal(Map<String, Object> map) {
			for (String key : PRINCIPAL_KEYS) {
				if (map.containsKey(key)) {
					return map.get(key);
				}
			}
			return "unknown";
		}

		@SuppressWarnings({ "unchecked" })
		private OAuth2Request getRequest(Map<String, Object> map) {
			Map<String, Object> request = (Map<String, Object>) map.get("oauth2Request");

			String clientId = (String) request.get("clientId");
			Set<String> scope = new LinkedHashSet<>(request.containsKey("scope") ?
					(Collection<String>) request.get("scope") : Collections.<String>emptySet());

			return new OAuth2Request(null, clientId, null, true, new HashSet<>(scope),
					null, null, null, null);
		}

		@Override
		public OAuth2AccessToken readAccessToken(String accessToken) {
			throw new UnsupportedOperationException("Not supported: read access token");
		}

		@SuppressWarnings({ "unchecked" })
		private Map<String, Object> getMap(String path, String accessToken) {
			this.logger.info("Getting user info from: " + path);
			try {
				OAuth2RestOperations restTemplate = this.restTemplate;
				if (restTemplate == null) {
					BaseOAuth2ProtectedResourceDetails resource = new BaseOAuth2ProtectedResourceDetails();
					resource.setClientId(this.clientId);
					restTemplate = new OAuth2RestTemplate(resource);
				}
				OAuth2AccessToken existingToken = restTemplate.getOAuth2ClientContext()
						.getAccessToken();
				if (existingToken == null || !accessToken.equals(existingToken.getValue())) {
					DefaultOAuth2AccessToken token = new DefaultOAuth2AccessToken(
							accessToken);
					token.setTokenType(this.tokenType);
					restTemplate.getOAuth2ClientContext().setAccessToken(token);
				}
				return restTemplate.getForEntity(path, Map.class).getBody();
			}
			catch (Exception ex) {
				this.logger.info("Could not fetch user details: " + ex.getClass() + ", "
						+ ex.getMessage());
				return Collections.<String, Object>singletonMap("error",
						"Could not fetch user details");
			}
		}
		}

* Create a class SecurityConfig

		@Configuration
		@EnableGlobalMethodSecurity(prePostEnabled = true)
		public class SecurityConfig  extends GlobalMethodSecurityConfiguration{
		@Override
		protected MethodSecurityExpressionHandler createExpressionHandler() {
			return new OAuth2MethodSecurityExpressionHandler();
		}
		}

* Add following code to the Main class

		@SpringBootApplication
		@EnableResourceServer
		public class Application {
		public static void main(String[] args) {
			SpringApplication.run(Application.class, args);
		}
		@Autowired
		private ResourceServerProperties sso;
		@Bean
		public ResourceServerTokenServices myUserInfoTokenServices(){
			return new CustomUserInfoTokenServices(sso.getUserInfoUri(), sso.getClientId());
		}
		}

* Modify controller method with following code

		@RequestMapping("/data")
		@PreAuthorize("#oauth2.hasScope('person_read') and hasAuthority('ROLE_OPERATOR')")
		public List<Person> getData(){

			List<Person> persons = new ArrayList<Person>();
			persons.add(new Person("Mo", "Salaha"));
			persons.add(new Person("Donald","Trump"));
			persons.add(new Person("Sagar","Kulkarni"));

			return persons;
		}

* Get access token for user **klopp** as only he has the adequate permission to access the method
* open postman and configure a **post**  request to **localhost:9000/oauth/token**  
* In Authorization tab select **Basic Auth** with Username as **rosemont** and password as **rosemont**
 
* Add following key-value pairs in **x-www-form-urlencoded** section of **Body**

	grant_type = password
	client_id = rosemont
	username = klopp
	password = pass2

* Send the request; you should receive access token

* Start the 13.Microservices-Giving-Filtered-Access-To-The-Service-Methods service

* Go to Postman
* Access *http://localhost:9090/data* service 
* Select No Auth in authorization type
* Add following header
	
	Authorization = Bearer ACCESS_TOKEN
where **ACCESS_TOKEN** is the token that you received from authorization server
