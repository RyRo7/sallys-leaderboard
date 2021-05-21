package com.ryro.sallys.repository;

import com.ryro.sallys.dto.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author ryro
 * @since 2021/05/21
 */
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Player findById(int id);

    List<Player> findAllByFirstName(String firstName);

    List<Player> findAllByLastName(String lastName);

    List<Player> findAllByFirstNameAndLastName(String firstName, String lastName);
}
