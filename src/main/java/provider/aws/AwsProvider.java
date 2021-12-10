package provider.aws;

import Domain.ResponseDto;
import Util.ServiceConstants;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.ServiceException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import Domain.ClienteDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@AllArgsConstructor
public class AwsProvider {
    private final RestTemplate restTemplate = new RestTemplate();

    private static final String GET_URL = "https://ex45nxdn0j.execute-api.us-east-1.amazonaws.com/First/";
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String IP = "192.168.0.1";

    public ResponseDto<ClienteDto> getAwsClient(int clientId) {
        log.debug("getAwsClients");
        ResponseDto<ClienteDto> response;
        HttpHeaders headers = new HttpHeaders();
        headers.set("ip", IP);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        var parametersUrl = GET_URL+clientId;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(parametersUrl);
        var responseTemplate = this.restTemplate.exchange(builder.build().toUriString(),
                HttpMethod.GET, entity, ResponseDto.class);
        try {
            if (responseTemplate.getStatusCode() != HttpStatus.OK) {
                throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ServiceConstants.SA100,
                        ServiceConstants.SA100);
            }
            var responseTemplateBody = responseTemplate.getBody();
            Objects.requireNonNull(responseTemplateBody);
            if (responseTemplateBody.getStatus() != HttpStatus.OK.value()) {
                throw new ServiceException(responseTemplateBody.getStatus(), responseTemplateBody.getResponseCode(),
                        responseTemplateBody.getResponseMessage());
            }
            var cliente = MAPPER.convertValue(responseTemplateBody.getData(), new TypeReference<ClienteDto>() {
            });
            response = new ResponseDto<>(responseTemplateBody.getStatus(), responseTemplateBody.getResponseCode(),
                    responseTemplateBody.getResponseMessage(), cliente);
        } catch (ServiceException e) {
            e.printStackTrace();
            response = new ResponseDto<>(e.getStatus(), e.getCode(), e.getMessage());
        }
        return response;
    }

    public ResponseDto<List<ClienteDto>> getAwsClientList() {
        log.debug("getAwsClientList");
        ResponseDto<List<ClienteDto>> response;
        HttpHeaders headers = new HttpHeaders();
        headers.set("ip", IP);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(GET_URL);
        var responseTemplate = this.restTemplate.exchange(builder.build().toUriString(),
                HttpMethod.GET, entity, ResponseDto.class);
       try {
           if (responseTemplate.getStatusCode() != HttpStatus.OK) {
               throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ServiceConstants.SA100,
                       ServiceConstants.SA100);
           }
           var responseTemplateBody = responseTemplate.getBody();
           Objects.requireNonNull(responseTemplateBody);
           if (responseTemplateBody.getStatus() != HttpStatus.OK.value()) {
               throw new ServiceException(responseTemplateBody.getStatus(), responseTemplateBody.getResponseCode(),
                       responseTemplateBody.getResponseMessage());
           }
           var cliente = MAPPER.convertValue(responseTemplateBody.getData(), new TypeReference<List<ClienteDto>>() {
           });
           response = new ResponseDto<>(responseTemplateBody.getStatus(), responseTemplateBody.getResponseCode(),
                   responseTemplateBody.getResponseMessage(), cliente);
       } catch (ServiceException e) {
           e.printStackTrace();
           response = new ResponseDto<>(e.getStatus(), e.getCode(), e.getMessage());
       }
        return response;
    }

    public ResponseDto<String> createAwsClientClient(ClienteDto clienteDto) {
        log.debug("createAwsClientClient");
        ResponseDto<String> response;
        HttpHeaders headers = new HttpHeaders();
        headers.set("ip", IP);
        HttpEntity<ClienteDto> entity = new HttpEntity<>(clienteDto, headers);
        var parametersUrl = GET_URL+"create";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(parametersUrl);
        var responseTemplate = this.restTemplate.exchange(builder.build().toUriString(),
                HttpMethod.POST, entity, ResponseDto.class);
        try {
            if (responseTemplate.getStatusCode() != HttpStatus.OK) {
                throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ServiceConstants.SA100,
                        ServiceConstants.SA100);
            }
            response = responseTemplate.getBody();
            Objects.requireNonNull(response);
            if (response.getStatus() != HttpStatus.OK.value()) {
                throw new ServiceException(response.getStatus(), response.getResponseCode(),
                        response.getResponseMessage());
            }
        } catch (ServiceException e) {
            e.printStackTrace();
            response = new ResponseDto<>(e.getStatus(), e.getCode(), e.getMessage());
        }
        return response;
    }

    public ResponseDto<String> updateAwsClientClient(ClienteDto clienteDto) {
        log.debug("updateAwsClientClient");
        ResponseDto<String> response;
        HttpHeaders headers = new HttpHeaders();
        headers.set("ip", IP);
        HttpEntity<ClienteDto> entity = new HttpEntity<>(clienteDto, headers);
        var parametersUrl = GET_URL+"update";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(parametersUrl);
        var responseTemplate = this.restTemplate.exchange(builder.build().toUriString(),
                HttpMethod.PUT, entity, ResponseDto.class);
        try {
            if (responseTemplate.getStatusCode() != HttpStatus.OK) {
                throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ServiceConstants.SA100,
                        ServiceConstants.SA100);
            }
            response = responseTemplate.getBody();
            Objects.requireNonNull(response);
            if (response.getStatus() != HttpStatus.OK.value()) {
                throw new ServiceException(response.getStatus(), response.getResponseCode(),
                        response.getResponseMessage());
            }
        } catch (ServiceException e) {
            e.printStackTrace();
            response = new ResponseDto<>(e.getStatus(), e.getCode(), e.getMessage());
        }
        return response;
    }

    public static ClienteDto createClient(){
        return  (new ClienteDto(777,"santiago", "Alvarez", "cc", 24, "Medellin"));
    }

    public ResponseDto<String> deleteAwsClient(int clientId) {
        log.debug("deleteAwsClient");
        ResponseDto<String> response;
        HttpHeaders headers = new HttpHeaders();
        headers.set("ip", IP);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        var parametersUrl = GET_URL+clientId;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(parametersUrl);
        var responseTemplate = this.restTemplate.exchange(builder.build().toUriString(),
                HttpMethod.DELETE, entity, ResponseDto.class);
        try {
            if (responseTemplate.getStatusCode() != HttpStatus.OK) {
                throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ServiceConstants.SA100,
                        ServiceConstants.SA100);
            }
            response = responseTemplate.getBody();
            Objects.requireNonNull(response);
            if (response.getStatus() != HttpStatus.OK.value()) {
                throw new ServiceException(response.getStatus(), response.getResponseCode(),
                        response.getResponseMessage());
            }
        } catch (ServiceException e) {
            e.printStackTrace();
            response = new ResponseDto<>(e.getStatus(), e.getCode(), e.getMessage());
        }
        return response;
    }
}
