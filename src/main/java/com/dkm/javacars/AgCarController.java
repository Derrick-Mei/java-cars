package com.dkm.javacars;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class AgCarController
{
    private final AcCarRepository carrepos;
    private final RabbitTemplate rt;

    public AgCarController(AcCarRepository carrepos, RabbitTemplate rt)
    {
        this.carrepos = carrepos;
        this.rt = rt;
    }


// /cars/id/{id} - returns the car based of of id
    @GetMapping("/cars/id/{id}")
    public AdCar findCarById(@PathVariable Long id)
    {
        return carrepos.findById(id)
                .orElseThrow(()-> new AeCarNotFoundByIdException(id));
    }

// /cars/year/{year} - returns a list of cars of that year model
    @GetMapping("/cars/year/{year}")
    public List<AdCar> findCarsByYear(@PathVariable Long year)
    {
        return carrepos
                .findAll()
                .stream()
                .filter(car -> car.getYear().equals(year))
                .collect(Collectors.toList());
    }

// /cars/brand/{brand} - returns a list of cars of that brand
// logged with a message of "search for {brand}"
    @GetMapping("cars/brand/{brand}")
    public List<AdCar> findCarsByBrand(@PathVariable String brand)
    {
        // logged message brand has capital letter for first character!!!
        AfCarLog logFromBrand = new AfCarLog("search for " + brand.substring(0, 1).toUpperCase() + brand.substring(1));
        rt.convertAndSend(AaJavacarsApplication.QUEUE_NAME, logFromBrand.toString());
        log.info("\n\nMsg Sent From /cars/brand/{brand}\n");

        return carrepos
                .findAll()
                .stream()
                .filter(car -> car.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
    }

// /cars/upload - loads multiple sets of data from the RequestBody
// logged with a message of "Data loaded"
    @PostMapping("/cars/upload")
    public List<AdCar> uploadCars(@RequestBody List<AdCar> carsList)
    {
        AfCarLog logFromUpload = new AfCarLog("Data Uploaded");
        rt.convertAndSend(AaJavacarsApplication.QUEUE_NAME, logFromUpload.toString());

        return carrepos.saveAll(carsList);
    }

// /cars/delete/{id} - deletes a car from the list based off of the id
// logged with a message of "{id} Data deleted"
    @DeleteMapping("/cars/delete/{id}")
    public AdCar deleteCar(@PathVariable Long id)
//    public ResponseEntity<?> deleteCar(@PathVariable Long id)
    {
        AdCar carToDelete = carrepos.findById(id).orElseThrow(()-> new AeCarNotFoundByIdException(id));

        carrepos.deleteById(id);

        return carToDelete;
//        return ResponseEntity.noContent().build();
    }

}
