package com.supplyflow.warehouse.controller;

import com.supplyflow.warehouse.entity.Produit;
import com.supplyflow.warehouse.service.MouvementStockService;
import com.supplyflow.warehouse.service.ProduitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MouvementStockController {

    private final MouvementStockService mouvementStockService;
    private final ProduitService produitService;

    public MouvementStockController(MouvementStockService mouvementStockService,
                                    ProduitService produitService) {
        this.mouvementStockService = mouvementStockService;
        this.produitService = produitService;
    }

    @GetMapping("/mouvements")
    public String listMouvements(Model model) {

        model.addAttribute("mouvements",
                mouvementStockService.getAllMouvements());

        return "mouvements";
    }

    @GetMapping("/mouvements/new")
    public String showForm(Model model) {

        model.addAttribute("produits",
                produitService.getAllProduits());

        return "add-mouvement";
    }

    @PostMapping("/mouvements/entree")
    public String entreeStock(@RequestParam Long produitId,
                              @RequestParam int quantite) {

        mouvementStockService.entreeStock(produitId, quantite);

        return "redirect:/mouvements";
    }

    @PostMapping("/mouvements/sortie")
    public String sortieStock(@RequestParam Long produitId,
                              @RequestParam int quantite) {

        mouvementStockService.sortieStock(produitId, quantite);

        return "redirect:/mouvements";
    }
}