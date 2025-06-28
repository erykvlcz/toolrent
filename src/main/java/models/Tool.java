package models;

import java.time.LocalDateTime;

public class Tool {
    String name;
    TypeTool typeTool;
    RentStatus rentStatus = RentStatus.AVAILABLE;

    public Tool(String name, TypeTool typeTool) {
        this.name = name;
        this.typeTool = typeTool;
    }

    public Tool(String name, TypeTool typeTool, RentStatus rentStatus) {
        this.name = name;
        this.typeTool = typeTool;
        this.rentStatus = rentStatus;
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

    @Override
    public String toString() {
        return "Tool{" +
                "name='" + name + '\'' +
                ", typeTool=" + typeTool +
                ", rentStatus=" + rentStatus +
                '}';
    }
}
