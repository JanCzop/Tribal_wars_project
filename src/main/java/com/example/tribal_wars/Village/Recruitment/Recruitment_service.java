package com.example.tribal_wars.Village.Recruitment;

import com.example.tribal_wars.Armies.Army_details;
import com.example.tribal_wars.Village.Village;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Getter
public class Recruitment_service {
    private final Recruitment_repository recruitment_repository;
    @Autowired
    public Recruitment_service(Recruitment_repository recruitment_repository) {
        this.recruitment_repository = recruitment_repository;
    }


    public boolean is_recruitment_viable(Village village){
        return village.getRecruitment() == null;
    }
    public void start_recruitment(Village village, Army_details army_details){
        Recruitment recruitment = new Recruitment();

    }
    //public void stop_recruitment(Village village){}
    //public void end_recruitment(Village village){}
    //public void merge_units(Village village){}
}
