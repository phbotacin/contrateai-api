package br.com.bruno2code.contrateai.model;

public class Message {

    private boolean success;
    private String message;
    private Object data;

    public Message(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Message(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public Message(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Message{");
        sb.append("success=").append(success);
        sb.append(", message=").append(message);
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }

}
