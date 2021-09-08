package com.game.service;

import com.game.exception.BadRequestException;
import com.game.entity.Player;
import com.game.repository.PlayerRepository;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService{

    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public List<Player> getPlayersList() {
        List<Player> playerList = new ArrayList<>();
//        playerList.addAll(playerRepository.findAll());
        return playerList;
    }

    @Override
    public Integer getPlayersCount() {
        List<Player> playerList = new ArrayList<>();
        playerRepository.findAll().forEach(playerList::add);
        Integer count = playerList.size();
        return count;
    }

    @Override
    public Player getPlayer(Long id) {
 return playerRepository.findById(id).get();
//       if(player.isPresent()){
//           return player.get();
//       }
//       else throw  new NotFoundException(id);
//       return playerRepository.findById(id).orElseThrow(() ->
//                new NotFoundException(id));
}

    @Override
    public void createPlayer(Player player) {
        String strDate1 = "01-01-2000";
        String strDate2 = "01-01-3000";
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date one;
        Date two;
        try {
            one = formatter.parse(strDate1);
            two = formatter.parse(strDate2);
        }
        catch (ParseException e) {
            throw new BadRequestException();
        }

        if (player == null
                || player.getName() == null
                || player.getTitle() == null
                || player.getRace() == null
                || player.getProfession() == null
                || player.getName().equals("")
                || player.getExperience() == null
                || player.getBirthday() == null
                || player.getExperience() < 0
                || player.getExperience() > 10000000
                || player.getBirthday().getTime() < 0
                || player.getTitle().length() > 30
                || player.getName().length() > 12
                || player.getBirthday().before(one)
                || player.getBirthday().after(two)) {
            throw new BadRequestException();
        }
        if (player.getBanned() == null) {
            player.setBanned(false);
        }
        player.setLevel(Math.toIntExact(Math.round((Math.sqrt(2500 + 200 * player.getExperience()) - 50) / 100)));
        player.setUntilNextLevel(50 * (player.getLevel() + 1) * (player.getLevel() + 2) - player.getExperience());
        playerRepository.save(player);
    }


    @Override
    public void updatePlayer(Long id, Player player) {
        Player playerFromDb = playerRepository.findById(id).get();
        playerFromDb.setName(player.getName());
        playerFromDb.setTitle(player.getTitle());
        playerFromDb.setRace(player.getRace());
        playerFromDb.setProfession(player.getProfession());
        playerFromDb.setBirthday(player.getBirthday());
        playerFromDb.setBanned(player.getBanned());
        playerFromDb.setExperience(player.getExperience());
        playerRepository.save(playerFromDb);
    }

    @Override
    public void deletePlayer(Long id) {
            playerRepository.deleteById(id);;
    }

    @Override
    public boolean existsById(Long id) {
        return playerRepository.existsById(id);
    }


}
