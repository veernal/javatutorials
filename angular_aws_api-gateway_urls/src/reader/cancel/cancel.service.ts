import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BookSubscriptionPayload } from 'src/models/book.subscription.payload';
import { readerurl } from 'src/urls/reader.url';

@Injectable({
  providedIn: 'root'
})
export class CancelService {

  private reader_url = readerurl.url;
  private reader_url_prefix: string =this.reader_url+"/";

  constructor(private http:HttpClient) { }

  refundBook(url:string, bookPurchasePayload:BookSubscriptionPayload){
    console.log("constructed Refund URL : " + this.reader_url_prefix+url)
    return this.http.post(this.reader_url_prefix+url,bookPurchasePayload);
  }
}
