package com.github.lugawe.usermanager.service.db;

import com.github.lugawe.usermanager.db.dao.PasswordDAO;
import com.github.lugawe.usermanager.model.db.Password;
import com.github.lugawe.usermanager.model.db.QPassword;
import com.github.lugawe.usermanager.service.db.core.BaseService;
import org.mindrot.jbcrypt.BCrypt;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.UUID;

public class PasswordService extends BaseService<PasswordDAO> {

    private static final QPassword password = PasswordDAO.PASSWORD;

    @Inject
    public PasswordService(PasswordDAO dao) {
        super(dao);
    }

    protected String hash(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(14));
    }

    public Password create(String plainPassword) {

        LocalDateTime now = LocalDateTime.now();

        Password password = new Password();
        password.setHash(hash(plainPassword));
        password.setCreatedAt(now);
        password.setLastAccess(now);

        return inTransaction(() -> baseDAO.get(baseDAO.insert(password)));
    }

    public void updateLastAccess(UUID id) {
        inTransaction(() -> {
            baseDAO.update()
                    .where(password.id.eq(id))
                    .set(password.lastAccess, LocalDateTime.now())
                    .execute();
        });
    }

}
