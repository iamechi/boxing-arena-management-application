package com.boxing_app.boxing_arena_management_app.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Component
public class ArenaManagementAppDao {
    @Autowired
    private ArenaManagementAppRepository repo;

    //handles returning all of the arenas
    public List<Arena> getAllArenas() {return repo.findAll();}

    //handles saving a new arena and/or updating its information to the database.
    public Arena saveArena(Arena arena) {return repo.save(arena);}

    public List<Arena> getFighters(Map<String, Object> searchCriteria) {
        Object value;

        /*This is the base specification object. Right now when this gets used by
        the pre-built findAll method from JpaSpecificationExecutor, a select query without a where clause
        will be run on the database*/
        Specification<Arena> spec = Specification.unrestricted();

        /*Each one of these if-conditions if true will lead to the addition of a
        condition to the "where" clause of the sql query that the specification object
        will eventually be used to create*/
        value = searchCriteria.get("name");
       if (value instanceof String providedName && StringUtils.hasLength(providedName)) {
           System.out.println("Entered name criteria if-block");
           spec = spec.and(ArenaSpecs.arenaNameStartsWtih(providedName));
       };

       value = searchCriteria.get("city");
       if (value instanceof String providedCity && StringUtils.hasLength(providedCity)) {
           System.out.println("entered city criteria if-block");
           spec = spec.and(ArenaSpecs.arenaCityStartsWith(providedCity));
       }

       value = searchCriteria.get("state");
       if (value instanceof String providedState && StringUtils.hasLength(providedState)) {
           System.out.println("Entered state criteria if-block");
           spec = spec.and(ArenaSpecs.arenaStateStartsWith(providedState));
       }

       value = searchCriteria.get("country");
       if (value instanceof String providedCountry && StringUtils.hasLength(providedCountry)) {
           System.out.println("Entered country criteria if-block");
           spec = spec.and(ArenaSpecs.arenaCountryStartsWith(providedCountry));
       }

        return repo.findAll(spec);
    }
}
