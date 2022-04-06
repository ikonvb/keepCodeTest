package com.konstantinbulygin.onlinestore.model.restmodel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewOrder {

    private Integer customerId;
    private Double orderCost;

    public NewOrder(Integer customerId, Double orderCost) {
        this.customerId = customerId;
        this.orderCost = orderCost;
    }

    public NewOrder() {
    }
}
