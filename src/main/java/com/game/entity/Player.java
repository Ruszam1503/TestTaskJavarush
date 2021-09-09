package com.game.entity;

import com.game.entity.Profession;
import com.game.entity.Race;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "player")
public class Player {

    public Player() {

    }
    public Player(Long id,
                  String name,
                  String title,
                  Race race,
                  Profession profession,
                  Integer experience,
                  Integer level,
                  Integer untilNextLevel,
                  Date birthday,
                  Boolean banned) {

        this.id = id;
        this.name = name;
        this.title = title;
        this.race = race;
        this.profession = profession;
        this.experience = experience;
        this.level = level;
        this.untilNextLevel = untilNextLevel;
        this.birthday = birthday;
        this.banned = banned;
    }

    //ID игрока
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Имя персонажа (до 12 знаков включительно)
    @Column(name = "name")
    private String name;

    //Титул персонажа (до 30 знаков включительно)
    @Column(name = "title")
    private String title;

    //Расса персонажа
    @Enumerated(EnumType.STRING)
    private Race race;

    //Профессия персонажа
    @Enumerated(EnumType.STRING)
    private Profession profession;

    //Опыт персонажа. Диапазон значений 0..10,000,000
    @Column(name = "experience")
    private Integer experience;

    //Уровень персонажа
    @Column(name = "level")
    private Integer level;

    //Остаток опыта до следующего уровня
    @Column(name = "untilNextLevel")
    private Integer untilNextLevel;

    //Дата регистрации
    @Column(name = "birthday")
    private Date birthday;

    //Забанен / не забанен
    @Column(name = "banned")
    private Boolean banned;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getUntilNextLevel() {
        return untilNextLevel;
    }

    public void setUntilNextLevel(Integer untilNextLevel) {
        this.untilNextLevel = untilNextLevel;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Boolean getBanned() {
        return banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", race=" + race +
                ", profession=" + profession +
                ", experience=" + experience +
                ", level=" + level +
                ", untilNextLevel=" + untilNextLevel +
                ", birthday=" + birthday +
                ", banned=" + banned +
                '}';
    }



}
