import { Component, OnInit } from '@angular/core';
import { BookSubscriptionPayload } from 'src/models/book.subscription.payload';
import { Router } from '@angular/router';
import { BookDto } from 'src/models/book.dto';
import { CancelService } from './cancel.service';
import { ReaderPayload } from 'src/models/reader.payload';

@Component({
  selector: 'app-cancel',
  templateUrl: './cancel.component.html',
  styleUrls: ['./cancel.component.scss']
})
export class CancelComponent implements OnInit {

  readerPayload: ReaderPayload = {
    readerDto: {
      readerId: 0,
      name: '',
      emailId: ''
    },
    notifications: [],
    bookDtoList: []
  }

  cancelMessage: string = ''

  constructor(private _refundService: CancelService, private router: Router) { 
    if(!this.isReader()){
      this.router.navigate(["home"])
    }
  }

  isReader(){
    var author = localStorage.getItem('authorToken')
    if (author) {
      return false
    } else {
      return true
    }
  }
  refundBook(url: string, bookPurchasePayload: BookSubscriptionPayload) {
    this._refundService.refundBook(url, bookPurchasePayload).subscribe({
      next: (res: any) => {
        if (!!res.statusCode) {
          alert(res.message)
        } else {
          this.readerPayload = res;
          var bookTitle = this.readerPayload.bookDtoList[0].title
          this.cancelMessage = 'Successfully cancelled the subscription for the book : ' + bookTitle
          console.log(this.readerPayload);
        }
      },
      error: (err: any) => {
        console.log(err)
      }
    })
  }

  ngOnInit(): void {
    var refundUrl = localStorage.getItem('refund');
    var refundPayloadInString = localStorage.getItem('refundPayload')
    if (refundUrl && refundPayloadInString) {
      var bookPurchasePayload: BookSubscriptionPayload = JSON.parse(refundPayloadInString);
      this.refundBook(refundUrl, bookPurchasePayload);
      localStorage.removeItem('refund')
      localStorage.removeItem('refundPayload')
    } else {
      this.router.navigate(["/reader/subscriptions"]);
    }
  }


}
