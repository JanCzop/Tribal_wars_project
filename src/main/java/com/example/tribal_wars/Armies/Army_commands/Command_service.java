package com.example.tribal_wars.Armies.Army_commands;



import com.example.tribal_wars.Armies.Army_commands.Command;
import com.example.tribal_wars.Armies.Army_commands.Command_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
;import java.util.List;
import java.util.Optional;

@Service
public class Command_service {

    private final Command_repository command_repository;
    @Autowired
    public Command_service(Command_repository command_repository) {
        this.command_repository = command_repository;
    }

    public Command save_command(Command command){
        return this.command_repository.save(command);
    }
    public void delete_command(Long id){
        this.command_repository.deleteById(id);
    }
    public Optional<Command> get_command_by_id(Long id){
        return this.command_repository.findById(id);
    }
    public List<Command> get_all_commands(){
        return this.command_repository.findAll();
    }
    public Optional<Command> put_command(Long id, Command command){
        return this.command_repository.findById(id).map(c -> {
            c.setOrigin_village(command.getOrigin_village());
            c.setTarget_village(command.getTarget_village());
            c.setPlayer(command.getPlayer());
            c.setArmy_details(command.getArmy_details());
            return this.command_repository.save(c);
        });
    }
}
