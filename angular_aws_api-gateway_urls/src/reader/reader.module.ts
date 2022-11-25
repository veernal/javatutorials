import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FindComponent } from './find/find.component';
import { InvoiceComponent } from './invoice/invoice.component';
import { NotificationsComponent } from './notifications/notifications.component';
import { ReadComponent } from './read/read.component';
import { CancelComponent } from './cancel/cancel.component';
import { SearchComponent } from './search/search.component';
import { SubscribeComponent } from './subscribe/subscribe.component';
import { SubscriptionsComponent } from './subscriptions/subscriptions.component';
import { RouterModule, Routes } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';


const routes:Routes = [
  { path: "search", component: SearchComponent },
  { path: "subscribe", component: SubscribeComponent },
  { path: "subscriptions", component: SubscriptionsComponent },
  { path: "notifications", component: NotificationsComponent },
  { path: "cancel", component: CancelComponent},
  { path: "read", component: ReadComponent},
  { path: "find", component: FindComponent},
  { path: "invoice", component: InvoiceComponent},
];


@NgModule({
  declarations: [
    FindComponent,
    InvoiceComponent,
    NotificationsComponent,
    ReadComponent,
    CancelComponent,
    SearchComponent,
    SubscribeComponent,
    SubscriptionsComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule.forChild(routes),
    HttpClientModule
  ]
})
export class ReaderModule { }
