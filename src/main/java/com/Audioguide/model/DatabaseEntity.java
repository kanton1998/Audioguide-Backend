package com.Audioguide.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import io.swagger.annotations.ApiModelProperty;

@MappedSuperclass
public class DatabaseEntity
{
    @ApiModelProperty(notes = "The database generated ID.")
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}