package handler;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import ep.fom_vorlesungen.xls.IVorlesungenController;
import ep.fom_vorlesungen.xls.VorlesungenController;

public class NextOrtIntentHandler extends BasicIntentHandler implements RequestHandler{

	public boolean canHandle(HandlerInput input) {
		return input.matches(Predicates.intentName("NextOrtIntent"));
	}

	public Optional<Response> handle(HandlerInput input) {
		IVorlesungenController vorlesungenController = new VorlesungenController();
		String speechText = vorlesungenController.getNextVorlesungsortOutput();

		return input.getResponseBuilder()

				.withSpeech(speechText)

				.withSimpleCard("Next Ort Vorlesung", speechText)

				.withReprompt(speechText)

				.build();
	}

}
