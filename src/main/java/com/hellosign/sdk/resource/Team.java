package com.hellosign.sdk.resource;

import com.hellosign.sdk.HelloSignException;
import java.util.List;
import org.json.JSONObject;

public class Team extends AbstractResource {

    public static final String TEAM_KEY = "team";
    public static final String TEAM_NAME = "name";
    public static final String TEAM_ACCOUNTS = "accounts";
    public static final String TEAM_INVITEES = "invited_accounts";

    public Team() {
        super();
    }

    public Team(JSONObject json) throws HelloSignException {
        super(json, TEAM_KEY);
    }

    public String getName() {
        return getString(TEAM_NAME);
    }

    public List<Account> getAccounts() {
        return getList(Account.class, TEAM_ACCOUNTS);
    }

    public List<Account> getInvitedAccounts() {
        return getList(Account.class, TEAM_INVITEES);
    }
}
