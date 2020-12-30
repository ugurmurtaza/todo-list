package com.bist.homework.todolist.service.impl;

import com.bist.homework.todolist.dto.ItemDto;
import com.bist.homework.todolist.entity.Item;
import com.bist.homework.todolist.repository.ItemRepository;
import com.bist.homework.todolist.service.ItemService;
import com.bist.homework.todolist.util.PageDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;

//Service : JPA : defines a service, create singleton instance after repositories.
@Service
@Slf4j
/**
 * Service implementation
 */
public class ItemServiceImpl implements ItemService {

    //Item repository injected as a Constructor injection.
    private final ItemRepository itemRepository;

    //Modelmapper for conversion of Entity and Model. It is injected too.
    private final ModelMapper modelMapper;

    public ItemServiceImpl(ItemRepository itemRepository, ModelMapper modelMapper){
        this.itemRepository = itemRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * save()
     * save new item if not exist
     * params : itemDto
     * return : itemDto with ID
     */
    @Override
    public ItemDto save(ItemDto itemDto) {
        log.info("ItemServiceImpl -> save");
        log.debug("ItemServiceImpl -> save -> Request Parameters -> itemDto : "+itemDto.toString());
        Item itemIsExist = itemRepository.getByName(itemDto.getName());

        if (itemIsExist != null) {
            throw new IllegalArgumentException("Item already exists");
        }

        Item item = modelMapper.map(itemDto, Item.class);
        item = itemRepository.save(item);
        itemDto.setId(item.getId());
        log.debug("ItemServiceImpl -> save -> Parameters -> Response itemDto : "+itemDto.toString());
        return itemDto;
    }

    /**
     * update()
     * update an item if exist
     * params : id,itemDto
     * return : updated itemDto
     */
    @Override
    public ItemDto update(Long id, ItemDto itemDto) {
        log.info("ItemServiceImpl -> update");
        log.debug("ItemServiceImpl -> update -> Request Parameters -> itemDto : "+itemDto.toString());
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
        log.debug("ItemServiceImpl -> update -> Response Parameters -> itemDb : "+itemDb.toString());
        return modelMapper.map(itemDb, ItemDto.class);
    }


    /**
     * getAllPageable()
     * all items get as page
     * params : pageable
     * return : page of item array
     */
    @Override
    public PageDto<ItemDto> getAllPageable(Pageable pageable) {
        log.info("ItemServiceImpl -> getAllPageable");
        log.debug("ItemServiceImpl -> getAllPageable -> Request Parameters -> pageable : "+pageable.toString());
        Page<Item> page = itemRepository.findAll(pageable);
        ItemDto[] itemDtos = modelMapper.map(page.getContent(),ItemDto[].class);
        log.debug("ItemServiceImpl -> getAllPageable -> Response Parameters -> page content : "+page.getContent());
        return new PageDto<ItemDto>(page,Arrays.asList(itemDtos));
    }

    /**
     * delete()
     * delete an item
     * params : id
     * return : true
     */
    @Override
    public Boolean delete(Long id) {
        log.info("ItemServiceImpl -> delete");
        log.debug("ItemServiceImpl -> delete -> Request Parameters -> id : "+id);
        itemRepository.deleteById(id);
        return true;
    }
}
