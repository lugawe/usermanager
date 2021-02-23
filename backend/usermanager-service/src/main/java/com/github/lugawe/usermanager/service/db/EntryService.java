package com.github.lugawe.usermanager.service.db;

import com.github.lugawe.usermanager.db.dao.EntryDAO;
import com.github.lugawe.usermanager.model.db.Entry;
import com.github.lugawe.usermanager.model.db.QEntry;
import com.github.lugawe.usermanager.model.db.User;
import com.github.lugawe.usermanager.service.db.core.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Optional;
import java.util.UUID;

public class EntryService extends BaseService<EntryDAO> {

    private static final Logger log = LoggerFactory.getLogger(EntryService.class);

    private static final QEntry entry = EntryDAO.ENTRY;

    @Inject
    public EntryService(EntryDAO dao) {
        super(dao);
    }

    protected Entry create(String key, String value, User user) {
        if (key == null || key.trim().isEmpty()) {
            throw new IllegalArgumentException("param key is null or empty");
        }
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("param value is null or empty");
        }
        log.info("#create - key: {}, value: {}, user: {}", key, value, user != null ? user.getName() : "none");
        Entry entry = new Entry();
        entry.setKey(key);
        entry.setValue(value);
        entry.setUser(user);
        return entry;
    }

    public UUID createGlobalEntry(String key, String value) {
        return inTransaction(() -> baseDAO.insert(create(key, value, null)));
    }

    public UUID createEntry(String key, String value, User user) {
        if (user == null) {
            throw new NullPointerException("param user is null");
        }
        return inTransaction(() -> baseDAO.insert(create(key, value, user)));
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
