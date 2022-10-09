package com.example.hooverspec.controller;

import com.example.hooverspec.model.request.RequestData;
import com.example.hooverspec.model.response.DetailedResponse;
import com.example.hooverspec.model.response.ResponseData;
import com.example.hooverspec.service.HooverService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/hoover")
public class HooverController {

    private final HooverService hooverService;

    public HooverController(HooverService hooverService) {
        this.hooverService = hooverService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseData> hooverSpec(@Valid @RequestBody RequestData requestData) {
        return new ResponseEntity<>(hooverService.servHoover(requestData), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DetailedResponse> getHooverDataById(@PathVariable Long id) {
        return new ResponseEntity<>(hooverService.getHooverData(id), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DetailedResponse>> getHooverData() {
        return new ResponseEntity<>(hooverService.getAllHooverData(), HttpStatus.OK);
    }
}
