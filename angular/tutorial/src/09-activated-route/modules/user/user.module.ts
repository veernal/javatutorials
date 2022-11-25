import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { WelcomeComponent } from './components/welcome/welcome.component';

const routes:Routes = [
  {path: "login", component: LoginComponent},
  {path: "signup", component: SignupComponent},
  // spring -> @GetMapping("/greet/{name}")
  {path: "welcome/:name", component: WelcomeComponent}
]

@NgModule({
  declarations: [
    LoginComponent,
    SignupComponent,
    WelcomeComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ]
})
export class UserModule { }
