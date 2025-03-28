package com.example.tribal_wars.backend.Player;

import com.example.tribal_wars.backend.Armies.Army_village.Army;
import com.example.tribal_wars.backend.Armies.Army_commands.Command;
import com.example.tribal_wars.backend.Village.Village;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Entity
@NoArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter @Setter
    private String username;
    @Getter @Setter
    private String email;

    @Getter String password;
    public void setPassword(String raw_password) {
        this.password = new BCryptPasswordEncoder().encode(raw_password);
    }

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    @Getter @Setter
    @JsonIgnore
    private Set<Village> villages;
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Army> armies;
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Command> army_commands;


}
