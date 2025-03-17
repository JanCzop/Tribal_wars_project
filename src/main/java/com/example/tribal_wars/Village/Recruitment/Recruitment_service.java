package com.example.tribal_wars.Village.Recruitment;

import com.example.tribal_wars.Armies.Army_details;
import com.example.tribal_wars.Village.Village;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
@Getter
public class Recruitment_service {

    private static final int TEST_ACCELERATION = 10;
    // TODO: MOVE PENALTY VALUE TO PROPERTIES
    private static final double MANY_UNITS_TYPE_PENALTY = 0.1;

    private final Recruitment_repository recruitment_repository;
    @Autowired
    public Recruitment_service(Recruitment_repository recruitment_repository) {
        this.recruitment_repository = recruitment_repository;
    }


    public void start_recruitment(Village village, Army_details army_details){
        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        LocalDateTime recruitment_end = now.plus
                (calculate_recruitment_time(army_details), ChronoUnit.SECONDS).truncatedTo(ChronoUnit.SECONDS);
        Recruitment recruitment = new Recruitment(army_details,now,recruitment_end,village);
        this.recruitment_repository.save(recruitment);
        village.getRecruitment().add(recruitment);
    }
    private int calculate_recruitment_time(Army_details army_details){
        // TODO: RECRUITMENT TIME WILL BE EXECUTED WITH PERCENTAGE PENALTY FOR EACH TYPE (PROTOTYPE)
        return Army_details.get_army_map(army_details).values()
                .stream().mapToInt(Integer::intValue).sum() / TEST_ACCELERATION;
    }
    public void update_recruitment(){}
    public void stop_recruitment(Village village, Recruitment recruitment){
        this.recruitment_repository.delete(recruitment);
        village.getRecruitment().remove(recruitment);
    }
    public void end_recruitment(Village village, Recruitment recruitment){
        merge_units(village, recruitment.getArmy_details());
        stop_recruitment(village, recruitment);
    }
    public void merge_units(Village village, Army_details recruited_army){
        Army_details updated_army = village.getLocal_army().getArmy_details().merge(recruited_army);
        village.getLocal_army().setArmy_details(updated_army);
    }
}
