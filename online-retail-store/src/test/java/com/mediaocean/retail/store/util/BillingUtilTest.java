package com.mediaocean.retail.store.util;

import org.junit.Test;
import static org.junit.Assert.*;

import java.math.BigDecimal;


public class BillingUtilTest {

    private final BigDecimal HUNDRED = new BigDecimal(100);
    private final BigDecimal FIFTY = new BigDecimal(50);
    private final BigDecimal TWENTY = new BigDecimal(20);

    @Test
    public void calculateAmountWhenValidInput(){
        BigDecimal amount = BillingUtil.calculateAmount(HUNDRED , new Long(20));
        assertEquals(amount,HUNDRED.multiply(TWENTY));
    }

    @Test
    public void calculateAmountWhenZeroQuantity(){
        BigDecimal amount = BillingUtil.calculateAmount(HUNDRED , new Long(0));
        assertEquals(amount,HUNDRED.multiply(BigDecimal.ZERO));
    }

    @Test
    public void calculateAmountWhenZeroCost(){
        BigDecimal amount = BillingUtil.calculateAmount(BigDecimal.ZERO , new Long(20));
        assertEquals(amount,HUNDRED.multiply(BigDecimal.ZERO));
    }

    @Test
    public void calculateTaxWhenValidInput(){
        BigDecimal tax = BillingUtil.calculateTax(HUNDRED , BigDecimal.TEN);
        assertEquals(tax,BigDecimal.TEN);
    }

    @Test
    public void calculateTaxWhenZeroTaxPercentage(){
        BigDecimal tax = BillingUtil.calculateTax(HUNDRED , BigDecimal.ZERO);
        assertEquals(tax,BigDecimal.ZERO);
    }
}
