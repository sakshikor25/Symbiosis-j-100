import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { AppointmentserviceService } from '../appointmentservice.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-service-history',
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './service-history.component.html',
  styleUrls: ['./service-history.component.css']
})
export class ServiceHistoryComponent implements OnInit {
  appointments: any
  constructor(private router: Router, private appointmentService: AppointmentserviceService) { }
  navigateCourseHistory(){
    this.router.navigateByUrl('coursehistory')
  }
  ngOnInit(): void {
    
    this.appointmentService.getAppointmentDetails().subscribe(data => {
      console.log(data);
      this.appointments = data
    })
    console.log("appointments", this.appointments.body);
  }
}
