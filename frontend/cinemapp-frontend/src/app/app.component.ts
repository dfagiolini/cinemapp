import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from './core/navbar/navbar.component';
import { FooterComponent } from './core/footer/footer.component';
import { ToolbarModule } from 'primeng/toolbar';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, NavbarComponent, FooterComponent, ButtonModule, ToolbarModule, CardModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'cinemapp-frontend';

  films = [
    { title: 'Il Padrino', image: 'assets/godfather.jpg' },
    { title: 'Inception', image: 'assets/inception.jpg' },
    { title: 'Interstellar', image: 'assets/interstellar.jpg' }
  ];
}
