import { Component } from "@angular/core";
import { FormControl, FormGroup, Validators } from "@angular/forms";

@Component({
    selector: "app-root",
    templateUrl: "app.component.html",
    styleUrls: ["app.component.scss"],
    providers: []
  })
export class AppComponent{
  loginForm:FormGroup;

  constructor(){
    this.loginForm = new FormGroup({
      username: new FormControl("", [
        Validators.maxLength(8),
        Validators.minLength(3),
        Validators.required
        // , Validators.pattern("")   
      ]),
      password: new FormControl("")
    })
  }

  getLogin(){
    console.log(this.loginForm);
    console.log("Value: "+JSON.stringify(this.loginForm.value));
    console.log("Valid: "+this.loginForm.valid);

    this.loginForm.reset();
  }
}