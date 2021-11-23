package ru.appline.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable {
    private static final Model instanse = new Model();
    private final Map<Integer,User> model;

    public static Model getInstanse() {
        return instanse;
    }

    private Model(){
        model=new HashMap<>();
        model.put(1, new User("Ksenia", "Markina", 100000));
        model.put(2, new User("Ivan", "Ivanov", 50000));
        model.put(3, new User("Petr","Petrov", 30000));
    }

    public void add(User user, int id){
        model.put(id, user);
    }

    public Map<Integer, User> getFromList(){
        return model;
    }

    public void delete(int id){
        model.remove(id);
    }
}
