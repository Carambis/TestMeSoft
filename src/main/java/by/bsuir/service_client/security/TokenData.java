package by.bsuir.service_client.security;

public enum TokenData {
    TOKEN("token"),
    ID("id"),
    FIRSTNAME("firstName"),
    LASTNAME("lastName"),
    GROUPNUMBER("groupNumber");

    private final String value;

    TokenData(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
