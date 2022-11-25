import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { BookSubscriptionPayload } from 'src/models/book.subscription.payload';
import { Router } from '@angular/router';
import { BookDto } from 'src/models/book.dto';
import { SubscribeService } from './subscribe.service';
import { InvoicePayload } from 'src/models/invoice.payload';


@Component({
  selector: 'app-subscribe',
  templateUrl: './subscribe.component.html',
  styleUrls: ['./subscribe.component.scss']
})
export class SubscribeComponent implements OnInit {

 
  purchaseForm: FormGroup;

  bookDto: BookDto = {
    bookId: 0,
    active: true,
    author: '',
    authorId: 0,
    price: 0,
    category: '',
    publishedDate: '',
    publisher: '',
    logo: '',
    title: '',
  }

  paymentInvoicePayload: InvoicePayload = {
    subscriptionId: 0,
    bookDto: {
      active: true,
      author: "",
      bookId: 0,
      authorId: 0,
      category: "",
      logo: "",
      price: 0,
      publishedDate: "",
      publisher: "",
      title: ""
    },
    readerDto: {
      name: "",
      emailId: ""
    },
    subscriptionDateTime: ''
  }

  paymentInvoiceHtml: any = {
    heading: "",
    title: "",
    bookId: "",
    subscriptionId: "",
    subscriptionDateTime: "",
    readerName: "",
    readerEmail: ""
  }

  invoiceGenerated:boolean=false

  constructor(private _subscribeService: SubscribeService, private router: Router) {
    if(!this.isReader()){
      this.router.navigate(["home"])
    }
    this.purchaseForm = new FormGroup({
      name: new FormControl("", [Validators.required]),
      emailId: new FormControl("", [Validators.required, Validators.email])
    })
  }

  isReader(){
    var author = localStorage.getItem('authorToken')
    if (author) {
      return false
    } else {
      return true
    }
  }

  ngOnInit(): void {
    var subscribePayloadInString = localStorage.getItem("subscribe");
    if (subscribePayloadInString) {
      this.bookDto = JSON.parse(subscribePayloadInString);
    } else {
      this.router.navigate(["/reader/search"]);
    }
  }

  viewInvoice(paymentInvoiceHtml:any){
    localStorage.setItem('invoice',JSON.stringify(paymentInvoiceHtml))
    this.router.navigate(['reader/invoice'])
  }

  buyBook(name: string, emailId: string) {
    var bookPurchasePayload: BookSubscriptionPayload= {
      bookId: this.bookDto.bookId,
      readerDto: {
        name: name,
        emailId: emailId
      }
    }
    this._subscribeService.buyBook(bookPurchasePayload).subscribe({

      next: (res: any) => {
        if (!!res.statusCode) {
          alert(res.message)
        } else {
          this.paymentInvoicePayload = res;
          console.log(this.paymentInvoicePayload);
          this.paymentInvoiceHtml.heading = "Subscription Invoice"
          this.paymentInvoiceHtml.title = "Title Subscribed : " + this.paymentInvoicePayload.bookDto.title
          this.paymentInvoiceHtml.bookId = "Book ID : " + this.paymentInvoicePayload.bookDto.bookId
          this.paymentInvoiceHtml.subscriptionId = "Subscription ID : " + this.paymentInvoicePayload.subscriptionId
          this.paymentInvoiceHtml.subscriptionDateTime = "Subscription Done On : " + this.paymentInvoicePayload.subscriptionDateTime
          this.paymentInvoiceHtml.readerName = "Name of Reader : " + this.paymentInvoicePayload.readerDto.name
          this.paymentInvoiceHtml.readerEmail = "Reader Email ID : " + this.paymentInvoicePayload.readerDto.emailId
          this.invoiceGenerated=true
        }
      },
      error: (err: any) => {
        console.log(err)
      }
    })

  }

}
