package com.bist.homework.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Date date;
    @NotNull
    private Boolean checked;


}
