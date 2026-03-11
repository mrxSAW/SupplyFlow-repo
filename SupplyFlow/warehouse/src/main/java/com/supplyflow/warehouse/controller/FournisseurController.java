package com.supplyflow.warehouse.controller;

import com.supplyflow.warehouse.entity.Fournisseur;
import com.supplyflow.warehouse.service.FournisseurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FournisseurController {

    private final FournisseurService fournisseurService;

    public FournisseurController(FournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }

    @GetMapping("/fournisseurs")
    public String listFournisseurs(Model model) {
        model.addAttribute("fournisseurs",
                fournisseurService.getAllFournisseurs());
        return "fournisseurs";
    }

    @GetMapping("/fournisseurs/new")
    public String showForm(Model model) {
        model.addAttribute("fournisseur", new Fournisseur());
        return "add-fournisseur";
    }

    @PostMapping("/fournisseurs")
    public String saveFournisseur(@ModelAttribute Fournisseur fournisseur) {
        fournisseurService.saveFournisseur(fournisseur);
        return "redirect:/fournisseurs";
    }
}