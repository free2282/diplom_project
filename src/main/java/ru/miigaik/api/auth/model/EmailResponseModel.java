package ru.miigaik.api.auth.model;

public class EmailResponseModel
{
    private String detail;


    public EmailResponseModel(String detail)
    {
        this.detail = detail;
    }

    public EmailResponseModel()
    {

    }

    public String getDetail()
    {
        return detail;
    }

    public void setDetail(String detail)
    {
        this.detail = detail;
    }
}
