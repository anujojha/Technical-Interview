package TsBlockingQueue;

/**
 * Created by Chaklader on 1/15/17.
 */
public class Message {

    private String crunchifyMsg;

    public Message(String string) {
        this.crunchifyMsg = string;
    }

    public String getMsg() {
        return crunchifyMsg;
    }
}
