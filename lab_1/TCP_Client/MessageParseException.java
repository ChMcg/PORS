import java.text.ParseException;

public class MessageParseException extends ParseException {
    private static final long serialVersionUID = 2703218443322787635L;
    public MessageParseException(String errorMessage, int err) {
        super(errorMessage, err);
    } 
}
