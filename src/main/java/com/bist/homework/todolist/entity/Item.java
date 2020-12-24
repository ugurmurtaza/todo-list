package com.bist.homework.todolist.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Item{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "item_name",length = 30, unique = true)
    private String name;

    @Column(name = "due_date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "is_checked")
    private Boolean checked;

}
