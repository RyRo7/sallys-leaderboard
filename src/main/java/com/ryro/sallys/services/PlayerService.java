package com.ryro.sallys.services;

import com.ryro.sallys.dto.entity.Player;
import com.ryro.sallys.repository.PlayerRepository;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ryro
 * @since 2021/05/21
 */
@Log
@Service
public class PlayerService {
    private final PlayerRepository playerRepository;


    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player getPlayer(int id) {
        return playerRepository.findById(id);
    }

    public List<Player> getPlayer(String firstName, String lastName) {
        if (lastName == null || lastName.equals("")) {
            return playerRepository.findAllByFirstName(firstName);
        } else if (firstName == null || firstName.equals("")) {
            return playerRepository.findAllByLastName(lastName);
        } else {
            return playerRepository.findAllByFirstNameAndLastName(firstName, firstName);
        }
    }

    public String createPlayer(Player player){
        String response = "";
        try {
            playerRepository.save(player);
            response = "Successfully saved new player [" + player.getFirstName() + " " + player.getLastName() + "]";
        } catch (RuntimeException e) {
            response = "Could not save new player [" + player.getFirstName() + " " + player.getLastName() + "] :: " + e.getMessage();
            log.severe(response);
        }

        return response;
    }
}
