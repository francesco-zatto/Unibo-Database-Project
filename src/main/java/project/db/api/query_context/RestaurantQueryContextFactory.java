package project.db.api.query_context;

import static project.db.api.utilities.RestaurantQueryDescriptions.*;
import static project.db.api.utilities.RestaurantQueryTexts.*;
import static project.db.api.utilities.RestaurantQueryValues.*;

/**
 * Factory for RestaurantQueryContexts, a class with the needed query text, description and required values.
 */
public class RestaurantQueryContextFactory {

    public static RestaurantQueryContext getAllergeniPiattoContext() {
        return new RestaurantQueryContext(
            getVisualizzareAllergeniPiatto(), 
            getVisualizzareAllergeniPiattoDescription(),
            getVisualizzareAllergeniPiattoValues()
        );
    }

    public static RestaurantQueryContext getInserireContoContext() {
        return new RestaurantQueryContext(
            getInserireContoPrenotazioneSalvata(), 
            getInserireContoDescription(), 
            getInserireContoValues()
        );
    }

    public static RestaurantQueryContext getTavoliPrenotatiContext() {
        return new RestaurantQueryContext(
            getVisualizzareTavoliPrenotati(), 
            getVisualizzareTavoliPrenotatiDescription(),
            getVisualizzareTavoliPrenotatiValues()
        );
    }

    public static RestaurantQueryContext getPortataCuocoContext() {
        return new RestaurantQueryContext(
            getVisualizzarePortataCuocoGiorno(), 
            getVisualizzarePortataDescription(), 
            getVisualizzarePortataValues()
        );
    }

    public static RestaurantQueryContext getFornitoriIngredienteContext() {
        return new RestaurantQueryContext(
            getVisualizzareFornitoriIngrediente(), 
            getVisualizzareFornitoriIngredienteDescription(),
            getVisualizzareFornitoriIngredienteValues()  
        );
    }

    public static RestaurantQueryContext getIncassoTurnoContext() {
        return new RestaurantQueryContext(
            getvisualizzareIncassoTurno(), 
            getVisualizzareIncassoTurnoDescription(), 
            getVisualizzareIncassoTurnoValues()
        );
    }

    public static RestaurantQueryContext getDipendenteStipendioMassimoContext() {
        return new RestaurantQueryContext(
            getVisualizzareDipendenteStipendioMassimo(), 
            getVisualizzareDipendenteStipendioMassimoDescription(), 
            getVisualizzareDipendenteStipendioMassimoValues()
        );
    }

    public static RestaurantQueryContext getPrenotazioniSlotAggiuntivoContext() {
        return new RestaurantQueryContext(
            getVisualizzarePrenotazioniSlotAggiuntivo(), 
            getVisualizzarePrenotazioniSlotAggiuntivoDescription(), 
            getVisualizzarePrenotazioniSlotAggiuntivoValues()
        );
    }
    
}
