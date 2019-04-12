package ru.stqa.pft.addressbook;

public class ContactData {
    private final String firstName;
    private final String lastName;
    private final String city;
    private final String phoneNumber;
    private final String email;

    public ContactData(String FirstName, String LastName, String City, String PhoneNumber, String Email) {
        firstName = FirstName;
        lastName = LastName;
        city = City;
        phoneNumber = PhoneNumber;
        email = Email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCity() {
        return city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}
