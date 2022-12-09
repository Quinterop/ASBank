import java.time.*;


public class Debit extends Flow { //1.3.3 Creation of the Transfert, Credit, Debit classes

    public Debit(String comment, int identifier, double amount, int targetAccountNumber, boolean effect,
            LocalDate flowDate) {
        super(comment, identifier, amount, targetAccountNumber, effect, flowDate);
    }

    public Debit() {
    }
    
}
