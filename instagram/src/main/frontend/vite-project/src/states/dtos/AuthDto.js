export class LoginDto {

    username = '';
    password = '';

    constructor(username, password){
        this.username = username;
        this.password = password;
    }
}