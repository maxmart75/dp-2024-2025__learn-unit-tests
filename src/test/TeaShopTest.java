package fr.anthonyquere.teashop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TeaShopTests {

    public static class TeaTest {
        @Test
        // test qui montre si le constructeur initialise bien les attribut
        public void testConstructor_ValidParameters_CreatesTeaObject() {
            
            Tea tea = new Tea("Green Tea", 180, 95, true);
            
            assertEquals("Green Tea", tea.getName());
            assertEquals(180, tea.getSteepingTimeSeconds());
            assertEquals(95, tea.getIdealTemperatureCelsius());
            assertTrue(tea.isLoose());
        }
    }
    
    public static class TeaCupTest {
        private TeaCup teaCup;
        
        public void setUp() {
            teaCup = new TeaCup();
        }
        
        @Test
        // test qui montre si l'ajout d'eau change l'état de la tasse
        public void testAddWater_ValidTemperature_SetsTemperature() {
            
            teaCup.addWater(85);
            
            assertFalse(teaCup.isEmpty());
        }
        
        @Test
        // test si une erreur est lancé quand on ajoute du thé sans eau
        public void testAddTea_EmptyCup_ThrowsException() {
            
            Tea tea = new Tea("Matcha", 240, 90, false);
            
            assertThrows(IllegalStateException.class, () -> teaCup.addTea(tea));
        }
        
        @Test
        // test si on peut ajouter a une tasse qui contient de l'eau
        public void testAddTea_NonEmptyCup_AddsTea() {
            
            teaCup.addWater(90);
            Tea tea = new Tea("Matcha", 240, 90, false);
            
            assertDoesNotThrow(() -> teaCup.addTea(tea));
        }
        
        @Test
        // test qui retourne une erreur si une tasse n'a pas de thé
        public void testIsReadyToDrink_NoTea_ReturnsFalse() {
            
            teaCup.addWater(90);
            
            assertFalse(teaCup.isReadyToDrink());
        }
        
        
        @Test
        // test qui retourne true si les condition sont bonnes
        public void testIsReadyToDrink_SteepingTimeComplete_IdealTemperature_ReturnsTrue() {
            TeaCup spyTeaCup = Mockito.spy(teaCup);
            doReturn(1000).when(spyTeaCup).getCurrentTimeInSeconds();
            
            spyTeaCup.addWater(90);
            
            Tea tea = new Tea("Matcha", 240, 90, false);
            spyTeaCup.addTea(tea);
            
            doReturn(1241).when(spyTeaCup).getCurrentTimeInSeconds();
            
            
            assertTrue(spyTeaCup.isReadyToDrink());
        }
        
        @Test
        //test qui retourne false si la température est trop basse
        public void testIsReadyToDrink_SteepingTimeComplete_TemperatureTooLow_ReturnsFalse() {

            TeaCup spyTeaCup = Mockito.spy(teaCup);
            doReturn(1000).when(spyTeaCup).getCurrentTimeInSeconds();
            
            spyTeaCup.addWater(80); 
            
            Tea tea = new Tea("Matcha", 240, 90, false);
            spyTeaCup.addTea(tea);
            
            doReturn(1241).when(spyTeaCup).getCurrentTimeInSeconds();
            
            assertFalse(spyTeaCup.isReadyToDrink());
        }
        
        @Test
        public void testIsReadyToDrink_SteepingTimeComplete_TemperatureWithinRange_ReturnsTrue() {

            TeaCup spyTeaCup = Mockito.spy(teaCup);
            doReturn(1000).when(spyTeaCup).getCurrentTimeInSeconds();
            
            spyTeaCup.addWater(93); 
            
            Tea tea = new Tea("Matcha", 240, 90, false);
            spyTeaCup.addTea(tea);
            
            doReturn(1241).when(spyTeaCup).getCurrentTimeInSeconds();
            
            assertTrue(spyTeaCup.isReadyToDrink());
        }
    }
  
    public static class TeaShopTest {
        private TeaShop teaShop;
        private Tea blackTea;
        private Tea greenTea;
        
        public void setUp() {
            teaShop = new TeaShop(90);
            blackTea = new Tea("Matcha", 240, 90, false);
            greenTea = new Tea("Irish cofee", 120, 80, true);
        }
        
        @Test
        // test qui verifie si le thé est correctement ajouter à la boutique
        public void testAddTea_ValidTea_TeaAvailableForPreparation() {

            teaShop.addTea(blackTea);
            
            TeaCup cup = teaShop.prepareTea("Matcha");
            assertNotNull(cup);
        }
        
        @Test
        //test qui verifie que la tasse est non null si le thé est disponible
        public void testPrepareTea_TeaAvailable_ReturnsCupWithTea() {
            
            teaShop.addTea(blackTea);
            
            TeaCup cup = teaShop.prepareTea("Matcha");
            
            assertNotNull(cup);
        }
        
        @Test
        // verifie si une erreur apparait en cas de non disponibilité du thé
        public void testPrepareTea_TeaNotAvailable_ThrowsException() {
            
            assertThrows(IllegalArgumentException.class, () -> teaShop.prepareTea("Green Tea"));
        }
        
        @Test
        // test qui verifie si la fonction prepareTea fonctionne avec plusieurs thé 
        public void testPrepareTea_MultipleTeaOptions_ReturnsCorrectTea() {
           
            teaShop.addTea(blackTea);
            teaShop.addTea(greenTea);
            
            
            TeaCup cup = teaShop.prepareTea("Irish cofee");
            
            
            assertNotNull(cup);
        }
        
        @Test
        // test qui verifie si la temperature change bien
        public void testSetWaterTemperature_ValidTemperature_TemperatureIsSet() {
            
            teaShop.setWaterTemperature(85);
            
            teaShop.addTea(greenTea);
            assertDoesNotThrow(() -> teaShop.prepareTea("Irish cofee"));
        }
        
        @Test
        // test qui affiche une erreur si la temperature est négative
        public void testSetWaterTemperature_TooLow_ThrowsException() {
            
            assertThrows(IllegalArgumentException.class, () -> teaShop.setWaterTemperature(-5));
        }
        
        @Test
        // test qui retourne une erreur si la temperature depasse 100
        public void testSetWaterTemperature_TooHigh_ThrowsException() {
            
            assertThrows(IllegalArgumentException.class, () -> teaShop.setWaterTemperature(101));
        }
        
        @Test
        // test qui verfie sur les valer de temperature sunt respecter
        public void testSetWaterTemperature_EdgeCases_AcceptsValues() {
            
            assertDoesNotThrow(() -> teaShop.setWaterTemperature(0));
            assertDoesNotThrow(() -> teaShop.setWaterTemperature(100));
        }
    }
}


    

