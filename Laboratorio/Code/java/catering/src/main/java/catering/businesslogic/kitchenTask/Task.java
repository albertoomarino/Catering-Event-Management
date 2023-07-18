package catering.businesslogic.kitchenTask;

import catering.businesslogic.recipe.Recipe;
import catering.businesslogic.turn.Turn;
import catering.businesslogic.user.User;
import catering.persistence.BatchUpdateHandler;
import catering.persistence.PersistenceManager;

import java.sql.*;
import java.util.ArrayList;

public class Task {
    private int quantity;
    private int portion;
    private double runningTime;
    private Recipe recipe;
    private Turn turn;
    private User cook;

    private int summarySheet;
    private int id;

    private int position;

    public static void modifyChange(Task task, int cook, double time, int turn, int recipe, int quantity, int portion) {
        String upd = "UPDATE `tasks` SET `quantity` = " + quantity
                + ", `portion` = " + portion
                + ", `runningTime` = " + time
                + ", `cook` = " + cook
                + ", `turn` = " + turn
                + ", `recipe` = " + recipe
                + " WHERE `id` = " + task.getId();
        PersistenceManager.executeUpdate(upd);
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Task(Recipe recipe, SummarySheet sumSh, Turn turn) {
        this.recipe = recipe;
        this.summarySheet = sumSh.getId();
        this.turn = turn;
        this.position = 0;
    }

    public Task() {

    }

    public Task addTaskInfo(int quantity, int portion, double time) {
        if (quantity != 0) {
            this.quantity = quantity;
        } else {
            this.portion = portion;
        }
        this.runningTime = time;
        return this;
    }

    public String toString() {
        return "(nome ricetta: " + recipe.getName()
                + ") (è una preparazione? " + recipe.getPreparation()
                + ") (location turno: " + turn.getLocation() + ")\n";
    }

    public static String toStringg(Task task) {
        return "INFO TASK id: " + task.getId()
                + ", quantità: " + task.getQuantity()
                + ", porzione: " + task.getPortion()
                + ", cuoco: " + task.getCook().getUserName()
                + ", tempo esecuzione: " + task.getRunningTime()
                + ", location turno: " + task.getTurn().getLocation()
                + ", ricetta: " + task.getRecipe().getName();
    }

    public int getId() {
        return this.id;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPortion() {
        return portion;
    }

    public double getRunningTime() {
        return runningTime;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public Turn getTurn() {
        return turn;
    }

    public void setCook(User cook) {
        this.cook = cook;
    }

    public User getCook() {
        return this.cook;
    }

    public static void saveNewTask(int sumShId, Recipe rec, Task task, int posInSumSh, Turn turn) {
        String query = "INSERT INTO catering.tasks (recipe, sumShId, position, turn) VALUES (?, ?, ?, ?);";
        int[] result = PersistenceManager.executeBatchUpdate(query, 1, new BatchUpdateHandler() {
            @Override
            public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                ps.setInt(1, rec.getId());
                ps.setInt(2, sumShId);
                ps.setInt(3, posInSumSh);
                ps.setInt(4, turn.getId());
            }

            @Override
            public void handleGeneratedIds(ResultSet rs, int count) throws SQLException {
                // should be only one
                if (count == 0) {
                    task.setId(rs.getInt(1));
                }
            }
        });
    }

    public Task modifyTask(Task task, User cook, double time, Turn turn, Recipe recipe, int quantity, int portion) {
        this.runningTime = time;
        this.turn = turn;
        this.cook = cook;
        this.portion = portion;
        this.quantity = quantity;
        this.recipe = recipe;

        return this;
    }

    public static void saveAssignTask(Task task, User cook) {
        String upd = "UPDATE `tasks` SET `cook` = ? WHERE `id` = ?";
        int[] result = PersistenceManager.executeBatchUpdate(upd, 1, new BatchUpdateHandler() {
            @Override
            public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                ps.setInt(1, cook.getId());
                ps.setInt(2, task.getId());
            }

            @Override
            public void handleGeneratedIds(ResultSet rs, int count) throws SQLException {

            }
        });
    }

    public static Task loadAllTaskInfo(Task task) {
        Task t = new Task();
        String query = "SELECT * FROM catering.turns WHERE id = " + task.getId();
        PersistenceManager.executeQuery(query, rs -> {
            t.id = rs.getInt("id");
            t.portion = rs.getInt("portion");
            t.position = rs.getInt("position");
            t.runningTime = rs.getDouble("runningTime");
            t.recipe = Recipe.loadRecipeById(rs.getInt("recipe"));
            t.cook = User.loadUserById(rs.getInt("cook"));
            t.turn = Turn.loadAllTurnInfo(rs.getInt("task"));
            t.summarySheet = rs.getInt("sumShId");
            t.quantity = rs.getInt("quantity");
        });
        return t;
    }

    public static void saveTaskInfo(Task task, int quantity, int portion, double time) {
        String upd = "UPDATE `tasks` SET `quantity` = " + quantity
                + ", `portion` = " + portion
                + ", `runningTime` = " + time
                + " WHERE `id` = " + task.getId();
        PersistenceManager.executeUpdate(upd);

        /* String upd = "UPDATE tasks SET quantity = ? , portion = ? , runningTime =  ? , WHERE id = ?";
        int[] result = PersistenceManager.executeBatchUpdate(upd, 1, new BatchUpdateHandler() {
            @Override
            public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                ps.setInt(1, quantity);
                ps.setInt(2, portion);
                ps.setDouble(3, time);
                ps.setInt(4, task.getId());
            }
            @Override
            public void handleGeneratedIds(ResultSet rs, int count) throws SQLException {

            }

        }); */

        /* String upd = "UPDATE tasks SET quantity = ?, portion = ?, runningTime = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/catering?serverTimezone=UTC", "root", "")) {
            // Creazione del prepared statement
            PreparedStatement statement = connection.prepareStatement(upd);
            // Impostazione dei valori dei parametri
            statement.setInt(1, quantity);
            statement.setInt(2, portion);
            statement.setDouble(3, time);
            statement.setInt(4, task.getId());
            // Esecuzione della query di aggiornamento
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query = "UPDATE catering.tasks SET quantity = ?, portion = ?, runningTime = ? WHERE id = ?";
        int[] result = PersistenceManager.executeBatchUpdate(query, 1, new BatchUpdateHandler() {
            @Override
            public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                ps.setInt(1, quantity);
                ps.setInt(2, portion);
                ps.setDouble(3, time);
                ps.setInt(4, task.getId());
            }
            @Override
            public void handleGeneratedIds(ResultSet rs, int count) throws SQLException {

            }
        }); */
    }
}
