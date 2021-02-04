import java.util.ArrayList;

public class Lists {
    private static ArrayList<Todo> lists = new ArrayList<Todo>();
    private static ArrayList<String> listNames = new ArrayList<String>();

    public static void addList(Todo newList){
        lists.add(newList);
        listNames.add(newList.getName());
    }

    public static String getAllListNames(){
        String allLists = "";
        for (String name : listNames){
            allLists += (name + " | ");
        }
        return allLists;
    }

    public static boolean checkForList(String listName){
        if (listNames.indexOf(listName) > -1){
            return true;
        }
        return false;
    }

    public static Todo findList(String listName){
        return lists.get(listNames.indexOf(listName));
    }

    public static void setNewListName(String oldListName, String newListName){
        lists.get(listNames.indexOf(oldListName)).setName(newListName);
        listNames.set((listNames.indexOf(oldListName)), newListName);
    }

    public static boolean checkTask(String taskName){
        for (int list = 0; list < lists.size(); list++){
            for (int task = 0; task < lists.get(list).getNumItems(); task++){
                if (lists.get(list).checkForItem(taskName)){
                    return true;
                }
            }
        }
        return false;
    }

    public static Todo searchTask(String taskName){
        for (int list = 0; list < lists.size(); list++){
            for (int task = 0; task < lists.get(list).getNumItems(); task++){
                if (lists.get(list).checkForItem(taskName)){
                    return lists.get(list);
                }
            }
        }
        //Should probably change this later even though this will not be reached after checkTask is called
        return lists.get(0);
    }
}
