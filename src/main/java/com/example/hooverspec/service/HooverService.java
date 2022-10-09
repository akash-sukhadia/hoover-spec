package com.example.hooverspec.service;

import com.example.hooverspec.entity.HooverSpec;
import com.example.hooverspec.model.request.RequestData;
import com.example.hooverspec.model.response.DetailedResponse;
import com.example.hooverspec.model.response.ResponseData;
import com.example.hooverspec.repo.HooverRepo;
import com.example.hooverspec.util.ResourceNotFound;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class HooverService {

    final private HooverRepo hooverRepo;
    private final Set<List<Integer>> cleanPatches = new HashSet<>();
    private Integer posX;
    private Integer posY;
    private Integer roomWidth;
    private Integer roomHeight;
    private List<List<Integer>> patches;

    public HooverService(HooverRepo hooverRepo) {
        this.hooverRepo = hooverRepo;
    }

    public ResponseData servHoover(RequestData requestData) {
        roomWidth = requestData.getRoomSize().get(0);
        roomHeight = requestData.getRoomSize().get(1);
        posX = requestData.getCoords().get(0);
        posY = requestData.getCoords().get(1);
        patches = new ArrayList<>(requestData.getPatches());
        char[] instructions = requestData.getInstructions().toCharArray();
        for (char c : instructions) {
            moveHover(c);
        }
        HooverSpec spec = hooverRepo.save(mapToEntity(requestData));
        return ResponseData.builder()
                .id(spec.getId())
                .coords(Arrays.asList(posX, posY))
                .patches(cleanPatches.size())
                .build();
    }

    public DetailedResponse getHooverData(Long id) {
        Optional<HooverSpec> spec = hooverRepo.findById(id);
        return spec.map(this::mapEntityToResponse)
                .orElseThrow(ResourceNotFound::new);
    }

        public List<DetailedResponse> getAllHooverData() {
        return hooverRepo.findAll().stream().map(this::mapEntityToResponse).collect(Collectors.toList());
    }

    private void moveHover(char instruction) {
        List<Integer> pos = new ArrayList<>();
        switch (instruction) {
            case 'N':
                posY = (posY + 1 >= roomHeight ? posY : posY + 1);
                break;
            case 'E':
                posX = (posX + 1 >= roomWidth ? posX : posX + 1);
                break;
            case 'S':
                posY = (posY - 1 < 0 ? posY : posY - 1);
                break;
            case 'W':
                posX = (posX - 1 < 0 ? posX : posX - 1);
                break;
        }
        pos.add(posX);
        pos.add(posY);
        if (patches.contains(pos)) {
            patches.remove(pos);
            cleanPatches.add(pos);
        }

    }

    private HooverSpec mapToEntity(RequestData requestData) {
        return HooverSpec.builder()
                .roomSize(requestData.getRoomSize())
                .coords(requestData.getCoords())
                .patches(requestData.getPatches())
                .cleanedPatched(cleanPatches)
                .finalCoords(Arrays.asList(posX, posY))
                .instructions(requestData.getInstructions())
                .build();
    }

    private DetailedResponse mapEntityToResponse(HooverSpec hooverSpec) {
        return DetailedResponse.builder()
                .id(hooverSpec.getId())
                .roomSize(hooverSpec.getRoomSize())
                .patches(hooverSpec.getPatches())
                .cleanedPatches(hooverSpec.getCleanedPatched())
                .cleanedPatchesCount(hooverSpec.getCleanedPatched().size())
                .instructions(hooverSpec.getInstructions())
                .startingCoordinates(hooverSpec.getCoords())
                .finalCoordinates(hooverSpec.getFinalCoords())
                .build();
    }
}
