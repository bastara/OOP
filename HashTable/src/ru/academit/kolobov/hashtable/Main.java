package ru.academit.kolobov.hashtable;

import ru.academit.kolobov.hashtable.Farm.Cow;
import ru.academit.kolobov.hashtable.Farm.Farm;
import ru.academit.kolobov.hashtable.Farm.Pig;
import ru.academit.kolobov.hashtable.hashmodule.DataBaseFarm;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Pig pig = new Pig("hruhru", 2, true,15);
        Pig pig1 = new Pig("nafnaf", 3, false,18);
        Pig pig2 = new Pig("hruhru", 2, true,15);

        Cow cow = new Cow("zorka", 4, false, 120);
        Cow cow1 = new Cow("chernish", 2, true, 90);

        DataBaseFarm[] dbf = new DataBaseFarm[50];

        DataBaseFarm d = new DataBaseFarm();

        ArrayList<Farm> names = new ArrayList<>();
        names.add(pig);



        System.out.println(pig.hashCode());
        System.out.println(pig1.hashCode());
        System.out.println(pig2.hashCode());


        System.out.println(pig);
        System.out.println(pig1);

        if (pig.equals(pig2)) {
            System.out.println("DAAAAA");
        } else {
            System.out.println("Neeet");
        }

    }
}
