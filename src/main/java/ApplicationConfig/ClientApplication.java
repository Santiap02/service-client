package ApplicationConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

@ComponentScan(basePackages= {"Rest", "ApplicationConfig", "Domain", })
@SpringBootApplication
@EnableFeignClients(basePackages = "provider.services")
public class ClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate template() {
		RestTemplate template=new RestTemplate();
		BasicAuthenticationInterceptor intercep;
		intercep=new BasicAuthenticationInterceptor("admin", "admin");
		template.getInterceptors().add(intercep);
		return template;
	}

}
