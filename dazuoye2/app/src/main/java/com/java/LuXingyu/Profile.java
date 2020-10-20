package com.java.LuXingyu;

import com.json.JSONObject;

import java.util.Arrays;

public class Profile {
    private String address;
    private String affiliation;
    private String affiliation_zh;
    private String bio;
    private String edu;
    private String email;
    private String email_cr;
    private EmailsU[] emails_u;
    private String homepage;
    private String note;
    private String position;
    private String work;

    public Profile(JSONObject profile) {
        affiliation = profile.getString("affiliation");
        bio = profile.getString("bio");
        try {
            position = profile.getString("position");
        }catch (Exception e){
        }
        try {
            edu = profile.getString("edu");
        }catch (Exception e){
        }
        try {
            work = profile.getString("work");
        }catch (Exception e){
        }
    }

    public void setAddress(String address) { this.address = address; }
    public void setAffiliation(String affiliation) { this.affiliation = affiliation; }
    public void setAffiliation_zh(String affiliation_zh) { this.affiliation_zh = affiliation_zh; }
    public void setBio(String bio) { this.bio = bio; }
    public void setEdu(String edu) { this.edu = edu; }
    public void setEmail(String email) { this.email = email; }
    public void setEmail_cr(String email_cr) { this.email_cr = email_cr; }
    public void setEmails_u(EmailsU[] emails_u) { this.emails_u = emails_u; }
    public void setHomepage(String homepage) { this.homepage = homepage; }
    public void setNote(String note) { this.note = note;}
    public void setPosition(String position) { this.position = position; }
    public void setWork(String work) { this.work = work; }
    public String getAddress() { return address; }
    public String getAffiliation() { return affiliation; }
    public String getAffiliation_zh() { return affiliation_zh; }
    public String getBio() { return bio;}
    public String getEdu() { return edu; }
    public EmailsU[] getEmails_u() { return emails_u; }
    public String getEmail_cr() { return email_cr; }
    public String getHomepage() { return homepage; }
    public String getNote() { return note; }
    public String getPosition() { return position; }
    public String getWork() { return work; }

    @Override
    public String toString() {
        return "Profile{" +
                "address='" + address + '\'' +
                ", affiliation='" + affiliation + '\'' +
                ", affiliation_zh='" + affiliation_zh + '\'' +
                ", bio='" + bio + '\'' +
                ", edu='" + edu + '\'' +
                ", email='" + email + '\'' +
                ", email_cr='" + email_cr + '\'' +
                ", emails_u=" + Arrays.toString(emails_u) +
                ", homepage='" + homepage + '\'' +
                ", note='" + note + '\'' +
                ", position='" + position + '\'' +
                ", work='" + work + '\'' +
                '}';
    }

}
