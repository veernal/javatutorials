import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { readerurl } from 'src/urls/reader.url';

@Injectable({
  providedIn: 'root'
})
export class FindService {
  private reader_url = readerurl.url;
  private find_url: string = this.reader_url + "/";

  constructor(private http: HttpClient) { }

  findBookBySubscriptionId(emailId: string, subscriptionId: number) {
    var url = this.find_url + emailId + "/books/find/" +subscriptionId
    console.log("Constructed Find By Payment URL : " + url)
    return this.http.post(url, subscriptionId);
  }
}
