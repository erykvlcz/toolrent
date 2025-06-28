package models;

import java.time.LocalDateTime;

public class Hire {
    private final Tool tool;
    private final LocalDateTime sinceRent;
    private final LocalDateTime untilRent;
    private final Client client;

    public Hire(Tool tool, Client client, LocalDateTime untilRent, LocalDateTime sinceRent) {
        this.tool = tool;
        this.client = client;
        this.untilRent = LocalDateTime.now();
        this.sinceRent = sinceRent;
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
