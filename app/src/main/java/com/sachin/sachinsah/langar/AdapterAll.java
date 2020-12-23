package com.sachin.sachinsah.langar;

public class AdapterAll {


        public String hostelname;
        public String messname;
        public String shift;
        public String date;
        public String category;
        public String menu;
        public  String personname;
        public String feedbackmsg;
        public  String rate;

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public  String phoneno;

    public String getPersonname() {
        return personname;
    }

    public void setPersonname(String personname) {
        this.personname = personname;
    }

    public String getFeedbackmsg() {
        return feedbackmsg;
    }

    public void setFeedbackmsg(String feedbackmsg) {
        this.feedbackmsg = feedbackmsg;
    }



    private static AdapterAll instance;

        public static synchronized AdapterAll getInstance(){
            if(instance==null){
                instance=new AdapterAll();
            }
            return instance;
        }

        public static void setInstance(AdapterAll instance) {
            AdapterAll.instance = instance;
        }



        public String getHostelname() {
            return hostelname;
        }

        public void setHostelname(String hostelname) {
            this.hostelname = hostelname;
        }

        public String getMessname() {
            return messname;
        }

        public void setMessname(String messname) {
            this.messname = messname;
        }

        public String getShift() {
            return shift;
        }

        public void setShift(String shift) {
            this.shift = shift;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getMenu() {
            return menu;
        }

        public void setMenu(String menu) {
            this.menu = menu;
        }


        public AdapterAll(){}


        public AdapterAll(String hostelname,String messname,String menu) {

            this.hostelname=hostelname;
            this.messname=messname;
            this.menu=menu;
        }


        public AdapterAll(String date,String shift) {


            this.date=date;
            this.shift=shift;
        }

        public AdapterAll(String name,String feedbackmsg,String rate,String number) {


        this.personname=name;
        this.feedbackmsg=feedbackmsg;
        this.rate=rate;
        this.phoneno=number;
    }




}


