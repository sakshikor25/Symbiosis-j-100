import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-frontpage',
  imports: [RouterModule,FormsModule,CommonModule],
  templateUrl: './frontpage.component.html',
  styleUrl: './frontpage.component.css'
})
export class FrontpageComponent {
  
   constructor(private router: Router) {}

   goToadmin1() {
     this.router.navigate(['/admin1']);
   }
 
  
}
