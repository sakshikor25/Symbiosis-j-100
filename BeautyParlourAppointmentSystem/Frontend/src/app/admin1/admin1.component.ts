import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-admin1',
  imports: [RouterModule,CommonModule,FormsModule],
  templateUrl: './admin1.component.html',
  styleUrl: './admin1.component.css'
})
export class Admin1Component {
constructor(private router: Router) {}

    goToservicehistory() {
    this.router.navigate(['/service-history']);
  }
}
