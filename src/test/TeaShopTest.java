import org.junit.jupiter.api.Test;
package fr.anthonyquere.teashop;

@Test

//test qui montre que le constructeur initialise correctement tous les attributs d'un objet Tea.
    public void testConstructor_ValidParameters_CreatesTeaObject() {
        Tea tea = new Tea("Matcha", 180, 95, true);
        
        assertEquals("Matcha", tea.getName());
        assertEquals(180, tea.getSteepingTimeSeconds());
        assertEquals(95, tea.getIdealTemperatureCelsius());
        assertTrue(tea.isLoose());
    }


@Test
//Test qui montre que la méthode setName() modifie correctement le nom du thé.
    public void testSetName_NewName_NameIsUpdated() {
        
        Tea tea = new Tea("Irish cofee", 120, 80, false);
        
        tea.setName("Matcha");
        
        assertEquals("Matcha", tea.getName());
    }

@Test
//Test qui montre que la méthode setSteepingTimeSeconds() modifie correctement le temps d'infusion du thé.
    public void testSetSteepingTimeSeconds_NewTime_TimeIsUpdated() {
        
        Tea tea = new Tea("Italian cofee", 120, 80, false);
        
        tea.setSteepingTimeSeconds(180);
        
        assertEquals(180, tea.getSteepingTimeSeconds());
    }

@Test
//Test qui montre que la méthode setIdealTemperatureCelsius() modifie correctement la température idéale

    public void test_SetIdealTemperatureCelsius_NewTemperature_TemperatureIsUpdated() {
        
        Tea tea = new Tea("Expresso", 120, 80, false);
        
        tea.setIdealTemperatureCelsius(95);

        assertEquals(95, tea.getIdealTemperatureCelsius());
    }

@Test
//Test qui montre que la méthode setLoose() modifie correctement le type de thé.
    public void testSetLoose_NewType_TypeIsUpdated() {
        
        Tea tea = new Tea("Expresso", 120, 80, false);
        
        tea.setLoose(true);
        
        assertTrue(tea.isLoose());
    }

    

