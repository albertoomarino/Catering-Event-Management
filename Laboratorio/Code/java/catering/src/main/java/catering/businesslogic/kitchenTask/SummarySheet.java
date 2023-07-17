package catering.businesslogic.kitchenTask;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import catering.businesslogic.event.ServiceInfo;
import catering.businesslogic.recipe.Recipe;
import catering.businesslogic.turn.Turn;
import catering.businesslogic.user.User;
import catering.persistence.BatchUpdateHandler;
import catering.persistence.PersistenceManager;

public class SummarySheet {
    private User holderChef;
    private ServiceInfo service;
    private ArrayList<Task> tasks;

    public SummarySheet(User user, ServiceInfo ser) {
        this.holderChef = user;
        this.service = ser;
        this.tasks = new ArrayList<>();
    }

    private int id;

    public static void deleteTask(Task task) {
        String delTasks = "DELETE FROM catering.tasks WHERE id = " + task.getId();
        PersistenceManager.executeUpdate(delTasks);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getId() {
        return id;
    }

    public void setHolderChef(User holderChef) {
        this.holderChef = holderChef;
    }

    public void setService(ServiceInfo service) {
        this.service = service;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public User getHolderChef() {
        return holderChef;
    }

    public ServiceInfo getService() {
        return service;
    }

    public boolean contains(Task task) {
        return tasks.contains(task);
    }

    public static void saveNewSummarySheet(SummarySheet sumSh) {
        String query = "INSERT INTO catering.summarysheets(chef, service) VALUES (?, ?);";
        int[] result = PersistenceManager.executeBatchUpdate(query, 1, new BatchUpdateHandler() {
            @Override
            public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                ps.setInt(1, sumSh.getHolderChef().getId());
                ps.setInt(2, sumSh.getService().getId());
            }

            @Override
            public void handleGeneratedIds(ResultSet rs, int count) throws SQLException {
                // should be only one
                if (count == 0) {
                    sumSh.setId(rs.getInt(1));
                }
            }
        });
    }

    public static void deleteSummarySheet(SummarySheet sumSh) {
        String delTasks = "DELETE FROM catering.tasks WHERE sumShId = " + sumSh.getId();
        PersistenceManager.executeUpdate(delTasks);

        String delSumSh = "DELETE FROM catering.summarysheets WHERE id = " + sumSh.getId();
        PersistenceManager.executeUpdate(delSumSh);
    }

    public static void saveTaskOrder(SummarySheet sumSh) {
        int i = 0;
        for (Task t : sumSh.getTasks()) {
            String update = "UPDATE catering.tasks SET position ='" + i + "' WHERE id =" + t.getId() + ";";
            PersistenceManager.executeUpdate(update);
            t.setPosition(i);
            i++;
        }
    }


    public boolean isHolder(User u) {
        return u.getId() == this.holderChef.getId();
    }

    public Task addTask(Recipe rec, SummarySheet sumSh, boolean prep, Turn turn) {
        Task task = new Task(rec, sumSh, turn);
        tasks.add(task);
        //sumSh.getTasks().add(task);
        if (prep)
            rec.addPreparation(true);
        return task;
    }


    public void printSumSh2() {
        System.out.println(this.toStringg());
    }

    public String toStringg() {
        return "Id: " + this.getId() + "\nchef titolare: " + holderChef.getUserName() + "\nservizio: " + this.service.toString()
                + "\ncompiti: [" + tasks.get(0).toString() + "ricetta: " + tasks.get(0).getRecipe() + " " + tasks.get(0).getTurn()
                + " " + tasks.get(1).getCook().getUserName() + "]" +
                "\n[" + tasks.get(1).toString() + "ricetta: " + tasks.get(1).getRecipe() + " " + tasks.get(1).getTurn()
                + " cuoco: " + tasks.get(1).getCook().getUserName() + "]" +
                "\n[" + tasks.get(2).toString() + "ricetta: " + tasks.get(2).getRecipe() + " " + tasks.get(2).getTurn()
                + " cuoco: " + tasks.get(1).getCook().getUserName() + "]";
    }

    public void printSumSh() {
        System.out.println(this);
    }

    public String toString() {
        return "Id: " + this.getId() + "\nchef titolare: " + holderChef.getUserName() + "\nservizio: " + this.service.toString()
                + "\ncompiti: " + tasks.toString();
    }

    public int getTaskPosition(Task task) {
        return this.tasks.indexOf(task);
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public void orderListTask(Task task, int position) {
        tasks.remove(task);
        tasks.add(position, task);
    }
}
