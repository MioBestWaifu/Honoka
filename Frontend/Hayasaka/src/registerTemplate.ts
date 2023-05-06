export class RegisterTemplate{
    email:string;
    password:string;
    name:string;
    birthday:Date;
    area:number;
    gender:string

    constructor(e:string,p:string,u:string,b:Date,g:string,a:number){
        this.email = e;
        this.password = p;
        this.name = u;
        this.birthday = b;
        this.area = a;
        this.gender = g;
    }
}