package com.github.lugawe.usermanager.service.test;

import com.github.javafaker.Faker;
import com.github.lugawe.usermanager.db.dao.UserDAO;
import com.github.lugawe.usermanager.model.db.auth.Password;
import com.github.lugawe.usermanager.model.db.auth.User;
import com.github.lugawe.usermanager.service.db.UserService;
import com.google.inject.Injector;

import java.time.LocalDateTime;

public final class DataGenerator {

    private DataGenerator() {
    }

    private static final Injector injector = BaseTestHelper.defaultInjector();

    private static final Faker faker = new Faker();

    public static Password createTestPassword() {
        Password password = new Password();
        password.setHash(faker.lorem().sentence(4));
        password.setCreatedAt(LocalDateTime.now());
        password.setLastAccess(LocalDateTime.now());
        return password;
    }

    public static User createTestUser() {
        User user = new User();
        user.setName(faker.name().username());
        user.setPassword(createTestPassword());
        user.setCreatedAt(LocalDateTime.now());
        return user;
    }

    public static void fillInTestUser(int count) {
        UserService service = injector.getInstance(UserService.class);
        service.inTransaction(() -> {
            UserDAO dao = service.getBaseDAO();
            for (int i = 0; i < count; i++) {
                dao.insert(createTestUser());
            }
        });
    }

}
