package com.game.service;

import com.game.entity.Player;

import java.util.List;

public interface PlayerService {
    //Получить список игроков
    public List<Player> getPlayersList();

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
