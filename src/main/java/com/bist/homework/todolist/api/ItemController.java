package com.bist.homework.todolist.api;

import com.bist.homework.todolist.dto.ItemDto;
import com.bist.homework.todolist.service.impl.ItemServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@RestController
@RequestMapping("/item")
@Tag(name = "ItemController", description = "Item APIs")
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
        return ResponseEntity.ok(itemServiceImpl.save(itemDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Item Operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update the Item",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ItemDto.class))})})
    public ResponseEntity<ItemDto> updateItem(@PathVariable(value = "id",required = true) Long id, @Valid @RequestBody ItemDto itemDto){
        return ResponseEntity.ok(itemServiceImpl.update(id,itemDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Item Operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete the Item",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Boolean.class))})})
    public ResponseEntity<Boolean> deleteItem(@PathVariable(value = "id",required = true) Long id){
        return ResponseEntity.ok(itemServiceImpl.delete(id));
    }
}
