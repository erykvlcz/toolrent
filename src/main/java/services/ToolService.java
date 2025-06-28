package services;

import models.*;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToolService {
    int hireId = loadHireId();
    private static final String LOGGERFILE = "logger.txt";
    private static final String TOOLSFILE = "hiretools.txt";
    private static final String HIREFILE = "renttools.txt";
    private static final String HIREID = "hireid.txt";
    private static final String CLIENTSFILE = "clients.txt";
    private static final String HISTORYFILE = "history.txt";

    List<Tool> tools = loadTools();
    List<Hire> hires = new ArrayList<>();
    List<Client> clients = loadClients();

    public ToolService() {
    }

    public void run() {
        boolean state = true;
        Scanner scanner = new Scanner(System.in);
        loadLists();

        while (state) {
            System.out.println(
                    "--- WYPOŻYCZALNIA ---\n" +
                            "1. Wypozycz\n" +
                            "2. Zwróć\n" +
                            "3. Dodaj klienta\n" +
                            "4. Dodaj narzędzie\n" +
                            "5. Dostępne narzędzia\n" +
                            "6. Pokaż ofertę\n" +
                            "7. Pokaż klientów\n" +
                            "8. Pokaż historię\n" +
                            "0. Wyjdź"
            );
            System.out.print("Podaj numer: ");
            String input = scanner.nextLine();

            int option = -1;
            try {
                option = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Wprowadź poprawny numer!");
                continue;
            }

            switch (option) {
                case 1 -> rent(scanner);
                case 2 -> bringBack(scanner);
                case 3 -> addClient(scanner);
                case 4 -> addTool(scanner);
                case 5 -> showAvailableTools();
                case 6 -> showOffer();
                case 7 -> showClients();
                case 8 -> showHistory();
                case 0 -> exitSystem();
            }
        }
    }

    private void rent(Scanner scanner) {
        System.out.print("Podaj ID klienta do wypożyczenia: ");
        String clientId = scanner.nextLine().trim();

        System.out.print("Podaj nazwę narzędzia: ");
        String toolName = scanner.nextLine().trim();

        Tool tool = tools.stream()
                .filter(t -> t.getName().equalsIgnoreCase(toolName))
                .findFirst()
                .orElse(null);

        Client client = clients.stream()
                .filter(c -> c.getId().equals(clientId))
                .findFirst()
                .orElse(null);

        if (client == null || tool == null) {
            System.out.println("Nie znaleziono klienta lub narzędzia.");
            return;
        }

        System.out.print("Podaj datę zakończenia wypożyczenia (format: dd.MM.yyyy HH:mm): ");
        String inputDate = scanner.nextLine();

        LocalDateTime endDate;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
            endDate = LocalDateTime.parse(inputDate, formatter);
        } catch (Exception e) {
            System.out.println("Niepoprawny format daty. Użyj formatu: dd.MM.yyyy HH:mm");
            return;
        }

        Hire hire = new Hire(++hireId, tool, LocalDateTime.now(), endDate, client);
        hires.add(hire);
        tool.setRentStatus(RentStatus.RENTED);

        System.out.println("Wypożyczenie zostało dodane.");
    }

    private void bringBack(Scanner scanner) {
        System.out.print("Podaj sprzęt do oddania: ");
        String toolName = scanner.nextLine().trim();
        Hire hire = hires.stream()
                .filter(h -> h.getTool().getName().equalsIgnoreCase(toolName))
                .findFirst()
                .orElse(null);

        if (hire == null) {
            System.out.println("Nie znaleziono wypożyczenia dla podanego narzędzia.");
            return;
        }

        Duration duration = Duration.between(hire.getSinceRent(), hire.getUntilRent());
        long totalHours = duration.toHours();
        long roundedDays = (long) Math.ceil(totalHours / 24.0);
        double cost = roundedDays * hire.getTool().getPricePer1Day();

        hire.getTool().setRentStatus(RentStatus.AVAILABLE);
        hires.remove(hire);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(HISTORYFILE, true))) {
            bw.write(hire.toString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Do zapłaty: " + cost);
    }

    private void addClient(Scanner scanner) {
        System.out.println("Podaj ID klienta: ");
        String id = scanner.nextLine().trim();

        boolean exists = clients.stream().anyMatch(c -> c.getId().equals(id));
        if (exists) {
            System.out.println("Klient o takim ID już istnieje.");
            return;
        }

        if (!id.isEmpty()) {
            clients.add(new Client(id));
            System.out.println("Dodano klienta.");
        } else {
            System.out.println("Wartość nie może być pusta.");
        }
    }

    private void addTool(Scanner scanner) {
        System.out.println("Podaj typ narzędzia: ");
        String type = scanner.nextLine().trim().toLowerCase();
        switch (type) {
            case "mlotek" -> tools.add(new Tool("młotek", TypeTool.HAMMER, 100.50));
            case "lopata" -> tools.add(new Tool("łopata", TypeTool.SHOVEL, 200.30));
            case "wiertarka" -> tools.add(new Tool("wiertarka", TypeTool.DRILL, 300.70));
            default -> System.out.println("Nie ma takiego narzędzia");
        }
    }

    private void showAvailableTools() {
        System.out.println("Dostępne narzędzia: ");
        tools.stream()
                .filter(tool -> tool.getRentStatus() == RentStatus.AVAILABLE)
                .forEach(System.out::println);
    }

    private void showOffer() {
        System.out.println("Oferta: ");
        tools.forEach(System.out::println);
    }

    private void showClients() {
        System.out.println("Klienci: ");
        clients.forEach(System.out::println);
    }

    private void showHistory() {
        try (BufferedReader br = new BufferedReader(new FileReader(HISTORYFILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Brak historii lub problem z plikiem.");
        }
    }

    private void exitSystem() {
        loadLists();
        System.exit(0);
    }

    private void loadLists() {
        saveTools();
        saveClients();
        saveHires();
        saveCurrentIdToTxt();
    }

    private void saveTools() {
        if (!tools.isEmpty()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(TOOLSFILE))) {
                for (Tool tool : tools) {
                    bw.write(tool.toolTXT());
                    bw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveClients() {
        List<Client> savedClients = loadClients();
        if (!clients.isEmpty()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(CLIENTSFILE))) {
                for (Client client : clients) {
                    if (!savedClients.contains(client)) {
                        bw.write(client.clientTXT());
                        bw.newLine();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveHires() {
        if (!hires.isEmpty()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(HIREFILE))) {
                for (Hire hire : hires) {
                    bw.write(hire.hireTXT());
                    bw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private List<Client> loadClients() {
        List<Client> clients = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CLIENTSFILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                clients.add(new Client(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clients;
    }

    private List<Tool> loadTools() {
        List<Tool> toolsList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(TOOLSFILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                toolsList.add(new Tool(
                        split[0],
                        TypeTool.valueOf(split[1]),
                        RentStatus.valueOf(split[2]),
                        Double.parseDouble(split[3])
                ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return toolsList;
    }

    private int loadHireId() {
        int id = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(HIREID))) {
            String line;
            while ((line = br.readLine()) != null) {
                id = Integer.parseInt(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return id;
    }

    private void saveCurrentIdToTxt() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(HIREID))) {
            bw.write(String.valueOf(hireId));
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
