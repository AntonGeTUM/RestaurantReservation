package logic;

public class SortingOptions {

    private SortingOrder sortingOrder = SortingOrder.ASCENDING;
    private SortField sortField = SortField.NAME;

    public SortingOptions() {}

    public enum SortingOrder {
        ASCENDING, DESCENDING
    }

    public SortingOrder getSortingOrder() {
        return sortingOrder;
    }

    public void setSortingOrder(SortingOrder sortingOrder) {
        this.sortingOrder = sortingOrder;
    }

    public SortField getSortField() {
        return sortField;
    }

    public void setSortField(SortField sortField) {
        this.sortField = sortField;
    }

    public enum SortField {
        NAME, CUISINE, PRICE_CATEGORY, REVIEW
    }
}
