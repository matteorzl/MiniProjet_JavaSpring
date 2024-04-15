<script>
import axios from "axios";

export default {
  data(){
    return{
      clients: []
    }
  },
  methods: {
    getClients(){
      axios.get(`http://localhost:8080/api/client/lister`)
        .then(response => {
          this.clients = response.data;
        })
        .catch(error => {
          console.error("Erreur lors de la récupération des messages reçus: ", error);
        });
    },
    deleteClient(id) {
      axios.delete(`http://localhost:8080/api/client/${id}`)
        .then(() => {
          this.getClients()
        })
        .catch(error => {
          window.alert("Un compte est associé à ce client, veuillez dissocier le compte avant de le supprimer")
          console.error("Erreur lors de la suppression du client: ", error);
        });
    }
  },
  created() {
    this.getClients();
  }
};
</script>

<template>
  <div style="text-align: right; margin: 5px">
    <v-btn elevation="2" size="medium"  to="/client/creer" style="background-color:#990000; color:#FFFFFF">
      Créer un client
    </v-btn>
  </div>
  <v-table style="height:100%">
    <div v-if="clients.length === 0" style="font-weight: bold;align-items: center; justify-content: center; margin-top: 25%; background-color: #FFFFFF">
      Il n'existe pas de client
    </div>
    <thead v-else>
      <tr>
        <th class="text-left" style="width:20px">
          Identifiant
        </th>
        <th class="text-left" style="width:30%">
          Nom
        </th>
        <th class="text-left" style="width:30%">
          Prénom
        </th>
        <th class="text-left">
          Edition
        </th>
      </tr>
    </thead>
    <tbody>
        <tr v-if="!clients">
          <td class="text-left"><v-skeleton-loader type="text"></v-skeleton-loader></td>
          <td class="text-left"><v-skeleton-loader type="text"></v-skeleton-loader></td>
          <td class="text-left"><v-skeleton-loader type="text"></v-skeleton-loader></td>
        </tr>
        <tr class="text-left" style="align-items: center;" v-else v-for="client in clients" :key="client.id">
          <td>{{ client.id}}</td>
          <td>{{ client.nom }}</td>
          <td>{{ client.prenom }}</td>
          <td style="padding: 20px">
            <v-btn :to="{path:'/client/'+client.id}" style="margin: 5px; background-color: #990000; color: #FFFFFF">
              Editer
            </v-btn>
            <v-btn @click="deleteClient(client.id)" style="background-color: #990000; color: #FFFFFF">
              Supprimer
            </v-btn>
          </td>
        </tr>
    </tbody>
    </v-table>
</template>