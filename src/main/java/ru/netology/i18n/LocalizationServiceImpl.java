package ru.netology.i18n;

import ru.netology.entity.Country;

public class LocalizationServiceImpl implements LocalizationService {

    public String locale(Country country) {
        switch (country) {
            case RUSSIA:
                return "Добро пожаловать";
            case BRAZIL:
                return "Bienvenido";
            case GERMANY:
                return "Herzlich willkommen";
            case FINLAND:
                return "Tervetuloa";
            case FRANCE:
                return "Bienvenue";
            case ITALY:
                return "Benvenuti";
            default:
                return "Welcome";
        }
    }
}
