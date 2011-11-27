/*
 * Passport.java
 * 
 * Copyright(c) 2007-2011 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2011-11-27 16:57:01
 */
package com.quanlei.demo.onoToOne;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author XiaoQuan
 */
@Entity
public class Passport implements Serializable {

    private Long id;
    private static final long serialVersionUID = -250672462817825778L;
    private Customer owner;

    @OneToOne(mappedBy = "passport")
    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
