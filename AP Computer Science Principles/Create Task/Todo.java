import java.util.ArrayList;
import java.time.LocalDate;

public class Todo {
    private String listName;
    private LocalDate listDate;
    private ArrayList<String> namesList = new ArrayList<String>();
    private ArrayList<Integer> hoursList = new ArrayList<Integer>();
    private ArrayList<Double> progressList = new ArrayList<Double>();

    public Todo(String initListName){
        this.listDate = LocalDate.now();
        this.listName = initListName;
        Lists.addList(this);
    }

    public String getName(){
        return this.listName;
    }

    public void setName(String newName){
        this.listName = newName;
    }

    public LocalDate getDate(){
        return this.listDate;
    }

    public String getItemNames(){
        String allNames = "";
        for (String name : namesList){
            allNames += (name + " | ");
        }
        return allNames;
    }

    public String getItems() {
        String allItems = "\n" + listName + ":";
        for (int i = 0; i < namesList.size(); i++){
            allItems += ("\nName: " + namesList.get(i) + "   Hours: " + hoursList.get(i) + "   Progress(%): " + progressList.get(i)*100);
        }
        return allItems + "\n";
    }

    public int getNumItems(){
        return namesList.size();
    }

    /* public void deleteItem(String itemName){
        int index = namesList.indexOf(itemName);
        namesList.remove(index);
        hoursList.remove(index);
        progressList.remove(index);
    } */

    public boolean checkForItem(String itemName){
        if (namesList.indexOf(itemName) > -1){
            return true;
        }
        return false;
    }

    public void addItem(String name, Integer hours, Double progress){
        namesList.add(name);
        hoursList.add(hours);
        progressList.add(progress);
    }

    public void editItemName(String oldName, String name){
        namesList.set((namesList.indexOf(oldName)), name);
    }

    public void editItemHours(String oldName, int hours){
        hoursList.set((namesList.indexOf(oldName)), hours);
    }

    public void editItemProgress(String oldName, double progress){
        progressList.set((namesList.indexOf(oldName)), progress);
    }

    /* public static void main(String[] args){
        Todo t1 = new Todo("newList");
        t1.addItem("item 1", 5, 0.85);
        t1.addItem("item 2", 6, 0.82);
        t1.addItem("item 7", 2, 0.47);
        System.out.println(t1.getItems());
    } */
}
