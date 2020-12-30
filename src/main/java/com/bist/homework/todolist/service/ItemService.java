package com.bist.homework.todolist.service;

import com.bist.homework.todolist.dto.ItemDto;
import com.bist.homework.todolist.util.PageDto;
import org.springframework.data.domain.Pageable;

/**
 * Service interface
 */
public interface ItemService {
    ItemDto save(ItemDto itemDto);
    ItemDto update(Long id, ItemDto itemDto);
    PageDto<ItemDto> getAllPageable(Pageable pageable);
    Boolean delete (Long id);
}
