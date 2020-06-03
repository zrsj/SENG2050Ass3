package servlets;

import java.util.Date;

public class AppointmentBean 
{
    private String title;
    private String description;
    private Date date;
    private int time; 
    
    public AppointmentBean(String title, String description, Date date, int hour)
    {
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = hour;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return this.title;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public Date getDate()
    {
        return this.date;
    }

    public void setTime(int hour)
    {
        this.time = hour;
    }

    public int getTime()
    {
        return this.time;
    }
}