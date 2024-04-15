<script>
import axios from "axios";

export default {
  data(){
    return{
      accounts: [],
    }
  },
  methods: {
    getAccounts(){
      axios.get(`http://localhost:8080/api/compte/lister`)
        .then(response => {
          this.accounts = response.data;
          console.log(this.accounts)
        })
        .catch(error => {
          console.error("Erreur lors de la récupération des messages reçus: ", error);
        });
    },
    deleteAccount(id) {
      axios.delete(`http://localhost:8080/api/compte/${id}`)
        .then(() => {
          this.getAccounts()
        })
        .catch(error => {
          console.error("Erreur lors de la suppression du account: ", error);
        });
    }
  },
  created() {
    this.getAccounts();
  }
};
</script>

<template>
  <div style="text-align: right; margin: 5px">
    <v-btn elevation="2" size="medium"  to="/compte/virement" style="background-color:#990000; color:#FFFFFF; margin-right: 10px">
      Effectuer un virement
    </v-btn>
    <v-btn elevation="2" size="medium"  to="/compte/creer" style="background-color:#990000; color:#FFFFFF">
      Créer un compte
    </v-btn>
  </div>
  <v-table style="height:100%">
    <div v-if="accounts.length === 0" style="font-weight: bold;align-items: center; justify-content: center; margin-top: 25%">
      Il n'existe pas de account
    </div>
    <thead v-else>
      <tr>
        <th class="text-left" style="width:20px">
          Identifiant
        </th>
        <th class="text-left" style="width:20px">
          Identifiant Client
        </th>
        <th class="text-left">
          Numéro de compte
        </th>
        <th class="text-left">
          Crédit
        </th>
        <th class="text-left">
          Edition
        </th>
      </tr>
    </thead>
    <tbody>
      <tr v-if="!accounts">
        <td class="text-left"><v-skeleton-loader type="text"></v-skeleton-loader></td>
        <td class="text-left"><v-skeleton-loader type="text"></v-skeleton-loader></td>
      </tr>
      <tr class="text-left" v-else v-for="account in accounts" :key="account.id">
        <td>{{ account.id}}</td>
        <td>{{ account.id_client}}</td>
        <td>{{ account.numero }}</td>
        <td>{{ account.credit}}</td>
        <td style="padding: 20px">
          <v-btn :to="{path:'/compte/'+account.id}" style="margin: 5px; background-color: #990000; color: #FFFFFF">
            Editer
          </v-btn>
          <v-btn @click="deleteAccount(account.id)" style="background-color: #990000; color: #FFFFFF">
            Supprimer
          </v-btn>
        </td>
      </tr>
    </tbody>
  </v-table>
</template>