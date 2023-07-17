package catering.businesslogic.turn;

import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.event.ServiceInfo;
import catering.businesslogic.kitchenTask.SummarySheet;
import catering.businesslogic.menu.Menu;
import catering.businesslogic.user.User;
import catering.persistence.PersistenceManager;

import java.util.ArrayList;

public class TurnManager {
    private static ArrayList<Turn> turns;

    public static ArrayList<Turn> getTurnBoard() {
        return Turn.getTurnBoard();
    }

}
