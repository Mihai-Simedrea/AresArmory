import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.scss']
})
export class CategoryComponent implements OnInit {
  constructor(private readonly router: Router) {

  }

  data = [1, 2, 3, 4, 5, 6];

  ngOnInit(): void {
    
  }
}
