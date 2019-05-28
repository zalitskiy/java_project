package ru.stqa.pft.mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Accounts extends ForwardingSet<AccountData> {

    private Set<AccountData> delegate;

    public Accounts(Accounts accounts) {
        this.delegate = new HashSet<AccountData>(accounts.delegate);
    }

    public Accounts() {
        this.delegate = new HashSet<AccountData>();
    }

    public Accounts(Collection<AccountData> accounts) {
        this.delegate = new HashSet<>(accounts);
    }

    @Override
    protected Set<AccountData> delegate() {
        return delegate;
    }

    public Accounts withAdded(AccountData account) {
        Accounts accounts = new Accounts(this);
        accounts.add(account);
        return accounts;
    }

    public Accounts withOut(AccountData account) {
        Accounts accounts = new Accounts(this);
        accounts.remove(account);
        return accounts;
    }

    public AccountData findAdmin(Accounts accounts) {
        AccountData admin = new AccountData();
        for (AccountData account : accounts) {
            if (account.getUsername().equals("administrator")) {
                admin.withUserName(account.getUsername()).withEmail(account.getEmail()).withId(account.getId()).withPassword(account.getPassword());
                break;
            }
        }
        return admin;
    }
}
