import java.util.Scanner;

public class Main {
    
    private static final Scanner scan = new Scanner(System.in);

    public static void createTodo(){
        System.out.println("What would you like to name this to-do list?");
        System.out.print("> ");
        String todoName = scan.nextLine();
        new Todo(todoName);
        System.out.println("New list successfully created.");
    }

    public static void viewLists(){
        boolean invalid = true;
        String listName = "";
        while (invalid){
            System.out.println("What is the name of the list you would like to view? The lists you have are called: ");
            System.out.println(Lists.getAllListNames());
            System.out.print("> ");
            listName = scan.nextLine();
            if(Lists.checkForList(listName)){
                invalid = false;
            }
            else{
                System.out.println("That list was not found. Try again.");
            }
        }
        Todo target = Lists.findList(listName);
        System.out.println(target.getItems());
    }

    public static void addToList(){
        boolean invalid = true;
        String listName = "";
        while (invalid){
            System.out.println("What is the name of the list you would like to add to? The lists you have are called: ");
            System.out.println(Lists.getAllListNames());
            System.out.print("> ");
            listName = scan.nextLine();
            if(Lists.checkForList(listName)){
                invalid = false;
            }
            else{
                System.out.println("That list was not found. Try again.");
            }
        }
        Todo target = Lists.findList(listName);
        System.out.print("Enter task name: ");
        String taskName = scan.nextLine();
        int taskHours = 0;
        double taskProgress = 0.0;
        try{
            System.out.print("Enter task hours (e.g. 4): ");
            taskHours = scan.nextInt();
        }
        catch (Exception e){
            System.out.println("That isn't a valid, please enter an integer (e.g. 4)");
        }
        scan.nextLine();
        try{
            System.out.print("Enter task progress percentage (e.g. 85): ");
            taskProgress = (double) scan.nextInt()/100;
        }
        catch (Exception e){
            System.out.println("That isn't valid, please enter an integer (e.g. 85)");
        }
        scan.nextLine();
        target.addItem(taskName, taskHours, taskProgress);
        System.out.println("Task successfully created.");
    }

    public static void editList(){
        boolean invalid = true;
        String listName = "";
        while (invalid){
            System.out.println("What is the name of the list you would like to edit? The lists you have are called: ");
            System.out.println(Lists.getAllListNames());
            System.out.print("> ");
            listName = scan.nextLine();
            if(Lists.checkForList(listName)){
                invalid = false;
            }
            else{
                System.out.println("That list was not found. Try again.");
            }
        }
        System.out.println("What would you like to edit?\n[1] List name\n[2] A Task in the list");
        int editChoice = 0;
        try{
            System.out.print("> ");
            editChoice = scan.nextInt();
        }
        catch (Exception e){
            System.out.println("Thats not a valid input, enter a integer (1 or 2).");
        }
        scan.nextLine();
        switch (editChoice){
            case 1:
                editListName(listName);
                break;
            case 2:
                editTask(listName);
                break;
        }
    }

    public static void editListName(String listName){
        System.out.print("Enter the new list name: ");
        String newListName = scan.nextLine();
        Lists.setNewListName(listName, newListName);
    }

    public static void editTask(String listName){
        Todo target = Lists.findList(listName);
        String taskName = "";
        boolean invalidTask = true;
        while (invalidTask){
            System.out.println("Please enter the task you wish to modify. The tasks in this list are:");
            System.out.println(target.getItemNames());
            System.out.print("> ");
            taskName = scan.nextLine();
            if (target.checkForItem(taskName)){
                invalidTask = false;
            }
            else{
                System.out.println("That task does not exist in this list. Try again.");
            }
        }
        //new variables
        String newTaskName = "";
        int newTaskHours = 0;
        double newTaskProgress = 0.0;
        boolean run = true;
        while (run){
            System.out.println("What would you like to modify?\n[1] Task Name\n[2] Task Hours\n[3] Task Progress\n[4] Go back to main menu");
            int editTaskChoice = 0;
            System.out.print("> ");
            editTaskChoice = scan.nextInt();
            scan.nextLine();
            switch (editTaskChoice){
                case 1:
                    System.out.print("Enter new task name: ");
                    newTaskName = scan.nextLine();
                    target.editItemName(taskName, newTaskName);
                    taskName = newTaskName;
                    break;
                case 2:
                    try{
                        System.out.print("Enter new task hours (e.g. 4): ");
                        newTaskHours = scan.nextInt();
                    }
                    catch (Exception e){
                        System.out.println("That isn't a valid, please enter an integer (e.g. 4)");
                    }
                    target.editItemHours(taskName, newTaskHours);
                    break;
                case 3:
                    try{
                        System.out.print("Enter new task progress percentage (e.g. 85): ");
                        newTaskProgress = (double) scan.nextInt()/100;
                    }
                    catch (Exception e){
                        System.out.println("That isn't valid, please enter an integer (e.g. 85)");
                    }
                    target.editItemProgress(taskName, newTaskProgress);
                    break;
                case 4:
                    run = false;
            }
        }
    }

    public static void searchTask(){
        System.out.print("Please enter the name of the task you are searching for: ");
        String taskName = scan.nextLine();
        if (Lists.checkTask(taskName)){
            System.out.println("Your task is located in the list: " + Lists.searchTask(taskName).getName());
        }
        else{
            System.out.println("Sorry, none of your lists have that task.");
        }
    }
    
    public static void main (String[] args){
        System.out.println("\n\nWelcome to Catalyst!");
        boolean run = true;
        while (run){
            System.out.println("What would you like to do?\n[1] Create a new to-do list\n[2] Add to a list\n[3] Edit a list\n[4] View a list\n[5] Search for a task\n[6] Quit");
            int command = 0;
            try{
                System.out.print("> ");
                command = scan.nextInt();
            }
            catch (Exception e){
                System.out.println("Thats not a valid input, enter a integer (1-6).");
            }
            scan.nextLine();
            switch (command){
                case 1:
                    createTodo();
                    break;
                case 2:
                    if (Lists.getAllListNames().length() > 0){
                        addToList();
                    }
                    else{
                        System.out.println("You don't have any to-do lists. To add a list, try command [1].");
                    }
                    break;
                case 3:
                    if (Lists.getAllListNames().length() > 0){
                        editList();
                    }
                    else{
                        System.out.println("You don't have any to-do lists. To add a list, try command [1].");
                    }
                    break;
                case 4:
                    if (Lists.getAllListNames().length() > 0){
                        viewLists();
                    }
                    else{
                        System.out.println("You don't have any to-do lists. To add a list, try command [1].");
                    }
                    break;
                case 5:
                    if (Lists.getAllListNames().length() > 0){
                        searchTask();
                    }
                    else{
                        System.out.println("You don't have any to-do lists. To add a list, try command [1].");
                    }
                    break;
                case 6:
                    System.out.println("Thanks for using Catalyst!");
                    run = false;
                    break;
            }
        }
    }
}