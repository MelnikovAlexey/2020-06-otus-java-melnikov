package ru.otus.messagesystem.message;

public enum MessageType {
    USER_DATA("UserData"), USER_SAVE("UserSave"), USER_LIST("UserList"),UNKNOWN("Unknown");

    private final String name;

    MessageType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static MessageType getNameOrUnknown(String value) {
        try {
            return valueOf(value);
        } catch (IllegalArgumentException e) {
            return MessageType.UNKNOWN;
        }
    }
}
