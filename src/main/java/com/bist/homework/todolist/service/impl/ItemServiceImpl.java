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
    public ItemDto save(ItemDto itemDto) {
        Item itemIsExist = itemRepository.getByName(itemDto.getName());

        if (itemIsExist != null) {
            throw new IllegalArgumentException("Item already exists");
        }

        Item item = modelMapper.map(itemDto, Item.class);
        item = itemRepository.save(item);
        itemDto.setId(item.getId());

        return itemDto;
    }

    @Override
    public ItemDto update(Long id, ItemDto itemDto) {
        Item itemDb = itemRepository.getOne(id);
        if (itemDb==null){
            throw new IllegalArgumentException("Item : " + id + " does not exists!");
        }
        Item itemIsExist = itemRepository.getByName(itemDto.getName());
        if (itemIsExist != null) {
            throw new IllegalArgumentException("Item already exists");
        }
        itemDb.setName(itemDto.getName());
        itemDb.setDate(itemDto.getDate());

        itemRepository.save(itemDb);
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

    @Override
    public Boolean delete(Long id) {
        itemRepository.deleteById(id);
        return true;
    }
}
