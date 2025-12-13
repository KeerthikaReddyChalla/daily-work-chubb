import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user';
@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl = 'http://localhost:3000/users';

  constructor(private http: HttpClient) {}

  getUsers() {
    return this.http.get<User[]>(this.apiUrl)

  }

  getUserById(id: number) {
    return this.http.get(`${this.apiUrl}/${id}`);
  }

  addUser(user: Omit<User, 'id'>) {
    return this.http.post(this.apiUrl, user);
  }

  updateUser(id: number, user: any) {
    return this.http.put(`${this.apiUrl}/${id}`, user);
  }

  deleteUser(id: number) {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}
