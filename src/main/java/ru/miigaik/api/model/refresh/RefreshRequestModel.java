package ru.miigaik.api.model.refresh;

public class RefreshRequestModel
{
    private String refresh;

    public RefreshRequestModel(String refresh)
    {
        this.refresh = refresh;
    }

    public String getRefresh()
    {
        return refresh;
    }

    public void setRefresh(String refresh)
    {
        this.refresh = refresh;
    }
}
