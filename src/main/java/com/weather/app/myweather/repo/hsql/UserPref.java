package com.weather.app.myweather.repo.hsql;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_PREF")
public class UserPref implements Serializable{

	private static final long serialVersionUID = 8069158605172041216L;
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;
    private String city;
    
    public UserPref() {
	}

	public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
