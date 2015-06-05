package com.priceminister.account;

/**
 * Exception when  a withdrawal leaves the account with a forbidden balance.
 */
public class IllegalBalanceException extends Exception {
    /** the number for serialization and deserialization of the object.  */
    private static final long serialVersionUID = -9204191749972551939L;

    /** the account's balance. */
    private final Double balance;

    /**
     * Initialize exception.
     * @param illegalBalance : the illegal balance.
     */
    public IllegalBalanceException(Double illegalBalance) {
        balance = illegalBalance;
    }

    /**
     * Display the infomration conernant illegal balance.
     * @return the text to be displayed
     */
    public String toString() {
        return "Illegal account balance: " + balance;
    }
}
