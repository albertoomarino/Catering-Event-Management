package catering.businesslogic.event;

import catering.businesslogic.menu.Menu;
import catering.businesslogic.menu.MenuItem;
import catering.businesslogic.menu.Section;
import catering.businesslogic.user.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import catering.persistence.PersistenceManager;
import catering.persistence.ResultHandler;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

public class ServiceInfo implements EventItemInfo {
    private int id;
    private String name;
    private Date date;
    private Time timeStart;
    private Time timeEnd;
    private int participants;
    private EventInfo evI;

    public int getId() {
        return id;
    }

    private Menu menu;

    public ServiceInfo(String name) {
        this.name = name;
    }

    public ServiceInfo() {
    }

    public void setEvent(EventInfo ev) {
        this.evI = ev;
    }

    public EventInfo getEvent() {
        return this.evI;
    }

    public String toString() {
        return name + ": " + date + " (" + timeStart + "-" + timeEnd + "), " + participants + " pp.";
    }

    // STATIC METHODS FOR PERSISTENCE

    public static ObservableList<ServiceInfo> loadServiceInfoForEvent(int event_id) {
        ObservableList<ServiceInfo> result = FXCollections.observableArrayList();
        String query = "SELECT id, name, service_date, time_start, time_end, expected_participants " +
                "FROM Services WHERE event_id = " + event_id;
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                String s = rs.getString("name");
                ServiceInfo serv = new ServiceInfo(s);
                serv.id = rs.getInt("id");
                serv.date = rs.getDate("service_date");
                serv.timeStart = rs.getTime("time_start");
                serv.timeEnd = rs.getTime("time_end");
                serv.participants = rs.getInt("expected_participants");
                result.add(serv);
            }
        });

        return result;
    }

    public static ServiceInfo loadAllServiceInfo(int service_id) {
        ServiceInfo ser = new ServiceInfo();
        String query = "SELECT * FROM Services WHERE id = " + service_id;
        PersistenceManager.executeQuery(query, rs -> {
            ser.name = rs.getString("name");
            ser.id = rs.getInt("id");
            ser.date = rs.getDate("service_date");
            ser.timeStart = rs.getTime("time_start");
            ser.timeEnd = rs.getTime("time_end");
            ser.participants = rs.getInt("expected_participants");
            ser.menu = Menu.loadMenuById(rs.getInt("approved_menu_id"));
        });
        return ser;
    }
}
