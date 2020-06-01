package servlets;

import java.util.Date;

public class UserBean 
{
    private String name;
    private AppointmentBean[] appointments;
    private ProjectBean[] projects;

    public UserBean(String name, AppointmentBean[] appointments, ProjectBean[] projects)
    {
        this.name = name;
        this.appointments = appointments;
        this.projects = projects;
        sortAppointmentsByDate();
        sortProjectsByDate();
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }
    
    public void setAppointments(AppointmentBean[] appointments)
    {
        this.appointments = appointments;
    }

    public AppointmentBean[] getAppointments()
    {
        return this.appointments;
    }

    public void setProjects(ProjectBean[] projects)
    {
        this.projects = projects;
    }

    public ProjectBean[] getProjects()
    {
        return this.projects;
    }

    /**
     * Sorts appointments by date, sooner dates are at the start of the array, later ones at the end.
     */
    public void sortAppointmentsByDate()
    {
        AppointmentBean temp;
        AppointmentBean[] sortedAppointments = new AppointmentBean[this.appointments.length];
        copyArray(this.appointments, sortedAppointments);
        while(!sorted(sortedAppointments))
        {
            for(int i = 0; i < sortedAppointments.length; i++)
            {
                if(sortedAppointments[i].getDate().compareTo(sortedAppointments[i + 1].getDate()) > 0)
                {
                    temp = sortedAppointments[i + 1];
                    sortedAppointments[i + 1] = sortedAppointments[i];
                    sortedAppointments[i] = temp;
                }
            }
        }
        this.appointments = sortedAppointments;
    }

    /**
     * Sorts projects by date, sooner dates are at the start of the array, later ones at the end.
     */
    public void sortProjectsByDate()
    {
        ProjectBean temp;
        ProjectBean[] sortedProjects = new ProjectBean[this.projects.length];
        copyArray(this.projects, sortedProjects);
        while(!sorted(sortedProjects))
        {
            for(int i = 0; i < sortedProjects.length; i++)
            {
                if(sortedProjects[i].getDateDue().compareTo(sortedProjects[i + 1].getDateDue()) > 0)
                {
                    temp = sortedProjects[i + 1];
                    sortedProjects[i + 1] = sortedProjects[i];
                    sortedProjects[i] = temp;
                }
            }
        }
        this.projects = sortedProjects;
    }
    
    /**
     * Checks if an array is sorted by date.
     * @param array The array that is being checked.
     * @return Returns true if the array sorted, false if it isn't.
     */
    public boolean sorted(AppointmentBean[] array)
    {
        for(int i = 0; i < array.length; i++)
        {
            if(array[i].getDate().compareTo(array[i + 1].getDate()) > 0)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if an array is sorted by date.
     * @param array The array that is being checked.
     * @return Returns true if the array sorted, false if it isn't.
     */
    public boolean sorted(ProjectBean[] array)
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

    /**
     * Copies the source array into the destination array. Starts at the 0th elements of both arrays, will overwrite data held in the destination array.
     * @param source The array being copied from.
     * @param destination The array being copied to.
     * @return Returns True if the copy was successful, if not returns False.
     */
    private boolean copyArray(AppointmentBean[] source, AppointmentBean[] destination)
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
     * Copies the source array into the destination array. Starts at the 0th elements of both arrays, will overwrite data held in the destination array.
     * @param source The array being copied from.
     * @param destination The array being copied to.
     * @return Returns True if the copy was successful, if not returns False.
     */
    private boolean copyArray(ProjectBean[] source, ProjectBean[] destination)
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
}