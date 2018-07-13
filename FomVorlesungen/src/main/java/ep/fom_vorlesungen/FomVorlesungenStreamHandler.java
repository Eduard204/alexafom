package ep.fom_vorlesungen;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;

import handler.AllModulesIntentHandler;
import handler.CancelAndStopIntentHandler;
import handler.DateVorlesungIntentHandler;
import handler.DefaultIntentHandler;
import handler.HelpIntentHandler;
import handler.LaunchRequestHandler;
import handler.NextOrtIntentHandler;
import handler.NextVorlesungIntentHandler;
import handler.SessionEndedRequestHandler;

public class FomVorlesungenStreamHandler extends SkillStreamHandler {

	public FomVorlesungenStreamHandler(Skill skill) {
		super(getSkill());
	}

	public FomVorlesungenStreamHandler() {
		super(getSkill());
	}

	private static Skill getSkill() {

		return Skills.standard()

				.addRequestHandlers(
						
						new LaunchRequestHandler(), 

						new AllModulesIntentHandler(),

						new NextVorlesungIntentHandler(),
						
						new NextOrtIntentHandler(),
						
						new DateVorlesungIntentHandler(),

						new HelpIntentHandler(),
						
						new CancelAndStopIntentHandler(),
						
						new SessionEndedRequestHandler(),
						
						new DefaultIntentHandler())

//				.withSkillId("1b67d6ba-850f-4fd0-98d7-2ccca6c9ea3d")
				.build();
	}

}
