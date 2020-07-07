package com.example.covid19helper;
//model
public class Guidelines {
    private String title;
    private String description;
    private boolean expandable;

    public Guidelines(String title, String description){
        this.title = title;
        this.description = description;
        this.expandable = false;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Guidelines{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }
}
