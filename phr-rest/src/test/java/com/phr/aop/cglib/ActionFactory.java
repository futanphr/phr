package com.phr.aop.cglib;

public class ActionFactory {
    public static Action action = new Action();
    
    public static Action getActionInstance(){
        return action;
    }
}