package ph.com.developer.jc.spywho;

/**
 * Created by john_dongalen on 7/20/2016.
 */
public class Model_SmsMessage {

    private static String MessageBody;
    private static String Receiver;
    private static String Sender;
    private static String DateReceived;
    private static String SimSlot;

    public Model_SmsMessage(){

    }

    public static String getMessageBody() {
        return MessageBody;
    }

    public static void setMessageBody(String messageBody) {
        MessageBody = messageBody;
    }

    public static String getReceiver() {
        return Receiver;
    }

    public static void setReceiver(String receiver) {
        Receiver = receiver;
    }

    public static String getSender() {
        return Sender;
    }

    public static void setSender(String sender) {
        Sender = sender;
    }

    public static String getDateReceived() {
        return DateReceived;
    }

    public static void setDateReceived(String dateReceived) {
        DateReceived = dateReceived;
    }

    public static String getSimSlot() {
        return SimSlot;
    }

    public static void setSimSlot(String simSlot) {
        SimSlot = simSlot;
    }
}
