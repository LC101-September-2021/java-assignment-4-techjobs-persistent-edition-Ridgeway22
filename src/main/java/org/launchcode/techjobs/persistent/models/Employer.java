package org.launchcode.techjobs.persistent.models;

import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


//For the purposes of this application, an employer can only have one location.
@Entity
public class Employer extends AbstractEntity {
    @Valid
    @NotNull(message="please do not leave empty")
    @Size(min=3, message="Name must be at least 3 characters long")
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
