package ru.academit.kolobov.hashtable.Farm;

import java.util.Arrays;
import java.util.Vector;

public class Pig implements Farm {
    private String name;
    private int age;
    private boolean type;
    private int wieght;

    public Pig(String name, int age, boolean type, int wieght) {
        this.name = name;
        this.age = age;
        this.type = type;
        this.wieght = wieght;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public boolean isMale() {
        return type;
    }

    @Override
    public int getWieght() {
        return wieght;
    }

    @Override
    public String toString() {
        return "Свинья "+name+", возраст "+age+((type==true)?", мальчик ":", девочка ")+wieght+ " кг";
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + name.hashCode();
        hash = prime * hash + age;
        hash = prime * hash + Boolean.hashCode(type);
        hash = prime * hash + wieght;
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        Pig p = (Pig) o;

        return name.equals(p.name)&&type==p.type&&wieght==p.wieght&&age==p.age;
    }

}
