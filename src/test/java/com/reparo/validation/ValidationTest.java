package com.reparo.validation;

import com.reparo.dto.user.UserRequestDto;
import com.reparo.exception.ValidationException;

import com.reparo.model.User;
import com.reparo.model.Vehicle;
import com.reparo.model.Workshop;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

 class ValidationTest {

    private final Validation validate = new Validation() ;


    @Test
    void stringValidationTest(){
        try {
           assertTrue(Validation.stringValidation("Abdul razak","name",15));
        } catch (ValidationException e) {
            fail();
            throw new RuntimeException(e);
        }

    }
    @Test
    void stringValidationPatternFailTest(){
        ValidationException exception = assertThrows(ValidationException.class, () -> Validation.stringValidation("Abdul 3a2ak","name",15));

        assertEquals("name can only contain alphabetic characters", exception.getMessage());

    }
    @Test
    void stringValidationLengthFailTest(){
        ValidationException exception = assertThrows(ValidationException.class, () -> Validation.stringValidation("Abdul razak","name",7));

        assertEquals("name can't be more than 7", exception.getMessage());

    }
    @Test
    void numberValidationTest(){
        try {
           assertTrue(Validation.numberValidation(8124311602L));
        } catch (ValidationException e) {
            fail();
            throw new RuntimeException(e);
        }

    }
    @Test
    void numberValidationFailTest(){
        ValidationException exception = assertThrows(ValidationException.class, () -> Validation.numberValidation(81243116023L));

        assertEquals("Number should not be more or less than 10 digits", exception.getMessage());

    }
    @Test
    void testPasswordValidation(){

        try {
           assertTrue(Validation.passWordValidation("abd123"));
        }catch(ValidationException e){
            fail();
            throw new RuntimeException(e);
        }

    }
    @Test
    void testInvalidPasswordValidation(){
        ValidationException exception = assertThrows(ValidationException.class, () -> Validation.passWordValidation("123456"));

        assertEquals("Password should be in AlphaNumeric characters. Example: abc123", exception.getMessage());

    }
    @Test
    void dateValidationTest(){
        try {
            assertTrue(Validation.dateValidation("22-07-2023"));
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void invalidDateValidation(){
        ValidationException exception = assertThrows(ValidationException.class, () -> Validation.dateValidation("223-07-2023"));

        assertEquals("Invalid Date Format", exception.getMessage());

    }
     @Test
     void timeValidationTest(){
         try {
             assertTrue(Validation.timeValidation("12:45"));
         } catch (ValidationException e) {
             throw new RuntimeException(e);
         }
     }
     @Test
   void invalidTimeValidation(){
         ValidationException exception = assertThrows(ValidationException.class, () -> Validation.timeValidation("25:10"));

         assertEquals("Invalid time Format", exception.getMessage());

     }
    @Test
    void addressValidation(){
        String address = "123  Main Street";
        try {
            assertTrue(Validation.addressValidation(address));

        }catch(ValidationException e){
           fail();
        }

    }
    @Test
    void invalidAddressValidation(){
        ValidationException exception = assertThrows(ValidationException.class, () -> Validation.addressValidation("123@4990?456"));

        assertEquals("Address should not contain  some special characters", exception.getMessage());


    }
    @Test
    void vehicleNumberValidationTest(){
        try {
            assertTrue(Validation.vehicleNumberValidation("TN09AB2343"));

        }catch(ValidationException e){
            fail();
        }

    }
    @Test
    void invalidVehicleNumberValidationTest(){
        ValidationException exception = assertThrows(ValidationException.class, () -> Validation.vehicleNumberValidation("1209AB2343"));

        assertEquals("Vehicle number should be Alphanumeric characters", exception.getMessage());


    }
    @Test
    void priceValidation(){
        try {
            assertTrue(Validation.priceValidation(800));

        }catch(ValidationException e){
            fail();
        }

    }
    @Test
    void invalidPriceValidation(){
        ValidationException exception = assertThrows(ValidationException.class, () -> Validation.priceValidation(80000));

        assertEquals("price can't be more than 4 digits", exception.getMessage());


    }
    @Test
    void yearValidation(){
        try {
            assertTrue(Validation.vehicleYearValidation(2020));

        }catch(ValidationException e){
            fail();
        }

    }
    @Test
    void invalidYearValidation(){
        ValidationException exception = assertThrows(ValidationException.class, () -> Validation.vehicleYearValidation(2024));

        assertEquals("Year Can't be in future", exception.getMessage());


    }
    @Test
    void userCredentialValidation(){
        try {
            User user = new User();
            user.setName("abdul");
            user.setNumber(8124311602L);
            user.setPassword("abd123");

            assertTrue(Validation.userCredentialValidation(user));

        }catch(ValidationException e){
            fail();
        }

    }
    @Test
    void invalidUserValidation(){
        User user = new User();
        user.setName("abdul");
        user.setNumber(81243116L);
        user.setPassword("abd123");
        ValidationException exception = assertThrows(ValidationException.class, () -> Validation.userCredentialValidation(user));

        assertEquals("Number should not be more or less than 10 digits", exception.getMessage());


    }
    @Test
    void loginCredentialTest(){
        try {
            UserRequestDto dto  =  new UserRequestDto();
            dto.setNumber(9840326198L);
            dto.setPassword("abd123");
            assertTrue(Validation.loginCredentialValidation(dto));
        } catch (ValidationException e) {
            fail();
            throw new RuntimeException(e);
        }

    }
    @Test
    void invalidLoginCredential(){
        UserRequestDto dto  =  new UserRequestDto();
        dto.setNumber(98403261L);
        dto.setPassword("abd123");
        ValidationException exception = assertThrows(ValidationException.class, () -> Validation.loginCredentialValidation(dto));

        assertEquals("Number should not be more or less than 10 digits", exception.getMessage());


    }
    @Test
    void workshopValidation(){
        try {
            Workshop workshop =  new Workshop() ;
            workshop.setWorkShopName("Auto Mobiles");
            workshop.setCity("chennai");
            workshop.setCountry("india");
            workshop.setState("tamil Nadu");
            workshop.setAddress("no 3 cross Street");
            workshop.setType(2);
            workshop.setElectricalPrice(3000);
            workshop.setGeneralPrice(2000);
            workshop.setSuspensionPrice(400);
            workshop.setEnginePrice(400);
            workshop.setOpenTime("12:30");
            workshop.setCloseTime("18.30");
            workshop.setLatitude(45.67);
            workshop.setLongitude(180.0);
            assertTrue(Validation.workshopValidation(workshop));
        } catch (ValidationException e) {
            fail();
            throw new RuntimeException(e);
        }


    }
    @Test
    void invalidWorkshopCredential(){
        Workshop workshop = new Workshop();
        workshop.setWorkShopName("123 auto");
        ValidationException exception = assertThrows(ValidationException.class, () -> Validation.workshopValidation(workshop));

        assertEquals("workshopName can only contain alphabetic characters", exception.getMessage());


    }
    @Test
    void vehicleCredentialValidation(){
        try {
            Vehicle vehicle = new Vehicle();
            vehicle.setCompany("hero");
            vehicle.setModel("splendor");
            vehicle.setVehicleNumber("TN09AB3232");
            vehicle.setType(2);
            vehicle.setYear(2022);
            assertTrue(Validation.vehicleCredentialValidation(vehicle));
        } catch (ValidationException e) {
            fail();
            throw new RuntimeException(e);
        }
    }
    @Test
    void invalidVehicleCredential(){
        Vehicle vehicle = new Vehicle();

        vehicle.setCompany("123 company");

        ValidationException exception = assertThrows(ValidationException.class, () -> Validation.vehicleCredentialValidation(vehicle));

        assertEquals("company can only contain alphabetic characters", exception.getMessage());



    }








    @Test
    void latitudeValidation(){
       assertTrue(Validation.isValidLatitude(45.678));
    }
    @Test
    void longitudeValidation(){
        assertTrue(Validation.isValidLongitude(180.0));
    }
    @Test
    void latitudeInValidation(){
        assertFalse(Validation.isValidLatitude(-91.0));
    }
    @Test
    void longitudeInValidation(){
        assertFalse(Validation.isValidLongitude(200.0));
    }
    @Test
    void doesNotAlphabet(){
        assertTrue(Validation.doesNotContainAlphabets("12345"));
    }
    @Test
    void doesAlphabet(){
        assertFalse(Validation.doesNotContainAlphabets("Hello123"));
    }


}
