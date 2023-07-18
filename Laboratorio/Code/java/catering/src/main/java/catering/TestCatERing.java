package catering;

import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.event.ServiceInfo;
import catering.businesslogic.kitchenTask.SummarySheet;
import catering.businesslogic.kitchenTask.Task;
import catering.businesslogic.recipe.Recipe;
import catering.businesslogic.turn.Turn;
import catering.businesslogic.user.User;

// Test per lo Scenario principale
public class TestCatERing {
    public static void main(String[] args) {
        try {
            // Simulazione del login di un utente di nome "Lidia"
            System.out.println("TEST FAKE LOGIN");
            CatERing.getInstance().getUserManager().fakeLogin("Lidia");
            System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());

            // 1) createSummarySheet
            System.out.println("\nTEST CREATE SUMMARY SHEET");
            ServiceInfo ser = ServiceInfo.loadAllServiceInfo(2); // Recapitiamo il servizio tramite id
            SummarySheet sumSh = CatERing.getInstance().getKitchenTaskManager().createSummarySheet(ser); // Crea un vero e proprio foglio riepilogativo
            sumSh.printSumSh();

            // 2) addTask
            System.out.println("\nTEST ADD TASK");
            Turn turn = Turn.loadAllTurnInfo(2); // Recapitiamo il turno tramite id
            Recipe rec1 = Recipe.loadRecipeById(2); // Recapitiamo la ricetta tramite id
            Task task1 = CatERing.getInstance().getKitchenTaskManager().addTask(rec1, false, turn);
            Recipe rec2 = Recipe.loadRecipeById(1);
            Task task2 = CatERing.getInstance().getKitchenTaskManager().addTask(rec2, false, turn);
            Recipe rec3 = Recipe.loadRecipeById(3);
            Task task3 = CatERing.getInstance().getKitchenTaskManager().addTask(rec3, false, turn);
            sumSh.printSumSh();

            // 3) orderListTask
            System.out.println("\nTEST ORDER LIST TASK");
            System.out.println("PRIMA:");
            sumSh.printSumSh();
            CatERing.getInstance().getKitchenTaskManager().orderListTask(task1, 1);
            CatERing.getInstance().getKitchenTaskManager().orderListTask(task3, 2);
            System.out.println("DOPO:");
            sumSh.printSumSh();

            // 4) consultScoreboard
            System.out.println("\nTEST CONSULT SCOREBOARD");
            CatERing.getInstance().getKitchenTaskManager().consultScoreboard();

            // 5) assignTask
            System.out.println("\nTEST ASSIGN TASK");
            User cook = User.loadUserById(5);
            Task task = CatERing.getInstance().getKitchenTaskManager().assignTask(task1, cook);
            System.out.println("ASSEGNATO CUOCO AL TASK CON ID: " + task1.getId());
            Task.loadAllTaskInfo(task1);
            System.out.println(Task.toStringg(task1));

            // 6) addTaskInfo
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
