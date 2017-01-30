package braintree.code.creditCard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestCreditCardProcess {

    @Test
    public void testProcessCommand() {
        CreditCardProcess ccp = new CreditCardProcess();

        // Normal Functionality Test:
        // Add Profile
        ccp.processCommand("Add Tom 4111111111111111 $2000");
        Profile tom = ccp.getClients().get("Tom");
        // Test Profile data
        assertEquals("Tom", tom.getName());
        assertEquals("4111111111111111", tom.getCardNum());
        assertEquals(2000, tom.getLimit());
        // Test initial account balance
        assertEquals(0, tom.getBalance());
        // Test luhn 10 account check
        assertTrue(tom.valid());

        // Charge money into account
        ccp.processCommand("Charge Tom $500");
        ccp.processCommand("Charge Tom $800");
        tom = ccp.getClients().get("Tom");
        assertEquals(1300, tom.getBalance());
        // Corner Case: Charge money to more than account limit
        ccp.processCommand("Charge Tom $800");
        tom = ccp.getClients().get("Tom");
        assertEquals(1300, tom.getBalance());
        // Corner Case: Charge money to exactly the account limit
        ccp.processCommand("Charge Tom $700");
        tom = ccp.getClients().get("Tom");
        assertEquals(2000, tom.getBalance());

        // Credit money from account
        ccp.processCommand("Credit Tom $700");
        tom = ccp.getClients().get("Tom");
        assertEquals(1300, tom.getBalance());
        // Corner Case: Credit money from account more than the account has
        ccp.processCommand("Credit Tom $1500");
        tom = ccp.getClients().get("Tom");
        assertEquals(-200, tom.getBalance());

        // Corner Case: Credit Card Number longer than 19 digits
        ccp.processCommand("Add Lisa 54545454545454545454 $3000");
        Profile lisa = ccp.getClients().get("Lisa");
        assertEquals("Lisa", lisa.getName());
        assertEquals("54545454545454545454", lisa.getCardNum());
        // Test invalid account: digits longer than 19
        assertFalse(lisa.valid());
        // Corner Case: Charge money to invalid account
        ccp.processCommand("Charge Lisa $700");
        lisa = ccp.getClients().get("Lisa");
        assertEquals(0, lisa.getBalance());
        // Corner Case: Credit money from invalid account
        ccp.processCommand("Credit Lisa $700");
        lisa = ccp.getClients().get("Lisa");
        assertEquals(0, lisa.getBalance());

        // Corner Case: invalid account number, failed Luhn 10 test
        ccp.processCommand("Add Quincy 1234567890123456 $2000");
        ccp.processCommand("Credit Quincy $200");
        Profile quincy = ccp.getClients().get("Quincy");
        assertEquals(0, quincy.getBalance());
        // Test accound validity
        assertFalse(quincy.valid());
    }
}
