import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../../services/user';
import { User } from '../../models/user';

@Component({
  selector: 'app-user-add',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './user-add.html',
  styleUrls: ['./user-add.css'],
})
export class UserAdd {

  user: User = {
    name: '',
    email: ''
  };

  constructor(
    private userService: UserService,
    private router: Router
  ) {}

 addUser(): void {
  const newUser = {
    name: this.user.name,
    email: this.user.email
  };

  this.userService.addUser(newUser).subscribe(() => {
    alert('User added');
  });
}

}
