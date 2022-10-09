package com.example.hooverspec.service;

import com.example.hooverspec.entity.HooverSpec;
import com.example.hooverspec.model.request.RequestData;
import com.example.hooverspec.model.response.DetailedResponse;
import com.example.hooverspec.model.response.ResponseData;
import com.example.hooverspec.repo.HooverRepo;
import com.example.hooverspec.util.ResourceNotFound;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.hooverspec.testutil.TestDataFactory.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class HooverServiceTest {

    @Mock
    HooverRepo hooverRepo;

    @InjectMocks
    HooverService hooverService;

    @Test
    void should_create_new_data_in_system() {
        // Given
        Mockito.when(hooverRepo.save(any())).thenReturn(DatabaseEntry());
        RequestData requestData = validRequest();
        ResponseData expectedResponse = validResponse();

        // When
        ResponseData actualResponse = hooverService.servHoover(requestData);

        // Then
        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    void should_return_data_from_system() {
        // Given
        Mockito.when(hooverRepo.findById(1L)).thenReturn(Optional.of(DatabaseEntry()));
        DetailedResponse expectedResponse = validDetailedResponse();

        // When
        DetailedResponse actualResponse = hooverService.getHooverData(1L);

        // Then
        assertThat(actualResponse).isEqualTo(expectedResponse);
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
        List<DetailedResponse> actualResponse = hooverService.getAllHooverData();

        // Then
        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    void should_throw_resource_not_found_error() {
        // Given
        Mockito.when(hooverRepo.findById(2L)).thenReturn(Optional.empty());

        // When, Then
        assertThatThrownBy(() -> hooverService.getHooverData(2L))
                .isInstanceOf(ResourceNotFound.class);
    }

}
