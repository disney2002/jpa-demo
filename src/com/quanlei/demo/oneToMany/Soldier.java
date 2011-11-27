/*
 * Soldier.java
 * 
 * Copyright(c) 2007-2011 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2011-11-27 17:17:39
 */
package com.quanlei.demo.oneToMany;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author XiaoQuan
 */
@Entity
public class Soldier implements Serializable {

    private static final long serialVersionUID = -3554501851845952574L;
    private Long id;
//    private Troop troop;

//    @ManyToOne
//    @JoinColumn(name = "troop_fk")
//    @JoinColumn(name = "troop_fk", insertable = false, updatable = false)
//    public Troop getTroop() {
//        return troop;
//    }

//    public void setTroop(Troop troop) {
//        this.troop = troop;
//    }

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
