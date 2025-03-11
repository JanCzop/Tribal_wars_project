package com.example.tribal_wars.Enums;

import lombok.Getter;

@Getter
public enum Unit_type {
    Spearman(Unit_subtype.Infantry, 50),
    Swordsman(Unit_subtype.Infantry, 70),
    Axeman(Unit_subtype.Infantry, 80),
    Archer(Unit_subtype.Infantry, 60),
    Scout(Unit_subtype.Infantry, 40),
    Light_cavalry(Unit_subtype.Cavalry, 90),
    Heavy_cavalry(Unit_subtype.Cavalry, 120),
    Ram(Unit_subtype.Siege, 150),
    Catapult(Unit_subtype.Siege, 200),
    Noble(Unit_subtype.Infantry, 150);

    Unit_type(Unit_subtype unit_subtype, Integer overall_power) {
        this.unit_subtype = unit_subtype;
        this.overall_power = overall_power;
    }

    public enum Unit_subtype{
        Infantry, Cavalry, Siege
    }

    private final Unit_subtype unit_subtype;
    private final Integer overall_power;
    //TODO : CONSTRUCT VALID STATS FOR UNITS

}
