import { platformBrowserDynamic } from "@angular/platform-browser-dynamic";
// import { AppModule } from "./01-binding/app.module";
// import { AppModule } from "./02-service/app.module";
// import { AppModule } from "./03-service-share/app.module";
// import { AppModule } from "./04-ajax/app.module";
// import { AppModule } from "./05-routing/app.module";
// import { AppModule } from "./06-lazy-loading/app.module";
// import { AppModule } from "./07-activate-route/app.module";
// import { AppModule } from "./08-activate-route/app.module";
// import { AppModule } from "./09-activated-route/app.module";
// import { AppModule } from "./10-form/app.module";
// import { AppModule } from "./11-child-comp/app.module";
// import { AppModule } from "./12-structral-directives/app.module";
// import { AppModule } from "./13-pipes/app.module";
// import { AppModule } from "./14-exception/app.module";
// import { AppModule } from "./15-logger/app.module";
import { AppModule } from "./16-ajax-aws/app.module";

platformBrowserDynamic().bootstrapModule(AppModule)
// .catch(e=>{
//   console.log(e)
// })