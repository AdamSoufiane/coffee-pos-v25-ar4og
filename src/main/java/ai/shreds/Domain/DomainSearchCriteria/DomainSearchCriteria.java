package ai.shreds.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

/**
 * DomainSearchCriteria is a value object that encapsulates search criteria for querying products and categories within the catalog.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class DomainSearchCriteria {
    private String name;
    private String description;

    /**
     * Maps the current search criteria to a DomainSearchCriteria entity.
     *
     * @return a new DomainSearchCriteria entity with the current search criteria
     */
    public DomainSearchCriteria internalMapper() {
        return new DomainSearchCriteria(this.name, this.description);
    }

    /**
     * Validates the search criteria to ensure they are non-empty and properly formatted.
     *
     * @param name        the name to search for
     * @param description the description to search for
     * @throws IllegalArgumentException if any of the search criteria are empty or improperly formatted
     */
    private void validateCriteria(String name, String description) {
        if (name == null || name.isEmpty() || description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Search criteria must be non-empty and properly formatted.");
        }
    }
}