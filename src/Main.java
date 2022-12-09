import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileNotFoundException;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;






public class Main {//1.1.2 Creation of main class for tests
    static ArrayList<Client> clients = new ArrayList<Client>();
    static ArrayList<Account> accounts = new ArrayList<Account>();
    static Hashtable<Integer, Account> accountsTable = new Hashtable<Integer, Account>();
    static ArrayList<Flow> flows = new ArrayList<Flow>();//1.3.4 Creation of flows
    
    
    public static void clientGenerator(int nbCLients){ 
        for (int i = 0; i < nbCLients; i++) {
            Client client = new Client("name"+i, "firstname"+i);
            clients.add(client);
        }
        
    }
    
    public static void displayClients(Collection<Client> clients){ 
        clients.stream().forEach(Client::toString);
        Stream<Client> stream = clients.stream();
        
        stream.forEach(obj -> System.out.println(obj.toString()));
    }
    
    public static void fillMap() {
        for (Account account : accounts) {
            accountsTable.put(account.getAccountNumber(), account);
        }
    }
    
    
    public static void displayHashTable(Hashtable<Integer, Account> table) {  //1.3.1 Adaptation of the table of accounts
        
        // Use streams to sort the entries in the HashTable by the Client's number field
        // in ascending order, and then collect the entries into a List
        List<Map.Entry<Integer, Account>> sortedEntries = accountsTable.entrySet().stream()
        .sorted(Map.Entry.comparingByValue((c1, c2) -> Double.compare(c1.getBalance(), c2.getBalance())))
        .collect(Collectors.toList());
        
        // Loop through the sorted entries and print out each one
        for (Map.Entry<Integer, Account> entry : sortedEntries) {
            System.out.println("hash"+entry.getKey() + ": " + entry.getValue());
        }
    }
    
    public static ArrayList<Account> loadAccounts(Collection<Client> clients, ArrayList<Account> accounts) { //1.2.3 Creation of the tablea account
        for (Client client : clients) {
            Account savings = new SavingsAccount("account"+client.getClientNumber(), client);
            Account checking = new currentAccount("account"+client.getClientNumber(), client);
            accounts.add(savings);
            accounts.add(checking);
        }
        return accounts;
    }
    
    public static void displayAccounts(ArrayList<Account> accounts) {
        accounts.stream().map(Object::toString).forEach(System.out::println);
    }
    
    public static void fillFlows(ArrayList<Flow> flows, ArrayList<Account> accounts) { //1.3.4 Creation of the flow array
        
        LocalDate date = LocalDate.now();
        date.plusDays(2);
        flows.add(new Debit("", 0, 50, 1, false, date));
        for (int i = 1; i < accounts.size(); i++) {
            if(accounts.get(i) instanceof SavingsAccount) 
            flows.add(new Credit("", 0, 1500, i, false, date));
            else
            flows.add(new Credit("", 0, 100.50, i, false, date));
        }
        flows.add( new Transfer("", 0, 50, 2, false, date, 1));
    }
    
    public static void applyFlows(List<Account> accounts, ArrayList<Flow> flows) { //1.3.5 Updating accounts
        for (Flow flow : flows) {
            accounts.get(flow.getTargetAccountNumber()).setBalance(flow);
            if(flow instanceof Transfer) {
                accounts.get(((Transfer) flow).getSourceAccountNumber()).setBalance(flow);
            }
        }
        
        for (Account account : accounts) {
            isPositive(account.getBalance()).ifPresentOrElse(
            (balance) -> System.out.print(""),
            () -> {
                System.out.println("Balance is negative for account "+account.getAccountNumber()+"!");
            }   
            );
        }
    }
    
    public static Flow[] getJsonFlows(String path) throws FileNotFoundException { //2.1 JSON file of flow
        Debit[] jflows = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            
            // read JSON file containing multiple flows
            jflows = mapper.readValue(new File("flows.json"), Debit[].class);
            
            // print out the details of each flow
            for (Flow flow : jflows) {
                System.out.println("Comment: " + flow.getComment());
                System.out.println("Identifier: " + flow.getIdentifier());
                System.out.println("Amount: " + flow.getAmount());
                System.out.println("Target Account Number: " + flow.getTargetAccountNumber());
                System.out.println("Effect: " + flow.isEffect());
                System.out.println("Flow Date: " + flow.getFlowDate());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //convert array to arraylist
        //put flows in this.flows
        flows = new ArrayList<Flow>(Arrays.asList(jflows));
        return jflows;
        
    }
    
    public static ArrayList<Account> getAccountsFromXML(String xmlFilePath) { //2.2 XML file of account
        // Create a list to store the accounts
        List<Account> accounts = new ArrayList<>();
        
        try {
            // Create a document builder to parse the XML file
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(xmlFilePath);
            
            // Get the root element of the document
            Element root = doc.getDocumentElement();
            
            // Get all elements with the tag "account"
            NodeList accountElements = root.getElementsByTagName("account");
            
            // Loop through each account element
            for (int i = 0; i < accountElements.getLength(); i++) {
                // Get the current account element
                Element accountElement = (Element) accountElements.item(i);
                
                // Extract the data for the account
                String label = accountElement.getAttribute("label");
                double balance = Double.parseDouble(accountElement.getAttribute("balance"));
                int accountNumber = Integer.parseInt(accountElement.getAttribute("accountNumber"));
                int clientNb = Integer.parseInt(accountElement.getAttribute("clientNumber"));
                
                // Create a new account object with the extracted data
                Account account = new Account(label, clients.get(clientNb));
                account.setBalance(new Credit("", 0, balance, accountNumber, false, LocalDate.now()));
                
                
                account.setAccountNumber(accountNumber);
                
                // Add the account to the list
                accounts.add(account);
            }
        } catch (Exception e) {
            // Handle any exceptions that occur
            e.printStackTrace();
        }
        //convert array to arraylist
        ArrayList<Account> acc2 = new ArrayList<Account>(accounts);
        displayAccounts(acc2);
        
        // Return the list of accounts
        accounts = acc2;
        return acc2;
        
    }
    
    
    public static Optional<Double> isPositive(Double balance) {
        Predicate<Double> isPositive = (amount) -> amount >= 0;
        return isPositive.test(balance) ? Optional.of(balance) : Optional.empty();
    }
    
    public static void main(String[] args) { 
        clientGenerator(5);
        displayClients(clients);
        // getJsonFlows("./flows.json");
        // accounts = getAccountsFromXML("./accounts.xml");
        //Note: these methods can only add Credit flows and normal accounts due to the implementation (would require other methods for other flows and accounts)
        accounts = loadAccounts(clients, accounts);
        fillMap();
        displayAccounts(accounts);
        fillFlows(flows, accounts);
        applyFlows(accounts, flows);
        displayAccounts(accounts);
        displayHashTable(accountsTable);
        
        
        
    }
}
