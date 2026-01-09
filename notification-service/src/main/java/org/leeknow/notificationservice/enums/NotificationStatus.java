package org.leeknow.notificationservice.enums;

import lombok.Getter;

@Getter
public enum NotificationStatus {

    READ(1, "Прочитано"),
    UNREAD(2, "Не прочитано");

    private final int id;
    private final String name;

    NotificationStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
