import java.util.HashSet;
import java.util.Set;

public class User {
    private String name;
    private TaskList toDoList;
    private static Set<String> userNames = new HashSet<>();

    public User(String name) {
        if (userNames.contains(name)) {
            throw new IllegalArgumentException("User name must be unique.");
        }
        this.name = name;
        this.toDoList = new TaskList();
        userNames.add(name);
    }

    public String getName() {
        return name;
    }

    public void addTask(String description) {
        Task newTask = new Task(description);
        toDoList.addTask(newTask);
    }

    public boolean markTaskAsCompleted(String description) {
        return toDoList.markTaskAsCompleted(description);
    }

    public void printTasks() {
        System.out.println("Tasks for " + name + ":");
        toDoList.printTasks();
    }
}

