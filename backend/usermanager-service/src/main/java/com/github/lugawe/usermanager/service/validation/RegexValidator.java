package com.github.lugawe.usermanager.service.validation;

import java.util.Objects;
import java.util.regex.Pattern;

public class RegexValidator implements StringValidator {

    private final String value;
    private final String regex;
    private final Pattern regexPattern;

    public RegexValidator(String value, String regex) {
        this.value = Objects.requireNonNull(value);
        this.regex = Objects.requireNonNull(regex);
        this.regexPattern = Pattern.compile(this.regex);
    }

    @Override
    public String get() throws ValidationException {
        if (isValid()) {
            return value;
        }
        throw new ValidationException();
    }

    @Override
    public boolean isValid() {
        return regexPattern.matcher(value).matches();
    }

    public final String getValue() {
        return value;
    }

    public final String getRegex() {
        return regex;
    }

    public final Pattern getRegexPattern() {
        return regexPattern;
    }

}
