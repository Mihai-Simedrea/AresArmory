import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CartClient, CartUserClient, CategoryClient, ProductClient } from 'src/app/backend/api';
import { CartModel } from '../shared/models/cart.model';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.scss']
})
export class CardComponent implements OnInit {
  @Input() name!: string;
  @Input() id!: number;
  @Input() description!: string;
  @Input() price!: number;
  @Input() type!: string;
  role!: string;
  cartId!: number;
  cartModel = new CartModel();

  constructor(private readonly router: Router,
    private readonly categoryClient: CategoryClient,
    private readonly productClient: ProductClient,
    private readonly cartUserClient: CartUserClient,
    private readonly cartClient: CartClient) {
  }

  ngOnInit(): void {
    this.role = localStorage.getItem('role')!;

    this.cartUserClient.getUserCart(localStorage.getItem("email")!).subscribe(id => {
      this.cartId = id;
    });
  }

  redirectTo(): void {
    localStorage.setItem('categoryId', this.id.toString());
    this.router.navigate(['/product']);
  }

  add(): void {
    this.cartModel.cart_id = this.cartId;
    this.cartModel.product_id = this.id;
    this.cartClient.add(this.cartModel).subscribe();
  }

  remove(): void {
    this.cartClient.remove(this.id).subscribe();
  }

  deleteItem(): void {
    if (this.type == 'category') {
      this.categoryClient.delete(this.id).subscribe(() => {

      }, () => { }, () => {

        this.router.navigateByUrl('/home', { skipLocationChange: true }).then(() => {
          this.router.navigate(['/category']);
        });
      });

    } else {
      this.productClient.delete(this.id).subscribe(() => { }, () => { }, () => {
        this.router.navigateByUrl('/home', { skipLocationChange: true }).then(() => {
          this.router.navigate(['/product']);
        });
      })
    }
  }
}
