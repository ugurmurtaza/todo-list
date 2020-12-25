package com.bist.homework.todolist.repository;

import com.bist.homework.todolist.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long> {

    Item getByName(String name);
    List<Item> findAll(Sort sort);
    Page<Item> findAll(Pageable pageable);
}
