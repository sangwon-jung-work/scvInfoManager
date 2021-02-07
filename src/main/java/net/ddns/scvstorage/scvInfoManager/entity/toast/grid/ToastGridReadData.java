package net.ddns.scvstorage.scvInfoManager.entity.toast.grid;

public class ToastGridReadData {
    
    private boolean result;
    private ToastGridData data;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public ToastGridData getData() {
        return data;
    }

    public void setData(ToastGridData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ToastGridReadData [data=" + data + ", result=" + result + "]";
    }

}