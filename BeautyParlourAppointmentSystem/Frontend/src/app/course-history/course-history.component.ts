import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { BookingserviceService } from '../bookingservice.service';

@Component({
  selector: 'app-course-history',
  imports: [RouterModule,CommonModule,FormsModule],
  templateUrl: './course-history.component.html',
  styleUrl: './course-history.component.css'
})
export class CourseHistoryComponent implements OnInit {
  bookings: any

  constructor(private router: Router , private bookingService: BookingserviceService) {}
  navigateServiceHistory(){
    this.router.navigateByUrl('servicehistory')
  }
 
  ngOnInit(): void {
    this.bookingService.getbookingDetails().subscribe(data => {
      console.log(data);
      this.bookings = data
    })

    console.log("bookings", this.bookings.body);

  }

 
    
}
