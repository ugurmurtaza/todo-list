package com.bist.homework.todolist.repository;

import com.bist.homework.todolist.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long> {
    List<Item> findAll(Sort sort);
    Page<Item> findAll(Pageable pageable);
}
