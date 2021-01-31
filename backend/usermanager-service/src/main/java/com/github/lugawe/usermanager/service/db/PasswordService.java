package com.github.lugawe.usermanager.service.db;

import com.github.lugawe.usermanager.db.dao.PasswordDAO;
import com.github.lugawe.usermanager.db.transaction.TransactionHandler;
import com.github.lugawe.usermanager.model.db.Password;
import com.github.lugawe.usermanager.model.db.QPassword;
import com.github.lugawe.usermanager.service.db.core.BaseService;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.UUID;

public class PasswordService extends BaseService<PasswordDAO> {

    private static final QPassword password = PasswordDAO.PASSWORD;

    @Inject
    public PasswordService(PasswordDAO dao, TransactionHandler handler) {
        super(dao, handler);
    }

    public Password getByHash(String hash) {
        return inTransaction(() -> baseDAO.query().where(password.hash.eq(hash)).fetchFirst());
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
