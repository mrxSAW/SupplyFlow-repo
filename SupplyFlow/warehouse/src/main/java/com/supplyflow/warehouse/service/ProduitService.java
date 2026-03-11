package com.supplyflow.warehouse.service;
import com.supplyflow.warehouse.entity.Produit;
import com.supplyflow.warehouse.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;

    public List<Produit> getAllProduits(){
        return produitRepository.findAll();
    }

    public Produit saveProduit(Produit produit){
        return produitRepository.save(produit);
    }

    public void deleteProduit(Long id){
        produitRepository.deleteById(id);
    }

    public List<Produit> searchProduits(String nom){
        return produitRepository.findByNomContaining(nom);
    }

    public long countProduits(){
        return produitRepository.count();
    }

}