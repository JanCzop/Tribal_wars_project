package com.example.tribal_wars.Armies.Army_commands;



import com.example.tribal_wars.Exceptions.Exc_item_not_found;
import com.example.tribal_wars.Player.Player;
import com.example.tribal_wars.Player.Player_repository;
import com.example.tribal_wars.Village.Village;
import com.example.tribal_wars.Village.Village_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
;import java.util.List;
import java.util.Optional;

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

    public void validate_foreign_keys(Command command) {
        Optional.ofNullable(command.getOrigin_village())
                .ifPresent(originVillage -> this.village_repository.findById(originVillage.getCoordinates())
                        .orElseThrow(() -> new Exc_item_not_found("Origin village with Coordinates " + originVillage.getCoordinates() + " not found.")));

        Optional.ofNullable(command.getTarget_village())
                .ifPresent(targetVillage -> this.village_repository.findById(targetVillage.getCoordinates())
                        .orElseThrow(() -> new Exc_item_not_found("Target village with Coordinates " + targetVillage.getCoordinates() + " not found.")));

        Optional.ofNullable(command.getPlayer())
                .ifPresent(player -> this.player_repository.findById(player.getId())
                        .orElseThrow(() -> new Exc_item_not_found("Player with ID " + player.getId() + " not found.")));
    }

    public Command save_command(Command command){
        validate_foreign_keys(command);
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
        validate_foreign_keys(command);
        return this.command_repository.findById(id).map(c -> {
            c.setOrigin_village(command.getOrigin_village());
            c.setTarget_village(command.getTarget_village());
            c.setPlayer(command.getPlayer());
            c.setArmy_details(command.getArmy_details());
            return this.command_repository.save(c);
        }).orElseThrow(() -> new Exc_item_not_found("Command with ID " + id + " not found."));
    }
}
