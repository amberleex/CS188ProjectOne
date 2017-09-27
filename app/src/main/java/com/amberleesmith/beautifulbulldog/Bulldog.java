package com.amberleesmith.beautifulbulldog;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by AmberLee on 9/24/2017.
 */
//fixed
public class Bulldog extends RealmObject {
    private String id;
    private String name;
    private String age;
    public RealmList<Vote> votes;
    private byte[] image;

    public byte[] getImage() { return image; }

    public void setImage(byte[] image) { this.image = image; }

    public String getId() { return id; }

    public RealmList<Vote> getVotes() {return votes;}

    public void setId(String id) { this.id = id; }

    public void setVotes(RealmList<Vote> votes) { this.votes = votes; }

    public void appendVote(Vote vote){ this.votes.add(vote); }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


}
