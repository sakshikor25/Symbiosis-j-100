package com.trg.springboot.Model1;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name="client2")
public class Client {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private int clientId;  
    private String clientName;
    private long clientMobileno;
    private String clientPassword;
    private String clientRole;
    private String clientEmail;

    @ManyToMany
    @JoinTable(
        name = "client_course",
        joinColumns = @JoinColumn(name = "client_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> course; 

    @OneToMany  
    private List<CourseBooking> booking;
    
    @ManyToMany
    @JoinTable(
        name = "client_service",
        joinColumns = @JoinColumn(name = "client_id"),
        inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private List<Service> service;
    
      @OneToMany(mappedBy = "client")
      private List<Appointment> appointment;



	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public long getClientMobileno() {
		return clientMobileno;
	}

	public void setClientMobileno(long clientMobileno) {
		this.clientMobileno = clientMobileno;
	}

	public String getClientPassword() {
		return clientPassword;
	}

	public void setClientPassword(String clientPassword) {
		this.clientPassword = clientPassword;
	}

	public String getClientRole() {
		return clientRole;
	}

	public void setClientRole(String clientRole) {
		this.clientRole = clientRole;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public List<Course> getCourse() {
		return course;
	}

	public void setCourses(List<Course> course) {
		this.course = course;
	}

	public List<CourseBooking> getBooking() {
		return booking;
	}

	public void setBooking(List<CourseBooking> booking) {
		this.booking = booking;
	}

	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", clientName=" + clientName + ", clientMobileno=" + clientMobileno
				+ ", clientPassword=" + clientPassword + ", clientRole=" + clientRole + ", clientEmail=" + clientEmail
				+ ", courses=" + course + ", booking=" + booking + "]";
	}

}
