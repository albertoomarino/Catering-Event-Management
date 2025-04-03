package catering.businesslogic.turn;

import catering.businesslogic.kitchenTask.Task;
import catering.businesslogic.user.User;
import catering.persistence.PersistenceManager;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class Turn {
    private int id;
    private Date date;
    private String location;
    private Time time;
    private boolean complete;
    private int cook;
    private int task;
    private boolean cookAvailable;

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public Time getTime() {
        return time;
    }

    public boolean isComplete() {
        return complete;
    }

    public int getCook() {
        return cook;
    }

    public int getTask() {
        return task;
    }

    public boolean isCookAvailable() {
        return cookAvailable;
    }

    public void setCookAvailable(boolean av) {
        this.cookAvailable = av;
    }

    public static ArrayList<Turn> getTurnBoard() {
        ArrayList<Turn> all = new ArrayList<>();
        String query = "SELECT * FROM Turns WHERE true";
        PersistenceManager.executeQuery(query, rs -> {
            Turn t = new Turn();
            t.id = rs.getInt("id");
            t.date = rs.getDate("date");
            t.location = rs.getString("location");
            t.time = rs.getTime("time");
            t.complete = rs.getBoolean("complete");
            t.cook = rs.getInt("cook");
            t.task = rs.getInt("task");
            t.cookAvailable = rs.getBoolean("cookAvailable");
            all.add(t);
        });
        return all;
    }

    public static Turn loadAllTurnInfo(int turn_id) {
        Turn t = new Turn();
        String query = "SELECT * FROM catering.turns WHERE id = " + turn_id;
        PersistenceManager.executeQuery(query, rs -> {
            t.id = rs.getInt("id");
            t.date = rs.getDate("date");
            t.location = rs.getString("location");
            t.time = rs.getTime("time");
            t.complete = rs.getBoolean("complete");
            t.cook = rs.getInt("cook");
            t.task = rs.getInt("task");
            t.cookAvailable = rs.getBoolean("cookAvailable");
        });
        return t;
    }

    private ArrayList<User> cooks;
    private ArrayList<Task> tasks;


    public ArrayList<User> getCooks() {
        return cooks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public String toString() {
        return "data: " + date
                + " location: " + location
                + " orario: " + time
                + " completo: " + complete
                + " cook: " + cook
                + " compito: " + task
                + " cuoco disponibile: " + cookAvailable;
    }
}
