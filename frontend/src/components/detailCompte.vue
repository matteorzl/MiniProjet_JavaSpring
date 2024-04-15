<script>
import axios from "axios";

export default {
  data(){
    return{
      accountInfos: [],
      otherIds: []
    }
  },
  methods: {
    getOtherId(){
      axios.get(`http://localhost:8080/api/client/lister`)
        .then(response => {
          this.otherIds = response.data;
        })
        .catch(error => {
          console.error("Erreur lors de la récupération des messages reçus: ", error);
        });
    },
    getAccountInfo(){
      var url = window.location.pathname;
      const id = url.substring(url.lastIndexOf('/') + 1);
      axios.get(`http://localhost:8080/api/compte/` + id)
        .then(response => {
          this.accountInfos = response.data;
        })
        .catch(error => {
          console.error("Erreur lors de la récupération des messages reçus: ", error);
        });
    },
    updateAccount() {
      console.log(this.accountInfos)
      axios.put(`http://localhost:8080/api/compte/${this.accountInfos.id}`, {
        numero: this.accountInfos.numero,
        id_client: this.accountInfos.id_client,
        credit: this.accountInfos.credit
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
    this.getAccountInfo();
    this.getOtherId()
  }
};
</script>

<template>
  <div class="submit-form" style="width: 30%; height: 300px; padding-top: 20px;margin: 20% 35%;border-radius: 10px; justify-content: center;background-color : #990000; color: #FFFFFF">
    <div>
      <div style="font-weight: bold; margin-bottom: 20px">
        Modification d'un compte
      </div>
      <div class="form-group" style="align-items: center;">
        <label for="numero" style="margin-right: 15px">Numéro</label>
        <input
            type="text"
            class="form-control"
            id="numero"
            required
            v-model="accountInfos.numero"
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
            v-model="accountInfos.id_client"
            name="id_client"
            style="background: #FFFFFF; width: 163px"
        >
          <option disabled selected value="">{{ accountInfos.id_client }}</option>
          <option v-for="otherId in otherIds" :key="otherId.id" :value="otherId.id">{{ otherId.id }}</option>
        </select>
      </div>
      <br>
      <div class="form-group" style="align-items: center;">
        <label for="credit" style="margin-right: 15px">Crédit</label>
        <input
            type="number"
            class="form-control"
            id="credit"
            v-model="accountInfos.credit"
            name="credit"
            style="background: #FFFFFF"
        />
      </div>
    </div>
    <v-btn @click="updateAccount" to="/compte/lister" class="btn btn-success" style="margin-top: 20px; background: #FFFFFF; color: #990000">
      Modifier
    </v-btn>
  </div>
</template>