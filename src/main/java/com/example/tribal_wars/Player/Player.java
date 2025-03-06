package com.example.tribal_wars.Player;

import com.example.tribal_wars.Village.Village;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

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
    private List<Village> villages;

}
