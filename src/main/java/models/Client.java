package models;

import java.util.List;

public class Client {
    private final String id;

    public Client(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String clientTXT(){
        return id;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id='" + id + '\'' +
                '}';
    }
}
