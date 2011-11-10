package com.quanlei.demo.manyToMany;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Student implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    /**
     * 
     */
    @Column(length = 10, nullable = false)
    private String name;
    //一般在多对多很少用到级联的操作。
    /**
     * inverseJoinColumns关系被维护端的外键在中间表中的定义
     * JoinColumns       关系维护端的外键在中间表中的定义
     */
    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "student_teacher", inverseJoinColumns =
    @JoinColumn(name = "teacherid"), joinColumns =
    @JoinColumn(name = "studentid"))
    private Set<Teacher> teachers = new HashSet<Teacher>();

    public Student() {
    }

    public Student(String name) {
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

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    //关系的维护
    public void addTeachers(Teacher teacher) {
        this.teachers.add(teacher);
    }

    public void removeTeachers(Teacher teacher) {
        if (this.teachers.contains(teacher))
        {
            this.teachers.remove(teacher);
        }
    }
}
