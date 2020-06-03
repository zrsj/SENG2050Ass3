package servlets;

import java.util.Date;

public class MilestoneBean
{
    private String title;
    private String description;
    private String status;
    private Date dateDue;

    public MilestoneBean(String title, String description, String status, Date date)
    {
        this.title = title;
        this.description = description;
        this.status = status;
        this.dateDue = date;
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

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return this.status;
    }

    public void setDateDue(Date date)
    {
        this.dateDue = date;
    }

    public Date getDateDue()
    {
        return this.dateDue;
    }
}