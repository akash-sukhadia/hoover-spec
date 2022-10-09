package com.example.hooverspec.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestData {

    @NotNull
    @Size(min = 2)
    private List<Integer> roomSize;
    @NotNull
    @Size(min = 2)
    private List<Integer> coords;
    private List<List<Integer>> patches;
    private String instructions;
}
