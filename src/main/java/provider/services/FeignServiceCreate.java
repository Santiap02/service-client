package provider.services;

import Domain.ResponseDto;
import ApplicationConfig.FeignConfiguration;
import Domain.ClienteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "service-create", configuration = FeignConfiguration.class)
public interface FeignServiceCreate {

    /**
     *
     * @param clienteDto
     * @return
     */
    @PostMapping(value="/clientes",consumes= MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    ResponseDto<String> saveClient(@RequestBody ClienteDto clienteDto);

    /**
     * Save photo
     * @param clientId
     * @param image
     * @return
     */
    @PostMapping(value = "/photos/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseDto<String> addPhoto(@RequestPart("image") MultipartFile image,@RequestParam("clientId") int clientId);

}