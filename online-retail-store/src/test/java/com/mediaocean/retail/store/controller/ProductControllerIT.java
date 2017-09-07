package com.mediaocean.retail.store.controller;

import com.mediaocean.retail.store.RetailStoreApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RetailStoreApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerIT {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void getAllProducts() {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/products"),
                HttpMethod.GET, entity, String.class);
        assertThat(response.getBody(), containsString("\"productName\":\"Oil\",\"category\":\"C\""));
        assertThat(response.getBody(), containsString("\"productName\":\"Chocolate\",\"category\":\"B\""));

    }

    @Test
    public void getProductById() {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/products/1"),
                HttpMethod.GET, entity, String.class);
       assertThat(response.getBody(), containsString("\"productID\":1,\"productName\":\"Singapore Apple\",\"category\":\"A\""));
    }

    @Test
    public void getProductByName() {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/products?name=Oil"),
                HttpMethod.GET, entity, String.class);
        assertThat(response.getBody(), containsString("\"productID\":5,\"productName\":\"Oil\",\"category\":\"C\""));
    }

    @Test
    public void getProductByCategory() {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/products?category=C"),
                HttpMethod.GET, entity, String.class);
        assertThat(response.getBody(), containsString("\"productName\":\"Oil\",\"category\":\"C\""));
        assertThat(response.getBody(), containsString("\"productName\":\"Milk\",\"category\":\"C\""));
    }

    @Test
    public void getProductByCategoryForException() {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/products?category=Z"),
                HttpMethod.GET, entity, String.class);
       assertEquals(HttpStatus.NOT_FOUND , response.getStatusCode());
        assertThat(response.getBody(), containsString("\"errorCode\":\"1001\",\"errorMessage\":\"Invalid Product category: Z\""));
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
