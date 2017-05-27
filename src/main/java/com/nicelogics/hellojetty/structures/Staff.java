/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicelogics.hellojetty.structures;

import com.fasterxml.jackson.annotation.JsonView;
import com.nicelogics.hellojetty.jackson.Views;
import java.math.BigDecimal;
import java.util.List;

/**
 * @JsonView, useful feature, it lets you control what fields to display. 6.1 A
 * simple class, do nothing, just define two views (static classes as members).
 * 6.2 For “Normal view”, salary will be hidden, for “Manager view”, display
 * everything.
 *
 * @author Developer
 */
public class Staff {

    @JsonView(Views.Normal.class)
    private String name;

    @JsonView(Views.Normal.class)
    private int age;

    @JsonView(Views.Normal.class)
    private String position;

    @JsonView(Views.Manager.class)
    private BigDecimal salary;

    @JsonView(Views.Normal.class)
    private List<String> skills;

    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
}
