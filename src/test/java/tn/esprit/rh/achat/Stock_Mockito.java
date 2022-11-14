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
import tn.esprit.rh.achat.entities.Stock;                                                 
import tn.esprit.rh.achat.repositories.StockRepository;                                   
import tn.esprit.rh.achat.services.IStockService;                                         
import tn.esprit.rh.achat.services.StockServiceImpl;                                      
                                                                                              
import static org.junit.Assert.assertEquals;                                              
import static org.junit.Assert.assertNotNull;                                             
import static org.mockito.Mockito.times;                                                  
import static org.mockito.Mockito.verify;                                                 
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;                                                                
import java.util.stream.Collectors;                                                       
import java.util.stream.Stream;                                                           
                                                                                              
  @RunWith(SpringRunner.class)                                                              
  @SpringBootTest                                                                           
  @Slf4j                                                                                    
  @Order(0)                                                                                 
   public class Stock_Mockito  {                                                           
    @MockBean                                                                               
    StockRepository stockRepository;                                                        
    @InjectMocks                                                                            
    StockServiceImpl stockServiceImpl;                                                      
    @Autowired                                                                              
IStockService stockService;                                                             
Stock s = new Stock("stock test",100,1);                                             
private Stock stock1 = new Stock(1L,"stock Mock1", 100, 1,null);                        
private Stock stock2 = new Stock(2L,"stock Mock2", 200, 2,null);                        
 @Test                                                                                
public void testAddStock() {                                                         
List<Stock> stocks = stockService.retrieveAllStocks();                           
int expected=stocks.size();                                                      
Stock savedStock= stockService.addStock(s);                                      
assertEquals(expected+1, stockService.retrieveAllStocks().size());               
assertNotNull(savedStock.getLibelleStock());                                     
 log.info("Add junit test stock works !!");                                       
 }                                                                                    
                                                                                              
@Test                                                                                   
public void addStockTestM() {                                                           
when(stockRepository.save(stock1)).thenReturn(stock1);                              
        assertNotNull(stock1);                                                              
        assertEquals(stock1, stockService.addStock(stock1));                                
        log.info("add works !!");                                                           
    }                                                                                       
    @Test                                                                                   
    public void retrieveAllStocksTestM() {                                                  
        when(stockRepository.findAll()).thenReturn(Stream                                   
                .of(stock1,stock2)                                                          
                .collect(Collectors.toList()));                                             
        assertEquals(2,stockService.retrieveAllStocks().size());                            
        log.info("Retrieve stocks works !");                                                
    }   
    
  
  @Test
  public void DeleteStockTestM() {
      stockRepository.save(stock1);
      stockService.deleteStock(stock1.getIdStock());
      verify(stockRepository, times(1)).deleteById(stock1.getIdStock());
      log.info("Delete works !");
  }
  @Test
  public void UpdateStockTestM() {
      when(stockRepository.save(stock1)).thenReturn(stock1);
      assertNotNull(stock1);
      assertEquals(stock1, stockService.updateStock(stock1));
      log.info("Update works !!");
  }
  @Test
  public void retrieveStockTestM() {
      when(stockRepository.findById(stock1.getIdStock())).thenReturn(Optional.of(stock1));
      assertEquals(stock1, stockService.retrieveStock(stock1.getIdStock()));
      log.info("Retrieve works !!");
  }
  }
                                                                                  