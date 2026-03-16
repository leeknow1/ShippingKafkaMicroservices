package org.leeknow.commonservice.order.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {

    CREATED(1, "Создано"),
    COMPLETED(2, "Завершено"),
    PAYED(3, "Оплачено")
    ;

    private final int id;
    private final String name;

    OrderStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
