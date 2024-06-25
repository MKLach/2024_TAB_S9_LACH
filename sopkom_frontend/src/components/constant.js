
export let [SERVER_URL, setSERVER_URL] = 'http://localhost:8080';

export const setServerUrl = (newUrl) => {
  SERVER_URL = newUrl;
};