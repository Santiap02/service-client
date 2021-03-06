package ApplicationConfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;
import provider.aws.AwsProvider;

@Slf4j
@Import(SecurityConfig.class)
@ComponentScan(basePackages= {"rest", "ApplicationConfig", "Domain", "provider"})
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
	@Bean
	CommandLineRunner run(AwsProvider awsProvider) {
		return args -> log.debug("Result: {}", awsProvider.getAwsClientList());
	}

}
