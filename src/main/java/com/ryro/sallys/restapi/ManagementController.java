package com.ryro.sallys.restapi;

import com.ryro.sallys.dto.entity.Player;
import com.ryro.sallys.dto.rest.PlayerDto;
import com.ryro.sallys.services.PlayerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ryro
 * @since 2021/05/21
 */
@Log
@RestController
@Api(tags = "Management API")
public class ManagementController {
    private final PlayerService playerService;


    public ManagementController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/create-player")
    @ApiOperation("Create a new player")
    public String createPlayer(@RequestBody PlayerDto playerDto) {
        return playerService.createPlayer(mapPlayerEnity(playerDto));
    }

    @PostMapping("/get-player")
    @ApiOperation("Get player list")
    public List<PlayerDto> getPlayers(@RequestBody PlayerDto playerDto) {
        List<Player> playerList = playerService.getPlayer(playerDto.getFirstName(), playerDto.getLastName());
        return mapPlayerDtoList(playerList);
    }

    private List<PlayerDto> mapPlayerDtoList(List<Player> playerList) {
        List<PlayerDto> response = new ArrayList<>();
        for(Player player : playerList) {
            response.add(mapPlayerDto(player));
        }
        return response;
    }

    private PlayerDto mapPlayerDto(Player player) {
        return new PlayerDto(
                player.getFirstName(),
                player.getLastName()
        );
    }

    private Player mapPlayerEnity(PlayerDto playerDto){
        Player player = new Player();
        player.setFirstName(playerDto.getFirstName());
        player.setLastName(playerDto.getLastName());
        return player;
    }
}
