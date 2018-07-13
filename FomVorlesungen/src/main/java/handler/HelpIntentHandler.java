package handler;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

public class HelpIntentHandler implements RequestHandler{

	public boolean canHandle(HandlerInput input) {
		return input.matches(Predicates.intentName("AMAZON.HelpIntent"));
	}

	public Optional<Response> handle(HandlerInput input) {
		String speechText = "Wenn du Hilfe brauchst, schaue doch noch einmal in die Skill Beschreibung";

        return input.getResponseBuilder()

                .withSpeech(speechText)

                .withSimpleCard("Help", speechText)

                .withReprompt(speechText)

                .build();
	}

}
