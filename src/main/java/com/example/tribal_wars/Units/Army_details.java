package com.example.tribal_wars.Units;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.EnumMap;
import java.util.Map;

@Embeddable
@Getter @Setter
public class Army_details {
    private Integer spearman;
    private Integer swordsman;
    private Integer axeman;
    private Integer archer;
    private Integer scout;
    private Integer light_cavalry;
    private Integer heavy_cavalry;
    private Integer ram;
    private Integer catapult;
    private Integer noble;

}
