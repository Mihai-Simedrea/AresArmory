import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CategoryClient } from 'src/app/backend/api';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.scss']
})
export class CategoryComponent implements OnInit {
  constructor(private readonly router: Router,
              private readonly categoryClient: CategoryClient) {

  }

  categories: any[] = []

  ngOnInit(): void {
    this.categoryClient.getAll().subscribe(result => {
      this.categories = result;
    })
  }
}
