import { Component } from '@angular/core';
import { UserService } from '../../services/user';
import { CommonModule, NgFor } from '@angular/common';
import { NgIf } from '@angular/common';
import { User } from '../../models/user';
import { RouterModule } from '@angular/router';
import { ChangeDetectorRef } from '@angular/core';


@Component({//decorator
  selector: 'app-user-list',
  imports: [CommonModule,  RouterModule],
  templateUrl: './user-list.html',
  styleUrl: './user-list.css',
  standalone: true

})
export class UserList {
    users: User[] = [];

  //constructor(private userService: UserService) {}//DI
  constructor(private userService: UserService, private cdr: ChangeDetectorRef) {}
  ngOnInit() {//callback method
    this.userService.getUsers().subscribe(data => {
      console.log(data);
      
      this.users = data;
      this.cdr.detectChanges();
    });
  }
  deleteUser(id: number): void {
  if (!confirm('Are you sure you want to delete this user?')) {
    return;
  }

  this.userService.deleteUser(id).subscribe(() => {
    this.users = this.users.filter(user => user.id !== id);
  });
}


}
