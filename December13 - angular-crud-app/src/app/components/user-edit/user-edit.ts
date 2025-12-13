import { Component } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup } from '@angular/forms';
import { UserService } from '../../services/user';

@Component({
  selector: 'app-user-edit',
  standalone: true,                 // ✅ REQUIRED
  imports: [ReactiveFormsModule],   // ✅ REQUIRED
  templateUrl: './user-edit.html',
  styleUrls: ['./user-edit.css'],
})
export class UserEdit {

  form!: FormGroup;
  id!: number;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));

    this.form = this.fb.group({
      name: [''],
      email: ['']
    });

    this.userService.getUserById(this.id).subscribe(data => {
      this.form.patchValue(data);
    });
  }

  updateUser(): void {
    this.userService.updateUser(this.id, {
      id: this.id,
      ...this.form.value
    }).subscribe(() => {
      alert('User updated');
    });
  }
}
