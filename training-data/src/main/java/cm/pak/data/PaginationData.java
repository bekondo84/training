package cm.pak.data;

import cm.pak.models.security.base.ItemModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PaginationData<T extends Object> implements Serializable {
    private int totalPages ;
    private int currentPage;
    private  List<T> items ;

    public PaginationData() {
        totalPages = 0 ;
        currentPage = 0;
        items = new ArrayList<>();
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
