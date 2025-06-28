package models;

import java.time.LocalDateTime;

public class Hire {
    private final int hireId;
    private final Tool tool;
    private final LocalDateTime sinceRent;
    private final LocalDateTime untilRent;
    private final Client client;

    public Hire(int hireId, Tool tool, LocalDateTime sinceRent, LocalDateTime untilRent, Client client) {
        this.hireId = hireId;
        this.tool = tool;
        this.sinceRent = sinceRent;
        this.untilRent = untilRent;
        this.client = client;
    }

    public int getHireId() {
        return hireId;
    }

    public Tool getTool() {
        return tool;
    }

    public LocalDateTime getSinceRent() {
        return sinceRent;
    }

    public LocalDateTime getUntilRent() {
        return untilRent;
    }

    public Client getClient() {
        return client;
    }



    public String hireTXT(){
        return tool +
                "," + sinceRent +
                "," + untilRent +
                "," + client;
    }

    @Override
    public String toString() {
        return "Hire{" +
                "tool=" + tool +
                ", sinceRent=" + sinceRent +
                ", untilRent=" + untilRent +
                ", client=" + client +
                '}';
    }
}
