package catering.persistence;

import catering.businesslogic.kitchenTask.KitchenTaskEventReceiver;
import catering.businesslogic.kitchenTask.SummarySheet;
import catering.businesslogic.kitchenTask.Task;
import catering.businesslogic.recipe.Recipe;
import catering.businesslogic.turn.Turn;
import catering.businesslogic.user.User;

public class KitchenTaskPersistence implements KitchenTaskEventReceiver {
    @Override
    public void updateSummarySheetCreated(SummarySheet sumSh) {
        SummarySheet.saveNewSummarySheet(sumSh);
    }

    @Override
    public void updateAddedTask(SummarySheet sumSh, Task task, Recipe rec, Turn turn) {
        Task.saveNewTask(sumSh.getId(), rec, task, sumSh.getTaskPosition(task), turn);
    }

    @Override
    public void updateTasksRearranged(SummarySheet sumSh) {
        SummarySheet.saveTaskOrder(sumSh);
    }

    @Override
    public void updateSummarySheetDeleted(SummarySheet sumSh) {
        SummarySheet.deleteSummarySheet(sumSh);
    }

    @Override
    public void updateTaskAssigned(Task task, User cook) {
        Task.saveAssignTask(task, cook);
    }

    @Override
    public void updateTaskDeleted(Task task) {
        SummarySheet.deleteTask(task);
    }

    @Override
    public void updateTaskChanged(Task task, int cook, double time, int turn, int recipe, int quantity, int portion) {
        Task.modifyChange(task, cook, time, turn, recipe, quantity, portion);
    }

    @Override
    public void updateAddTaskInfo(Task task, int quantity, int portion, double time) {
        Task.saveTaskInfo(task, quantity, portion, time);
    }
}
