import { Component, OnInit } from '@angular/core';
import { CartUserClient } from 'src/app/backend/api';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  username!: string | null;

  constructor(private readonly cartUserClient: CartUserClient) {}

  ngOnInit(): void {
    this.username = localStorage.getItem('username');

    this.cartUserClient.getUserCart(localStorage.getItem("email")!).subscribe(result => {
      localStorage.setItem("cartId", result.id);
    });
  }
}
