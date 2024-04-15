<template>
    <nav id="navbar">
      <v-btn elevation="2" to="/" style="color:#990000; background-color: #FFFFFF; margin-right: 10px">
        Accueil
      </v-btn>
      <div @click="$router.go(-1)" style="color:#FFFFFF; text-align: center; align-items: center; align-self: center">
        Revenir à la page précedente
      </div>
      <div style="margin-left: auto; font-weight: bold; color:#FFFFFF; text-align: center; align-items: center; align-self: center">
        BANQUE TOULOUSAINE
      </div>
    </nav>
</template>

<style scoped>
/* Style général de la barre de navigation */
#navbar {
    background: #990000;
    display: flex;
    padding: 10px;
    font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
}

/* Style des liens de la navbar */
nav a, nav button {
    padding: 10px 15px;
    color: #fff;
    text-transform: uppercase;
    transition: background-color 0.3s ease;
}

/* Style pour le bouton de déconnexion */
nav button {
    background: none;
    border: none;
    cursor: pointer;
}

/* Ajoutez des media queries si nécessaire pour gérer le responsive design de la navbar */
@media (max-width: 768px) {
    #navbar {
        flex-direction: column;
    }
}
</style>
<script setup>
</script>