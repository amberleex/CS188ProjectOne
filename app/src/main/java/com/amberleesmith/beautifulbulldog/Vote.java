package com.amberleesmith.beautifulbulldog;

import com.amberleesmith.beautifulbulldog.User;
import io.realm.RealmObject;

/**
 * Created by AmberLee on 9/24/2017.
 */

public class Vote extends RealmObject {
    private User owner;
    private int rating;

    public User getOwner() { return owner; }

    public void setOwner(User owner) { this.owner = owner; }

    public int getRating() { return rating; }

    public void setRating(int rating) { this.rating = rating; }
}
