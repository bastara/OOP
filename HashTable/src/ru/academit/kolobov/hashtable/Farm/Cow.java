package ru.academit.kolobov.hashtable.Farm;

public class Cow implements Farm {
    private String name;
    private int age;
    private boolean type;
    private int wieght;

    public Cow(String name, int age, boolean type, int wieght) {
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
}
