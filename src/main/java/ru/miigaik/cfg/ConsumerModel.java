package ru.miigaik.cfg;

public class ConsumerModel
{
    private String name;
    private String phoneNumbers;
    private String zipCode;
    private String city;
    private String state;
    private String street;
    private String house;
    public ConsumerModel(String name, String phoneNumbers, String zipCode, String state, String city, String street, String house)
    {
        this.name = name;
        this.phoneNumbers = phoneNumbers;
        this.zipCode = zipCode;
        this.state = state;
        this.city = city;
        this.street = street;
        this.house = house;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }

    public String getName()
    {
        return name;
    }

    public String getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(String phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
