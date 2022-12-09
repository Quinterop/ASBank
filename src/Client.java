public class Client { //1.1.1 Creation of the Client class
    private String name;
    private String firstname;
    private  int clientNumber = 0;
    private static int nbOfClients = 0;
 
    public Client(String name, String firstname) {
        this.name = name;
        this.firstname = firstname;
        clientNumber = nbOfClients;
        nbOfClients++;
    }
    

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return int return the clientNumber
     */
    public int getClientNumber() {
        return clientNumber;
    }

    /**
     * @param clientNumber the clientNumber to set
     */
    public void setClientNumber(int clientNumber) {
        this.clientNumber = clientNumber;
    }

    /*
     * @return int return the nbOfClients
     */
    public String toString() {
        return "Client [clientNumber=" + clientNumber + ", firstname=" + firstname + ", name=" + name + "]";
    }

}
