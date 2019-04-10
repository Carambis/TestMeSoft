package service_client.data.request;


public class UserCreation {
    public Long Id;
    public String firstName;
    public String lastName;

    public UserCreation() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
