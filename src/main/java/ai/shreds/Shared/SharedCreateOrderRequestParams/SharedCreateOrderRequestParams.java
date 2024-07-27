package ai.shreds.shared;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SharedCreateOrderRequestParams {
    private UUID userId;
    private List<SharedOrderItemRequestParams> items;

    // Validation to ensure userId and items are not null or empty
    public void validate() {
        if (userId == null) {
            throw new IllegalArgumentException("UserId cannot be null");
        }
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Items cannot be null or empty");
        }
        for (SharedOrderItemRequestParams item : items) {
            if (item == null) {
                throw new IllegalArgumentException("Items list cannot contain null elements");
            }
        }
    }
}