package com.reparo.service;
import com.reparo.datamapper.BookingMapper;
import com.reparo.dto.booking.BookingAcceptRequestDto;
import com.reparo.dto.booking.BookingRequestDto;
import com.reparo.dto.booking.BookingResponseDto;
import com.reparo.dto.booking.LiveBookingRequestDto;
import com.reparo.dto.workshop.WorkshopDistanceResponseDto;
import com.reparo.exception.ServiceException;
import com.reparo.exception.ValidationException;
import com.reparo.model.Booking;
import com.reparo.model.Vehicle;
import com.reparo.model.Workshop;
import com.reparo.repository.BookingRepository;
import com.reparo.repository.VehicleRepository;
import com.reparo.repository.WorkshopRepository;
import com.reparo.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SettableListenableFuture;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private WorkshopService workshopService;
    @Autowired
    private WorkshopRepository workshopRepository;
    private final BookingMapper map = new BookingMapper();

    public boolean isBookingExists(int id) throws  ServiceException{
        boolean exist = false;
        if(bookingRepository!=null){
            exist =  bookingRepository.existsById(id);
            if(!exist) throw  new ServiceException("Booking Not present");
        }
        return exist;
    }


    public int createBooking(BookingRequestDto request)throws ServiceException{
        try {
            int id = 0 ;
            if(bookingRepository!=null && vehicleService!=null && vehicleRepository!=null){
                vehicleService.isVehicleExist(request.getBookedVehicleId());
                Vehicle  vehicle = vehicleRepository.findByVehicleId(request.getBookedVehicleId());
                Booking newBooking = map.mapRequestToBooking(request);
                Date current= new Date();
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat time = new SimpleDateFormat("HH:mm");
                newBooking.setBookingDate(date.format(current));
                newBooking.setBookingTime(time.format(current));
                Validation.bookingCredentialValidation(newBooking);
                newBooking.setVehicle(vehicle);
                newBooking.setRequestStatus(true);
                newBooking.setLive(true);
                Booking book  = bookingRepository.save(newBooking);
                id= book.getBookingId();
            }
            return id;
        } catch (ServiceException | ValidationException e) {
            throw new ServiceException(e.getMessage());
        }

    }

    public BookingResponseDto acceptBooking(BookingAcceptRequestDto accept) throws ServiceException{
        try {
            Booking booking =  new Booking();
            if(bookingRepository!=null && workshopRepository!=null){
                workshopService.isWorkshopExist(accept.getWorkshopId());
                Workshop workshop = workshopRepository.findById(accept.getWorkshopId());
                isBookingExists(accept.getBookingId());
                Booking book = bookingRepository.findByBookingId(accept.getBookingId());
                book.setAcceptStatus(true);
                book.setWorkshop(workshop);
                book.setOtp(accept.getOtp());
                book.setMecLatitude(workshop.getLatitude());
                book.setMecLongitude(workshop.getLongitude());
                booking = bookingRepository.save(book);
            }

          return  map.mapBookingToResponse(booking);

        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    @Async("taskExecutor")
    public ListenableFuture<BookingResponseDto> liveBookingTrack(LiveBookingRequestDto requestDto) throws ServiceException {
        SettableListenableFuture<BookingResponseDto> future = new SettableListenableFuture<>();

        try {
            BookingResponseDto resp = new BookingResponseDto();
            Validation.liveBookingRequest(requestDto);
            isBookingExists(requestDto.getId());
            Booking book = bookingRepository.findByBookingId(requestDto.getId());
            if (requestDto.getRole() == 2) {
                book.setLatitude(requestDto.getLatitude());
                book.setLongitude(requestDto.getLongitude());
            } else {
                book.setMecLongitude(requestDto.getLongitude());
                book.setMecLatitude(requestDto.getLatitude());
            }
            Booking booking = bookingRepository.save(book);
            double distance = workshopService.calculateDistance(booking.getMecLatitude(), booking.getMecLongitude(),booking.getLatitude(), booking.getLongitude());
            System.out.println(distance);
            resp = map.mapBookingToResponse(booking);
            resp.setDistance(distance);

            future.set(resp);
        } catch (ServiceException | ValidationException e) {
            future.setException(e);
        }

        return future;
    }
    public List<WorkshopDistanceResponseDto> getBookingNearWorkshops(int id)throws ServiceException{
        try {
            isBookingExists(id);
            List<WorkshopDistanceResponseDto> responseDto =  new ArrayList<>();

            if(bookingRepository!=null){
                Booking book = bookingRepository.findByBookingId(id);
                responseDto = workshopService.findWorkshopsNearByArea(book.getLatitude(),book.getLongitude(),book.getBookingCity());
            }
            return responseDto;

        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public  BookingResponseDto getBookingById(int id) throws ServiceException{
        BookingResponseDto response =  new BookingResponseDto();
        try {
            isBookingExists(id);
            if(bookingRepository!=null) {
                Booking book  =  bookingRepository.findByBookingId(id);
                response = map.mapBookingToResponse(book);

            }
            return response;

        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public boolean cancelBooking(int bookingId , String role) throws ServiceException{
        try {
            boolean chk = true ;
            isBookingExists(bookingId);
            if(bookingRepository!=null){
                Booking book = bookingRepository.findByBookingId(bookingId);
                if(role.equals("user")){
                    book.setRequestStatus(false);
                }else{
                    book.setAcceptStatus(false);
                }
                book.setLive(false);
                Booking canceled = bookingRepository.save(book);
                chk = canceled.isLive();
            }
            return !chk;
        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<BookingResponseDto> getBookingByWorkshopId(int workshopId) throws ServiceException{
        try {
           List<BookingResponseDto>  arrayList =  new ArrayList<>();
            workshopService.isWorkshopExist(workshopId);
            if(bookingRepository!=null && workshopRepository !=null){
                Workshop workshop = workshopRepository.findById(workshopId);
               List<Booking> bookings = bookingRepository.findByWorkshop(workshop);
                if(bookings.isEmpty())throw  new ServiceException("No Booking were Available");
                for (Booking book :bookings) {
                    arrayList.add(map.mapBookingToResponse(book));
                }

            }
            return arrayList ;
        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage());
        }

    }


    }
