import { ReaderDto } from './reader.dto';

export interface  BookSubscriptionPayload{
    bookId?:number,
    subscriptionId?:number,
    readerDto:ReaderDto
}