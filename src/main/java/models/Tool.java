package models;

import java.time.LocalDateTime;

public class Tool {
    private String name;
    private TypeTool typeTool;
    private RentStatus rentStatus = RentStatus.AVAILABLE;
    private double pricePer1Day;

    public Tool(String name, TypeTool typeTool, double pricePer1Day) {
        this.name = name;
        this.typeTool = typeTool;
        this.pricePer1Day = pricePer1Day;
    }

    public Tool(String name, TypeTool typeTool, RentStatus rentStatus, double pricePer1Day) {
        this.name = name;
        this.typeTool = typeTool;
        this.rentStatus = rentStatus;
        this.pricePer1Day = pricePer1Day;
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

    public String toolTXT(){
        return name + "," +
                typeTool + "," +
                rentStatus;
    }

    public double getPricePer1Day() {
        return pricePer1Day;
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
