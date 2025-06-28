package app;

import models.Tool;
import services.ToolService;

public class Runner {
    public static void main(String[] args) {
        ToolService toolService = new ToolService();

        toolService.run();
    }
}
