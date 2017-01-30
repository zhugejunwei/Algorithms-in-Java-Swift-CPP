package braintree.code.creditCard;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

//Multi-thread safe class with locking only at important part of the method
class CreditCardProcess {
	// Normally should store into database, in this case use HashMap for fast
	// access and storage, it's not thread safe, but I handle that within
	// methods that use it
	private static volatile HashMap<String, Profile> clients = new HashMap<String, Profile>();

	// Add new user and account information
	private void add(String name, String cardNum, int limit) {
		try {
			Profile p = new Profile(name, limit, cardNum);
			if (!clients.containsKey(name)) {
				// Locking on CreditCardProcess Class because clients variable
				// is shared among different threads
				synchronized (CreditCardProcess.class) {
					clients.put(name, p);
				}
			}
		} catch (Throwable e) {
			// Exception Handling, normally should write to log file for
			// checking and debugging.
			throw new RuntimeException(e);
		}
	}

	// Charge money to user account specified by name with the amount
	private void charge(String name, int amount) {
		try {
			if (clients.containsKey(name)) {
				Profile p = clients.get(name);
				p.addBalance(amount);
				synchronized (CreditCardProcess.class) {
					clients.put(name, p);
				}
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	// Credit money from user account specified by name with the amount
	private void credit(String name, int amount) {
		try {
			if (clients.containsKey(name)) {
				Profile p = clients.get(name);
				p.deductBalance(amount);
				synchronized (CreditCardProcess.class) {
					clients.put(name, p);
				}
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	// Process add, charge and credit command
	public void processCommand(String line) {
		try {
			String[] command = line.replaceAll("[$]", "").split(" ");
			if (command[0].equals("Add") && command.length == 4) {
				add(command[1], command[2], Integer.valueOf(command[3]));
			} else if (command[0].equals("Charge") && command.length == 3) {
				charge(command[1], Integer.valueOf(command[2]));
			} else if (command[0].equals("Credit") && command.length == 3) {
				credit(command[1], Integer.valueOf(command[2]));
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	// Print out all the users' name and balance
	public void print() {
		try {
			ArrayList<String> list = new ArrayList<String>(clients.keySet());
			Collections.sort(list);
			for (String name : list) {
				Profile p = clients.get(name);
				if (p.valid()) {
					System.out.println(p.getName() + ": $" + p.getBalance());
				} else {
					System.out.println(p.getName() + ": error");
				}
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	// Unit testing purpose only
	public HashMap<String, Profile> getClients() {
		return clients;
	}

	// Accept either command or file location from stdIn
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		CreditCardProcess ccp = new CreditCardProcess();
		try {
			// Read in the first line to check
			String line = br.readLine();
			try {
				// Try use this first-line as file location
				BufferedReader fbr = new BufferedReader(new FileReader(line));
				// Success, then proceed to read in commands from file
				while ((line = fbr.readLine()) != null) {
					ccp.processCommand(line);
				}
				fbr.close();
				//Encountered error - file does not exist
			} catch (FileNotFoundException e) {
				//Use first line as command
				ccp.processCommand(line);
				//Keep accepting command from console until an empty line is enter
				while (!(line = br.readLine()).isEmpty()) {
					ccp.processCommand(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ccp.print();
	}
}