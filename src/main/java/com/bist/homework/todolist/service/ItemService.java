package com.bist.homework.todolist.service;

import com.bist.homework.todolist.dto.ItemDto;
import com.bist.homework.todolist.util.PageDto;
import org.springframework.data.domain.Pageable;

public interface ItemService {
    ItemDto save(ItemDto item);
    PageDto<ItemDto> getAllPageable(Pageable pageable);
    Boolean delete (ItemDto item);
}
