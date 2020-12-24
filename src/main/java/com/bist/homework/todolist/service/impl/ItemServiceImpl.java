package com.bist.homework.todolist.service.impl;

import com.bist.homework.todolist.dto.ItemDto;
import com.bist.homework.todolist.entity.Item;
import com.bist.homework.todolist.repository.ItemRepository;
import com.bist.homework.todolist.service.ItemService;
import com.bist.homework.todolist.util.PageDto;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;

    public ItemServiceImpl(ItemRepository itemRepository, ModelMapper modelMapper){
        this.itemRepository = itemRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ItemDto save(ItemDto item) {
        //Add Business Logic
        Item itemDb = modelMapper.map(item, Item.class);
        itemDb = itemRepository.save(itemDb);
        return modelMapper.map(itemDb, ItemDto.class);
    }

    @Override
    public PageDto<ItemDto> getAllPageable(Pageable pageable) {
        Page<Item> page = itemRepository.findAll(pageable);
        ItemDto[] itemDtos = modelMapper.map(page.getContent(),ItemDto[].class);
        PageDto pageDto = new PageDto(page, Arrays.asList(itemDtos));
        return pageDto;
    }

    @Override
    public Boolean delete(ItemDto item) {
        return null;
    }
}
