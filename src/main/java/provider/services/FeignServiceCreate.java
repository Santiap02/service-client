package provider.services;

import Domain.ResponseDto;
import feign.Headers;
import ApplicationConfig.FeignConfiguration;
import model.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "service-create", configuration = FeignConfiguration.class)
public interface FeignServiceCreate {

    /**
     *
     * @param cliente
     * @return
     */
    @PostMapping(value="/clientes",consumes= MediaType.APPLICATION_JSON_VALUE,produces=MediaType.TEXT_PLAIN_VALUE)
    ResponseDto<String> saveClient(@RequestBody Cliente cliente);

    /**
     * Save photo
     * @param clientId
     * @param image
     * @param model
     * @return
     */
    @Headers("Content-Type: multipart/form-data")
    @PostMapping(value = "/photos/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseDto<String> addPhoto(@RequestParam("clientId") int clientId, @RequestParam("image") String image, Model model);



}