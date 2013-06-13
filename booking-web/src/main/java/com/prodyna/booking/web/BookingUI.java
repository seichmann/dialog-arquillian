package com.prodyna.booking.web;

import com.prodyna.booking.AircraftService;
import com.prodyna.booking.BookingService;
import com.prodyna.booking.model.Aircraft;
import com.prodyna.booking.model.Booking;
import com.prodyna.booking.model.Flight;
import com.vaadin.cdi.CDIUI;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;

import javax.ejb.EJB;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: seichmann
 * Date: 11.06.13
 * Time: 13:45
 * To change this template use File | Settings | File Templates.
 */
@CDIUI
public class BookingUI extends UI {

    @EJB
    AircraftService aircraftService;

    @EJB
    BookingService bookingService;


    @Override
    protected void init(VaadinRequest request) {
        setSizeFull();

        VerticalLayout layout = new VerticalLayout();

        List<Aircraft> message = aircraftService.list();
        Label label = new Label(message.toString());
        final TextField field = new TextField();
        Button button = new Button();

        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                aircraftService.create(field.getValue());
            }
        });
        HorizontalLayout fieldPanel = new HorizontalLayout();
        fieldPanel.addComponent(field);
        fieldPanel.addComponent(button);
        fieldPanel.addComponent(label);

        Table table = new Table();
        table.setRowHeaderMode(Table.RowHeaderMode.ID);
        table.setSizeFull();

        final BeanItemContainer<Booking> container = new BeanItemContainer<Booking>(Booking.class);
        container.addAll(bookingService.list());
        container.addNestedContainerProperty("flight.flightNumber");
        container.removeContainerProperty("flight");
        table.setContainerDataSource(container);

        HorizontalLayout toolbar = new HorizontalLayout();
        Button addButton = new Button("Add");
        addButton.addClickListener(new Button.ClickListener() {


            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                FormLayout formLayout = new FormLayout();

                // Form for editing the bean
                final BeanFieldGroup<Booking> binder =
                        new BeanFieldGroup<Booking>(Booking.class);
                binder.setBuffered(true);
                final Booking bean = new Booking();
                bean.setFlight(new Flight());
                binder.setItemDataSource(bean);
                formLayout.addComponent(binder.buildAndBind("Ticket", "ticket"));
                formLayout.addComponent(binder.buildAndBind("Flight Nr.", "flight.flightNumber"));


                Button save = new Button("Save");
                save.addClickListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        try {
                            binder.commit();
                            container.addBean(bean);
                        } catch (FieldGroup.CommitException e) {
                            throw new RuntimeException(e.getMessage());
                        }
                    }
                });
                formLayout.addComponent(save);

                UI.getCurrent().addWindow(new Window("Edit Booking", formLayout));
            }
        });
        toolbar.addComponent(addButton);

        layout.addComponent(fieldPanel);
        layout.addComponent(toolbar);
        layout.addComponent(table);


        UI.getCurrent().setErrorHandler(new ErrorHandler() {
            @Override
            public void error(com.vaadin.server.ErrorEvent event) {
                Notification.show(event.getThrowable().getLocalizedMessage(), Notification.Type.ERROR_MESSAGE);
            }
        });
        setContent(layout);
    }
}
