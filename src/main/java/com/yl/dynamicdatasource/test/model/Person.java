package com.yl.dynamicdatasource.test.model;

import java.util.Objects;

/**
 * @description:
 * @author: yl
 * @date: 2022-08-07
 **/
public class Person {
    private int age;

    private int birth;

    public Person(int age, int birth) {
        this.age = age;
        this.birth = birth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                birth == person.birth;
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, birth);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getBirth() {
        return birth;
    }

    public void setBirth(int birth) {
        this.birth = birth;
    }
}
