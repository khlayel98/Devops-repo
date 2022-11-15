package tn.esprit.rh.achat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import java.util.List;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
//import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import lombok.extern.slf4j.Slf4j;
import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.services.ISecteurActiviteService;

//@TestMethodOrder(OrderAnnotation.class)
@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class SecteurActiviteTest {

@Autowired
ISecteurActiviteService iSecteuractiviteService;
	
@Order(0)
@Test
public void testAddSecteur() {

	List<SecteurActivite> secteursactivite = iSecteuractiviteService.retrieveAllSecteurActivite();
		int expected=secteursactivite.size();
		SecteurActivite s = new SecteurActivite();
		s.setCodeSecteurActivite("codesecteur");
		s.setLibelleSecteurActivite("libellesecteur");
		SecteurActivite savedSecteur= iSecteuractiviteService.addSecteurActivite(s);
		assertEquals(expected+1, iSecteuractiviteService.retrieveAllSecteurActivite());
		assertNotNull(savedSecteur.getLibelleSecteurActivite());
} 
	
//@Order(1)
@Test
public void testDeleteSecteur()
	{
		iSecteuractiviteService.deleteSecteurActivite(4L);
		assertNull(iSecteuractiviteService.retrieveSecteurActivite(4L));
	}
	@Order(2)
	@Test
	public void testRetrieveAllSecteursactivite()
	{
		List<SecteurActivite> secteursactivite = iSecteuractiviteService.retrieveAllSecteurActivite();
		assertEquals(4,secteursactivite.size());
	}
	@Order(3)
	@Test
	public void testRetrieveSecteuractivite()
	{
		SecteurActivite s = iSecteuractiviteService.retrieveSecteurActivite(6L);
		assertEquals(6L,s.getIdSecteurActivite().longValue());
		
	}
	@Order(4)
	@Test
	public void testUpdateSecteuractivite()
	{
		SecteurActivite s = new SecteurActivite();
		s.setIdSecteurActivite(7L);
		s.setLibelleSecteurActivite("libelle secteur test");
		s.setCodeSecteurActivite("code secteur test");
		SecteurActivite updatedSecteurActivite=iSecteuractiviteService.updateSecteurActivite(s);
		assertEquals(s.getLibelleSecteurActivite(),updatedSecteurActivite.getLibelleSecteurActivite());
	}
	
	@Order(5)
	@Test
	public void retrieveSecteuractivite()
	{
		log.info("it works !!");
	}

}
