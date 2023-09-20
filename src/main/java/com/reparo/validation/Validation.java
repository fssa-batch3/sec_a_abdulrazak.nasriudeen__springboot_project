package com.reparo.validation;
import com.reparo.dto.user.UserRequestDto;
import com.reparo.exception.ValidationException;
import com.reparo.model.*;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    private static  final String STRING_REGEX = "^[A-Za-z\\s]+$";
    private static  final String PASS = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,10}$";
    private static  final String ADDRESS_PATTERN = "^[a-zA-Z0-9\\s.,'#\\-]+(\\s[A-Za-z0-9\\-#]+)?$";
    private static final String VEHICLE_NUMBER_PATTERN = "^[A-Z]{2}\\d{2}[A-Z]{2}\\d{4}$";
    private static  final String DATE_PATTERN = "\\d{4}-\\d{2}-\\d{2}";
    private static final String TIME_PATTERN = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
    public boolean stringValidation(String str, String name, int n) throws ValidationException {
        // Define a regular expression pattern for validating the string
        Pattern pat = Pattern.compile(STRING_REGEX);

        // Check if the input string is empty, and if so, throw a ValidationException
        if (str.trim().isEmpty()) {
            throw new ValidationException(name + " can't be empty");
        }

        // Check if the length of the input string is greater than the specified maximum length (n),
        // and if so, throw a ValidationException
        if (str.length() > n) {
            throw new ValidationException(name + " can't be more than " + n);
        }

        // Create a Matcher object to check if the input string matches the defined regular expression
        Matcher match = pat.matcher(str);

        // Check if the input string matches the regular expression,
        // and if not, throw a ValidationException
        if (!match.matches()) {
            throw new ValidationException(name + " can only contain alphabetic characters");
        }
        return match.matches();
    }

    public boolean numberValidation(long number) throws ValidationException {
        // Convert the long number to a string for validation
        String str = Long.toString(number);

        // Check if the length of the converted string is greater than 10 digits
        if (str.length() != 10) {
            throw new ValidationException("Number should not be more or less than 10 digits");
        }
        return true;

    }

    public boolean passWordValidation(String s) throws ValidationException {
        Matcher match;

        // Define a regular expression pattern for password validation (PASS)
        Pattern pt = Pattern.compile(PASS);

        // Create a Matcher object to check if the input string s matches the password pattern
        match = pt.matcher(s);

        // Check if the input string s matches the password pattern,
        // and if not, throw a ValidationException
        if (!match.matches()) {
            throw new ValidationException("Password should be in AlphaNumeric characters. Example: abc123");
        }

        // Return whether the input string s matches the password pattern (true if it matches, false otherwise)
        return match.matches();
    }
    public  boolean addressValidation(String address) throws ValidationException {
        Matcher match;

            Pattern pat = Pattern.compile(ADDRESS_PATTERN);
            match = pat.matcher(address);
            if(!match.matches())throw new ValidationException("Address should not contain  some special characters");

        return match.matches();


    }
    public boolean vehicleNumberValidation(String num ) throws  ValidationException{
        Matcher match;
            Pattern pat = Pattern.compile(VEHICLE_NUMBER_PATTERN);
            match = pat.matcher(num);
        if(!match.matches()) throw new ValidationException("Vehicle number should be Alphanumeric characters");
        return match.matches();
    }
    public boolean isValidLatitude(double latitude) {
        return latitude >= -90.0 && latitude <= 90.0;
    }

    public boolean isValidLongitude(double longitude) {
        return longitude >= -180.0 && longitude <= 180.0;
    }
    public boolean vehicleTypeValidation(int i ){
        return i == 2 || i == 3 || i == 4;
    }
    public boolean priceValidation(int price) throws ValidationException{
        if(price>9999){
            throw new ValidationException("price can't be more than 4 digits");
        }
        if(price<=0){
            throw new ValidationException("price can't be less than 0 or  0");
        }
        return price < 9999;
    }
    public boolean dateValidation(String date) throws ValidationException{
        Matcher match;
        Pattern pat = Pattern.compile(DATE_PATTERN);
        match = pat.matcher(date);
        if(!match.matches())throw new ValidationException("Invalid Date Format");
        return match.matches();

    }
    public boolean timeValidation(String time) throws ValidationException{
        Matcher match;
        Pattern pat = Pattern.compile(TIME_PATTERN);
        match = pat.matcher(time);
        if(!match.matches())throw new ValidationException("Invalid time Format");
        return match.matches();

    }
    public boolean doesNotContainAlphabets(String input) {
        // Use a regular expression to check if the string contains no alphabetic characters
        return input != null && !input.matches(".*[a-zA-Z].*");
    }
    public boolean vehicleYearValidation(int yr) throws ValidationException {
            String year = Integer.toString(yr);
            if (year.length() != 4)  throw new ValidationException("Year Can't be more or less than 4 digits");
            LocalDate currentDate = LocalDate.now();
            int currentYear = currentDate.getYear();
            if(yr>currentYear) throw new ValidationException("Year Can't be in future");
            else return true;
        }

    public boolean userCredentialValidation(User user) throws ValidationException {

        return stringValidation(user.getName(), "name", 15) && passWordValidation(user.getPassword()) &&  numberValidation(user.getNumber());
    }
    public boolean loginCredentialValidation(UserRequestDto user) throws ValidationException {
        // Validate the user's password using passWordValidation method


        // Validate the user's number using numberValidation method
        numberValidation(user.getNumber());
        return passWordValidation(user.getPassword())&& numberValidation(user.getNumber());
    }


    public boolean workshopValidation(Workshop work) throws ValidationException{
        stringValidation(work.getWorkShopName(),"workshopName",25);
        stringValidation(work.getCity(),"city",15);
        stringValidation(work.getState(),"state",15);
        stringValidation(work.getCountry(),"country",15);
        addressValidation(work.getAddress());
        if(!vehicleTypeValidation(work.getType()))throw new ValidationException("Invalid Vehicle Type");
        priceValidation(work.getElectricalPrice());
        priceValidation(work.getEnginePrice());
        priceValidation(work.getSuspensionPrice());
        priceValidation(work.getGeneralPrice());
        if(!doesNotContainAlphabets(work.getOpenTime()))throw new ValidationException("openTime shouldn't contain Alphabets");
        if(!doesNotContainAlphabets(work.getCloseTime()))throw new ValidationException("closeTime shouldn't contain Alphabets");
        if(!isValidLatitude(work.getLatitude()))throw new ValidationException("Invalid Latitude");
        if(!isValidLongitude(work.getLongitude()))throw new ValidationException("Invalid Longitude");
        return true;
    }
    public boolean vehicleCredentialValidation(Vehicle vehicle) throws ValidationException{
        stringValidation(vehicle.getCompany(), "company",15);
        vehicleNumberValidation(vehicle.getVehicleNumber());
        vehicleTypeValidation(vehicle.getType());
        vehicleYearValidation(vehicle.getYear());
        return true ;
    }
    public boolean bookingCredentialValidation(Booking booking) throws ValidationException {
        addressValidation(booking.getBookingAddress());
        stringValidation(booking.getBookingCity(), "city",25);
        stringValidation(booking.getBookingState(),"state",25 );
        stringValidation(booking.getBookingCountry(),"country",25);
        isValidLatitude(booking.getLatitude());
        isValidLongitude(booking.getLongitude());
        stringValidation(booking.getProblem(), "problem",40);
        dateValidation(booking.getBookingDate());
        timeValidation(booking.getBookingTime());
        return true;
    }

    public boolean serviceCredentialValidation(ServiceList service) throws ValidationException{
        stringValidation(service.getServiceName(), "service name", 20);
        priceValidation(service.getServicePrice());
        return true;

    }


}
