export const requestHeader = (authUtils) => {
    return { 'Authorization': 'Bearer ' + authUtils.getToken() }
}

export const baseURL = 'http://wj-code-server.com:8080/';