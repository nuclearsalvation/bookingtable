package presenters;

import models.TableService;
import views.BookingView;

import java.util.Date;


public class BookingPresenter implements ViewObserver {

    private Model model;
    private  View view;

    public BookingPresenter(Model model, View view){
        this.model = model;
        this.view = view;
        view.registerObserver(this);
    }

    public Model getModel(){
        return model;
    }

    public void setModel(Model model){
        this.model = model;
    }

    public void updateTablesView(){
        view.showTables(model.loadTables());
    }

    private void updateReservationTableView(int reservationNo){
        view.showReservationTableResult(reservationNo);
    }

    @Override
    public void onReservationTable(Date orderDate, int tableNo, String name) {
        try {
            int reservationNo = model.reservationTable(orderDate, tableNo, name);
            updateReservationTableView(reservationNo);
        }
        catch (Exception e){
            updateReservationTableView(-1);
        }

    }
    public void removeReservationTables(int id){
        if (model instanceof TableService){
            TableService tableService = (TableService) model;
            tableService.removeReservationTable(id);
            model = tableService;
        }
    }
}