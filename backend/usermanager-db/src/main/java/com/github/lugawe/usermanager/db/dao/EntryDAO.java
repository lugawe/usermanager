package com.github.lugawe.usermanager.db.dao;

import com.github.lugawe.usermanager.db.dao.core.BaseDAO;
import com.github.lugawe.usermanager.db.transaction.TransactionHandler;
import com.github.lugawe.usermanager.model.db.Entry;
import com.github.lugawe.usermanager.model.db.QEntry;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import java.util.Optional;
import java.util.UUID;

public class EntryDAO extends BaseDAO<Entry> {

    public static final QEntry ENTRY = new QEntry(Entry.TABLE_NAME);

    @Inject
    public EntryDAO(SessionFactory sessionFactory,
                    TransactionHandler transactionHandler) {

        super(sessionFactory, transactionHandler, Entry.class, ENTRY);
    }

    @Override
    public Optional<Entry> tryGet(UUID id) {
        if (id == null) {
            throw new NullPointerException("param id is null");
        }
        Entry result = query().where(ENTRY.id.eq(id)).fetchOne();
        return Optional.ofNullable(result);
    }

}
