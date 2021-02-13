package com.github.lugawe.usermanager.service.db;

import com.github.lugawe.usermanager.db.dao.EntryDAO;
import com.github.lugawe.usermanager.db.transaction.TransactionHandler;
import com.github.lugawe.usermanager.model.db.Entry;
import com.github.lugawe.usermanager.model.db.QEntry;
import com.github.lugawe.usermanager.model.db.User;
import com.github.lugawe.usermanager.service.db.core.BaseService;

import javax.inject.Inject;
import java.util.Optional;

public class EntryService extends BaseService<EntryDAO> {

    private static final QEntry entry = EntryDAO.ENTRY;

    @Inject
    public EntryService(EntryDAO dao, TransactionHandler handler) {
        super(dao, handler);
    }

    public Optional<Entry> getGlobalEntry(String key) {
        if (key == null || key.trim().isEmpty()) {
            throw new IllegalArgumentException("param key is null or empty");
        }
        Entry result = inTransaction(() -> baseDAO.query()
                .where(entry.user.isNull().and(entry.key.eq(key)))
                .fetchFirst());
        return Optional.ofNullable(result);
    }

    public Optional<Entry> getEntry(User user, String key) {
        if (user == null) {
            throw new NullPointerException("param user is null");
        }
        if (key == null || key.trim().isEmpty()) {
            throw new IllegalArgumentException("param key is null or empty");
        }
        Entry result = inTransaction(() -> baseDAO.query()
                .where(entry.user.eq(user).and(entry.key.eq(key)))
                .fetchFirst());
        return Optional.ofNullable(result);
    }

}
