package com.heren.his.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by joshua on 2016/5/6.
 */

@Entity
@Table(name = "NODE_TYPE")
@XmlRootElement
public class NodeType {
    @Id
    @Column(name = "ID")
    private long id;

    @Column(name = "DESCRIPTION")
    private String description;

    public NodeType(long id, String description) {
        this.id = id;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
