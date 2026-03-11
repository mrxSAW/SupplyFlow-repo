package com.supplyflow.warehouse.controller;

import com.supplyflow.warehouse.entity.Produit;
import com.supplyflow.warehouse.service.ProduitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProduitController {

    private final ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @GetMapping("/produits")
    public String listProduits(Model model) {

        model.addAttribute("produits", produitService.getAllProduits());

        model.addAttribute("totalProduits",
                produitService.countProduits());

        return "produits";
    }

    @GetMapping("/produits/new")
    public String showForm(Model model) {

        model.addAttribute("produit", new Produit());

        return "add-produit";
    }

    @PostMapping("/produits")
    public String saveProduit(@ModelAttribute Produit produit) {

        produitService.saveProduit(produit);

        return "redirect:/produits";
    }

    @GetMapping("/produits/delete/{id}")
    public String deleteProduit(@PathVariable Long id) {

        produitService.deleteProduit(id);

        return "redirect:/produits";
    }

    @GetMapping("/produits/search")
    public String searchProduit(@RequestParam String nom, Model model){

        model.addAttribute("produits",
                produitService.searchProduits(nom));

        model.addAttribute("totalProduits",
                produitService.countProduits());

        return "produits";
    }
}