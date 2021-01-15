package com.github.lugawe.usermanager.service.validation;

import java.util.Objects;
import java.util.regex.Pattern;

public class RegexValidator implements StringValidator {

    private final String value;
    private final Pattern regexPattern;

    public RegexValidator(String value, String regex) {
        this.value = value;
        this.regexPattern = Pattern.compile(Objects.requireNonNull(regex));
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
        return value != null && regexPattern.matcher(value).matches();
    }

    public final String getValue() {
        return value;
    }

    public final Pattern getRegexPattern() {
        return regexPattern;
    }

}
