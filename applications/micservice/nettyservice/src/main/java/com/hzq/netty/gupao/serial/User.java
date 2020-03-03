package com.hzq.netty.gupao.serial;

import java.io.Serializable;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-02-22
 */
public class User implements Serializable {
    private static final long serialVersionUID = 8025548774456516774L;

    private transient String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    private void readObject(java.io.ObjectInputStream s)
            throws java.io.IOException, ClassNotFoundException{
        s.defaultReadObject();
        name = (String) s.readObject();
    }

    private void writeObject(java.io.ObjectOutputStream s)
            throws java.io.IOException{
        s.defaultWriteObject();
        s.writeObject(name);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
