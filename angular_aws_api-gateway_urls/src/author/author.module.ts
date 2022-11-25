import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BooksComponent } from './books/books.component';
import { CreateComponent } from './create/create.component';
import { EditComponent } from './edit/edit.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { SignupComponent } from './signup/signup.component';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { ValidUserGuard } from 'src/guards/valid-user.guard';

const routes:Routes = [
  { path: "create", component: CreateComponent },
  { path: "edit", component: EditComponent },
  { path: "login", component: LoginComponent},
  { path: "signup", component: SignupComponent },
  { path: "logout", component: LogoutComponent},
  { path: "books", component: BooksComponent}
];



@NgModule({
  declarations: [
    BooksComponent,
    CreateComponent,
    EditComponent,
    LoginComponent,
    LogoutComponent,
    SignupComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule.forChild(routes),
    HttpClientModule
  ]
})
export class AuthorModule { }
//, canActivate: [ValidUserGuard]