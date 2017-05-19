/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicelogics.hellojetty.structures;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author davide
 */
@XmlRootElement
public class Student implements Serializable {

    public String first_name;
    public String last_name;
    public String[] array;
    public List<String> list;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String[] getArray() {
        return array;
    }

    public void setArray(String[] array) {
        this.array = array;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Student() {

        first_name = "Fahad";
        last_name = "Mullaji";
        array = new String[] {"one", "two", "three"};
        list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
    }
}