package stay.walker.com.eventbus;

public class MessageEvent {
    private String message;
    public  MessageEvent(String message){
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
