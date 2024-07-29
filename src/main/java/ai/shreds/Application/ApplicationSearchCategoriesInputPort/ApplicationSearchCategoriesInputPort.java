package ai.shreds.application;

import ai.shreds.shared.SharedCategoryDTO;
import ai.shreds.shared.SharedSearchCriteria;
import java.util.List;

/**
 * ApplicationSearchCategoriesInputPort is an interface that defines the contract for searching categories
 * based on provided search criteria.
 */
public interface ApplicationSearchCategoriesInputPort {
    /**
     * Searches for categories based on provided search criteria.
     *
     * @param searchCriteria the criteria to search categories
     * @return a list of SharedCategoryDTO objects matching the search criteria
     * @throws IllegalArgumentException if the search criteria are invalid
     * @throws RuntimeException if there is an error during the search
     */
    List<SharedCategoryDTO> searchCategories(SharedSearchCriteria searchCriteria) throws IllegalArgumentException, RuntimeException;
}