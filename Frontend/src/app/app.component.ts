import { Component } from '@angular/core';
import { AuthenticationService } from 'src/app/modules/authentication/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'movie-cruiser-capsule-frontend';
 constructor(private auth:AuthenticationService,private routes:Router){}

 Logout(){
   this.auth.deleteToken();
   this.routes.navigate(['/login'])
 }

}
