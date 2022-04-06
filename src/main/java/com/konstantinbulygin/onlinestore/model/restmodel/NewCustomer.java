package com.konstantinbulygin.onlinestore.model.restmodel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewCustomer {

    private String customerName;

    public NewCustomer(String customerName) {
        this.customerName = customerName;
    }
    public NewCustomer() {
    }
}