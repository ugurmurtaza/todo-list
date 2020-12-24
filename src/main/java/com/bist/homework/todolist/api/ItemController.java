package com.bist.homework.todolist.api;

import com.bist.homework.todolist.dto.ItemDto;
import com.bist.homework.todolist.service.impl.ItemServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/item")
public class ItemController {

    private final ItemServiceImpl itemServiceImpl;

    public ItemController(ItemServiceImpl itemServiceImpl) {
        this.itemServiceImpl = itemServiceImpl;
    }


    @PostMapping
    public ResponseEntity<ItemDto> createItem(@Valid @RequestBody ItemDto itemDto) {
        return ResponseEntity.ok(itemServiceImpl.save(itemDto));
    }
}
