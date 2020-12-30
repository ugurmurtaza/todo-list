package com.bist.homework.todolist.api;

import com.bist.homework.todolist.dto.ItemDto;
import com.bist.homework.todolist.service.impl.ItemServiceImpl;
import com.bist.homework.todolist.util.PageDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@RestController
@RequestMapping("/item")
@Tag(name = "ItemController", description = "Item APIs")
@CrossOrigin
@Slf4j//Logging instance log4j
public class ItemController {

    private final ItemServiceImpl itemServiceImpl;

    public ItemController(ItemServiceImpl itemServiceImpl) {
        this.itemServiceImpl = itemServiceImpl;
    }

    @PostMapping
    @Operation(summary = "Create Item Operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Item",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ItemDto.class))})})

    public ResponseEntity<ItemDto> createItem(@Valid @RequestBody ItemDto itemDto) {
        log.info("ItemController -> createItem");
        log.debug("ItemController -> createItem -> Parameters -> itemDto : "+itemDto.toString());
        return ResponseEntity.ok(itemServiceImpl.save(itemDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Item Operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update the Item",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ItemDto.class))})})
    public ResponseEntity<ItemDto> updateItem(@PathVariable(value = "id",required = true) Long id, @Valid @RequestBody ItemDto itemDto){
        log.info("ItemController -> updateItem");
        log.debug("ItemController -> updateItem -> Parameters -> id : "+id);
        log.debug("ItemController -> updateItem -> Parameters -> itemDto : "+itemDto.toString());
        return ResponseEntity.ok(itemServiceImpl.update(id,itemDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Item Operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete the Item",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Boolean.class))})})
    public ResponseEntity<Boolean> deleteItem(@PathVariable(value = "id",required = true) Long id){
        log.info("ItemController -> deleteItem");
        log.debug("ItemController -> deleteItem -> Parameters -> id : "+id);
        return ResponseEntity.ok(itemServiceImpl.delete(id));
    }

    @GetMapping("/pagination")
    public ResponseEntity<PageDto<ItemDto>> getAllByPagination(Pageable pageable){
        log.info("ItemController -> getAllByPagination");
        log.debug("ItemController -> getAllByPagination -> Parameters -> pageable : "+pageable.toString());
        PageDto<ItemDto> pageDto = itemServiceImpl.getAllPageable(pageable);
        return ResponseEntity.ok(pageDto);
    }
}
