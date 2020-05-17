* Create a copy of 08.Microservices-AuthorizationCodeGrantType project

* Create a class with following code

		@Configuration
		public class ServiceConfig {
		@Bean
		public OAuth2RestTemplate oauth2RestTemplate(OAuth2ProtectedResourceDetails resource, OAuth2ClientContext context){
			return new OAuth2RestTemplate(resource, context);
		}
		}

* Create a Controller and following code



		@Controller
		@EnableOAuth2Sso
		public class ReportController {
		@Autowired
		private OAuth2ClientContext clientContext;
		@Autowired
		private OAuth2RestTemplate oauth2RestTemplate;

		@RequestMapping("/persons")
		public String getPersons(Model model){
		ResponseEntity<List<Person>> persons = oauth2RestTemplate.exchange("http://localhost:9090/data", HttpMethod.GET, null, new ParameterizedTypeReference<List<Person>>() {
		});
		System.out.println(persons.getBody());
		model.addAttribute("persons", persons.getBody());
		return "reports";
		}

* Create reports.html in templates folder, with  following code



		<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			    <table class="table table-striped">
				<tr><th>First Name</th><th>Last Name</th></tr>
				<tr th:each="person : ${persons}">
					<td th:text="${person.firstName}"></td>
					<td th:text="${person.lastName}"></td>
				</tr>
			    </table>
	    </div>
		</div>

