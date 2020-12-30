package com.bist.homework.todolist.repository;

import com.bist.homework.todolist.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * JPA repository get basic methods such as get,save,delete,pagination...
 * <Item,Long>
 *     Item defines that this is an Item Repository
 *     Long defines Item table id type
 *  Each Entity has an own repository. It supports single responsibility
 * For JPA repository no need to annotate @Respository, It is already injectable.
 */
public interface ItemRepository extends JpaRepository<Item,Long> {
    //get Item name by name. Spring Data supports this approach. it needs to start with get or find.
    //And it must be matched with Entity properties.
    Item getByName(String name);
}
