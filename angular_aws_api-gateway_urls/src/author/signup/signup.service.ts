import { Injectable } from '@angular/core';
import { authorurl } from 'src/urls/author.url';
import { HttpClient } from '@angular/common/http';
import { CredentialPayload } from 'src/models/credential.payload';

@Injectable({
  providedIn: 'root'
})
export class SignupService {

  private author_url = authorurl.url;
  private signup_url: string = this.author_url+"/signup";

  constructor(private http:HttpClient) { }

  signup(credentialPayload:CredentialPayload){
    console.log("Created Signup URL : ",this.signup_url);
    return this.http.post(this.signup_url,credentialPayload);
  }
}
