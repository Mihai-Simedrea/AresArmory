import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.scss']
})
export class ContactComponent implements OnInit {
  userName!: string;
  userEmail!: string;
  userRole!: string;
  userInitials: string = '';

  ngOnInit(): void {
    this.userName = localStorage.getItem("username")!;
    this.userEmail = localStorage.getItem("email")!;
    this.userRole = localStorage.getItem("role")!;
    this.userInitials = this.calculateInitials(this.userName);
  }

  calculateInitials(name: string): string {
    if (!name) {
      return '';
    }

    const words = name.split(' ');
    const initials = words
      .map(word => word.charAt(0).toUpperCase())
      .join('');

    return initials;
  }
}
