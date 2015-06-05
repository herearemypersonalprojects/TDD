/*
 * =============================================================================
 *
 *   PRICE MINISTER APPLICATION
 *   Copyright (c) 2000 Babelstore.
 *   All Rights Reserved.
 *
 *   $Source$
 *   $Revision$
 *   $Date$
 *   $Author$
 *
 * =============================================================================
 */
package com.priceminister.account.implementation;

import com.priceminister.account.AccountRule;

/**
 * Checks if the requested operation is permitted.
 * @author Le Quoc Anh
 *
 */
public class CustomerAccountRule implements AccountRule {

    /**
     * Checks if the resulting account balance after a withdrawal is not negative.
     * @param resultingAccountBalance : the balance to be checked
     * @return true if the balance is not negative, false otherwise
     */
    public boolean withdrawPermitted(Double resultingAccountBalance) {
        checkNullValue(resultingAccountBalance, "The account's balance as input is null");
        return resultingAccountBalance >= 0;
    }

    /**
     * Tests in case a null value throws an exception.
     * @param input : the value to be checked.
     * @param msg : the message to inform.
     */
    private void checkNullValue(Double input, String msg) {
        if (input == null) {
            throw new IllegalArgumentException(msg);
        }
    }
}
