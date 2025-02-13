import { Component } from '@angular/core';
import { Menubar } from 'primeng/menubar';

@Component({
  imports:[Menubar],
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
   styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  searchType: string = ''; 
  items = [
    { label: 'Home', icon: 'pi pi-home', routerLink: '/' },
    { label: 'Cinema', icon: 'pi pi-video', routerLink: '/cinema' },
    { label: 'Film', icon: 'pi pi-film', routerLink: '/film' },
    { label: 'Proiezioni', icon: 'pi pi-calendar', routerLink: '/proiezioni' },
    { label: 'Prenotazioni', icon: 'pi pi-ticket', routerLink: '/booking' }
  ];
}

