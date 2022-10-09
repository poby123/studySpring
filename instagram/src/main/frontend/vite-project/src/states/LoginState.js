class LoginStates {
    #isLogined;
    #accessToken;

    constructor() {
        console.log('new instance!');
        if (localStorage.getItem('loginStates') !== null) {
            const loginStates = JSON.parse(localStorage.getItem('loginStates'));
            this.#isLogined = loginStates.isLogined;
            this.#accessToken = loginStates.accessToken;
        }
        else {
            this.#isLogined = false;
            this.#accessToken = null;
        }
    }

    setLogined(accessToken) {
        this.#isLogined = true;
        this.#accessToken = accessToken;

        const loginStates = JSON.stringify({ isLogined: true, accessToken: accessToken })
        localStorage.setItem('loginStates', loginStates);
    }

    setLogout() {
        this.#isLogined = false;
        this.#accessToken = null;

        const loginStates = JSON.stringify({ isLogined: false, accessToken: null })
        localStorage.setItem('loginStates', loginStates);
    }

    isLogined() {
        return this.#isLogined;
    }

    getToken() {
        return this.#accessToken;
    }

    getValues(){
        return {
            isLogined: this.#isLogined,
            accessToken: this.#accessToken
        }
    }
}

const loginStateInstance = new LoginStates();
export default loginStateInstance;