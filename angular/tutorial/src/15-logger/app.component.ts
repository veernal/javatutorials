import { Component } from "@angular/core";
import { NGXLogger } from "ngx-logger";

@Component({
    selector: "app-root",
    templateUrl: `app.component.html`,
    styleUrls: ['app.component.scss']
})
export class AppComponent { 

    constructor(private logger: NGXLogger){
        logger.info("This is message - info");
        logger.log("This is message - log");
        logger.warn("This is message - warn");
        logger.error("This is message - error");
    }
   
}