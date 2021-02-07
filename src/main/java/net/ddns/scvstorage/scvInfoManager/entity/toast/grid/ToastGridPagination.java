package net.ddns.scvstorage.scvInfoManager.entity.toast.grid;

public class ToastGridPagination {
    private int page = 1;
    private int totalCount = 0;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "ToastGridPagination [page=" + page + ", totalCount=" + totalCount + "]";
    }

}