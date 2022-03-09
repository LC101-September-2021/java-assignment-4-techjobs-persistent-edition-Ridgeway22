package org.launchcode.techjobs.persistent.models;

import javax.persistence.Entity;
import javax.validation.Valid;


//For the purposes of this application, an employer can only have one location.
@Entity
public class Employer extends AbstractEntity {
    @Valid
    private String location;

    public Employer(String location) {
        this.location = location;
    }

    public Employer(){

    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
