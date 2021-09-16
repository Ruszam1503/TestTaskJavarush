package com.game.service;

import com.game.controller.PlayerOrder;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.exception.BadRequestException;
import com.game.entity.Player;
import com.game.repository.PlayerRepository;

import com.game.specification.PlayerSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PlayerServiceImpl implements PlayerService{

    PlayerRepository playerRepository;
    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }


    @Override
    public List<Player> getPlayersList(String name,
                                       String title,
                                       Race race,
                                       Profession profession,
                                       Long after,
                                       Long before,Boolean banned,
                                       Integer minExperience,
                                       Integer maxExperience,
                                       Integer minLevel,
                                       Integer maxLevel,
                                       PlayerOrder order,
                                       Integer pageNumber,
                                       Integer pageSize) {
        Sort sort = null;
        switch (order) {
            case ID:
                sort = Sort.by("id");
                break;
            case NAME:
                sort = Sort.by("name");
                break;
            case EXPERIENCE:
                sort = Sort.by("experience");
                break;
            case BIRTHDAY:
                sort = Sort.by("birthday");
                break;
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Player> page = playerRepository.findAll(PlayerSpecification.findPlayerByName(name)
                .and(PlayerSpecification.findPlayerByRace(race))
                .and(PlayerSpecification.findPlayerByProfession(profession))
                .and(PlayerSpecification.findPlayerByBanned(banned))
                .and(PlayerSpecification.findPlayerByTitle(title))
                .and(PlayerSpecification.findPlayerByDate(after,before))
                .and(PlayerSpecification.findPlayerByExperience(minExperience, maxExperience))
                .and(PlayerSpecification.findPlayerByLevel(minLevel, maxLevel)),
                pageable);
        return page.getContent();
    }

    @Override
    public Integer getPlayersCount(String name,
                                   String title,
                                   Race race,
                                   Profession profession,
                                   Long after,
                                   Long before,Boolean banned,
                                   Integer minExperience,
                                   Integer maxExperience,
                                   Integer minLevel,
                                   Integer maxLevel,
                                   PlayerOrder order,
                                   Integer pageNumber,
                                   Integer pageSize) {
        Sort sort = null;
        switch (order) {
            case ID:
                sort = Sort.by("id");
                break;
            case NAME:
                sort = Sort.by("name");
                break;
            case EXPERIENCE:
                sort = Sort.by("experience");
                break;
            case BIRTHDAY:
                sort = Sort.by("birthday");
                break;
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Player> page = playerRepository.findAll(PlayerSpecification.findPlayerByName(name)
                        .and(PlayerSpecification.findPlayerByRace(race))
                        .and(PlayerSpecification.findPlayerByProfession(profession))
                        .and(PlayerSpecification.findPlayerByBanned(banned))
                        .and(PlayerSpecification.findPlayerByTitle(title))
                        .and(PlayerSpecification.findPlayerByDate(after,before))
                        .and(PlayerSpecification.findPlayerByExperience(minExperience, maxExperience))
                        .and(PlayerSpecification.findPlayerByLevel(minLevel, maxLevel)),
                pageable);
        return (int)page.getTotalElements();
    }

    @Override
    public Player getPlayer(Long id) {
        return playerRepository.findById(id).get();
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
        player.setLevel((int)(Math.sqrt(2500 + 200 * player.getExperience())- 50) / 100);
        player.setUntilNextLevel(50 * (player.getLevel() + 1) * (player.getLevel() + 2) - player.getExperience());
        playerRepository.save(player);
    }


    @Override
    public Player updatePlayer(Long id, Player player) {
        Player playerFromDb = playerRepository.findById(id).get();

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



        if (player.getName()!=null
        && !(player.getName().equals(""))
        && player.getName().length() <= 12){
        playerFromDb.setName(player.getName());}


        if (player.getTitle()!=null
        && player.getTitle().length() <= 30){
        playerFromDb.setTitle(player.getTitle());}


        if (player.getRace()!=null){
        playerFromDb.setRace(player.getRace());}


        if (player.getProfession()!=null){
        playerFromDb.setProfession(player.getProfession());}


        if(player.getBirthday() != null) {
            if(player.getBirthday().getTime() < 0L)
                throw  new BadRequestException();
            playerFromDb.setBirthday(player.getBirthday());
        }

            if (player.getBanned()!=null){
        playerFromDb.setBanned(player.getBanned());}



        if(player.getExperience() != null) {

            if(player.getExperience() > 10_000_00 || player.getExperience() < 0)
                throw  new BadRequestException();

            playerFromDb.setExperience(player.getExperience());
            playerFromDb.setLevel((int)(Math.sqrt(2500 + 200 * player.getExperience())- 50) / 100);

            playerFromDb.setUntilNextLevel(50 * (playerFromDb.getLevel() + 1) * (playerFromDb.getLevel() + 2) - playerFromDb.getExperience());
        }

        playerFromDb.setId(id);
        return playerRepository.save(playerFromDb);

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



