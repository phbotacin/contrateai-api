package br.com.bruno2code.contrateai.model;

public class MessagePaginated {

    private boolean success;
    private String message;
    private int page;
    private int qtdReg;
    private int qtdLoad;
    private Object data;

    public MessagePaginated(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public MessagePaginated(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public MessagePaginated(boolean success, int page, int qtdReg, int qtdLoad, Object data) {
        this.success = success;
        this.page = page;
        this.qtdReg = qtdReg;
        this.qtdLoad = qtdLoad;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getQtdReg() {
        return qtdReg;
    }

    public void setQtdReg(int qtdReg) {
        this.qtdReg = qtdReg;
    }

    public int getQtdLoad() {
        return qtdLoad;
    }

    public void setQtdLoad(int qtdLoad) {
        this.qtdLoad = qtdLoad;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
