package com.github.lugawe.usermanager.service.validation;

import java.util.regex.Pattern;

public class RegexValidator implements Validator<String> {

    public static final String MATCH_ALL_REGEX = "(.*?)";

    private final String target;
    private final String value;
    private final Pattern regexPattern;

    public RegexValidator(String target, String value, String regex) {
        this.target = target;
        this.value = value;
        this.regexPattern = Pattern.compile(regex);
    }

    public RegexValidator(String value, String regex) {
        this(null, value, regex);
    }

    @Override
    public String get() throws ValidationException {
        if (!isValid()) {
            throw new ValidationException("validation failed", this);
        }
        return value;
    }

    @Override
    public boolean isValid() {
        return value != null && regexPattern.matcher(value).matches();
    }

    @Override
    public String type() {
        return "regex";
    }

    @Override
    public String pattern() {
        return regexPattern.pattern();
    }

    @Override
    public String target() {
        return target;
    }

    public final String getTarget() {
        return target;
    }

    public final String getValue() {
        return value;
    }

    public final Pattern getRegexPattern() {
        return regexPattern;
    }

}
