package in.strikes.service;

import in.strikes.model.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class userservice {

    private Map<Integer, user> userdb;

    public userservice() {
        userdb = new HashMap<>();
    }

    public user createuser(user userreq) {
        userdb.put(userreq.getId(), userreq);
        return userreq;
    }

    public List<user> getallusers() {

        List<user> userlist = new ArrayList<>();

        for (user u : userdb.values()) {
            userlist.add(u);
        }

        return userlist;
    }

    public user getuser(int id) {
        return userdb.get(id);
    }
}