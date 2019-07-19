package ToDoList;

/**
 * The <code>TaskList</code> object represents a list of tasks created by the user.
 * It contains all basic operations to insert or delete a Task.
 *
 * @author Lukas Mendel
 */

import custom_exceptions.UserException;
import logging.MyLogger;
import java.util.ArrayList;
import java.util.logging.Level;


public class TaskList {

    private ArrayList<Task> tasks = new ArrayList<>();

    TaskList() {

    }


    /**
     * the method adds an element Task to the arrayList in Tasks
     *
     * @param task Element Task which should be added to the ArrayList
     */

    private void addTask(Task task) throws UserException {

        MyLogger.LOGGER.entering(getClass().toString(), "addTask", new Object[]{task});
        for (Task e : tasks) {

            try {

                if (task.equals(e)) {
                    throw new IllegalArgumentException("Eintrag ist bereits vorhanden");
                }
            } catch (IllegalArgumentException exception) {

                MyLogger.LOGGER.log(Level.WARNING, exception.getMessage(), exception);
                throw new UserException("Die Aufgabe mit dieser Nummer" + task.getTaskId() +  "exisitert bereits");
            }

        }
        tasks.add(task);
        MyLogger.LOGGER.exiting(getClass().toString(), "addTask");
    }


    /**
     * the method deletes an element Task from the arrayList in TaskList.
     *
     * @param index index indicates which element should be deleted from ArrayList
     */

    private void deleteTask(int index) {

        MyLogger.LOGGER.entering(getClass().toString(), "deleteTask", new Object[]{index});
        if (tasks.size() > index) {
            tasks.remove(index);
        } else {
            throw new IllegalArgumentException("Index out of Bounds");
        }
        MyLogger.LOGGER.exiting(getClass().toString(), "deleteTask");

    }

    /**
     * the method returns the length of the arraylist.
     */

    public int size() {

        MyLogger.LOGGER.entering(getClass().toString(), "size");
        int size = tasks.size();
        MyLogger.LOGGER.exiting(getClass().toString(), "size", size);
        return size;
    }


    /**
     * the method iterates over the individual elements of the array list and checks certain parameters is set to true.
     * If yes, the element is added to the returning list.
     *
     * @param parameter :"upc" = upcoming exam / "pas" = passed exam / "fai" = failed exam
     */

    public ArrayList<Task> getExamWithSpecalProperties(String parameter) {


        return new ArrayList<>();
    }


}
