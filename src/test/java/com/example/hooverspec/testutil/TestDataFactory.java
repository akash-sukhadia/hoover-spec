package com.example.hooverspec.testutil;

import com.example.hooverspec.entity.HooverSpec;
import com.example.hooverspec.model.request.RequestData;
import com.example.hooverspec.model.response.DetailedResponse;
import com.example.hooverspec.model.response.ResponseData;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestDataFactory {

    public static RequestData validRequest() {
        return RequestData.builder()
                .roomSize(Arrays.asList(5, 5))
                .coords(Arrays.asList(1, 2))
                .instructions("NNESEESWNWW")
                .patches(Arrays.asList(
                        Arrays.asList(1, 0),
                        Arrays.asList(2, 2),
                        Arrays.asList(2, 3)
                ))
                .build();
    }

    public static ResponseData validResponse() {
        return ResponseData.builder()
                .id(1L)
                .coords(Arrays.asList(1, 3))
                .patches(1)
                .build();
    }

    public static DetailedResponse validDetailedResponse() {
        Set<List<Integer>> cleanedPatches = new HashSet<>();
        cleanedPatches.add(Arrays.asList(2, 3));
        return DetailedResponse.builder()
                .id(1L)
                .startingCoordinates(Arrays.asList(1, 2))
                .roomSize(Arrays.asList(5, 5))
                .patches(Arrays.asList(
                        Arrays.asList(1, 0),
                        Arrays.asList(2, 2),
                        Arrays.asList(2, 3)
                ))
                .cleanedPatchesCount(1)
                .cleanedPatches(cleanedPatches)
                .instructions("NNESEESWNWW")
                .finalCoordinates(Arrays.asList(1, 3))
                .build();
    }

    public static HooverSpec DatabaseEntry() {
        Set<List<Integer>> cleanedPatches = new HashSet<>();
        cleanedPatches.add(Arrays.asList(2, 3));
        return HooverSpec.builder()
                .id(1L)
                .roomSize(Arrays.asList(5, 5))
                .coords(Arrays.asList(1, 2))
                .instructions("NNESEESWNWW")
                .patches(Arrays.asList(
                        Arrays.asList(1, 0),
                        Arrays.asList(2, 2),
                        Arrays.asList(2, 3)
                ))
                .cleanedPatched(cleanedPatches)
                .finalCoords(Arrays.asList(1, 3))
                .build();
    }
}
