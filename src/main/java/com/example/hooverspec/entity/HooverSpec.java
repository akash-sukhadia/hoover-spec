package com.example.hooverspec.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "hoover_spec")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TypeDefs({

        @TypeDef(name = "json", typeClass = JsonBinaryType.class)
})
public class HooverSpec {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "room_size")
    @Type(type = "json")
    private List<Integer> roomSize;

    @Column(name = "start_position")
    @Type(type = "json")
    private List<Integer> coords;

    @Column
    @Type(type = "json")
    private List<List<Integer>> patches;

    @Column
    private String instructions;

    @Column(name = "cleaned_patched")
    @Type(type = "json")
    private Set<List<Integer>> cleanedPatched;

    @Column(name = "end_position")
    @Type(type = "json")
    private List<Integer> finalCoords;
}
