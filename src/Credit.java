import java.time.*;


public class Credit extends Flow { //1.3.3 Creation of the Transfert, Credit, Debit classes

    public Credit(String comment, int identifier, double amount, int targetAccountNumber, boolean effect,
            LocalDate flowDate) {
        super(comment, identifier, amount, targetAccountNumber, effect, flowDate);
    }
    
}
