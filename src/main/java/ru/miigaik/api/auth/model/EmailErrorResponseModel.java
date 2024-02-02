package ru.miigaik.api.auth.model;

import java.util.ArrayList;

public class EmailErrorResponseModel
{
    private ArrayList<String> email;

    public ArrayList<String> getEmail() {
        return email;
    }

    public void setEmail(ArrayList<String> email) {
        this.email = email;
    }
}
