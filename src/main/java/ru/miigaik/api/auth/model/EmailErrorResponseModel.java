package ru.miigaik.api.auth.model;

import java.util.ArrayList;
import java.util.List;

public class EmailErrorResponseModel
{
    public ArrayList<String> email;

    public String getEmail()
    {
        return email.get(0);
    }
}
