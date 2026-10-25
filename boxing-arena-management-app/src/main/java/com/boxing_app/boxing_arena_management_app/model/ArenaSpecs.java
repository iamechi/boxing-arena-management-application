package com.boxing_app.boxing_arena_management_app.model;

import org.springframework.data.jpa.domain.Specification;

public class ArenaSpecs {
    public static Specification<Arena> arenaNameStartsWtih(String providedName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), providedName + "%");
    }

    public static Specification<Arena> arenaCityStartsWith(String providedCity) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("city"), providedCity + "%");
    }

    public static Specification<Arena> arenaStateStartsWith(String providedState) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("state"), providedState + "%");
    }

    public static Specification<Arena> arenaCountryStartsWith(String providedCountry) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("country"), providedCountry + "%");
    }
}
