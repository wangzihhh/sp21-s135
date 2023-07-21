package deque;

import java.util.Comparator;
import java.util.concurrent.ConcurrentMap;

public class Dog {

    public String name;
    public int age;
    public int weight;

    public Dog(String name, int age, int weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }


    public static Comparator<Dog> getNameCmp() {
        return new NameComparator();
    }

    public static Comparator<Dog> getAgeCmp() {
        return new AgeComparator();
    }

    public static Comparator<Dog> getWeightCmp() {
        return new weightComparator();
    }


    private static class NameComparator implements Comparator<Dog> {

        public int compare(Dog d1, Dog d2) {
            return d1.name.compareTo(d2.name);
        }

    }

    private static class AgeComparator implements Comparator<Dog> {

        public int compare(Dog d1, Dog d2) {
            return (d1.age - d2.age);
        }

    }

    private static class weightComparator implements Comparator<Dog> {

        public int compare(Dog d1, Dog d2) {
            return (d1.weight - d2.weight);
        }
    }

}
