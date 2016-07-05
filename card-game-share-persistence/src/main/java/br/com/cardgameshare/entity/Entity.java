package br.com.cardgameshare.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class Entity implements Serializable {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
