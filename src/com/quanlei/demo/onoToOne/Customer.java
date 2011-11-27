/*
 * Customer.java
 * 
 * Copyright(c) 2007-2011 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2011-11-27 16:56:33
 */
package com.quanlei.demo.onoToOne;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;

/**
 *
 * @author XiaoQuan
 */
@Entity
public class Customer implements Serializable {

    private Long id;
    private static final long serialVersionUID = 1036566329812092371L;
    private Passport passport;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "CustomerPassports",
    joinColumns =
    @JoinColumn(name = "customer_fk"),
    inverseJoinColumns =
    @JoinColumn(name = "passport_fk"))
    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
