/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicelogics.hellojetty.jackson;

/**
 * @JsonView, useful feature, it lets you control what fields to display.
 * 6.1 A simple class, do nothing, just define two views (static classes as members).
 * 
 * @author Developer
 */
public class Views {

    public static class Normal {};

    public static class Manager extends Normal {};
}
