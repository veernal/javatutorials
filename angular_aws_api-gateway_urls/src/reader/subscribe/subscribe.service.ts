import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BookSubscriptionPayload } from 'src/models/book.subscription.payload';
import { readerurl } from 'src/urls/reader.url';

@Injectable({
  providedIn: 'root'
})
export class SubscribeService {

  private reader_url = readerurl.url;
  private buy_books_url: string = this.reader_url + "/books/subscribe";

  constructor(private http: HttpClient) { }

  buyBook(bookPurchasePayload: BookSubscriptionPayload ) {
    console.log("constructed Buy URL : " + this.buy_books_url)
    return this.http.post(this.buy_books_url, bookPurchasePayload);
  }
}
