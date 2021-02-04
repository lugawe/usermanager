package com.github.lugawe.usermanager.service.db;

import com.github.lugawe.usermanager.db.dao.EntryDAO;
import com.github.lugawe.usermanager.db.transaction.TransactionHandler;
import com.github.lugawe.usermanager.model.db.QEntry;
import com.github.lugawe.usermanager.service.db.core.BaseService;

import javax.inject.Inject;

public class EntryService extends BaseService<EntryDAO> {

    private static final QEntry entry = EntryDAO.ENTRY;

    @Inject
    public EntryService(EntryDAO dao, TransactionHandler handler) {
        super(dao, handler);
    }

}
