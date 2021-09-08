package com.game.exception;

import com.game.entity.Player;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{
    private Long id;
    private Player player;

    public NotFoundException(Long id) {
        super("not found with: " + id);
        this.id = id;

    }

    public NotFoundException(Player player) {
        super("not found with: " + player);
        this.player = player;

    }

    public NotFoundException() {

    }

    public Long getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }
}
