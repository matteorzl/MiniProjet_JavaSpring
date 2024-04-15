package com.banque.persistance.controller;

import com.banque.persistance.model.Client;
import com.banque.persistance.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api/client")
@RestController
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/lister")
    public List<Client> listeClients(){
        List<Client> clients = (List<Client>) clientService.getClients();
        return clients;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> detailClient(@PathVariable Integer id) {
        Optional<Client> client = clientService.getClient(id);
        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Integer id, @RequestBody Client updatedClient) {
        Optional<Client> optionalClient = clientService.getClient(id);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            client.setNom(updatedClient.getNom());
            client.setPrenom(updatedClient.getPrenom());
            clientService.saveClient(client);
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Integer id) {
        Optional<Client> client = clientService.getClient(id);
        if (client.isPresent()) {
            clientService.deleteClient(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/creer")
    public ModelAndView submit(@RequestBody Client client, ModelMap model){
        model.addAttribute("nom", client.getNom());
        model.addAttribute("prenom", client.getPrenom());
        clientService.saveClient(client);

        return new ModelAndView("redirect:/api/client/creer"); // Redirection vers une URL qui affiche la liste des clients
    }
}
