package views;

import models.Table;
import models.TableService;
import presenters.BookingPresenter;
import presenters.View;
import presenters.ViewObserver;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class BookingView implements View {


    private Collection<ViewObserver> observers;

    public void showTables(Collection<Table> tables){
        for (Table table: tables) {
            System.out.println(table);
        }
    }

    @Override
    public void registerObserver(ViewObserver observer) {
        if (observers == null)
            observers = new ArrayList<>();
        observers.add(observer);
    }

    @Override
    public void showReservationTableResult(int reservationNo) {
        if (reservationNo > 0){
            System.out.printf("Столик успешно забронирован. Номер брони: #%d\n", reservationNo);
        }
        else {
            System.out.println("Произошла ошибка при попытке забронировать столик.\nПовторите операцию позже.");
        }
    }


    public void reservationTable(Date orderDate, int tableNo, String name){
        if (observers != null)
        {
            for (ViewObserver observer : observers){
                observer.onReservationTable(orderDate, tableNo, name);
            }
        }
    }

    public void changeReservationTable(int oldReservation, Date reservationDate, int tableNo, String name){
        if (observers != null){
            for (ViewObserver observer: observers){
                if (observer instanceof BookingPresenter){
                    BookingPresenter presenter = (BookingPresenter) observer;
                    presenter.removeReservationTables(oldReservation);
                    observer = presenter;
                }
            }
        }

        reservationTable(reservationDate, tableNo, name);

    }

}