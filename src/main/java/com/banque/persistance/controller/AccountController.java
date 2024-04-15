package com.banque.persistance.controller;

import com.banque.persistance.model.Client;
import com.banque.persistance.model.Account;
import com.banque.persistance.service.AccountService;
import com.banque.persistance.service.ClientService;
import com.banque.persistance.service.VirementRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/compte")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private ClientService clientService;

    @GetMapping("/lister")
    public List<Account> listAccounts(){
        return (List<Account>) accountService.getAccounts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Integer id) {
        Optional<Account> account = accountService.getAccount(id);
        return account.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Integer id, @RequestBody Account updatedAccount) {
        Optional<Account> optionalAccount = accountService.getAccount(id);
        if (optionalAccount.isPresent()) {
            Account c = optionalAccount.get();
            c.setNumero(updatedAccount.getNumero());
            c.setCredit(updatedAccount.getCredit());
            c.setClient(c.getClient());
            c.setCredit(updatedAccount.getCredit());
            accountService.saveAccount(c);
            return ResponseEntity.ok(c);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Integer id) {
        Optional<Account> account = accountService.getAccount(id);
        if (account.isPresent()) {
            accountService.deleteAccount(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/creer")
    public Account account(@RequestBody Account account){
        Account c = new Account();
        c.setNumero(account.getNumero());
        c.setClient(account.getClient());
        c.setCredit(account.getCredit());
        return accountService.saveAccount(c);
    }

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.saveAccount(account);
    }

    @PostMapping("/virement")
    public ResponseEntity<?> virement(@RequestBody VirementRequest virementRequest) {
        Integer expediteurId = virementRequest.getExpediteur();
        Integer destinataireId = virementRequest.getDestinataire();
        BigDecimal montant = null;
        if (virementRequest.getMontant() != null) {
            montant = BigDecimal.valueOf(virementRequest.getMontant());
        } else {
            return ResponseEntity.badRequest().body("Le montant du virement est nul.");
        }

        Optional<Account> optionalExpediteur = accountService.getAccount(expediteurId);
        Optional<Account> optionalDestinataire = accountService.getAccount(destinataireId);

        if (optionalExpediteur.isEmpty() || optionalDestinataire.isEmpty()) {
            return ResponseEntity.badRequest().body("Compte expéditeur ou destinataire introuvable.");
        }

        Account expediteur = optionalExpediteur.get();
        Account destinataire = optionalDestinataire.get();

        BigDecimal soldeExpediteur = expediteur.getCredit();
        if (soldeExpediteur.compareTo(montant) < 0) {
            return ResponseEntity.badRequest().body("Solde insuffisant pour effectuer le virement.");
        }

        BigDecimal nouveauSoldeExpediteur = soldeExpediteur.subtract(montant);
        BigDecimal nouveauSoldeDestinataire = destinataire.getCredit().add(montant);

        expediteur.setCredit(nouveauSoldeExpediteur);
        destinataire.setCredit(nouveauSoldeDestinataire);

        accountService.saveAccount(expediteur);
        accountService.saveAccount(destinataire);

        return ResponseEntity.ok("Virement effectué avec succès.");
    }
}

