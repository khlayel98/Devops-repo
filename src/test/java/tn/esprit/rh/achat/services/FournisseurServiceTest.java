package tn.esprit.rh.achat.services;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.rh.achat.entities.Fournisseur;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class FournisseurServiceTest {
	@Autowired
	IFournisseurService fournisseurService;
	
	
	@Order(1)
	@Test
	public void testAddFournisseur() {
		Fournisseur f=new Fournisseur();
		f.setCode("123");
		f.setLibelle("Fournisseur1");
		Fournisseur savedFournisseur=fournisseurService.addFournisseur(f);
		
		assertNotNull(fournisseurService.retrieveFournisseur(savedFournisseur.getIdFournisseur()));
		assertNotNull(savedFournisseur.getCode());
		assertNotNull(savedFournisseur.getLibelle());
		fournisseurService.deleteFournisseur(savedFournisseur.getIdFournisseur());
		
	}
	
	@Order(2)
	@Test
	public void testDeleteFournisseur() {
		Fournisseur f=new Fournisseur();
		f.setCode("123");
		f.setLibelle("Fournisseur1");
		Fournisseur savedFournisseur=fournisseurService.addFournisseur(f);
		fournisseurService.deleteFournisseur(savedFournisseur.getIdFournisseur());
		assertNull(fournisseurService.retrieveFournisseur(savedFournisseur.getIdFournisseur()));
	}
	
	@Order(3)
	@Test
	public void testRetriveAllFournisseur() {
		
		Fournisseur f=new Fournisseur();
		Fournisseur f1=new Fournisseur();
		f.setCode("123");
		f.setLibelle("Fournisseur1");
		fournisseurService.addFournisseur(f);
		
		f1.setCode("456");
		f1.setLibelle("Fournisseur2");
		fournisseurService.addFournisseur(f1);
		
		
		List<Fournisseur> fournisseurs=fournisseurService.retrieveAllFournisseurs();
		 assertNotNull( fournisseurs.size());
	}
	
	
	

}
