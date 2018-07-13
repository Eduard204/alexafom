package handler;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import ep.fom_vorlesungen.xls.IVorlesungenController;
import ep.fom_vorlesungen.xls.VorlesungenController;

public class NextVorlesungIntentHandler extends BasicIntentHandler implements RequestHandler {

	public boolean canHandle(HandlerInput input) {
		return input.matches(Predicates.intentName("NextVorlesungIntent"));
	}

	public Optional<Response> handle(HandlerInput input) {
		IVorlesungenController vorlesungenController = new VorlesungenController();
		String speechText = vorlesungenController.getNextVorlesungOutput();

		return input.getResponseBuilder()

				.withSpeech(speechText)

				.withSimpleCard("Next Vorlesung", speechText)

				.withReprompt(speechText)

				.build();

	}

}
