package poly.com.entity;

import java.util.Date;

public class Newsletter {
    private String email;
    private boolean enabled;
    private Date subscribedDate;

    // Default constructor
    public Newsletter() {
    }

    // Constructor with all fields
    public Newsletter(String email, boolean enabled, Date subscribedDate) {
        this.email = email;
        this.enabled = enabled;
        this.subscribedDate = subscribedDate;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getSubscribedDate() {
        return subscribedDate;
    }

    public void setSubscribedDate(Date subscribedDate) {
        this.subscribedDate = subscribedDate;
    }
}