package com.game.exception;

import com.game.entity.Player;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{
    private Long id;
    private Player player;

    public BadRequestException(Long id) {
        super("not found with: " + id);
        this.id = id;

    }

    public BadRequestException(Player player) {
        super("not found with: " + player);
        this.player = player;

    }

    public BadRequestException() {

    }

    public Long getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }
}

