package tn.esprit.rh.achat;

import org.junit.jupiter.api.Test;                                                          
import org.junit.runner.RunWith;                                                         
import org.mockito.InjectMocks;                                                           
import org.springframework.beans.factory.annotation.Autowired;                            
import org.springframework.boot.test.context.SpringBootTest;                              
import org.springframework.boot.test.mock.mockito.MockBean;                               
import org.springframework.core.annotation.Order;                                         
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

//                                                                                            
import static org.junit.Assert.assertEquals;                                              
import static org.junit.Assert.assertNotNull;                                             
import static org.mockito.Mockito.times;                                                  
import static org.mockito.Mockito.verify;                                                 
import static org.mockito.Mockito.when;
//
import java.util.List;
import java.util.Optional;                                                                
import java.util.stream.Collectors;                                                       
import java.util.stream.Stream;                                                           
//                            
import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;
import tn.esprit.rh.achat.services.ISecteurActiviteService;
import tn.esprit.rh.achat.services.SecteurActiviteServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@Order(0)
public class MockSecteurActivite {
	@MockBean
    SecteurActiviteRepository SecteurRepo;
    @InjectMocks
    SecteurActiviteServiceImpl secteurServiceImpl;
    @Autowired
    ISecteurActiviteService SecteurServ;
    
    
                                                            
SecteurActivite s = new SecteurActivite("secteur test","libelle secteur test");

private SecteurActivite secteur1 = new SecteurActivite(1L,"secteur Mock1", "libelle mock1",null);                        
private SecteurActivite secteur2 = new SecteurActivite(2L,"secteur Mock2", "libelle mock2",null); 

@Test                                                                                
public void testAddSecteur() {   
	List<SecteurActivite> SecteurActivites = SecteurServ.retrieveAllSecteurActivite();                         
	int size_expected=SecteurActivites.size();                                                      
	SecteurActivite savedSecteurActivite= SecteurServ.addSecteurActivite(s);                                      
	assertEquals(size_expected+1, SecteurServ.retrieveAllSecteurActivite().size());               
	assertNotNull(savedSecteurActivite.getLibelleSecteurActivite());                                     
	log.info("Add junit test stock works !!");                                       
}                                                                                    
                                                                                           
@Test                                                                                   
public void addStockTestM() { 
	when(SecteurRepo.save(secteur1)).thenReturn(secteur1);       
	assertNotNull(secteur1);                                                              
    assertEquals(secteur1, SecteurServ.addSecteurActivite(secteur1));                                
    log.info("add works !!");                                                           
   }                                                                                       
@Test                                                                                   
public void retrieveAllSecteurActivitesTestM() {                                                  
        when(SecteurRepo.findAll()).thenReturn(Stream                                  
                .of(secteur1,secteur2)                                                          
                .collect(Collectors.toList()));                                             
       assertEquals(2,SecteurServ.retrieveAllSecteurActivite().size());                            
        log.info("Retrieve secteur activite works !");                                                
        }   

@Test
  public void DeleteSecteuractiviteTestM() {
	  SecteurRepo.save(secteur1);
      SecteurServ.deleteSecteurActivite(secteur1.getIdSecteurActivite());
      verify(SecteurRepo, times(1)).deleteById(secteur1.getIdSecteurActivite());
      log.info("Delete works !");
  }
  @Test
  public void UpdateSecteuractiviteTestM() {
      when(SecteurRepo.save(secteur1)).thenReturn(secteur1);
      assertNotNull(secteur1);
      assertEquals(secteur1,SecteurServ.updateSecteurActivite(secteur1));
      log.info("Update works !!");
  }
  @Test
  public void retrieveSecteuractiviteTestM() {
      when(SecteurRepo.findById(secteur1.getIdSecteurActivite())).thenReturn(Optional.of(secteur1));
      assertEquals(secteur1, SecteurServ.retrieveSecteurActivite(secteur1.getIdSecteurActivite()));
      log.info("Retrieve works !!");
 }
}