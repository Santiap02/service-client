package Rest;
import Domain.ResponseDto;
import lombok.AllArgsConstructor;
import model.Cliente;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import provider.services.FeignServiceCreate;
import provider.services.FeignServiceDelete;
import provider.services.FeignServiceGet;
import provider.services.FeignServiceModify;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
public class ClientRest {

	private final RestTemplate template;
	private final FeignServiceGet feignServiceGet;
	private final FeignServiceCreate feignServiceCreate;
	private final FeignServiceDelete feignServiceDelete;
	private final FeignServiceModify feignServiceModify;
	private final String url="http://service-get";

	//Normal Request
	/*
	@GetMapping(value="/clientes2", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Cliente> getclientes(){
		Cliente[] clientes=template.getForObject(url+"/clientes", Cliente[].class);
		return Arrays.asList(clientes);
	}
	*/

	@GetMapping(value="/clientes",produces= MediaType.APPLICATION_JSON_VALUE)
	ResponseDto<List<Cliente>> getAllClients(){
		return (feignServiceGet.recuperarClientes());
	}

	@GetMapping(value="clientes/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	ResponseDto<Cliente> getClientId(@PathVariable("id") int id){
		return feignServiceGet.getClientId(id);
	}

	@GetMapping("/photos/{mongoid}")//imagen by MongoId
	public ResponseDto<String> getPhoto(@PathVariable String mongoid) {
		var response = feignServiceGet.getPhoto(mongoid);
		return response;
	}

	@GetMapping("/photo/{clientId}")
	public ResponseDto<String> getPhotoById(@PathVariable int clientId) {
		var response = feignServiceGet.getPhotoid(clientId);
		return response;
	}

	@GetMapping(value="/clientes/mayores/{age}",produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseDto<List<Cliente>> getOlderClients(@PathVariable("age") int edad) {
		return feignServiceGet.recuperarClientesMayores(edad);
	}

	@PostMapping(value="/clientes")
	public ResponseDto<String> saveClient(@RequestBody Cliente cliente){
		return feignServiceCreate.saveClient(cliente);
	}

	@PostMapping("/photos/add")
	public ResponseDto<String> addPhoto(@RequestParam("clientId") int clientId, @RequestParam("image") MultipartFile image) throws IOException {
		return feignServiceCreate.addPhoto( image, clientId);
	}

	@DeleteMapping(value="/clientes/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseDto<String> BorrarClienteId(@PathVariable("id") int id){
		return feignServiceDelete.BorrarClienteId(id);
	}

	@DeleteMapping("/photos/{clientId}")
	public ResponseDto<String> deletePhotoById(@PathVariable int clientId){
		return feignServiceDelete.BorrarfotoId(clientId);
	}

	@PutMapping(value="clientes/actualizar/",consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseDto<String> actualizarContacto(@RequestBody Cliente contacto){
		return feignServiceModify.actualizarContacto(contacto);
	}

	@PutMapping(value = "/photos/update" )
	public ResponseDto<String> actualizarFoto(@RequestParam("title") int title, @RequestParam("image") MultipartFile image, Model model) throws IOException{
		return feignServiceModify.actualizarFoto(title, image, model);
	}



}
