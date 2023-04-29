export class RegisterTemplate{
    email:string;
    password:string;
    name:string;
    birthday:Date;

    constructor(e:string,p:string,u:string,b:Date){
        this.email = e;
        this.password = p;
        this.name = u;
        this.birthday = b;
    }
}