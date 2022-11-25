import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'capitalize'
})
export class CapitalizePipe implements PipeTransform {

  transform(input: string): string {
    return input.charAt(0).toUpperCase()+input.substring(1).toLowerCase();
  }

}
