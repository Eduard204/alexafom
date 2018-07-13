package handler;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

public class CancelAndStopIntentHandler extends BasicIntentHandler implements RequestHandler{

	public boolean canHandle(HandlerInput input) {
		return input.matches(Predicates.intentName("AMAZON.CancelIntent")) || input.matches(Predicates.intentName("AMAZON.StopIntent"));
	}

	public Optional<Response> handle(HandlerInput input) {
		String speechText = "Peace Out ich bin draußen";

        return input.getResponseBuilder()

                .withSpeech(speechText)

                .withSimpleCard("Stop", speechText)

                .withReprompt(speechText)

                .build();
	}

}
