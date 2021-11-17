package rest;

import Domain.ResponseDto;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import model.Cliente;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import provider.aws.AwsProvider;
import provider.services.FeignServiceCreate;
import provider.services.FeignServiceDelete;
import provider.services.FeignServiceGet;
import provider.services.FeignServiceModify;
import java.util.List;

@AllArgsConstructor
@RestController
public class ClientRest {


	private final FeignServiceGet feignServiceGet;
	private final FeignServiceCreate feignServiceCreate;
	private final FeignServiceDelete feignServiceDelete;
	private final FeignServiceModify feignServiceModify;
	private final AwsProvider awsProvider;

	@Operation(summary = "Obtener la lista de clientes", description = "Permite consultar la lista completa de clientes")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Consulta correcta", response = ResponseDto.class),
			@ApiResponse(code = 404, message = "No hay clientes para mostrar", response = ResponseDto.class),
			@ApiResponse(code = 500, message = "Error inesperado durante el proceso", response = ResponseDto.class) })
	@GetMapping(value="/clientes",produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseDto<List<Cliente>> getAllClients(){
		return (feignServiceGet.recuperarClientes());
	}

	@Operation(summary = "Obtener un cliente por Id", description = "Permite consultar un cliente correspondiente al Id provisto")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Consulta correcta", response = ResponseDto.class),
			@ApiResponse(code = 404, message = "No hay clientes para mostrar", response = ResponseDto.class),
			@ApiResponse(code = 500, message = "Error inesperado durante el proceso", response = ResponseDto.class) })
	@GetMapping(value="clientes/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseDto<Cliente> getClientId(@PathVariable("id") int id){
		return feignServiceGet.getClientId(id);
	}

	@Operation(summary = "Obtener los clientes mayores", description = "Permite consultar la lista de clientes cuya edad sea mayor que la provista")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Consulta correcta", response = ResponseDto.class),
			@ApiResponse(code = 404, message = "No hay clientes para mostrar", response = ResponseDto.class),
			@ApiResponse(code = 500, message = "Error inesperado durante el proceso", response = ResponseDto.class) })
	@GetMapping(value="/clientes/mayores/{age}",produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseDto<List<Cliente>> getOlderClients(@PathVariable("age") int edad) {
		return feignServiceGet.recuperarClientesMayores(edad);
	}

	@Operation(summary = "Obtener foto", description = "Permite consultar la foto correspondiente al id de la base de datos provisto")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Consulta correcta", response = ResponseDto.class),
			@ApiResponse(code = 404, message = "No hay clientes para mostrar", response = ResponseDto.class),
			@ApiResponse(code = 500, message = "Error inesperado durante el proceso", response = ResponseDto.class) })
	@GetMapping("/photos/{mongoid}")//imagen by MongoId
	public ResponseDto<String> getPhoto(@PathVariable String mongoid) {
		return feignServiceGet.getPhoto(mongoid);
	}

	@Operation(summary = "Obtener foto", description = "Permite consultar la foto correspondiente al cliente con el id provisto")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Consulta correcta", response = ResponseDto.class),
			@ApiResponse(code = 404, message = "No hay clientes para mostrar", response = ResponseDto.class),
			@ApiResponse(code = 500, message = "Error inesperado durante el proceso", response = ResponseDto.class) })
	@GetMapping("/photo/{clientId}")
	public ResponseDto<String> getPhotoById(@PathVariable int clientId) {
		return feignServiceGet.getPhotoid(clientId);
	}

	@Operation(summary = "Creación de nuevo cliente", description = "Permite crear un nuevo objeto con la informacion de un cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Cliente guardado exitosamente", response = ResponseDto.class),
			@ApiResponse(code = 403, message = "El cliente ya existe", response = ResponseDto.class),
			@ApiResponse(code = 400, message = "Solicitud incorrecta. Por favor valide los datos enviados.", response = ResponseDto.class),
			@ApiResponse(code = 500, message = "Error inesperado durante el proceso", response = ResponseDto.class) })
	@PostMapping(value="/clientes")
	public ResponseDto<String> saveClient(@RequestBody Cliente cliente){
		return feignServiceCreate.saveClient(cliente);
	}

	@Operation(summary = "Creación de imagen de un cliente", description = "Permite crear una nueva imagen de un cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Cliente guardado exitosamente", response = ResponseDto.class),
			@ApiResponse(code = 403, message = "El cliente ya existe", response = ResponseDto.class),
			@ApiResponse(code = 500, message = "Error inesperado durante el proceso", response = ResponseDto.class) })
	@PostMapping("/photos/add")
	public ResponseDto<String> addPhoto(@Parameter(name = "clientId", required = true, description = "Id del cliente a actualizar", schema = @Schema(implementation = int.class), in = ParameterIn.QUERY)@RequestParam("clientId") int clientId,
										@Parameter(name = "image", required = true, description = "Imagen nueva", schema = @Schema(implementation = MultipartFile.class), in = ParameterIn.QUERY)@RequestParam("image") MultipartFile image){
		return feignServiceCreate.addPhoto( image, clientId);
	}

	@Operation(summary = "Borrar un cliente", description = "Permite borrar un cliente con su Id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Cliente borrado", response = ResponseDto.class),
			@ApiResponse(code = 400, message = "No se encuentra información para borrar", response = ResponseDto.class),
			@ApiResponse(code = 500, message = "Error inesperado durante el proceso", response = ResponseDto.class) })
	@DeleteMapping(value="/clientes/{clientId}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseDto<String> deleteClientById(@Parameter(name = "clientId", required = true, description = "Id del cliente a borrar", schema = @Schema(implementation = int.class), in = ParameterIn.PATH) @PathVariable int clientId){
		return feignServiceDelete.BorrarClienteId(clientId);
	}

	@Operation(summary = "Borrar un cliente", description = "Permite borrar un cliente con su Id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Cliente borrado", response = ResponseDto.class),
			@ApiResponse(code = 400, message = "No se encuentra información para borrar", response = ResponseDto.class),
			@ApiResponse(code = 500, message = "Error inesperado durante el proceso", response = ResponseDto.class) })
	@DeleteMapping("/photos/{clientId}")
	public ResponseDto<String> deletePhotoById(@Parameter(name = "clientId", required = true, description = "Id del cliente a borrar", schema = @Schema(implementation = int.class), in = ParameterIn.PATH) @PathVariable("clientId") int clientId) {
		return feignServiceDelete.BorrarfotoId(clientId);
	}

	@Operation(summary = "Actualizar datos de un cliente", description = "Permite actualizar los datos de un cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Cliente guardado exitosamente", response = ResponseDto.class),
			@ApiResponse(code = 400, message = "Solicitud incorrecta. Por favor valide los datos enviados.", response = ResponseDto.class),
			@ApiResponse(code = 500, message = "Error inesperado durante el proceso", response = ResponseDto.class) })
	@PutMapping(value="clientes/actualizar/",consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseDto<String> updateClient(@Parameter(name = "cliente", required = true, description = "Nuevos datos del cliente", schema = @Schema(implementation = Cliente.class), in = ParameterIn.QUERY)@RequestBody Cliente client){
		return feignServiceModify.actualizarContacto(client);
	}

	@Operation(summary = "Actualizar foto de un cliente", description = "Permite actualizar la foto de un cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Cliente guardado exitosamente", response = ResponseDto.class),
			@ApiResponse(code = 400, message = "Solicitud incorrecta. Por favor valide los datos enviados.", response = ResponseDto.class),
			@ApiResponse(code = 500, message = "Error inesperado durante el proceso", response = ResponseDto.class) })
	@PutMapping(value = "/photos/update" )
	public ResponseDto<String> updatePhoto(@Parameter(name = "clientId", required = true, description = "Id del cliente a actualizar", schema = @Schema(implementation = int.class), in = ParameterIn.QUERY)@RequestParam("clientId") int clientId,
										   @Parameter(name = "image", required = true, description = "Imagen nueva", schema = @Schema(implementation = MultipartFile.class), in = ParameterIn.QUERY)@RequestParam("image") MultipartFile image){
		return feignServiceModify.actualizarFoto(image, clientId);
	}

	@Operation(summary = "Obtener la lista de clientes Aws", description = "Permite consultar la lista completa de clientes")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Consulta correcta", response = ResponseDto.class),
			@ApiResponse(code = 404, message = "No hay clientes para mostrar", response = ResponseDto.class),
			@ApiResponse(code = 500, message = "Error inesperado durante el proceso", response = ResponseDto.class) })
	@GetMapping(value="/aws/clientes",produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseDto<List<Cliente>> getAllAwsClients(){
		return this.awsProvider.getAwsClientList();
	}

	@Operation(summary = "Obtener un cliente Aws por Id", description = "Permite consultar un cliente correspondiente al Id provisto")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Consulta correcta", response = ResponseDto.class),
			@ApiResponse(code = 404, message = "No hay clientes para mostrar", response = ResponseDto.class),
			@ApiResponse(code = 500, message = "Error inesperado durante el proceso", response = ResponseDto.class) })
	@GetMapping(value="/aws/clientes/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseDto<Cliente> getAwsClientId(@PathVariable("id") int id){
		return this.awsProvider.getAwsClient(id);
	}

	@Operation(summary = "Creación de nuevo cliente Aws", description = "Permite crear un nuevo objeto con la informacion de un cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Cliente guardado exitosamente", response = ResponseDto.class),
			@ApiResponse(code = 403, message = "El cliente ya existe", response = ResponseDto.class),
			@ApiResponse(code = 400, message = "Solicitud incorrecta. Por favor valide los datos enviados.", response = ResponseDto.class),
			@ApiResponse(code = 500, message = "Error inesperado durante el proceso", response = ResponseDto.class) })
	@PostMapping(value="/aws/clientes")
	public ResponseDto<String> saveAwsClient(@RequestBody Cliente cliente){
		return this.awsProvider.createAwsClientClient(cliente);
	}

	@Operation(summary = "Actualizar datos de un cliente Aws", description = "Permite actualizar los datos de un cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Cliente guardado exitosamente", response = ResponseDto.class),
			@ApiResponse(code = 400, message = "Solicitud incorrecta. Por favor valide los datos enviados.", response = ResponseDto.class),
			@ApiResponse(code = 500, message = "Error inesperado durante el proceso", response = ResponseDto.class) })
	@PutMapping(value="/aws/clientes/actualizar/",consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseDto<String> updateAwsClient(@Parameter(name = "cliente", required = true, description = "Nuevos datos del cliente", schema = @Schema(implementation = Cliente.class), in = ParameterIn.QUERY)@RequestBody Cliente client){
		return this.awsProvider.updateAwsClientClient(client);
	}

	@Operation(summary = "Borrar un cliente Aws", description = "Permite borrar un cliente con su Id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Cliente borrado", response = ResponseDto.class),
			@ApiResponse(code = 400, message = "No se encuentra información para borrar", response = ResponseDto.class),
			@ApiResponse(code = 500, message = "Error inesperado durante el proceso", response = ResponseDto.class) })
	@DeleteMapping(value="/aws/clientes/{clientId}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseDto<String> deleteAwsClientById(@Parameter(name = "clientId", required = true, description = "Id del cliente a borrar", schema = @Schema(implementation = int.class), in = ParameterIn.PATH) @PathVariable int clientId){
		return this.awsProvider.deleteAwsClient(clientId);
	}
}
