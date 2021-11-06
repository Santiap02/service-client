package provider.services;

import ApplicationConfig.FeignConfiguration;
import Domain.ResponseDto;
import model.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@FeignClient(name = "service-modify", configuration = FeignConfiguration.class)

public interface FeignServiceModify {
    @PutMapping(value="clientes/actualizar/",consumes= MediaType.APPLICATION_JSON_VALUE)
    ResponseDto<String> actualizarContacto(@RequestBody Cliente contacto);

    @PutMapping(value= "/photos/update")
    ResponseDto<String> actualizarFoto(@RequestParam("title") int title, @RequestParam("image") MultipartFile image, Model model) throws IOException;

}
