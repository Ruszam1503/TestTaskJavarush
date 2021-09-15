package com.game.repository;

import com.game.entity.Player;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface PlayerRepository extends PagingAndSortingRepository<Player,Long>, JpaSpecificationExecutor<Player>{
    boolean existsById(Long id);
//    Page<Player> findAll(Pageable pageable);
//    Page<Player> findAll(Specification<Player> PlayerSpecification, Pageable pageable);
}

