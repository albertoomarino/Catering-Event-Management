package catering.businesslogic.kitchenTask;

import catering.businesslogic.recipe.Recipe;
import catering.businesslogic.turn.Turn;
import catering.businesslogic.user.User;

public interface KitchenTaskEventReceiver {
    // 1) createSummarySheet
    public void updateSummarySheetCreated(SummarySheet sumSh);

    // 1b.1) deleteSummarySheetDeleted
    public void updateSummarySheetDeleted(SummarySheet sumSh);

    // 2) addTask
    public void updateAddedTask(SummarySheet sumSh, Task task, Recipe rec, Turn turn);

    // 3) orderListTask
    public void updateTasksRearranged(SummarySheet sumSh);

    // 5) assignTask
    public void updateTaskAssigned(Task task, User cook);

    // 5a.1) changeTask
    public void updateTaskChanged(Task task, int cook, double time, int turn, int recipe, int quantity, int portion);

    // 5b.1) deleteTask
    public void updateTaskDeleted(Task task);

    // 6) addTaskInfo
    public void updateAddTaskInfo(Task task, int quantity, int portion, double time);
}
