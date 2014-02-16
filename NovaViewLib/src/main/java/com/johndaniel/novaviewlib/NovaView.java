package com.johndaniel.novaviewlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 * Created by JohnDaniel on 2014-02-12.
 * This
 */
public class NovaView extends WebView {
    String schoolId;
    int weekNo;
    Context context;
    String day = "";
    String schoolClass = "";
    int mode = 2;
    String period = ""; //If week left blank it will load the whole season.
    public static int viewWidth = 0;
    public static int viewHeight = 0;


    final String URL_PART_1 = "http://www.novasoftware.se/ImgGen/schedulegenerator.aspx?format=png&";
                                //After which you insert schooldid

    final String URL_PART_2 = "sv-se&type=-1&";
                                //After which you insert class id, period, week, mode

    final String URL_PART_3 = "printer=0&colors=32&head=0&clock=0&foot=0&";
                                //After which you insert day, width and height

    final String URL_PART_LAST = "maxwidth=1420&maxheight=682";


    final String SCHOOLID = "schoolid=";
    final String ID = "id=";
    final String PERIOD = "period=";
    final String WEEK = "week=";
    final String MODE = "mode=";
    final String DAY = "day=";
    final String WIDTH = "width=";
    final String HEIGHT = "height=";



    public NovaView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.NovaView, 0 , 0);
        //Handle id's and stuff
        try {
            String schoolId = a.getString(R.styleable.NovaView_schoolId);
        }
        finally {
            a.recycle();
        }
        //This is the url of n2c schedule week 7 in swedish
        /*http://www.novasoftware.se/ImgGen/schedulegenerator.aspx?format=png&
        schoolid=52550/             //School. Thorildsplan = 80710, Blackeberg = 52550
        sv-se&
        type=-1&                     //Nada...
        id=n2c&                    //Class. N2c, N3c etc.
        period=&
        week=7&                     //Week number.
        mode=0&                     //0 = show nothing under the days, 1 = show week, 2 = show date,
        printer=0&                  //Either 0 or 1, and 1 is fucked up... Guessing 1 means true, and shows the names of the mentors.
        colors=32&                  //Let it be set to 32. Couldn't change it.
        head=0&                     //Didn't do a shit...
        clock=0&                    //Doesn't seem to work...
        foot=0&                     //Nada, just like head.
        day=0&                      //0 = the full week. 1 = monday. 2 = tuesday, 3 = mon and tue, 4 = wed, 5 = mon and wed, 6 = tue and wed, 7 = mon tue and wed, 8 = thursday, 9 = mon and thur, 10 = tue and thur, 11 = mon tue and thur, 12 = wed and thur, 13 = mon wed and thur, 14 = tue wed and thur, 15 = mon tue wed thur, 16 = FRIDAY!!!, 17 = mon and fri, 18 = tue and fri, 19 = mon tue and fri (shit is ridiculous), 20 = wed and fri, 21 = mon wed fri

                                    /*
                                    * 0 = full week
                                    * 1 = monday
                                    * 2 = tuesday
                                    * 4 = wed
                                    * 8 = thursday
                                    * 16 = friday
                                    * (you see the pattern? Let's create )

        width=1420&                 //Doesn't do anything
        height=682&                 //Nada...
        maxwidth=1420&              //Probably shouldn't change this
        maxheight=682*/

        schoolId = a.getString(R.styleable.NovaView_schoolId);
        setWebViewClient(new WebViewClient());
        setWebChromeClient(new WebChromeClient());
        getSettings().setJavaScriptEnabled(true);
        getSettings().setLoadWithOverviewMode(true);
        getSettings().setUseWideViewPort(true);

        viewHeight = getHeight();
        viewWidth = getWidth();
    }

    public void setSchoolId(String id){
        schoolId = id;
    }
    public void setWeekNo(int week){
        weekNo = week;
    }
    public void setSchoolClass(String classId){
        schoolClass = classId;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
    public void setPeriod(String period){
        this.period = period;
    }

    /**
     * Will set the day of the week to show.
     * @param day is the day to show. 0 is the whole week, 1 is monday and 2 is tuesday.
     *            Set it to -1 if you want to see the whole period.
     */
    public void setDay(int day){
        if (day > -1)
        switch(day){
            case 0:
                this.day = Integer.toString(0);
                break;
            case 1:
                this.day = Integer.toString(1);
                break;
            case 2:
                this.day = Integer.toString(2);
                break;
            case 3:
                this.day = Integer.toString(4); //THIS IS NOT WRONG! CHECK THE LONG COMMENT ABOVE
                break;
            case 4:
                this.day = Integer.toString(8);
                break;
            case 5:
                this.day = Integer.toString(16);
                break;
        }
    }

    public void loadSchoolUrl(){
        loadUrl(generateSchoolUrl());
    }

    private String generateSchoolUrl(){
        String url = URL_PART_1 + SCHOOLID + schoolId + "&" +
                URL_PART_2 + ID + schoolClass + "&" + PERIOD + period + "&" + WEEK + weekNo + "&" + MODE + mode + "&" +
                URL_PART_3 + DAY + day + "&" + WIDTH + 480 + "&" + HEIGHT + 720 + "&" +
                URL_PART_LAST;

        return url;
    }

    /*@Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if(getWidth() != viewWidth || getHeight() != viewHeight){
            viewWidth = getWidth();
            viewHeight = getHeight();
            loadSchoolUrl();
        }
    }*/
}
