import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static DateTimeFormatter FORMAT =
            DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private static ApplicationManager manager =
            new ApplicationManager(
                    new ApplicationStore("data/applications.txt"));

    public static void main(String[] args) {
        System.out.println();
        System.out.println("==============================================");
        System.out.println("       INTERNSHIP APPLICATION TRACKER");
        System.out.println("==============================================");

        boolean running = true;

        while (running) {
            showMenu();
            int choice = readNumber("Enter your choice: ");

            switch (choice) {
                case 1:
                    addApplication();
                    break;
                case 2:
                    showApplications(manager.getApplications());
                    break;
                case 3:
                    searchApplications();
                    break;
                case 4:
                    updateStatus();
                    break;
                case 5:
                    showDeadlines();
                    break;
                case 6:
                    showStatistics();
                    break;
                case 7:
                    deleteApplication();
                    break;
                case 8:
                    running = false;
                    System.out.println("Thank you for using the tracker.");
                    break;
                default:
                    System.out.println("Please enter a number from 1 to 8.");
            }
        }
    }

    private static void showMenu() {
        System.out.println();
        System.out.println("1. Add application");
        System.out.println("2. View all applications");
        System.out.println("3. Search applications");
        System.out.println("4. Update application status");
        System.out.println("5. Check upcoming deadlines");
        System.out.println("6. View statistics");
        System.out.println("7. Delete application");
        System.out.println("8. Exit");
        System.out.println();
    }

    private static void addApplication() {
        System.out.println("\n--- Add Application ---");

        String company = readText("Company: ");
        String role = readText("Role: ");
        String location = readText("Location: ");
        LocalDate deadline = readDate("Deadline (dd-MM-yyyy): ");
        Status status = readStatus();
        String notes = readOptional("Notes: ");

        InternshipApplication application =
                manager.addApplication(company, role, location,
                        deadline, status, notes);

        System.out.println("Application added with ID "
                + application.getId() + ".");
    }

    private static void searchApplications() {
        System.out.println("\n--- Search Applications ---");

        String word = readText("Search word: ");
        ArrayList<InternshipApplication> result =
                manager.search(word);

        showApplications(result);
    }

    private static void updateStatus() {
        System.out.println("\n--- Update Status ---");

        int id = readNumber("Application ID: ");
        InternshipApplication application = manager.find(id);

        if (application == null) {
            System.out.println("No application found with that ID.");
            return;
        }

        System.out.println("Company: " + application.getCompany());
        System.out.println("Current status: " + application.getStatus());

        Status newStatus = readStatus();

        if (manager.changeStatus(id, newStatus)) {
            System.out.println("Status updated successfully.");
        }
    }

    private static void showDeadlines() {
        System.out.println("\n--- Upcoming Deadlines ---");

        int days = readNumber("Check the next how many days? ");

        if (days < 0) {
            System.out.println("Days cannot be negative.");
            return;
        }

        ArrayList<InternshipApplication> result =
                manager.deadlinesWithin(days);

        showApplications(result);
    }

    private static void showStatistics() {
        System.out.println("\n--- Statistics ---");
        System.out.println("Total       : " + manager.total());
        System.out.println("Applied     : " + manager.count(Status.APPLIED));
        System.out.println("Shortlisted : " + manager.count(Status.SHORTLISTED));
        System.out.println("Interview   : " + manager.count(Status.INTERVIEW));
        System.out.println("Selected    : " + manager.count(Status.SELECTED));
        System.out.println("Rejected    : " + manager.count(Status.REJECTED));
    }

    private static void deleteApplication() {
        System.out.println("\n--- Delete Application ---");

        int id = readNumber("Application ID: ");
        InternshipApplication application = manager.find(id);

        if (application == null) {
            System.out.println("No application found with that ID.");
            return;
        }

        System.out.println("You selected: "
                + application.getCompany() + " - "
                + application.getRole());

        String answer = readOptional("Delete it? (y/n): ");

        if (answer.equalsIgnoreCase("y")) {
            manager.remove(id);
            System.out.println("Application deleted.");
        } else {
            System.out.println("Delete cancelled.");
        }
    }

    private static void showApplications(
            ArrayList<InternshipApplication> applications) {

        if (applications.isEmpty()) {
            System.out.println("No applications found.");
            return;
        }

        System.out.printf("%-4s %-18s %-23s %-15s %-12s %-12s%n",
                "ID", "Company", "Role", "Location",
                "Deadline", "Status");

        System.out.println(
                "--------------------------------------------------------------------------------");

        for (InternshipApplication application : applications) {
            System.out.println(application);
        }

        System.out.println(
                "--------------------------------------------------------------------------------");
    }

    private static Status readStatus() {
        while (true) {
            System.out.println("1. APPLIED");
            System.out.println("2. SHORTLISTED");
            System.out.println("3. INTERVIEW");
            System.out.println("4. SELECTED");
            System.out.println("5. REJECTED");

            int choice = readNumber("Choose status: ");

            switch (choice) {
                case 1: return Status.APPLIED;
                case 2: return Status.SHORTLISTED;
                case 3: return Status.INTERVIEW;
                case 4: return Status.SELECTED;
                case 5: return Status.REJECTED;
                default:
                    System.out.println("Invalid status. Try again.");
            }
        }
    }

    private static LocalDate readDate(String message) {
        while (true) {
            String input = readText(message);

            try {
                return LocalDate.parse(input, FORMAT);
            } catch (DateTimeParseException e) {
                System.out.println(
                        "Wrong format. Example: 25-09-2026");
            }
        }
    }

    private static int readNumber(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static String readText(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println("This field cannot be empty.");
        }
    }

    private static String readOptional(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }
}
