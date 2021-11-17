package provider.aws;

import Domain.ResponseDto;
import Util.ServiceConstants;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.ServiceException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.Cliente;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class AwsProvider {
    private final RestTemplate restTemplate = new RestTemplate();

    private static final String GET_URL = "https://ex45nxdn0j.execute-api.us-east-1.amazonaws.com/First/";
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String IP = "192.168.0.1";
    public Cliente getAwsClient(int clientId) {
        log.debug("getAwsClients");
        HttpHeaders headers = new HttpHeaders();
        headers.set("ip", IP);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        var parametersUrl = GET_URL+clientId;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(parametersUrl);
        var responseTemplate = this.restTemplate.exchange(builder.build().toUriString(),
                HttpMethod.GET, entity, ResponseDto.class);
        if (responseTemplate.getStatusCode() != HttpStatus.OK) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ServiceConstants.SA100,
                    ServiceConstants.SA100);
        }
        var responseTemplateBody = responseTemplate.getBody();
        Objects.requireNonNull(responseTemplateBody);
        if (responseTemplateBody.getStatus() == HttpStatus.NOT_FOUND.value()) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), ServiceConstants.SA001,
                    ServiceConstants.SA001M);
        }
        var cliente = MAPPER.convertValue(responseTemplateBody.getData(), new TypeReference<Cliente>() {
        });
        return cliente;
    }

    public List<Cliente> getAwsClientList() {
        log.debug("getAwsClientList");
        HttpHeaders headers = new HttpHeaders();
        headers.set("ip", IP);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        var parametersUrl = GET_URL;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(parametersUrl);
        var responseTemplate = this.restTemplate.exchange(builder.build().toUriString(),
                HttpMethod.GET, entity, ResponseDto.class);
        if (responseTemplate.getStatusCode() != HttpStatus.OK) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ServiceConstants.SA100,
                    ServiceConstants.SA100);
        }
        var responseTemplateBody = responseTemplate.getBody();
        Objects.requireNonNull(responseTemplateBody);
        if (responseTemplateBody.getStatus() == HttpStatus.NOT_FOUND.value()) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), ServiceConstants.SA001,
                    ServiceConstants.SA001M);
        }
        var clientes = MAPPER.convertValue(responseTemplateBody.getData(), new TypeReference<List<Cliente>>() {
        });
        return clientes;
    }

    public ResponseDto<String> createAwsClientClient(Cliente cliente) {
        log.debug("createAwsClientClient");
        HttpHeaders headers = new HttpHeaders();
        headers.set("ip", IP);
        HttpEntity<Cliente> entity = new HttpEntity<>(cliente, headers);
        var parametersUrl = GET_URL+"create";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(parametersUrl);
        var responseTemplate = this.restTemplate.exchange(builder.build().toUriString(),
                HttpMethod.POST, entity, ResponseDto.class);
        if (responseTemplate.getStatusCode() != HttpStatus.OK) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ServiceConstants.SA100,
                    ServiceConstants.SA100);
        }
        var responseTemplateBody = responseTemplate.getBody();
        Objects.requireNonNull(responseTemplateBody);
        if (responseTemplateBody.getStatus() == HttpStatus.NOT_FOUND.value()) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), ServiceConstants.SA001,
                    ServiceConstants.SA001M);
        }

        return responseTemplateBody;
    }

    public ResponseDto<String> updateAwsClientClient(Cliente cliente) {
        log.debug("updateAwsClientClient");
        HttpHeaders headers = new HttpHeaders();
        headers.set("ip", IP);
        HttpEntity<Cliente> entity = new HttpEntity<>(cliente, headers);
        var parametersUrl = GET_URL+"update";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(parametersUrl);
        var responseTemplate = this.restTemplate.exchange(builder.build().toUriString(),
                HttpMethod.PUT, entity, ResponseDto.class);
        if (responseTemplate.getStatusCode() != HttpStatus.OK) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ServiceConstants.SA100,
                    ServiceConstants.SA100);
        }
        var responseTemplateBody = responseTemplate.getBody();
        Objects.requireNonNull(responseTemplateBody);
        if (responseTemplateBody.getStatus() == HttpStatus.NOT_FOUND.value()) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), ServiceConstants.SA001,
                    ServiceConstants.SA001M);
        }
        return responseTemplateBody;
    }

    public static Cliente createClient(){
        return  (new Cliente(777,"santiago", "Alvarez", "cc", 24, "Medellin"));
    }

    public ResponseDto<String> deleteAwsClient() {
        log.debug("deleteAwsClient");
        HttpHeaders headers = new HttpHeaders();
        headers.set("ip", IP);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        var parametersUrl = GET_URL+555;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(parametersUrl);
        var responseTemplate = this.restTemplate.exchange(builder.build().toUriString(),
                HttpMethod.DELETE, entity, ResponseDto.class);
        if (responseTemplate.getStatusCode() != HttpStatus.OK) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ServiceConstants.SA100,
                    ServiceConstants.SA100);
        }
        var responseTemplateBody = responseTemplate.getBody();
        Objects.requireNonNull(responseTemplateBody);
        if (responseTemplateBody.getStatus() == HttpStatus.NOT_FOUND.value()) {
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), ServiceConstants.SA001,
                    ServiceConstants.SA001M);
        }
        return responseTemplateBody;
    }
}
