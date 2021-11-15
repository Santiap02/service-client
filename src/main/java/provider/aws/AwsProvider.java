package provider.aws;

import lombok.AllArgsConstructor;
import model.Cliente;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class AwsProvider {
    private final RestTemplate template;
    private final static String url="http://service-get";

    @GetMapping(value="/clientes2", produces= MediaType.APPLICATION_JSON_VALUE)
    public List<Cliente> getclientes(){
        Cliente[] clientes=template.getForObject(url+"/clientes", Cliente[].class);
        return Arrays.asList(clientes);
    }
}
