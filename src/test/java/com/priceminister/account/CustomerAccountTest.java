package com.priceminister.account;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.priceminister.account.implementation.CustomerAccount;
import com.priceminister.account.implementation.CustomerAccountRule;


/**
 * CERTAIN REQUIREMENTS FOR A CUSTOMER ACCOUNT
 * 1. An empty account means its balance is 0.0, not a NULL
 * 2. It can not be added by a negative amount nor by a NULL
 * 3. The customer can not withdraw an amount that is more than the account's balance
 * 4. It can not be withdrawn by a negative amount nor by a NULL
 * 5. There is an amount limit for each withdrawal.
 * 6. The account's rule must not be a NULL
 * 7. The reporting balance to be checked by the account's rule must not be a NULL.
 *
 * Each requirement has several test cases: one positive test, one negative test and one NULL test.
 * We are following the red/green/refactor methododology.
 *
 * @author Le Quoc Anh
 */
public class CustomerAccountTest {
    /** A simple client account. */
    private Account customerAccount;
    /** Rules for requested operations on an account. */
    private AccountRule rule;

    /**
     * Initialize the customer account.
     * @throws java.lang.Exception : if the operation fails.
     */
    @Before
    public void setUpCustomerAccount() throws Exception {
        customerAccount = new CustomerAccount();
    }

    /**
     * Initialize the customer account rule.
     * @throws Exception : if the operation fails.
     */
    @Before
    public void setUpCustomerAccountRule() throws Exception {
        rule = new CustomerAccountRule();
    }

    /**
     * Tests that an empty account always has a balance of 0.0, not a NULL.
     */
    @Test
    public void testAccountWithoutMoneyHasZeroBalance() {
        Assert.assertEquals(customerAccount.getBalance(), new Double(0.0));
    }

    /**
     * Adds money to the account and checks that the new balance is as expected.
     */
    @Test
    public void testAddPositiveAmount() {
        Double addedAmount = Math.random() * Double.MAX_VALUE;
        Assert.assertEquals(addedAmount, this.addAmount(addedAmount));
    }

    /**
     * Adds a negative amount to the account and check that this throws the expected exception..
     */
    @Test (expected = IllegalArgumentException.class)
    public void testAddNegativeAmount() {
        Double addedAmount = 0d - Math.random() * Double.MAX_VALUE;
        this.addAmount(addedAmount);
    }

    /**
     * Adds a null amount to the account and check that this throws the expected exception..
     */
    @Test (expected = IllegalArgumentException.class)
    public void testAddNullAmount() {
        Double addedAmount = null;
        this.addAmount(addedAmount);
    }

    /**
     * Tests that a null withdrawal throws the expected exception.
     * @throws IllegalBalanceException if the operation fails
     */
    @Test (expected = IllegalArgumentException.class)
    public void testWithdrawNullAmount() throws IllegalBalanceException {
        Double withdrawnAmount = null;
        customerAccount.withdrawAndReportBalance(withdrawnAmount, rule);
    }
    /**
     * Tests in case a negative withdrawal throws the expected exception.
     * @throws IllegalBalanceException if the operation fails
     */
    @Test (expected = IllegalArgumentException.class)
    public void testWithdrawNegativeAmount() throws IllegalBalanceException {
        Double withdrawnAmount = 0d - 1d;
        customerAccount.withdrawAndReportBalance(withdrawnAmount, rule);
    }

    /**
     * Tests in case withdrawal exceeds permitted amount throws the expected exception.
     * @throws IllegalBalanceException if the operation fails
     */
    @Test (expected = IllegalArgumentException.class)
    public void testWithdrawExceededAmount() throws IllegalBalanceException {
        Double withdrawnAmount = CustomerAccount.MAX_WITHDRAWAL_AMOUNT_PERMITTED + 1d;
        customerAccount.withdrawAndReportBalance(withdrawnAmount, rule);
    }

    /**
     * Tests in case an illegal withdrawal throws the expected exception.
     * Use the logic contained in CustomerAccountRule; feel free to refactor the existing code.
     * @throws IllegalBalanceException if the operation fails
     */
    @Test (expected = IllegalBalanceException.class)
    public void testWithdrawAndReportBalanceIllegalBalance() throws IllegalBalanceException {
        Double withdrawnAmount = Math.random() * CustomerAccount.MAX_WITHDRAWAL_AMOUNT_PERMITTED;
        customerAccount.withdrawAndReportBalance(withdrawnAmount, rule);
    }

    /**
     * Tests in case a null rule throws the expected exception.
     * @throws IllegalBalanceException if the operation fails
     */
    @Test (expected = IllegalArgumentException.class)
    public void testWithdrawalAndNullRule() throws IllegalBalanceException {
        Double addedAmount = Math.random() * CustomerAccount.MAX_WITHDRAWAL_AMOUNT_PERMITTED;
        addAmount(addedAmount);
        Double withdrawnAmount = Math.random() * customerAccount.getBalance();
        rule = null;
        customerAccount.withdrawAndReportBalance(withdrawnAmount, rule);
    }

    /**
     * Tests in case a null balance to be checked by the account's rule throws the expected exception.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testNullReportingBalanceAndRule() {
        Double checkBalance = null;
        rule.withdrawPermitted(checkBalance);
    }
    /**
     * Add an amount to the account.
     * @param addedAmount : the added amount
     * @return the account's balance
     */
    private Double addAmount(Double addedAmount) {
        customerAccount.add(addedAmount);
        return customerAccount.getBalance();
    }
}
