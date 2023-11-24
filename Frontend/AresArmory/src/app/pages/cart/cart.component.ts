import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { CartClient, CartUserClient } from 'src/app/backend/api';
import { CartModel } from 'src/app/components/shared/models/cart.model';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {
  constructor(private readonly cartClient: CartClient,
              private readonly router: Router,
              private readonly cartUserClient: CartUserClient) { }
  cartId!: number;
  productCountMap = new Map<string, { id: number; count: number, price: number }>();
  displayedColumns: string[] = ['productName', 'productCount', 'actions'];
  dataSource = new MatTableDataSource<{ name: string; count: number, price: number }>();

  decreaseCount(element: any): void {
    const product = this.productCountMap.get(element.name);
    if (product) {
      product.count--;
      this.updateDataSource();
      console.log(product);
      this.cartClient.remove(product.id, this.cartId).subscribe();
    }
  }

  increaseCount(element: any): void {
    const product = this.productCountMap.get(element.name);
    if (product) {
      product.count++;
      this.updateDataSource();
      const cartModel = new CartModel();
      cartModel.cart_id = this.cartId;
      cartModel.product_id = product.id;
      this.cartClient.add(cartModel).subscribe();
    }
  }

  ngOnInit(): void {
    this.cartId = parseInt(localStorage.getItem('cartId')!);
    this.cartClient.get(this.cartId).subscribe(result => {
      result.forEach((item: any) => {
        const productName = item.product.name;
        const productId = item.product.id;
        const productPrice = item.product.price;

        if (this.productCountMap.has(productName)) {
          const product = this.productCountMap.get(productName)!;
          product.count++;
        } else {
          this.productCountMap.set(productName, { id: productId, count: 1, price: productPrice });
        }
      });
      this.updateDataSource();
    });

    console.log(this.productCountMap);
    console.log(this.dataSource);
  }

  calculateTotalPrice(): number {
    let totalPrice = 0;
  

    this.dataSource.data.forEach(element => {
      totalPrice += element.count * element.price;
    });
  
    return totalPrice;
  }

  placeOrder(): void {
    this.router.navigate(['/order']);
    this.cartUserClient.deleteUserCart(localStorage.getItem("email")!).subscribe();
  }

  updateDataSource(): void {
    this.dataSource.data = Array.from(this.productCountMap).map(([name, product]) => ({
      name,
      id: product.id,
      count: product.count,
      price: product.price
    }));
  }
}
