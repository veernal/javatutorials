import { BookDto } from './book.dto';
import { ReaderDto } from './reader.dto';

export interface InvoicePayload{
    subscriptionId:number,
    subscriptionDateTime:string,
    bookDto:BookDto
    readerDto:ReaderDto
}