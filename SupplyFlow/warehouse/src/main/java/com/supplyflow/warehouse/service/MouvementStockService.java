package com.supplyflow.warehouse.service;

import com.supplyflow.warehouse.entity.MouvementStock;
import com.supplyflow.warehouse.entity.Produit;
import com.supplyflow.warehouse.repository.MouvementStockRepository;
import com.supplyflow.warehouse.repository.ProduitRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MouvementStockService {

    private final MouvementStockRepository mouvementStockRepository;
    private final ProduitRepository produitRepository;

    public MouvementStockService(MouvementStockRepository mouvementStockRepository,
                                 ProduitRepository produitRepository) {
        this.mouvementStockRepository = mouvementStockRepository;
        this.produitRepository = produitRepository;
    }

    public List<MouvementStock> getAllMouvements() {
        return mouvementStockRepository.findAll();
    }

    public void entreeStock(Long produitId, int quantite) {

        Produit produit = produitRepository.findById(produitId).orElseThrow();

        produit.setQuantite(produit.getQuantite() + quantite);

        MouvementStock mouvement = new MouvementStock();
        mouvement.setProduit(produit);
        mouvement.setQuantite(quantite);
        mouvement.setType("ENTREE");
        mouvement.setDate(LocalDate.now());

        produitRepository.save(produit);
        mouvementStockRepository.save(mouvement);
    }

    public void sortieStock(Long produitId, int quantite) {

        Produit produit = produitRepository.findById(produitId).orElseThrow();

        produit.setQuantite(produit.getQuantite() - quantite);

        MouvementStock mouvement = new MouvementStock();
        mouvement.setProduit(produit);
        mouvement.setQuantite(quantite);
        mouvement.setType("SORTIE");
        mouvement.setDate(LocalDate.now());

        produitRepository.save(produit);
        mouvementStockRepository.save(mouvement);
    }
}