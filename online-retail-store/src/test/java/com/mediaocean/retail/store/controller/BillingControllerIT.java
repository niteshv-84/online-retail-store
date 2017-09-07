package com.mediaocean.retail.store.controller;

import com.mediaocean.retail.store.RetailStoreApp;
import com.mediaocean.retail.store.dto.Cart;
import com.mediaocean.retail.store.testutil.TestDataBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RetailStoreApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BillingControllerIT {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void generateBill() {

        HttpEntity<Cart> entity = new HttpEntity<Cart>(TestDataBuilder.getCart(), headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/bills"),
                HttpMethod.POST, entity, String.class);
       assertThat(response.getBody(), containsString("\"taxableAmount\":1060.00,\"salesTax\":106.0000,\"totalAmount\":1166.0000"));

    }

    @Test
    public void generateBillForException() {

        Cart cart = TestDataBuilder.getCart();
        cart.setItemList(null);
        HttpEntity<Cart> entity = new HttpEntity<Cart>(cart, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/bills"),
                HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.NOT_FOUND , response.getStatusCode());
        assertThat(response.getBody(), containsString("\"errorCode\":\"1001\",\"errorMessage\":\"Invalid Cart Details: Null Cart or null/empty Item List\""));
    }
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
