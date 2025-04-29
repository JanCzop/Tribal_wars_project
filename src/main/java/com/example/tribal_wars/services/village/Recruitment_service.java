package com.example.tribal_wars.services.village;

import com.example.tribal_wars.entities.army.embbed.Army_details;
import com.example.tribal_wars.entities.village.Village;
import com.example.tribal_wars.entities.village.embbed.Recruitment;
import com.example.tribal_wars.repositories.Recruitment_repository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;

@Service
@Getter
public class Recruitment_service {

    private static final int TEST_ACCELERATION = 10;
    // TODO: MOVE PENALTY VALUE TO PROPERTIES
    private static final double MANY_UNITS_TYPE_PENALTY = 0.1;
    // TODO: RECRUITMENT QUEUE IS NOW INF
    private static final int MAX_RECRUITMENT_QUEUE = Integer.MAX_VALUE;

    private final Recruitment_repository recruitment_repository;
    @Autowired
    public Recruitment_service(Recruitment_repository recruitment_repository) {
        this.recruitment_repository = recruitment_repository;
    }

    public boolean is_recruitment_viable(Village village){
        return village.getRecruitment().size() <= MAX_RECRUITMENT_QUEUE;
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
        return army_details.get_army_map().values()
                .stream().mapToInt(Integer::intValue).sum() / TEST_ACCELERATION;
    }
    public void update_recruitment(Village village){
        Iterator<Recruitment> recruitments = village.getRecruitment().iterator();
        while(recruitments.hasNext()) {
            Recruitment current = recruitments.next();
            if(current.getRecruitment_end_time().isBefore(LocalDateTime.now())) {
                merge_units(village,current.getArmy_details());
                this.recruitment_repository.deleteById(current.getId());
                recruitments.remove();
            }
        }
    }

    public void merge_units(Village village, Army_details recruited_army){
        Army_details updated_army = village.getLocal_army().getArmy_details().merge(recruited_army);
        village.getLocal_army().setArmy_details(updated_army);
    }


    public void stop_recruitment(Village village, Recruitment recruitment){
        this.recruitment_repository.delete(recruitment);
        village.getRecruitment().remove(recruitment);
    }

}
