package com.abhaycharanvoice.abhaycharan.Enum;

import lombok.Getter;

@Getter
public enum ResponseEnum {
    PENDING("PENDING"),
    CANCEL("CANCELLED"),
    SUCCESS("SUCCESS"),
    FAILED("FAILED"),
    NOTFOUND("NOT FOUND");

    private final String status;

    ResponseEnum(String status) {
        this.status = status;
    }
}
