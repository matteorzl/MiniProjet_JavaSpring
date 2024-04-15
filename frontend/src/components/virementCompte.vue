<script>
import axios from "axios";

export default {
  data(){
    return{
      accounts: [],
      account : {
        expediteur: '',
        destinataire: '',
        montant: null
      }
    }
  },
  methods: {
    getAccounts(){
      axios.get(`http://localhost:8080/api/compte/lister`)
          .then(response => {
            this.accounts = response.data;
          })
          .catch(error => {
            console.error("Erreur lors de la récupération des messages reçus: ", error);
          });
    },
    doTransfer() {
      axios.post('http://localhost:8080/api/compte/virement', this.account)
          .then(() => {
            document.location = "http://localhost:8081/compte/lister"
          })
          .catch(error => {
            console.error('Erreur lors du virement :', error);
          });
    }
  },
  created() {
    this.getAccounts();
  }
}
</script>

<template>
  <div class="submit-form" style="width: 40%; height: 300px; padding-top: 20px;margin: 20% 30%;border-radius: 10px; justify-content: center;background-color : #990000; color: #FFFFFF">
    <div style="font-weight: bold; margin-bottom: 20px">
      Virement
    </div>
    <div class="form-group" style="align-items: center;">
      <label for="expediteur" style="margin-right: 15px">Compte expéditeur</label>
      <select
          class="form-control"
          id="expediteur"
          required
          v-model="account.expediteur"
          name="expediteur"
          style="background: #FFFFFF; width: 250px"
      >
        <option value="">Choississez l'ID</option>
        <option v-for="account in accounts" :key="account.id" :value="account.id">{{ account.id }} - (n°{{account.numero}}, Solde : {{account.credit}})</option>
      </select>
    </div>
    <br>
    <div class="form-group" style="align-items: center;">
      <label for="id_client" style="margin-right: 10px">Compte destinataire</label>
      <select
          class="form-control"
          id="destinataire"
          required
          v-model="account.destinataire"
          name="destinataire"
          style="background: #FFFFFF; width: 250px"
      >
        <option value="">Choississez l'ID</option>
        <option v-for="account in accounts" :key="account.id" :value="account.id">{{ account.id }} - (n°{{account.numero}}, Solde : {{account.credit}})</option>
      </select>
    </div>
    <br>
    <div class="form-group" style="align-items: center;">
      <label for="credit" style="margin-right: 15px">Montant</label>
      <input
          type="number"
          class="form-control"
          id="credit"
          v-model="account.montant"
          name="credit"
          style="background: #FFFFFF"
      />
    </div>
    <button @click="doTransfer" class="btn btn-success" style="margin-top: 20px; padding: 5px; border-radius: 5px; background: #FFFFFF; color: #990000">Effectuer le virement</button>
  </div>
</template>