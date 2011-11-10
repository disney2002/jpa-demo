package com.quanlei.demo.oneToMany;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    /**
     * 双向的一对多关系里面，多的一方为关系的维护端。
     * 关系的维护端负责外键字段（记录）的更新；
     * 关系的被维护端是没有权力更新外键字段（记录）的。
     */
    @Id
    @Column(length = 12)
    private String orderID;
    /**
     * 
     */
    @Column(nullable = false)
    private Float amount = 0f;
    /**
     * CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE
     * 是有应用条件的，即调用em.refresh,em.persist,em.merge,em.remove才有效。
     * 如果是采用query的更新，删除和添加则是无效的。
     * 
     * refresh是从数据库中获取最新的实体：
     * 因为在em.find获得的实体和你要真正处理时可能相差一些时间，
     * 这些时间里面，该实体可能被其他方法更新到数据库里面了，但是em缓存还没有变，
     * 因此需要用refresh来获取最新的对象。
     * 
     * 延迟加载必须保证em是开着的
     * 如果是ToMany则默认延迟加载
     * 如果是ToOne则默认是立即加载
     * 通过mappedBy声明关系的维护端，这里是指明在OrderItem类里面的哪个属性来维护关系
     * 在哪个类出现mappedBy则表明该实体是关系被维护端。
     */
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "order")
    private Set<OrderItem> items = new HashSet<OrderItem>();
    
    /**
     * 仅在JPA2.0中才可以使用的注解
     * 类似 @OneToMany
     */
    @ElementCollection
    private List<String> memos;

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public void setItems(Set<OrderItem> items) {
        this.items = items;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    /**
     * 建立关系维护
     */
    public void addOrderItem(OrderItem orderItem) {
        //关系维护端orderItem来设置，维护关系
        orderItem.setOrder(this);
        this.items.add(orderItem);
    }

    public List<String> getMemos() {
        return memos;
    }

    public void setMemos(List<String> memos) {
        this.memos = memos;
    }
}
