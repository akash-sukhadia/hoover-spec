package com.example.hooverspec.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailedResponse {

    private Long id;
    private List<Integer> roomSize;
    private List<Integer> startingCoordinates;
    private List<Integer> finalCoordinates;
    private List<List<Integer>> patches;
    private Set<List<Integer>> cleanedPatches;
    private Integer cleanedPatchesCount;
    private String instructions;
}
