package com.github.lugawe.usermanager.server.core.mapper;

import com.github.lugawe.usermanager.service.validation.ValidationException;
import com.github.lugawe.usermanager.service.validation.Validator;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

    @Override
    public Response toResponse(ValidationException e) {
        return Response.status(400).entity(buildEntity(e)).build();
    }

    protected Map<String, String> buildEntity(ValidationException e) {
        Map<String, String> result = new HashMap<>();
        if (e != null) {
            result.put("message", e.getMessage());
            Validator<?> validator = e.getValidator();
            if (validator != null) {
                result.put("type", validator.type());
                result.put("pattern", validator.pattern());
                result.put("target", validator.target());
            }
        }
        return result;
    }

}
