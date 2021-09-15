package com.game.service;

import com.game.controller.PlayerOrder;
import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface PlayerService {
    //Получить список игроков
    public List<Player> getPlayersList(String name,
                                       String title,
                                       Race race,
                                       Profession profession,
                                       Long after,
                                       Long before,
                                       Boolean banned,
                                       Integer minExperience,
                                       Integer maxExperience,
                                       Integer minLevel,
                                       Integer maxLevel,
                                       PlayerOrder order,
                                       Integer pageNumber,
                                       Integer pageSize);

    //Получите количество игроков
    public Integer getPlayersCount();

    //Получить игрока по id
    public Player getPlayer (Long id);

    //Создать игрока
    public void createPlayer (Player player);

    //Обновить игрока
    public Player updatePlayer ( Long id, Player player);

    //Удалить игрока
    public void deletePlayer ( Long id);

    boolean existsById (Long id);


}
