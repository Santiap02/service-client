package provider.services;


import ApplicationConfig.FeignConfiguration;
import Domain.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-delete", configuration = FeignConfiguration.class)

public interface FeignServiceDelete {
    @DeleteMapping(value="/clientes/{id}",produces= MediaType.APPLICATION_JSON_VALUE)
    ResponseDto<String> BorrarClienteId(@PathVariable("id") int id);

    @DeleteMapping("/photos/{title}")
    ResponseDto<String> BorrarfotoId(@PathVariable int title);

}
