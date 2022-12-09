public class Account {
    protected String label;
    protected double balance; 
    protected int accountNumber;
    protected static int nbOfAccounts = 0;
    protected Client client;

    public Account(String label, Client client) { //1.2.1 Creation of the account class
        this.label = label;
        this.client = client;
        this.balance = 0;
        this.accountNumber = nbOfAccounts;
        nbOfAccounts++;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(Flow balance) {
       //check type of flow
         if (balance instanceof Credit) {
              this.balance += balance.getAmount();
         } else if (balance instanceof Debit) {
              this.balance -= balance.getAmount();
         } else if (balance instanceof Transfer) {
                if(this.accountNumber == ((Transfer) balance).getSourceAccountNumber()) {
                    this.balance -= balance.getAmount();
                } else if (this.accountNumber == ((Transfer) balance).getTargetAccountNumber()) {
                    this.balance += balance.getAmount();
                } else {
                    System.out.println("Error: account number not found");
                }
         }
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String toString() {
        return("Account number: " + accountNumber + " Label: " + label + " Balance: " + balance + " Client: " + client.toString());
    }

}
