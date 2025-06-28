package services;

import models.Hire;
import models.Tool;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToolService {
    List<Tool> tools = new ArrayList<>();
    List<Hire> hires = new ArrayList<>();

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
                case 4 -> showAvailableTools(scanner);
                case 5 -> showOffer(scanner);
                case 0 -> exitSystem();
            }
        }
    }

    private void rent(Scanner scanner) {
    }

    private void bringBack(Scanner scanner) {
    }

    private void addClient(Scanner scanner) {
    }

    private void showAvailableTools(Scanner scanner) {
    }

    private void showOffer(Scanner scanner) {
    }

    private void exitSystem() {
    }

    private void loadLists() {
    }
}
