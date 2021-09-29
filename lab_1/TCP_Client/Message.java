
public class Message {
    public int header;
    public String data;

    public Message() {}

    public Message(int header, String data) {
        this.header = header;
        this.data = data;
    }

    public String toString() {
        return String.valueOf(header) + ":" + data;
    }

    public static Message fromString(String data) throws MessageParseException {
        Message ret = new Message();
        String[] splitted = data.split(":");
        if (splitted.length != 2) {
            throw new MessageParseException("Error parsing: " + data, 2);
        } else {
            try {
                ret.header = Integer.parseInt(splitted[0]);
                ret.data = splitted[1];
                return ret;
            } catch (NumberFormatException e) {
                throw new MessageParseException("Error parsing: " + data, 0);
            }
        }
    }
}
