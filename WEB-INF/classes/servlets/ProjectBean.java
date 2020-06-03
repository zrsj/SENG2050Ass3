package servlets;

import java.util.Date;

import servlets.MilestoneBean;

public class ProjectBean
{
    private String title;
    private String description;
    private String status;
    private Date dateDue;
    private MilestoneBean[] milestones;

    public ProjectBean(String title, String description, String status, Date date, MilestoneBean[] milestones)
    {
        this.title = title;
        this.description = description;
        this.status = status;
        this.dateDue = date;
        this.milestones = milestones;
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

    public void setMilestones(MilestoneBean[] milestones)
    {
        this.milestones = milestones;
    }

    public MilestoneBean[] getMilestones()
    {
        return this.milestones;
    }

    /**
     * Add a Milestone to Milestones.
     * @param milestone The milestone to be added.
     */
    public void addMilestone(MilestoneBean milestone)
    {
        MilestoneBean[] newMilestones = new MilestoneBean[this.milestones.length + 1];
        newMilestones[newMilestones.length - 1] = milestone;
        copyArray(this.milestones, newMilestones);
        this.milestones = newMilestones;
    }

    /**
     * Remove a milesotne from the milestones aray.
     * @param title The title of the milestone to be removed.
     */
    public void removeMilestone(String title)
    {
        int position = findMilestoneByTitle(title);
        MilestoneBean[] newMilestones = new MilestoneBean[this.milestones.length - 1];
        this.milestones[position] = null;
        for(int i = 0; i < this.milestones.length; i++)
        {
            if(milestones[i] != null)
            {
                newMilestones[i] = milestones[i];
            }
            else
            {
                newMilestones[i] = milestones[i + 1];
                i++;
            }
        }
        this.milestones = newMilestones;
    }

    /**
     * Calculate the progess completed on the project.
     * @return Returns the percentage of the project completed as a double.
     */
    public double calculateProgess()
    {
        int count = 0;
        for(int i = 0; i < this.milestones.length; i++)
        {
            if(milestones[i].getStatus().equals("complete"))
            {
                count++;
            }
        }
        return ((count/milestones.length) * 100);
    }

    /**
     * Copies the source array into the destination array. Starts at the 0th elements of both arrays, will overwrite data held in the destination array.
     * @param source The array being copied from.
     * @param destination The array being copied to.
     * @return Returns True if the copy was successful, if not returns False.
     */
    private boolean copyArray(MilestoneBean[] source, MilestoneBean[] destination)
    {
        if(source.length > destination.length)
        {
            for(int i = 0; i < source.length; i++)
            {
                destination[i] = source[i];
            }
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Find a milestone by its title.
     * @param title The title of the milestone being searched for.
     * @return Returns, as an int, the position of the milestone.
     */
    public int findMilestoneByTitle(String title)
    {
        for(int i = 0; i < this.milestones.length; i++)
        {
            if(milestones[i].getTitle().equals(title))
            {
                return i;
            }
        }
        return -1;
    }

    /**
     * Sorts milestones by date, sooner dates are at the start of the array, later ones at the end.
     */
    public void sortMilestonesByDate()
    {
        MilestoneBean temp;
        MilestoneBean[] sortedMilestones = new MilestoneBean[this.milestones.length];
        copyArray(this.milestones, sortedMilestones);
        while(!sorted(sortedMilestones))
        {
            for(int i = 0; i < sortedMilestones.length; i++)
            {
                if(sortedMilestones[i].getDateDue().compareTo(sortedMilestones[i + 1].getDateDue()) > 0)
                {
                    temp = sortedMilestones[i + 1];
                    sortedMilestones[i + 1] = sortedMilestones[i];
                    sortedMilestones[i] = temp;
                }
            }
        }
        copyArray(sortedMilestones, this.milestones);
    }

    /**
     * Checks if an array is sorted by date.
     * @param array The array that is being checked.
     * @return Returns true if the array sorted, false if it isn't.
     */
    public boolean sorted(MilestoneBean[] array)
    {
        for(int i = 0; i < array.length; i++)
        {
            if(array[i].getDateDue().compareTo(array[i + 1].getDateDue()) > 0)
            {
                return false;
            }
        }
        return true;
    }
}