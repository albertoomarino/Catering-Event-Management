package catering.businesslogic.kitchenTask;

import catering.businesslogic.recipe.Recipe;
import catering.businesslogic.turn.Turn;
import catering.businesslogic.user.User;

public interface KitchenTaskEventReceiver {
    public void updateSummarySheetCreated(SummarySheet sumSh);

    public void updateAddedTask(SummarySheet sumSh, Task task, Recipe rec, Turn turn);

    public void updateTasksRearranged(SummarySheet sumSh);

    public void updateSummarySheetDeleted(SummarySheet sumSh);

    public void updateTaskAssigned(Task task, User cook);

    public void updateTaskDeleted(Task task);

    public void updateTaskChanged(Task task, int cook, double time, int turn, int recipe, int quantity, int portion);

    public void updateAddTaskInfo(Task task, int quantity, int portion, double time);
}
