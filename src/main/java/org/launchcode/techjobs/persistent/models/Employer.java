package org.launchcode.techjobs.persistent.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


//For the purposes of this application, an employer can only have one location.
@Entity
public class Employer extends AbstractEntity {

    @OneToMany
    @JoinColumn(name = "employer_id")
    private List<Job> jobs = new ArrayList<>();

    @Valid
    @NotNull(message="please do not leave empty")
    @Size(min=3, message="Name must be at least 3 characters long")
    private String location;

    public List<Job> getJobs() {
        return jobs;
    }

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
