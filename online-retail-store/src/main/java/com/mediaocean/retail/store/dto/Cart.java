package com.mediaocean.retail.store.dto;

import lombok.Data;

import java.util.List;


@Data
public class Cart {

    private List<Item> itemList;
}
