package catering;

import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.event.ServiceInfo;
import catering.businesslogic.kitchenTask.KitchenTaskException;
import catering.businesslogic.kitchenTask.SummarySheet;

public class TestCatERing1b {
    public static void main(String[] args) {
        try {
            System.out.println("TEST FAKE LOGIN");
            CatERing.getInstance().getUserManager().fakeLogin("Lidia");
            System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());

            ServiceInfo ser = ServiceInfo.loadAllServiceInfo(6);
            SummarySheet sumSh = CatERing.getInstance().getKitchenTaskManager().createSummarySheet(ser);
            sumSh.printSumSh();
            System.out.println();
            System.out.println("AGGIUNTO SUMMARY SHEET CON ID: " + sumSh.getId());

            System.out.println("\nTEST DELETE SUMMARY SHEET");
            SummarySheet sumShDel = CatERing.getInstance().getKitchenTaskManager().deleteSummarySheet(sumSh);
            sumShDel.printSumSh();
            System.out.println();
            System.out.println("ELIMINATO SUMMARY SHEET CON ID: " + sumShDel.getId());

        } catch (UseCaseLogicException | KitchenTaskException e) {
            System.out.println("Errore di logica nello use case");
        }
    }
}