package handler;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

public class DefaultIntentHandler implements RequestHandler{

	public boolean canHandle(HandlerInput input) {
		return true;
	}

	public Optional<Response> handle(HandlerInput input) {
		String speechText = "Ich konnte deine Anfrage leider nicht verstehen. Kannst du die Eingabe nochmal wiederholen";

        return input.getResponseBuilder()

                .withSpeech(speechText)

                .withSimpleCard("Default", speechText)

                .withReprompt(speechText)

                .build();
	}

}
