import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { bookurl } from 'src/urls/books.url';

@Injectable({
  providedIn: 'root'
})
export class SearchService {
  private book_url = bookurl.url;
  private search_url_prefix: string = this.book_url+"/search";

  constructor(private http: HttpClient) { }

  findAllBooks() {
    return this.http.get(this.search_url_prefix);
  }

  findBooksByParams(category: string, author: string, price: number, publisher: string) {

    var url = this.search_url_prefix
    if (null != category || null != author || null != price || null != publisher) {
      url = url + "?";
      if (category) {
        url = url + "category=" + category + "&";
      }
      if (author) {
        url = url + "author=" + author + "&";
      }
      if (price && price != 0) {
        url = url + "price=" + price + "&";
      }
      if (publisher) {
        url = url + "publisher=" + publisher + "&";
      }
      url = url.substring(0, url.length - 1);
    }
    console.log("constructed Search URL : " + url)
    return this.http.get(url);
  }
}
