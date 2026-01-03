package org.leeknow.paymentservice.enums;

import lombok.Getter;

@Getter
public enum PaymentStatus {
    SUCCESS(1, "Успешно"),
    FAILED(2, "Неуспешно")
    ;

    private final int id;
    private final String name;

    PaymentStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

