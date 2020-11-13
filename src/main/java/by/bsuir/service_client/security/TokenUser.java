package by.bsuir.service_client.security;

public final class TokenUser {
    private final String id;
    private final String firstName;
    private final String lastName;
    private final String groupNumber;

    public TokenUser(String id, String firstName, String lastName, String groupNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.groupNumber = groupNumber;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGroupNumber() {
        return groupNumber;
    }
}
