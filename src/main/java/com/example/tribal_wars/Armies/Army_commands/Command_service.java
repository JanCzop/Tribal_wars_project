package com.example.tribal_wars.Armies.Army_commands;



import com.example.tribal_wars.Exceptions.Exc_item_not_found;
import com.example.tribal_wars.Player.Player;
import com.example.tribal_wars.Player.Player_repository;
import com.example.tribal_wars.Village.Village;
import com.example.tribal_wars.Village.Village_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
;import java.util.List;

@Service
public class Command_service {

    private final Command_repository command_repository;
    private final Village_repository village_repository;
    private final Player_repository player_repository;
    @Autowired
    public Command_service(Command_repository command_repository, Village_repository villageRepository, Player_repository playerRepository) {
        this.command_repository = command_repository;
        this.village_repository = villageRepository;
        this.player_repository = playerRepository;
    }

    public Command save_command(Command command){
        return this.command_repository.save(command);
    }
    public void delete_command(Long id){
        this.command_repository.deleteById(id);
    }
    public Command get_command_by_id(Long id){
        return this.command_repository.findById(id).
                orElseThrow(() -> new Exc_item_not_found("Command with ID " + id + " not found."));
    }
    public List<Command> get_all_commands(){
        return this.command_repository.findAll();
    }
    public Command put_command(Long id, Command command){
        Village origin_village = this.village_repository.findById(command.getOrigin_village().getCoordinates())
                .orElseThrow(() -> new Exc_item_not_found("Origin village with Coordinates " + command.getOrigin_village().getCoordinates() + " not found."));
        Village target_village = this.village_repository.findById(command.getTarget_village().getCoordinates())
                .orElseThrow(() -> new Exc_item_not_found("Target village with Coordinates " + command.getTarget_village().getCoordinates() + " not found."));
        Player player = this.player_repository.findById(command.getPlayer().getId())
                .orElseThrow(() -> new Exc_item_not_found("Player with ID " + command.getPlayer().getId() + " not found."));
        return this.command_repository.findById(id).map(c -> {
            c.setOrigin_village(origin_village);
            c.setTarget_village(target_village);
            c.setPlayer(player);
            c.setArmy_details(command.getArmy_details());
            return this.command_repository.save(c);
        }).orElseThrow(() -> new Exc_item_not_found("Command with ID " + id + " not found."));
    }
}
