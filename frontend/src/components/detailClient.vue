<script>
import axios from "axios";

export default {
  data(){
    return{
      clientInfos: []
    }
  },
  methods: {
    getClientInfo(){
      var url = window.location.pathname;
      const id = url.substring(url.lastIndexOf('/') + 1);
      axios.get(`http://localhost:8080/api/client/` + id)
        .then(response => {
          this.clientInfos = response.data;
          console.log(this.clientInfos)
        })
        .catch(error => {
          console.error("Erreur lors de la récupération des messages reçus: ", error);
        });
    },
    updateClient() {
      axios.put(`http://localhost:8080/api/client/${this.clientInfos.id}`, {
        nom: this.clientInfos.nom,
        prenom: this.clientInfos.prenom
      })
        .then(() => {
          console.log("Client mis à jour avec succès");
        })
        .catch(error => {
          console.error("Erreur lors de la mise à jour du client: ", error);
        });
    }
  },
  created() {
    this.getClientInfo();
  }
};
</script>

<template>
  <div class="submit-form" style="width: 30%; height: 200px; padding-top: 20px;margin: 20% 35%;border-radius: 10px; justify-content: center;background-color : #990000; color: #FFFFFF">
    <div>
      <div style="font-weight: bold; margin-bottom: 20px">
        Modification d'un client
      </div>
      <div class="form-group" style="align-items: center;">
        <label for="nom" style="margin-right: 29px">Nom</label>
        <input
            type="text"
            class="form-control"
            id="nom"
            required
            v-model="clientInfos.nom"
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
            v-model="clientInfos.prenom"
            name="prenom"
            style="background: #FFFFFF"
        />
      </div>
    </div>
    <v-btn @click="updateClient" to="/client/lister" class="btn btn-success" style="margin-top: 20px; background: #FFFFFF; color: #990000">
      Modifier
    </v-btn>
  </div>
</template>