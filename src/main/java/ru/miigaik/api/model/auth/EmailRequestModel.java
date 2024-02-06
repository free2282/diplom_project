package ru.miigaik.api.model.auth;

public class EmailRequestModel
{
    private String email;


    public EmailRequestModel(String email)
    {
        this.email = email;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
