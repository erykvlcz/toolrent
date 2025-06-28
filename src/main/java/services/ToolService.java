package services;

import models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToolService {
    List<Tool> tools = new ArrayList<>();
    List<Hire> hires = new ArrayList<>();
    List<Client> clients = new ArrayList<>();

    public ToolService() {
    }

    public void run() {

        boolean state = true;
        Scanner scanner = new Scanner(System.in);
        loadLists();

        while (state) {
            System.out.println(
                    "--- SYSTEM KURIERSKI ---\n" +
                            "1. Wypozycz\n" +
                            "2. Zwróć\n" +
                            "3. Dodaj klienta\n" +
                            "4. Dodaj narzędzie\n" +
                            "5. Dostępne narzędzia\n" +
                            "6. Pokaż ofertę\n" +
                            "0. Wyjdź"
            );
            System.out.print("Podaj numer: ");
            String input = scanner.nextLine();

            int option = -1;
            try {
                option = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Wprowadź poprawny numer!");
                continue; // pomiń iterację pętli
            }


            switch (option) {
                case 1 -> rent(scanner);
                case 2 -> bringBack(scanner);
                case 3 -> addClient(scanner);
                case 4 -> addTool(scanner);
                case 5 -> showAvailableTools(scanner);
                case 6 -> showOffer(scanner);
                case 0 -> exitSystem();
            }
        }
    }

    private void rent(Scanner scanner) {
    }

    private void bringBack(Scanner scanner) {
    }

    private void addClient(Scanner scanner) {
        System.out.println("Podaj id klienta: ");
        String id = scanner.nextLine();
        if(!id.equals("")){
            clients.add(new Client(id));
        }else {
            System.out.println("Wartość nie może być pusta");
        }

    }

    private void addTool(Scanner scanner) {
        System.out.println("Podaj typ narzędzia: ");
        String type = scanner.nextLine().trim().toLowerCase();
        switch (type){
            case "mlotek" -> tools.add(new Tool("młotek", TypeTool.HAMMER));
            case "lopata" -> tools.add(new Tool("łopata", TypeTool.SHOVEL));
            case "wiertarka" -> tools.add(new Tool("wiertarka", TypeTool.DRILL));
            default -> System.out.println("Nie ma takiego narzędzia");
        }
    }

    private void showAvailableTools(Scanner scanner) {
        System.out.println("Dostępne narzędzia: ");
        for (Tool tool : tools) {
            if(tool.getRentStatus() == RentStatus.AVAILABLE){
                System.out.println(tool);
            }
        }
    }

    private void showOffer(Scanner scanner) {
        System.out.println("Oferta: ");
        for (Tool tool : tools) {
            System.out.println(tool);
        }
    }

    private void exitSystem() {
    }

    private void loadLists() {
    }
}
