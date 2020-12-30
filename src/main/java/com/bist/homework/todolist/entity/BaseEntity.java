package com.bist.homework.todolist.entity;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
//Data : Lombok : Add setter getter automatically.
@MappedSuperclass
//MappedSuperClass : JPA : Defined for every table.
/**
 * Shared Entity which is used by all entities.
 * It would be usefull for ORM implementations.
 * Include attributes which must exist in every table, such as create time, status
 * In this project, just use Serializable for I/0 operations such as transfering network or writing into disk.
 */
public abstract class BaseEntity implements Serializable {
}
