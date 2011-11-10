package com.quanlei.demo.onoToOne;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "id_card")
public class IDCard implements Serializable {

    /**
     * ID主键
     */
    @Id
    @GeneratedValue
    private Integer id;
    /**
     * 卡号
     */
    @Column(length = 18, nullable = false)
    private String cardNumber;
    /**
     * 谁的卡
     * mappedBy 指定为关系的被维护端
     * optional=false意思是不可为空，因为外键不允许为空
     */
    @OneToOne(mappedBy = "idCard", cascade = {CascadeType.ALL}, optional = false)
    private People people;

    public IDCard() {
    }

    public IDCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }
}
