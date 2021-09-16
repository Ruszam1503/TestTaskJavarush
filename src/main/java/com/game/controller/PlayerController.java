package com.game.controller;

import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.entity.Player;
import com.game.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping("/rest/players")
public class PlayerController {

    PlayerService playerService;
    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    //Получить список игроков
        @GetMapping
        public ResponseEntity<List<Player>> getPlayersList(@RequestParam(name ="name", defaultValue = "", required = false)String name,
                                                           @RequestParam(name ="title", defaultValue = "", required = false)String title,
                                                           @RequestParam(name ="race", defaultValue = "", required = false) Race race,
                                                           @RequestParam(name ="profession", defaultValue = "", required = false) Profession profession,
                                                           @RequestParam(name ="after", defaultValue = "", required = false)Long after,
                                                           @RequestParam(name ="before", defaultValue = "", required = false)Long before,
                                                           @RequestParam(name ="banned", defaultValue = "", required = false)Boolean banned,
                                                           @RequestParam(name ="minExperience", defaultValue = "", required = false)Integer minExperience,
                                                           @RequestParam(name ="maxExperience", defaultValue = "", required = false)Integer maxExperience,
                                                           @RequestParam(name ="minLevel", defaultValue = "", required = false)Integer minLevel,
                                                           @RequestParam(name ="maxLevel", defaultValue = "", required = false)Integer maxLevel,
                                                           @RequestParam( name ="order", defaultValue = "ID", required = false) PlayerOrder order,
                                                           @RequestParam(name ="pageNumber", defaultValue = "0", required = false)Integer pageNumber,
                                                           @RequestParam(name ="pageSize", defaultValue = "3", required = false)Integer pageSize)
        {
            HttpHeaders headers = new HttpHeaders();
            List<Player> playerList = playerService.getPlayersList( name, title, race, profession, after, before, banned, minExperience, maxExperience, minLevel, maxLevel, order, pageNumber, pageSize);
            return new ResponseEntity<>(playerList,headers, HttpStatus.OK);
    }

    //Получить количество игроков
    @GetMapping("/count")
    public ResponseEntity<Integer> GetPlayersCount(@RequestParam(name ="name", defaultValue = "", required = false)String name,
                                                   @RequestParam(name ="title", defaultValue = "", required = false)String title,
                                                   @RequestParam(name ="race", defaultValue = "", required = false) Race race,
                                                   @RequestParam(name ="profession", defaultValue = "", required = false) Profession profession,
                                                   @RequestParam(name ="after", defaultValue = "", required = false)Long after,
                                                   @RequestParam(name ="before", defaultValue = "", required = false)Long before,
                                                   @RequestParam(name ="banned", defaultValue = "", required = false)Boolean banned,
                                                   @RequestParam(name ="minExperience", defaultValue = "", required = false)Integer minExperience,
                                                   @RequestParam(name ="maxExperience", defaultValue = "", required = false)Integer maxExperience,
                                                   @RequestParam(name ="minLevel", defaultValue = "", required = false)Integer minLevel,
                                                   @RequestParam(name ="maxLevel", defaultValue = "", required = false)Integer maxLevel,
                                                   @RequestParam( name ="order", defaultValue = "ID", required = false) PlayerOrder order,
                                                   @RequestParam(name ="pageNumber", defaultValue = "0", required = false)Integer pageNumber,
                                                   @RequestParam(name ="pageSize", defaultValue = "3", required = false)Integer pageSize)
    {
        HttpHeaders headers = new HttpHeaders();
        Integer playersCount = playerService.getPlayersCount( name, title, race, profession, after, before, banned, minExperience, maxExperience, minLevel, maxLevel, order, pageNumber, pageSize);
        return new ResponseEntity<>(playersCount,headers, HttpStatus.OK);
    }

    //Получить игрока по id
    @GetMapping({"/{id}"})
    public ResponseEntity<Player> getPlayer(@PathVariable Long id) {

        if (id == null || id == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(!playerService.existsById(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Player player = playerService.getPlayer(id);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }
    //Создать игрока
  @PostMapping
    public ResponseEntity<Player> createPlayer(@RequestBody(required = false) Player newPlayer) {
        HttpHeaders headers = new HttpHeaders();
        try {
            playerService.createPlayer(newPlayer);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(newPlayer,headers, HttpStatus.OK);
    }


    //Удалить игрока
    @DeleteMapping({"/{id}"})
    public ResponseEntity<Player> deletePlayer(@PathVariable("id") Long id) {
        if (id == null || id == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(!playerService.existsById(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        playerService.deletePlayer(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }
    //Обновить игрока
    @PostMapping({"/{id}"})
    public ResponseEntity<Player> updatePlayer(@PathVariable("id") Long id , @RequestBody Player player) {
        HttpHeaders headers = new HttpHeaders();
        if (id == null || id == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(!playerService.existsById(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if(player.getName()==null &&
                player.getTitle()==null &&
                player.getRace()==null &&
                player.getProfession()==null &&
                player.getBirthday()==null &&
                player.getBanned()==null &&
                player.getExperience()==null)
            return new ResponseEntity<>(playerService.getPlayer(id), HttpStatus.OK);
        try {
            playerService.updatePlayer(id, player);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(playerService.updatePlayer(id,player),headers, HttpStatus.OK);
    }

}
