<script>
import axios from "axios";

export default {
  data(){
    return{
      clients: [],
      account : {
        numero: '',
        id_client: ''
      }
    }
  },
  methods: {
    getClient(){
      axios.get(`http://localhost:8080/api/client/lister`)
          .then(response => {
            this.clients = response.data;
          })
          .catch(error => {
            console.error("Erreur lors de la récupération des messages reçus: ", error);
          });
    },
    createAccount() {
      console.log(this.account)
      axios.get(`http://localhost:8080/api/client/` + this.account.id_client)
          .then(response => {
            if (response.data) {
              this.account.client = response.data;

              axios.post('http://localhost:8080/api/compte/creer', this.account)
                  .then(() => {
                    document.location = "http://localhost:8081/compte/lister"
                  })
                  .catch(error => {
                    console.error('Erreur lors de la création du compte:', error);
                  });
            } else {
              alert("Le client spécifié n'existe pas !");
            }
          })
          .catch(error => {
            console.error('Erreur lors de la récupération du client:', error);
          });
    }
  },
  created() {
    this.getClient();
  }
}
</script>

<template>
  <div class="submit-form" style="width: 30%; height: 300px; padding-top: 20px;margin: 20% 35%;border-radius: 10px; justify-content: center;background-color : #990000; color: #FFFFFF">
    <div style="font-weight: bold; margin-bottom: 20px">
      Création d'un compte
    </div>
    <div class="form-group" style="align-items: center;">
      <label for="numero" style="margin-right: 15px">Numéro</label>
      <input
          type="text"
          class="form-control"
          id="numero"
          required
          v-model="account.numero"
          name="numero"
          style="background: #FFFFFF"
      />
    </div>
    <br>
    <div class="form-group" style="align-items: center;">
      <label for="id_client" style="margin-right: 10px">ID Client</label>
      <select
          class="form-control"
          id="id_client"
          required
          v-model="account.id_client"
          name="id_client"
          style="background: #FFFFFF; width: 163px"
      >
        <option value="">Choississez l'ID</option>
        <option v-for="client in clients" :key="client.id">{{ client.id }}</option>
      </select>
    </div>
    <br>
    <div class="form-group" style="align-items: center;">
      <label for="credit" style="margin-right: 15px">Crédit</label>
      <input
          type="number"
          class="form-control"
          id="credit"
          v-model="account.credit"
          name="credit"
          style="background: #FFFFFF"
      />
    </div>
    <button @click="createAccount" class="btn btn-success" style="margin-top: 20px; padding: 5px; border-radius: 5px; background: #FFFFFF; color: #990000">Créer</button>
  </div>
</template>