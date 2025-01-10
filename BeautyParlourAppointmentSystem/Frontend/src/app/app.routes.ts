import { Routes, RouterModule } from '@angular/router';
import { Admin1Component } from './admin1/admin1.component';
import { FrontpageComponent } from './frontpage/frontpage.component';
import { ServiceHistoryComponent } from './service-history/service-history.component';
import { CourseHistoryComponent } from './course-history/course-history.component';
//import { ServiceHistoryComponent } from './service-history/service-history.component';
//import { CourseHistoryComponent } from './course-history/course-history.component';

export const routes: Routes = [

    { path: '', component: FrontpageComponent },
    { path: 'admin1', component: Admin1Component },
    { path: 'servicehistory', component: ServiceHistoryComponent },
    { path: 'coursehistory', component: CourseHistoryComponent }
];
