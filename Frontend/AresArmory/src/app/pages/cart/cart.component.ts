import { Component, OnInit } from '@angular/core';
import { CartClient } from 'src/app/backend/api';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {
  constructor(private readonly cartClient: CartClient) {}
  cartId!: number;

  ngOnInit(): void {
    this.cartId = parseInt(localStorage.getItem('cartId')!);
    this.cartClient.get(this.cartId).subscribe(result => {
      console.log(result);
    })
  }
}
