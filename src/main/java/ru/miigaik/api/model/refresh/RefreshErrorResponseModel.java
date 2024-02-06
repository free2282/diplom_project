package ru.miigaik.api.model.refresh;

import java.util.List;

public class RefreshErrorResponseModel
{
    private String detail;
    private String code;
    private List<String> refresh;

    public String getRefresh()
    {
        return refresh.get(0);
    }

    public String getDetail()
    {
        return detail;
    }

    public String getCode()
    {
        return code;
    }
}
