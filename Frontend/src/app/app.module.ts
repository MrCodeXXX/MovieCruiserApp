import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MatButtonModule, MatButton } from '@angular/material/button'
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { MovieModule } from 'src/app/modules/movie/movie.module';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations'
import { MatToolbarModule, MatToolbar } from '@angular/material/toolbar';
import {MatDialogModule} from '@angular/material/dialog';
import { FormsModule } from '@angular/forms';
import { AuthenticationModule } from './modules/authentication/authentication.module';
import { AuthGuardService } from './auth-guard.service';
const appRoutes:Routes = [

  /* {
    path:'',
    redirectTo: 'movies',
    pathMatch:'full'
  } */
]



 @NgModule({
   declarations: [
     AppComponent
   ],
   imports :[MovieModule,
  BrowserModule,
  BrowserAnimationsModule,
  MatToolbarModule,FormsModule,
  MatDialogModule,
  MatButtonModule,
  AuthenticationModule,
RouterModule.forRoot(appRoutes)],
   providers: [AuthGuardService],
   bootstrap: [AppComponent]
 })
export class AppModule { }
