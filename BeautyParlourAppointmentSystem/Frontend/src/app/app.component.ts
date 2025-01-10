import { Component } from '@angular/core';
import { RouterModule, RouterOutlet} from '@angular/router';
import { FrontpageComponent } from './frontpage/frontpage.component';
import { Admin1Component } from './admin1/admin1.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ServiceHistoryComponent } from './service-history/service-history.component';
import {  HttpClientModule ,withFetch} from '@angular/common/http';
import { CourseHistoryComponent } from './course-history/course-history.component';

@Component({
  selector: 'app-root',
  imports: [CommonModule,FormsModule,RouterOutlet,FrontpageComponent,Admin1Component,ServiceHistoryComponent,CourseHistoryComponent,RouterModule,HttpClientModule ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'angularapp';
}
