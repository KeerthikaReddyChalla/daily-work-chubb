let name = "Keerthika";
let age = 21;
let isStudent = true;

console.log(name);
console.log(age);
console.log(isStudent);

let a = 10;
let b = 3;

console.log(a + b);
console.log(a - b);
console.log(a * b);
console.log(a / b);
console.log(a % b);

let marks = 75;


//conditions
if (marks >= 50) {
  console.log("Pass");
} else {
  console.log("Fail");
}


//loops
for (let i = 1; i <= 5; i++) {
  console.log(i);
}


//Function
function add(x, y) {
  return x + y;
}

console.log(add(5, 3));


//Arrow function
const multiply = (a, b) => a * b;
console.log(multiply(4, 2));

//Arrays
let fruits = ["Apple", "Banana", "Mango"];

console.log(fruits[0]);
fruits.push("Orange");
console.log(fruits);

//Objects
let student = {
  name: "Keerthika",
  age: 21,
  course: "CSE"
};

console.log(student.name);
console.log(student.age);
