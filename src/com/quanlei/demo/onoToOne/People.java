package com.quanlei.demo.onoToOne;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "people")
public class People implements Serializable {

    /**
     * 主键
     */
    @Id
    @GeneratedValue
    private Integer id;
    /**
     * 姓名
     */
    @Column(name = "name", length = 10, nullable = false)
    private String name;
    /**
     * 用户的身份证
     */
    @OneToOne(cascade = CascadeType.ALL)
    //主键关联
//    @PrimaryKeyJoinColumn
    //指定外键名称
//    @JoinColumn(name = "idcard_id")
    @JoinTable(name = "CustomerPassports", joinColumns =
    @JoinColumn(name = "customer_fk"), inverseJoinColumns =
    @JoinColumn(name = "passport_fk"))
    private IDCard idCard;

    public People() {
    }

    public People(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IDCard getIdCard() {
        return idCard;
    }

    public void setIdCard(IDCard idCard) {
        this.idCard = idCard;
    }
}
