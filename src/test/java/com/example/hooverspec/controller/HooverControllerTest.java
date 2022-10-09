package com.example.hooverspec.controller;

import com.example.hooverspec.Application;
import com.example.hooverspec.entity.HooverSpec;
import com.example.hooverspec.model.request.RequestData;
import com.example.hooverspec.model.response.DetailedResponse;
import com.example.hooverspec.model.response.ErrorResponse;
import com.example.hooverspec.model.response.ResponseData;
import com.example.hooverspec.repo.HooverRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.hooverspec.testutil.TestDataFactory.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class HooverControllerTest {

    @MockBean
    HooverRepo hooverRepo;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void should_create_new_data_in_system() {
        // Given
        Mockito.when(hooverRepo.save(any())).thenReturn(DatabaseEntry());
        RequestData requestData = validRequest();
        ResponseData expectedResponse = validResponse();
        HttpEntity<RequestData> entity = new HttpEntity<>(requestData);

        // When
        ResponseEntity<ResponseData> actualResponse = restTemplate.exchange(
                createURL("/hoover"),
                HttpMethod.POST, entity, ResponseData.class);

        // Then
        assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actualResponse.getBody()).isEqualTo(expectedResponse);
    }

    @Test
    void should_return_data_from_system() {
        // Given
        Mockito.when(hooverRepo.findById(1L)).thenReturn(Optional.of(DatabaseEntry()));
        DetailedResponse expectedResponse = validDetailedResponse();

        // When
        ResponseEntity<DetailedResponse> actualResponse = restTemplate.getForEntity(
                createURL("/hoover/1"), DetailedResponse.class);

        // Then
        assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actualResponse.getBody()).isEqualTo(expectedResponse);
    }

    @Test
    void should_return_all_data_from_system() {
        // Given
        List<HooverSpec> specs = new ArrayList<>();
        specs.add(DatabaseEntry());
        Mockito.when(hooverRepo.findAll()).thenReturn(specs);
        List<DetailedResponse> expectedResponse = new ArrayList<>();
        expectedResponse.add(validDetailedResponse());

        // When
        ResponseEntity<List<DetailedResponse>> actualResponse = restTemplate.exchange(
                createURL("/hoover"),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<DetailedResponse>>() {
                });

        // Then
        assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actualResponse.getBody()).isEqualTo(expectedResponse);
    }

    @Test
    void should_throw_resource_not_found_error() {
        // Given
        Mockito.when(hooverRepo.findById(2L)).thenReturn(Optional.empty());

        // When
        ErrorResponse errorResponse = restTemplate.getForEntity(createURL("/hoover/2"),
                ErrorResponse.class).getBody();

        // Then
        assertThat(errorResponse.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(errorResponse.getMessage()).isEqualTo("requested resource not found.");
    }

    private String createURL(String uri) {
        return "http://localhost:" + port + uri;
    }
}
