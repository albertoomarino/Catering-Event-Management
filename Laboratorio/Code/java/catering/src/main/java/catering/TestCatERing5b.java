package catering;

import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.event.ServiceInfo;
import catering.businesslogic.kitchenTask.KitchenTaskException;
import catering.businesslogic.kitchenTask.SummarySheet;
import catering.businesslogic.kitchenTask.Task;
import catering.businesslogic.recipe.Recipe;
import catering.businesslogic.turn.Turn;
import catering.businesslogic.user.User;

// Test per l'estensione 5b.1) deleteTask
public class TestCatERing5b {
    public static void main(String[] args) {
        try {
            // Simulazione del login di un utente di nome "Lidia"
            System.out.println("TEST FAKE LOGIN");
            CatERing.getInstance().getUserManager().fakeLogin("Lidia");
            System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());

            // 5b.1) deleteTask
            ServiceInfo ser = ServiceInfo.loadAllServiceInfo(2);
            SummarySheet sumSh = CatERing.getInstance().getKitchenTaskManager().createSummarySheet(ser);
            sumSh.printSumSh();

            Turn turn = Turn.loadAllTurnInfo(2);
            Recipe rec1 = Recipe.loadRecipeById(2);
            Task task1 = CatERing.getInstance().getKitchenTaskManager().addTask(rec1, false, turn);
            System.out.println();
            System.out.println("AGGIUNTO TASK CON ID: " + task1.getId());

            User cook = User.loadUserById(5);
            Task task = CatERing.getInstance().getKitchenTaskManager().assignTask(task1, cook);

            System.out.println("\nTEST DELETE TASK");
            Task taskDel = CatERing.getInstance().getKitchenTaskManager().deleteTask(task);
            System.out.println();
            System.out.println("ELIMINATO TASK CON ID: " + task.getId());

        } catch (UseCaseLogicException e) {
            System.out.println("Errore di logica nello use case");
        }
    }
}
