/*
 * Flight.java
 * 
 * Copyright(c) 2007-2011 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2011-11-27 17:10:56
 */
package com.quanlei.demo.oneToMany;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

/**
 *
 * @author XiaoQuan
 */
@Entity
public class Troop implements Serializable {

    private static final long serialVersionUID = -553268835986767901L;
    private Long id;
    private Set<Soldier> soldiers;

    public Troop() {
        this.id = 1L;
    }

//    @OneToMany(mappedBy = "troop", cascade = CascadeType.ALL)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "troop_fk")
    @JoinTable(name = "TroopSoldiers",
    joinColumns =
    @JoinColumn(name = "troop_id"),
    inverseJoinColumns =
    @JoinColumn(name = "soldier_id"))
    public Set<Soldier> getSoldiers() {
        return soldiers;
    }

    public void setSoldiers(Set<Soldier> soldiers) {
        this.soldiers = soldiers;
    }

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
