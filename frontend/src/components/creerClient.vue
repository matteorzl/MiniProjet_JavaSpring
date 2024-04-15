<script>
import axios from "axios";

export default {
  data(){
    return{
      client : {
        nom: '',
        prenom: ''
      }
    }
  },
  methods: {
    createClient() {
      axios.post('http://localhost:8080/api/client/creer',  this.client, {
        headers: {
          'Content-Type': 'application/json'
        }
      })
        .then(function (response) {
          console.log(response);
        })
        .catch(function (error) {
          console.log(error);
        });
    }
  }
}
</script>

<template>
  <div class="submit-form" style="width: 30%; height: 200px; padding-top: 20px;margin: 20% 35%;border-radius: 10px; justify-content: center;background-color : #990000; color: #FFFFFF">
    <div>
      <div style="font-weight: bold; margin-bottom: 20px">
      Création d'un client
      </div>
      <div class="form-group" style="align-items: center;">
        <label for="nom" style="margin-right: 29px">Nom</label>
        <input
          type="text"
          class="form-control"
          id="nom"
          required
          v-model="client.nom"
          name="nom"
          style="background: #FFFFFF"
        />
      </div>
      <br>
      <div class="form-group" style="align-items: center;">
        <label for="prenom" style="margin-right: 10px">Prénom</label>
        <input
          class="form-control"
          id="prenom"
          required
          v-model="client.prenom"
          name="prenom"
          style="background: #FFFFFF"
        />
        </div>
    </div>
    <v-btn @click="createClient" to="/client/lister" class="btn btn-success" style="margin-top: 20px; background: #FFFFFF; color: #990000">
      Créer
    </v-btn>
  </div>
</template>