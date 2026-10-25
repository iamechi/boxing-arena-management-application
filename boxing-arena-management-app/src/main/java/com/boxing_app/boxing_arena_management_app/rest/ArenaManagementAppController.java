package com.boxing_app.boxing_arena_management_app.rest;

import com.boxing_app.boxing_arena_management_app.model.Arena;
import com.boxing_app.boxing_arena_management_app.model.ArenaManagementAppDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(
        origins = "http://localhost:5173",
        allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS}
)
public class ArenaManagementAppController {
    @Autowired
    private ArenaManagementAppDao service;

    //Get request method for returning all arenas
    @GetMapping("/arenas")
    public List<Arena> getAllArenas() {
        return service.getAllArenas();
    }

    @PostMapping("/arenas/add")
    public ResponseEntity<?> addFighters(@RequestBody Arena arena) {
        Arena savedArena;

        /*This is to prevent hibernate from sending back and update sql query instead of an
        insert query with the information because it will if there is any value for the id on the area*/
        if (arena.getArena_id() == 0) {
            arena.setArena_id(null);
        }

        //checks to see if the area already has been saved
        if (ifArenaExists(arena)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An arena already has been saved at the location provided. Please change the address, city, state, or country.");
        }

        //gets the return data from saving the arena to the database
        savedArena = service.saveArena(arena);
        System.out.println("saveArena called");
        //Returns a 200 ok response with the saved data for logging
        return ResponseEntity.ok(savedArena);
    }

    @PostMapping("/arenas/update")
    public ResponseEntity<?> updateArena(@RequestBody Arena arena) {
        Arena savedArena;

        savedArena = service.saveArena(arena);

        return ResponseEntity.ok(savedArena);
    }

    @PostMapping("/arenas/search")
    public ResponseEntity<?> searchArenas(@RequestBody Map<String, Object> areaSearchCriteria) {
        List<Arena> arenas = service.getFighters(areaSearchCriteria);

        return ResponseEntity.ok(arenas);
    }

    private boolean ifArenaExists(Arena arena) {
        boolean hasMatch;

        //uses pre-existing method for retrieving all arena records to check against during the validation process
        List<Arena> allArenas = service.getAllArenas();

        /*This checks to see if there is an arena at the same place and location that has already been saved.
        We don't validate against the name because it could be possible to have an arena with the same name,
        but at a different location if a business possibly has multiple locations for hosting sportings events*/
        hasMatch = allArenas.stream().anyMatch((arenaElement) ->
                    arenaElement.getAddress().equalsIgnoreCase(arena.getAddress()) &&
                    arenaElement.getCity().equalsIgnoreCase(arena.getCity()) &&
                    arenaElement.getState().equalsIgnoreCase(arena.getState()) &&
                    arenaElement.getCountry().equalsIgnoreCase(arena.getCountry()));

        return hasMatch;
    }


}
