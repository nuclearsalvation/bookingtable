import models.TableService;
import presenters.Model;
import presenters.BookingPresenter;
import views.BookingView;
import models.Table;
import models.Reservation;

import java.util.Date;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        Model model = new TableService();
        BookingView view = new BookingView();
        BookingPresenter presenter = new BookingPresenter(model, view);
        presenter.updateTablesView();
        view.reservationTable(new Date(), 2, "First");
        view.reservationTable(new Date(), 2, "Second");
        view.changeReservationTable(1001, new Date(), 2, "Third");
        for (Table table: model.loadTables()){
            if(table.getNo() == 2){
                for (Reservation reservation: table.getReservations()){
                    System.out.println(reservation.getName());
                }

            }
        }
    }
}
