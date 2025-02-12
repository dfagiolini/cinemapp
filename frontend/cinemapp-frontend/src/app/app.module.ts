import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { MenubarModule } from 'primeng/menubar';
import { AppRoutingModule } from './app-routing.module';
import { NavbarComponent } from './core/navbar/navbar.component';
import { FooterComponent } from './core/footer/footer.component';  
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { ToolbarModule } from 'primeng/toolbar';
import { CarouselModule } from 'primeng/carousel';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    FormsModule,
    CardModule,
    ToolbarModule,
  ],
  imports: [
    BrowserAnimationsModule,
    ButtonModule,
    CardModule,
    MenubarModule,
    AppRoutingModule, 
    CardModule,
    ToolbarModule,
    CarouselModule,
  ],
  providers: [],
  bootstrap: [AppComponent]

})
export class AppModule {}