package models;

import java.util.List;

public class Client {
    private final String id;
    private List<Hire> hireList;

    public Client(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public List<Hire> getHireList() {
        return hireList;
    }

    public void addHireToList(Hire hire){
        hireList.add(hire);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id='" + id + '\'' +
                ", hires=" + hireList +
                '}';
    }
}
