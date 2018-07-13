package handler;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.request.Predicates;

import ep.fom_vorlesungen.xls.IVorlesungenController;
import ep.fom_vorlesungen.xls.VorlesungenController;

public class DateVorlesungIntentHandler extends BasicIntentHandler implements RequestHandler {

	public static final String DATUM_SLOT = "Datum";

	public boolean canHandle(HandlerInput input) {
		return input.matches(Predicates.intentName("DateVorlesungIntent"));
	}

	public Optional<Response> handle(HandlerInput input) {
		String datumSlotValue = this.liesDatumAusSlot(input.getRequestEnvelope().getRequest());

		IVorlesungenController vorlesungenController = new VorlesungenController();
		String speechText = vorlesungenController.getNextVorlesungToDateOutput(datumSlotValue);

		return input.getResponseBuilder()

				.withSpeech(speechText)

				.withSimpleCard("Date Vorlesung", speechText)

				.withReprompt(speechText)

				.build();

	}

	private String liesDatumAusSlot(Request request) {
		Map<String, Slot> slots = ((IntentRequest) request).getIntent().getSlots();
		Slot datumSlot = slots.get(DATUM_SLOT);
		String datumSlotValue = datumSlot.getValue();
		return datumSlotValue;
	}

}
