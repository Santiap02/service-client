package provider.services;

import ApplicationConfig.FeignConfiguration;
import Domain.ResponseDto;
import model.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "service-get", configuration = FeignConfiguration.class)
public interface FeignServiceGet {
    @GetMapping(value="/clientes",produces= MediaType.APPLICATION_JSON_VALUE)
    ResponseDto<List<Cliente>> recuperarClientes();

    @GetMapping(value="clientes/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
    ResponseDto<Cliente> getClientId(@PathVariable("id") int id);

    @GetMapping(value="/clientes/mayores/{age}",produces= MediaType.APPLICATION_JSON_VALUE)
    ResponseDto<List<Cliente>> recuperarClientesMayores(@PathVariable("age") int edad);

    @GetMapping("/photos/{Mongoid}")//imagen by MongoId
    ResponseDto<String> getPhoto(@PathVariable String Mongoid) ;

    @GetMapping("/photo/{ClientId}")
    ResponseDto<String> getPhotoid(@PathVariable int ClientId);
}
