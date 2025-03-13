package com.example.tribal_wars.Enums;

import lombok.Getter;

@Getter
public enum Unit_type {
    Spearman(Unit_subtype.Infantry, 50, 30),
    Swordsman(Unit_subtype.Infantry, 70, 40),
    Axeman(Unit_subtype.Infantry, 80, 50),
    Archer(Unit_subtype.Infantry, 60, 35),
    Scout(Unit_subtype.Infantry, 40, 25),
    Light_cavalry(Unit_subtype.Cavalry, 90, 60),
    Heavy_cavalry(Unit_subtype.Cavalry, 120, 80),
    Ram(Unit_subtype.Siege, 150, 100),
    Catapult(Unit_subtype.Siege, 200, 120),
    Noble(Unit_subtype.Infantry, 150, 90);

    Unit_type(Unit_subtype unit_subtype, Integer overall_power, Integer recruitment_time) {
        this.unit_subtype = unit_subtype;
        this.overall_power = overall_power;
        this.recruitment_time = recruitment_time;
    }

    public enum Unit_subtype{
        Infantry, Cavalry, Siege
    }

    private final Unit_subtype unit_subtype;
    private final Integer recruitment_time;
    private final Integer overall_power;
    //TODO : CONSTRUCT VALID STATS FOR UNITS

}
