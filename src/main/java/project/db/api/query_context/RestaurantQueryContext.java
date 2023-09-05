package project.db.api.query_context;

import java.util.List;

public record RestaurantQueryContext(
    String queryText, 
    String description, 
    List<String> requiredValues
) { }
