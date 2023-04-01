export class RegisterTemplate{
    email:string;
    password:string;
    username:string;
    birthday:Date;

    constructor(e:string,p:string,u:string,b:Date){
        this.email = e;
        this.password = p;
        this.username = u;
        this.birthday = b;
    }
}