package models;

import java.time.LocalDateTime;

public class Tool {
    String name;
    TypeTool typeTool;
    RentStatus rentStatus = RentStatus.AVAILABLE;
    LocalDateTime sinceRent;
    LocalDateTime untilRent;

    public Tool(String name, TypeTool typeTool) {
        this.name = name;
        this.typeTool = typeTool;
    }

    public Tool(String name, TypeTool typeTool, RentStatus rentStatus, LocalDateTime sinceRent, LocalDateTime untilRent) {
        this.name = name;
        this.typeTool = typeTool;
        this.rentStatus = rentStatus;
        this.sinceRent = sinceRent;
        this.untilRent = untilRent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeTool getTypeTool() {
        return typeTool;
    }

    public void setTypeTool(TypeTool typeTool) {
        this.typeTool = typeTool;
    }

    public RentStatus getRentStatus() {
        return rentStatus;
    }

    public void setRentStatus(RentStatus rentStatus) {
        this.rentStatus = rentStatus;
    }

    public LocalDateTime getSinceRent() {
        return sinceRent;
    }

    public void setSinceRent(LocalDateTime sinceRent) {
        this.sinceRent = sinceRent;
    }

    public LocalDateTime getUntilRent() {
        return untilRent;
    }

    public void setUntilRent(LocalDateTime untilRent) {
        this.untilRent = untilRent;
    }

    @Override
    public String toString() {
        return "Tool{" +
                "name='" + name + '\'' +
                ", typeTool=" + typeTool +
                ", rentStatus=" + rentStatus +
                ", sinceRent=" + sinceRent +
                ", untilRent=" + untilRent +
                '}';
    }
}
