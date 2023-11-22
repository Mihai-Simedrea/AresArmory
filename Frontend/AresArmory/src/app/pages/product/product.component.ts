import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductClient } from 'src/app/backend/api';
import { ProductModel } from 'src/app/components/shared/models/product.model';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit {
  constructor(private readonly router: Router,
    private readonly productClient: ProductClient) {

  }

  categoryId!: number;
  products: any[] = [];
  productModel = new ProductModel();
  role!: string;

  ngOnInit(): void {
    let categoryIdString = localStorage.getItem('categoryId')!;
    this.role = localStorage.getItem('role')!;

    this.categoryId = parseInt(categoryIdString, 10);
    this.productClient.getByCategory(this.categoryId).subscribe(result => {
      this.products = result;
    });
  }

  addProduct(): void {
    this.productModel.categoryId = this.categoryId.toString();
    this.productModel.email = 'idk';

    this.productClient.add(this.productModel).subscribe(() => { }, () => { }, () => {
      this.router.navigateByUrl('/home', { skipLocationChange: true }).then(() => {
        this.router.navigate(['/product']);
      });
    });
  }
}
