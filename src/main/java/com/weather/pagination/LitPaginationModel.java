package com.weather.pagination;



import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.Synchronize;

public interface LitPaginationModel extends HasElement {


    @Synchronize("page-change")
    default int getPage(){
        return Integer.valueOf(getElement().getProperty("page", "0"));
    }


    default void setPage(int page){
        getElement().setProperty("page", Integer.toString(page));
    }


    @Synchronize("change")
    default int getTotal(){
        return Integer.valueOf(getElement().getProperty("total", "0"));
    }


    default void setTotal(int total){
        getElement().setProperty("total", Integer.toString(total));
    }


    default void setLimit(int limit){
        getElement().setProperty("limit", Integer.toString(limit));
    }


    @Synchronize("change")
    default int getLimit(){
        return Integer.valueOf(getElement().getProperty("limit", "0"));
    }


    default void setSize(int size){
        getElement().setProperty("size", Integer.toString(size));
    }


    default void setPageText(String pageText){
        getElement().setProperty("pageText", pageText);
    }


    default void setOfText(String ofText){
        getElement().setProperty("ofText", ofText);
    }


}
