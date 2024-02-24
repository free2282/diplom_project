package ru.miigaik.action;

import com.github.javafaker.Faker;
import com.github.javafaker.service.RandomService;
import ru.miigaik.api.model.auth.EmailRequestModel;
import com.github.javafaker.service.FakeValuesService;
import ru.miigaik.pages.ConsumerModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Generator
{
    private static String email = "@yandex.ru";


    public static EmailRequestModel setEmailToAuthRequest1Var()
    {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyyHH-mm-ss");
        String formattedDate = dateFormat.format(currentDate);

        email = formattedDate + email;
        return new EmailRequestModel(email);
    }

    public static EmailRequestModel setEmailToAuthRequest2Var()
    {
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        String email = fakeValuesService.bothify("????##@gmail.com");
        Matcher emailMatcher = Pattern.compile("\\w{4}\\d{2}@gmail.com").matcher(email);

        return new EmailRequestModel(email);
    }

    public static ConsumerModel setConsumersData()
    {

        Faker faker = new Faker(new Locale("ru"));
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());


        String name = faker.name().firstName();
        String phoneNumber = fakeValuesService.bothify("(929) ###-##-##");
        String zipCode = faker.address().zipCode();
        String state = faker.address().state();
        String city = faker.address().city();
        String street = faker.address().streetName();
        String house = faker.address().buildingNumber();
        return new ConsumerModel(name, phoneNumber, zipCode, state, city, street, house);
    }
}
