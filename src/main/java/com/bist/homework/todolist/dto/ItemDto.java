package com.bist.homework.todolist.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Item Data Transfer Object")
public class ItemDto {

    @Schema(description = "Item ID")
    private Long id;

    @NotNull
    @Schema(description= "Item Name", required = true)
    private String name;

    @NotNull
    @Schema(description= "Due Date", required = true)
    private Date date;

    @NotNull
    @Schema(description= "Status", required = true)
    private Boolean checked;
}
