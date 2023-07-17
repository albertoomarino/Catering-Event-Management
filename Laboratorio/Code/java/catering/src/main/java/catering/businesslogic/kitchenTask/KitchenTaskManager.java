package catering.businesslogic.kitchenTask;

import java.util.ArrayList;

import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.event.ServiceInfo;
import catering.businesslogic.recipe.Recipe;
import catering.businesslogic.turn.Turn;
import catering.businesslogic.turn.TurnManager;
import catering.businesslogic.user.User;

public class KitchenTaskManager {
    private ArrayList<KitchenTaskEventReceiver> eventReceivers;
    private SummarySheet currentSummarySheet;

    public KitchenTaskManager() {
        eventReceivers = new ArrayList<>();
    }

    public SummarySheet createSummarySheet(ServiceInfo ser) throws UseCaseLogicException {
        User user = CatERing.getInstance().getUserManager().getCurrentUser();

        if (!user.isChef()) {
            throw new UseCaseLogicException();
        }

        SummarySheet sumSh = new SummarySheet(user, ser);
        this.setCurrentSummarySheet(sumSh);
        this.notifySummarySheetCreated(sumSh);

        return sumSh;
    }

    public SummarySheet openSummarySheet(SummarySheet sumSh) throws UseCaseLogicException, KitchenTaskException {
        User u = CatERing.getInstance().getUserManager().getCurrentUser();
        if (!u.isChef())
            throw new UseCaseLogicException();
        if (!sumSh.isHolder(u)) {
            throw new KitchenTaskException();
        }
        this.setCurrentSummarySheet(sumSh);
        return sumSh;
    }

    public SummarySheet deleteSummarySheet(SummarySheet sumSh) throws UseCaseLogicException, KitchenTaskException {
        User u = CatERing.getInstance().getUserManager().getCurrentUser();
        if (!u.isChef())
            throw new UseCaseLogicException();
        if (!sumSh.isHolder(u)) {
            throw new KitchenTaskException();
        }
        this.notifySummarySheetDeleted(sumSh);
        return sumSh;
    }

    public Task deleteTask(Task task) throws UseCaseLogicException {
        if (currentSummarySheet == null || !currentSummarySheet.contains(task)) {
            throw new UseCaseLogicException();
        }
        currentSummarySheet.removeTask(task);
        this.notifyTaskDeleted(task);
        return task;
    }

    public Task addTask(Recipe rec, boolean prep, Turn turn) throws UseCaseLogicException {
        if (currentSummarySheet == null) {
            throw new UseCaseLogicException();
        }
        Task task = this.currentSummarySheet.addTask(rec, currentSummarySheet, prep, turn);
        this.notifyAddedTask(currentSummarySheet, task, rec, turn);
        return task;
    }

    public Task assignTask(Task task, User cook) throws UseCaseLogicException {
        if (currentSummarySheet == null || !currentSummarySheet.contains(task)) {
            throw new UseCaseLogicException();
        }

        task.setCook(cook);

        this.notifyTaskAssigned(task, cook);
        return task;
    }

    public void orderListTask(Task task, int position) throws UseCaseLogicException {
        if (currentSummarySheet == null || currentSummarySheet.getTaskPosition(task) < 0) {
            throw new UseCaseLogicException();
        }
        if (position < 0 || position >= currentSummarySheet.getTaskCount()) {
            throw new IllegalArgumentException();
        }
        this.currentSummarySheet.orderListTask(task, position);
        this.notifyTasksRearranged(currentSummarySheet);
    }

    public void consultScoreboard() {
        ArrayList<Turn> turns;
        turns = TurnManager.getTurnBoard();
        for (Turn t : turns) {
            System.out.println("DATA:" + t.getDate() + " LUOGO:" + t.getLocation() + " ORARIO:" + t.getTime() +
                    " COMPLETO:" + t.isComplete() + " CUOCO:" + t.getCook() + " COMPITO:" + t.getTask() + " CUOCO DISPONIBILE:"
                    + t.isCookAvailable());
        }
    }

    public Task addTaskInfo(Task task, int quantity, int portion, double time) throws UseCaseLogicException {
        if (currentSummarySheet == null || !currentSummarySheet.contains(task) || (quantity == 0 && portion == 0)) {
            throw new UseCaseLogicException();
        }
        Task tas = task.addTaskInfo(quantity, portion, time);
        this.notifyAddTaskInfo(tas, quantity, portion, time);
        return tas;
    }

    public Task addTaskInfo(Task task, double time) throws UseCaseLogicException {
        throw new UseCaseLogicException();
    }

    public Task changeTask(Task task, User cook, double time, Turn turn, Recipe recipe, int quantity, int portion) throws UseCaseLogicException {
        if (currentSummarySheet == null || !currentSummarySheet.contains(task) || (quantity == 0 && portion == 0)) {
            throw new UseCaseLogicException();
        }
        Task taskMod = task.modifyTask(task, cook, time, turn, recipe, quantity, portion);
        this.notifyChangeTask(taskMod, cook.getId(), time, turn.getId(), recipe.getId(), quantity, portion);
        return taskMod;
    }

    private void notifyChangeTask(Task task, int cook, double time, int turn, int recipe, int quantity, int portion) {
        for (KitchenTaskEventReceiver er : this.eventReceivers) {
            er.updateTaskChanged(task, cook, time, turn, recipe, quantity, portion);
        }
    }

    private void notifySummarySheetCreated(SummarySheet sumSh) {
        for (KitchenTaskEventReceiver er : this.eventReceivers) {
            er.updateSummarySheetCreated(sumSh);
        }
    }

    private void notifySummarySheetDeleted(SummarySheet sumSh) {
        for (KitchenTaskEventReceiver er : this.eventReceivers) {
            er.updateSummarySheetDeleted(sumSh);
        }
    }

    private void notifyTaskDeleted(Task task) {
        for (KitchenTaskEventReceiver er : this.eventReceivers) {
            er.updateTaskDeleted(task);
        }
    }

    private void notifyAddedTask(SummarySheet sumSh, Task task, Recipe rec, Turn turn) {
        for (KitchenTaskEventReceiver er : this.eventReceivers) {
            er.updateAddedTask(sumSh, task, rec, turn);
        }
    }

    private void notifyTasksRearranged(SummarySheet sumSh) {
        for (KitchenTaskEventReceiver er : this.eventReceivers) {
            er.updateTasksRearranged(sumSh);
        }
    }

    private void notifyTaskAssigned(Task task, User cook) {
        for (KitchenTaskEventReceiver er : this.eventReceivers) {
            er.updateTaskAssigned(task, cook);
        }
    }

    private void notifyAddTaskInfo(Task task, int quantity, int portion, double time) {
        for (KitchenTaskEventReceiver er : this.eventReceivers) {
            er.updateAddTaskInfo(task, quantity, portion, time);
        }
    }

    public void setCurrentSummarySheet(SummarySheet sumSh) {
        this.currentSummarySheet = sumSh;
    }

    public SummarySheet getCurrentSummarySheet() {
        return this.currentSummarySheet;
    }

    public void addEventReceiver(KitchenTaskEventReceiver rec) {
        this.eventReceivers.add(rec);
    }

    public void removeEventReceiver(KitchenTaskEventReceiver rec) {
        this.eventReceivers.remove(rec);
    }
}
