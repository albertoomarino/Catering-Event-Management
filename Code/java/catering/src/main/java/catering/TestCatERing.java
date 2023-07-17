package catering;

import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.event.ServiceInfo;
import catering.businesslogic.kitchenTask.SummarySheet;
import catering.businesslogic.kitchenTask.Task;
import catering.businesslogic.recipe.Recipe;
import catering.businesslogic.turn.Turn;
import catering.businesslogic.user.User;

public class TestCatERing {
    public static void main(String[] args) {
        try {
            System.out.println("TEST FAKE LOGIN");
            CatERing.getInstance().getUserManager().fakeLogin("Lidia");
            System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());

            System.out.println("\nTEST CREATE SUMMARY SHEET");
            ServiceInfo ser = ServiceInfo.loadAllServiceInfo(2);
            SummarySheet sumSh = CatERing.getInstance().getKitchenTaskManager().createSummarySheet(ser);
            sumSh.printSumSh();

            System.out.println("\nTEST ADD TASK");
            Turn turn = Turn.loadAllTurnInfo(2);
            Recipe rec1 = Recipe.loadRecipeById(2);
            Task task1 = CatERing.getInstance().getKitchenTaskManager().addTask(rec1, false, turn);
            Recipe rec2 = Recipe.loadRecipeById(1);
            Task task2 = CatERing.getInstance().getKitchenTaskManager().addTask(rec2, false, turn);
            Recipe rec3 = Recipe.loadRecipeById(3);
            Task task3 = CatERing.getInstance().getKitchenTaskManager().addTask(rec3, false, turn);
            sumSh.printSumSh();

            System.out.println("\nTEST ORDER LIST TASK");
            System.out.println("PRIMA:");
            sumSh.printSumSh();
            CatERing.getInstance().getKitchenTaskManager().orderListTask(task1, 1);
            CatERing.getInstance().getKitchenTaskManager().orderListTask(task3, 2);
            System.out.println("DOPO:");
            sumSh.printSumSh();

            System.out.println("\nTEST CONSULT SCOREBOARD");
            CatERing.getInstance().getKitchenTaskManager().consultScoreboard();

            System.out.println("\nTEST ASSIGN TASK");
            User cook = User.loadUserById(5);
            Task task = CatERing.getInstance().getKitchenTaskManager().assignTask(task1, cook);
            System.out.println("ASSEGNATO CUOCO AL TASK CON ID: " + task1.getId());
            Task.loadAllTaskInfo(task1);
            System.out.println(Task.toStringg(task1));

            System.out.println("\nTEST ADD INFO TASK");
            Task taskMod = CatERing.getInstance().getKitchenTaskManager().addTaskInfo(task1, 69, 0, 3.30);
            System.out.println("MODIFICATO TASK CON ID: " + task1.getId());
            Task.loadAllTaskInfo(task1);
            System.out.println(Task.toStringg(task1));

        } catch (UseCaseLogicException e) {
            System.out.println("Errore di logica nello use case");
        }
    }
}