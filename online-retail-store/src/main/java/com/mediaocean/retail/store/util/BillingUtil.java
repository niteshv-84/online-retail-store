package com.mediaocean.retail.store.util;

import java.math.BigDecimal;

/**
 * Created by Nitesh on 9/7/2017.
 */
public class BillingUtil {

    private static final BigDecimal HUNDRED = new BigDecimal(100);
   public static BigDecimal calculateAmount(BigDecimal cost , Long quantity){
       return cost.multiply(BigDecimal.valueOf(quantity));
   }

   public static BigDecimal calculateTax(BigDecimal amount , BigDecimal taxPercentage){
       return amount.multiply(taxPercentage).divide(HUNDRED);
   }
}
