package handler;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

public class LaunchRequestHandler implements RequestHandler{

	public boolean canHandle(HandlerInput input) {
//		Predicate<>
//		return input.matches((LaunchRequest.class));
		return input.matches(Predicates.requestType(LaunchRequest.class));
	}

	public Optional<Response> handle(HandlerInput input) {
		String speechText = "Herzlich Willkommen zum Fom Vorlesungen Alexa SKill. Du kannst mich nach deinen Vorlesungen befragen.";

        return input.getResponseBuilder()

                .withSpeech(speechText)

                .withSimpleCard("Willkommen Fom Vorlesungen", speechText)

                .withReprompt(speechText)

                .build();
	}

}
