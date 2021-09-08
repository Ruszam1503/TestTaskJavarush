package com.game.repository;

import com.game.entity.Player;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends PagingAndSortingRepository<Player,Long>, JpaSpecificationExecutor<Player>{
    boolean existsById(Long id);
}

