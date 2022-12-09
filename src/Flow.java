import java.time.*;

public abstract class Flow {
    private String comment;
    private int identifier;
    private double amount;
    private int targetAccountNumber;
    private boolean effect;
    private java.time.LocalDate flowDate;

    public Flow(String comment, int identifier, double amount, int targetAccountNumber, boolean effect, LocalDate flowDate) {
        this.comment = comment;
        this.identifier = identifier;
        this.amount = amount;
        this.targetAccountNumber = targetAccountNumber;
        this.effect = effect;
        this.flowDate = flowDate;
    }

    public Flow() {
    }

    /**
     * @return String return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return int return the identifier
     */
    public int getIdentifier() {
        return identifier;
    }

    /**
     * @param identifier the identifier to set
     */
    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    /**
     * @return double return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @return int return the targetAccountNumber
     */
    public int getTargetAccountNumber() {
        return targetAccountNumber;
    }

    /**
     * @param targetAccountNumber the targetAccountNumber to set
     */
    public void setTargetAccountNumber(int targetAccountNumber) {
        this.targetAccountNumber = targetAccountNumber;
    }

    /**
     * @return boolean return the effect
     */
    public boolean isEffect() {
        return effect;
    }

    /**
     * @param effect the effect to set
     */
    public void setEffect(boolean effect) {
        this.effect = effect;
    }

    /**
     * @return LocalDate return the flowDate
     */
    public LocalDate getFlowDate() {
        return flowDate;
    }

    /**
     * @param flowDate the flowDate to set
     */
    public void setFlowDate(LocalDate flowDate) {
        this.flowDate = flowDate;
    }

}
