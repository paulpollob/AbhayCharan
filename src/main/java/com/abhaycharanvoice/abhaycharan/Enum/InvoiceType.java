package com.abhaycharanvoice.abhaycharan.Enum;
import lombok.Getter;

@Getter
public enum InvoiceType {
    RECEIVED_FROM_DISTRIBUTOR("RECEIVED_FROM_DISTRIBUTOR"),
    SOLD_TO_CUSTOMER("SOLD_TO_CUSTOMER"),
    RETURN_TO_DISTRIBUTOR("RETURN_TO_DISTRIBUTOR"),
    RETURN_FROM_CUSTOMER("RETURN_FROM_CUSTOMER"),
    DAMAGE_TO_DISTRIBUTOR("DAMAGE_TO_DISTRIBUTOR"),
    DAMAGE_FROM_CUSTOMER("DAMAGE_FROM_CUSTOMER");

    private final String status;

    InvoiceType(String status) {
        this.status = status;
    }
}
