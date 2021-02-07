package net.ddns.scvstorage.scvInfoManager.entity.toast.grid;

public class ToastGridData {
    
    private Object content;
    private ToastGridPagination pagination;

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public ToastGridPagination getPagination() {
        return pagination;
    }

    public void setPagination(ToastGridPagination pagination) {
        this.pagination = pagination;
    }

    @Override
    public String toString() {
        return "ToastGridData [content=" + content.toString() + ", pagination=" + pagination.toString() + "]";
    }

}