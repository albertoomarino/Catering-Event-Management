package catering;

import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.event.ServiceInfo;
import catering.businesslogic.kitchenTask.KitchenTaskException;
import catering.businesslogic.kitchenTask.SummarySheet;

// Test per l'estensione 1a.1) openSummarySheet
public class TestCatERing1a {
    public static void main(String[] args) {
        try {
            // Simulazione del login di un utente di nome "Lidia"
            System.out.println("TEST FAKE LOGIN");
            CatERing.getInstance().getUserManager().fakeLogin("Lidia");
            System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());

            ServiceInfo ser = ServiceInfo.loadAllServiceInfo(4);
            SummarySheet sumSh = CatERing.getInstance().getKitchenTaskManager().createSummarySheet(ser);

            // 1a.1) openSummarySheet
            System.out.println("\nTEST OPEN SUMMARY SHEET");
            SummarySheet sumSh2 = CatERing.getInstance().getKitchenTaskManager().openSummarySheet(sumSh);
            sumSh2.printSumSh();

        } catch (UseCaseLogicException | KitchenTaskException e) {
            System.out.println("Errore di logica nello use case");
        }
    }
}
