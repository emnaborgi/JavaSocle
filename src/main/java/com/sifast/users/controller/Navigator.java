package com.sifast.users.controller;

 

public class Navigator {
    private String pageToNavigate = "";
     
    public String navigateTo(){
        if("ToSecure".equalsIgnoreCase(pageToNavigate)){
            return "Secured";
        } else if("ToUnSecure".equalsIgnoreCase(pageToNavigate)){
            return "UnSecured";
        } else {
            //This will never happen but we will use this to extend this application
            return "none";
        }
    }
     
    public String getPageToNavigate() {
        return pageToNavigate;
    }
    public void setPageToNavigate(String option) {
        this.pageToNavigate = option;
    }
}