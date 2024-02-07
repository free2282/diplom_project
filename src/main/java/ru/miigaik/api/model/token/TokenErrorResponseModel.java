package ru.miigaik.api.model.token;

import java.util.ArrayList;

public class TokenErrorResponseModel
{
    public ArrayList<String> email;
    public ArrayList<String> token;

    public String getEmail()
    {
        return email.get(0);
    }

    public String getToken(int order)
    {
        return token.get(order);
    }
}
