import java.time.*;


public class Transfer extends Flow { //1.3.3 Creation of the Transfert, Credit, Debit classes
    private int sourceAccountNumber;

    public Transfer(String comment, int identifier, double amount, int targetAccountNumber, boolean effect,
            LocalDate flowDate, int sourceAccountNumber) {
        super(comment, identifier, amount, targetAccountNumber, effect, flowDate);
        this.sourceAccountNumber = sourceAccountNumber;
    }

    public int getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public void setSourceAccountNumber(int sourceAccountNumber) {
        this.sourceAccountNumber = sourceAccountNumber;
    }
    
}
