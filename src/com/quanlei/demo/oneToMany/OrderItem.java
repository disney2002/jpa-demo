package com.quanlei.demo.oneToMany;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_item")
public class OrderItem implements Serializable {

    /**
     * 双向的一对多关系里面，多的一方为关系的维护端。
     * 关系的维护端负责外键字段（记录）的更新；
     * 关系的被维护端是没有权力更新外键字段（记录）的。
     */
    @Id
    @GeneratedValue
    private Integer id;
    /**
     * 品名
     */
    @Column(length = 40, nullable = false)
    private String productName;
    /**
     * 价格
     */
    @Column(nullable = false)
    private Float priceFloat = 0f;
    /**
     * 对应的订单
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    //设置外键名称
    @JoinColumn(name = "orderID")
    private Order order;

    public OrderItem() {
    }

    public OrderItem(String string, float f) {
        this.priceFloat = f;
        this.productName = string;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setPriceFloat(Float priceFloat) {
        this.priceFloat = priceFloat;
    }

    public Float getPriceFloat() {
        return priceFloat;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}
