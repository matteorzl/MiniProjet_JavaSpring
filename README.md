## Project Setup

### Frontend

Installation des dépendances
```sh
cd frontend
npm install
```

Lancement de l'application frontend
```sh
npm run serve
```

### Backend

Configuration (si nécessaire)
```sh
%PROJECT%/src/main/resources/application.properties
 - spring.datasource.url=jdbc:mysql://localhost:3306/projetbanque
 - spring.datasource.username=root
 - spring.datasource.password=
```

Lancement de l'application backend
```sh
%PROJECT%/src/main/java/com.banque.persistance/BanquePersistanceApplication
> Launch
```
