import http from "../http-common";

class DataService {
    // getAllClient() {
    //     return http.get("/client/lister");
    // }
    //
    // getClient(id) {
    //     return http.get(`/client/lister/{id}`);
    // }

    createClient(data) {
        return http.post("/client/creer", data);
    }
    //
    // getAllCompte() {
    //     return http.get("/compte/lister");
    // }
    //
    // getCompte(id) {
    //     return http.get(`/compte/lister/{id}`);
    // }
    //
    // createCompte(data) {
    //     return http.post("/compte/", data);
    // }
}

export default new DataService();