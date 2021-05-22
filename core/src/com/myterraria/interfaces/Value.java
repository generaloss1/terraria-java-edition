package com.myterraria.interfaces;

public class Value<T>{

    T value;

    public Value(T v){
        value=v;
    }

    public Value<T> setValue(T v){
        value=v;
        return this;
    }

    public T getValue(){
        return value;
    }

}
