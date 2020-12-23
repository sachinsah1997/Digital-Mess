package com.sachin.sachinsah.langar;

public class NavDrawerItem {

    private boolean showNotify;
    private String title;

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    private String subtitle;


    public NavDrawerItem() {

    }

    public NavDrawerItem(boolean showNotify, String title) {
        this.showNotify = showNotify;
        this.title = title;
    }

    public boolean isShowNotify() {
        return showNotify;
    }

    public void setShowNotify(boolean showNotify) {
        this.showNotify = showNotify;
    }

        public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



}
