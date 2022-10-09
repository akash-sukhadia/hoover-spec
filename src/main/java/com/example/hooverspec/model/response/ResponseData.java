package com.example.hooverspec.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseData {

    private Long id;
    private List<Integer> coords;
    private Integer patches;
}
