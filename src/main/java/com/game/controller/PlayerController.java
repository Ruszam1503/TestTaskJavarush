package com.game.controller;

import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.entity.Player;
import com.game.service.PlayerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/rest/players")
public class PlayerController {

    private final PlayerService playerService;
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    //Получить список игроков
        @GetMapping
        public ResponseEntity<List<Player>> getPlayersList(@RequestParam("name") String name,
                                                           @RequestParam("title")String title,
                                                           @RequestParam("race") Race race,
                                                           @RequestParam("profession") Profession profession,
                                                           @RequestParam("after")Long after,
                                                           @RequestParam("before")Long before,
                                                           @RequestParam("banned")Boolean banned,
                                                           @RequestParam("minExperience")Integer minExperience,
                                                           @RequestParam("maxExperience")Integer maxExperience,
                                                           @RequestParam("minLevel")Integer minLevel,
                                                           @RequestParam("maxLevel")Integer maxLevel,
                                                           @RequestParam("order")PlayerOrder order,
                                                           @RequestParam("pageNumber")Integer pageNumber,
                                                           @RequestParam("pageSize")Integer pageSize) {
            List<Player> playerList = playerService.getPlayersList();
            return new ResponseEntity<>(playerList, HttpStatus.OK);
    }

    //Получить игрока по id
    @GetMapping({"/{id}"})
    public ResponseEntity<Player> getPlayer(@PathVariable Long id) {
//        String strId = Long.toString(id);
//        if ( id>0 && strId.matches("\\d+")) {
//            return new ResponseEntity<>( playerService.getPlayer(id), HttpStatus.OK);
//        }
//        else return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
//    }

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

//        String strId = Long.toString(id);
//       if (id==0 || id<0 || !(strId.matches("\\d+")) ){
//            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);}
//        else playerService.deletePlayer(id);
//     return new ResponseEntity<>(HttpStatus.OK);


    }
    //Обновить игрока


}
