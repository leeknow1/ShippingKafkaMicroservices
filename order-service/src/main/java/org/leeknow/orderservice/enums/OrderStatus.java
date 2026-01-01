package org.leeknow.orderservice.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {

    CREATED(1, "Создано"),
    COMPLETED(2, "Завершено")
    ;

    private final int id;
    private final String name;

    OrderStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
