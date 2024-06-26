package com.weather.pagination;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.data.provider.QuerySortOrder;
import com.vaadin.flow.function.SerializableComparator;
import com.vaadin.flow.shared.Registration;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class PaginatedGrid <T, F> extends Grid<T> {

    private final LitPagination pagination = new LitPagination();

    private Component paginationContainer = null;

    private PaginationLocation paginationLocation = PaginationLocation.BOTTOM;

    private DataProvider<T, ?> dataProvider;

    private F filter;

    public PaginatedGrid() {
        super();
        init();
    }

    public PaginatedGrid(Class<T> beanType) {
        super(beanType);
        init();
    }

    private void init() {
        this.dataProvider = super.getDataProvider();
        this.setAllRowsVisible(true);
        pagination.addPageChangeListener(e -> doCalcs(e.getNewPage()));
        addSortListener(e -> doCalcs(pagination.getPage()));
    }


    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);

        Span wrapper = new Span(pagination);
        wrapper.getElement().getStyle().set("width", "100%");
        wrapper.getElement().getStyle().set("display", "flex");
        wrapper.getElement().getStyle().set("justify-content", "center");

        if (paginationContainer!=null){
            paginationContainer.getElement().insertChild(0, wrapper.getElement());
        } else {
            getParent().ifPresent(p -> {

                int indexOfChild = p.getElement().indexOfChild(this.getElement());

                //this moves the pagination element below the grid
                if (paginationLocation == PaginationLocation.BOTTOM)
                    indexOfChild++;

                p.getElement().insertChild(indexOfChild, wrapper.getElement());
            });
        }
        doCalcs(0);
    }

    private void doCalcs(int newPage) {
        int offset = newPage > 0 ? (newPage - 1) * this.getPageSize() : 0;

        InnerQuery query = new InnerQuery<>(offset, filter);

        pagination.setTotal(dataProvider.size(query));

        super.setDataProvider(DataProvider.fromStream(dataProvider.fetch(query)));

    }

    public void refreshPaginator() {
        if (pagination != null) {
            pagination.setPageSize(getPageSize());
            pagination.setPage(1);
            if (dataProvider != null) {
                doCalcs(pagination.getPage());
            }
            pagination.refresh();
        }
    }

    @Override
    public void setAllRowsVisible(boolean allRowsVisible) {
        super.setAllRowsVisible(true);
    }


    public void setPaginatorSize(int size) {
        pagination.setPage(1);
        pagination.setPaginatorSize(size);
        pagination.refresh();
    }


    @Override
    public void setDataProvider(DataProvider<T, ?> dataProvider) {
        Objects.requireNonNull(dataProvider, "DataProvider shoul not be null!");

        if (!Objects.equals(this.dataProvider, dataProvider)) {
            this.dataProvider = dataProvider;
            this.dataProvider.addDataProviderListener(event -> {
                refreshPaginator();
            });
            refreshPaginator();
        }

    }


    public enum PaginationLocation {TOP, BOTTOM}

    private class InnerQuery<F> extends Query<T, F> {

        InnerQuery() {
            this(0);
        }

        InnerQuery(F filter) {
            this(0, filter);
        }

        InnerQuery(int offset) {
            super(offset, pagination.getPageSize(), getDataCommunicator().getBackEndSorting(), getDataCommunicator().getInMemorySorting(), null);
        }

        InnerQuery(int offset, F filter) {
            super(offset, pagination.getPageSize(), getDataCommunicator().getBackEndSorting(), getDataCommunicator().getInMemorySorting(), filter);
        }

        InnerQuery(int offset, List<QuerySortOrder> sortOrders, SerializableComparator<T> serializableComparator) {
            super(offset, pagination.getPageSize(), sortOrders, serializableComparator, null);
        }

    }

}