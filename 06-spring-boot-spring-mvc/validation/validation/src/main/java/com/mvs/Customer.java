package com.mvs;

import com.mvs.validation.CouresCode;
import jakarta.validation.constraints.*;

public class Customer {
    private String firstName;

    @NotNull(message = "Is required")
    @Size(min = 1, message = "Is required")
    private String lastName = "";

    @Min(value = 0, message = "must be greater than or equal to zero")
    @Max(value = 10, message = "must be greater than or equal to 10")
    private int freePasses;

    private String postalCode;

    //نهاية المقطع دقيقة 4 مقطع 226
    @CouresCode()
    private String couresCode;

    public String getCouresCode() {
        return couresCode;
    }

    public void setCouresCode(String couresCode) {
        this.couresCode = couresCode;
    }

    @Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "only 5 digit")
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public int getFreePasses() {
        return freePasses;
    }

    public void setFreePasses(int freePasses) {
        this.freePasses = freePasses;
    }

    public Customer() {
    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
