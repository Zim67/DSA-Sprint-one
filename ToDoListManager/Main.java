import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static ArrayList<User> users = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline
                switch (choice) {
                    case 1:
                        createUser(scanner);
                        break;
                    case 2:
                        addTask(scanner);
                        break;
                    case 3:
                        markTaskAsCompleted(scanner);
                        break;
                    case 4:
                        viewTasks(scanner);
                        break;
                    case 5:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear the invalid input
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void printMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Create User");
        System.out.println("2. Add Task");
        System.out.println("3. Mark Task as Completed");
        System.out.println("4. View Tasks");
        System.out.println("5. Exit");
        System.out.print("Enter choice: ");
    }

    private static void createUser(Scanner scanner) {
        System.out.print("Enter user name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            throw new IllegalArgumentException("User name cannot be empty.");
        }
        users.add(new User(name));
        System.out.println("User created successfully.");
    }

    private static void addTask(Scanner scanner) {
        System.out.print("Enter user name: ");
        String name = scanner.nextLine().trim();
        User user = findUser(name);
        if (user != null) {
            System.out.print("Enter task description: ");
            String description = scanner.nextLine().trim();
            if (description.isEmpty()) {
                throw new IllegalArgumentException("Task description cannot be empty.");
            }
            user.addTask(description);
            System.out.println("Task added successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    private static void markTaskAsCompleted(Scanner scanner) {
        System.out.print("Enter user name: ");
        String name = scanner.nextLine().trim();
        User user = findUser(name);
        if (user != null) {
            System.out.print("Enter task description: ");
            String description = scanner.nextLine().trim();
            if (user.markTaskAsCompleted(description)) {
                System.out.println("Task marked as completed.");
            } else {
                System.out.println("Task not found.");
            }
        } else {
            System.out.println("User not found.");
        }
    }

    private static void viewTasks(Scanner scanner) {
        System.out.print("Enter user name: ");
        String name = scanner.nextLine().trim();
        User user = findUser(name);
        if (user != null) {
            user.printTasks();
        } else {
            System.out.println("User not found.");
        }
    }

    private static User findUser(String name) {
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null;
    }
}

