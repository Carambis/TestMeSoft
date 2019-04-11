package service_client.security;

public final class TokenUser {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String groupNumber;

    public TokenUser(Long id, String firstName, String lastName, String groupNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.groupNumber = groupNumber;
    }

    public Long getId() {
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
