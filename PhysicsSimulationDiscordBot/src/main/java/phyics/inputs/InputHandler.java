package phyics.inputs;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import phyics.commands.Command;
import phyics.commands.Prefix;
import phyics.database.Database;
import testing.TestingCommand;

public class InputHandler {

    public static String prefix = "physic";
    private final Database database;
    private final Command[] commands;
    private GatewayDiscordClient gateway;
//    private final Help help;

    public InputHandler(GatewayDiscordClient gateway, Database database) {
        this.gateway = gateway;
        commands = new Command[] {
                new Prefix(), new TestingCommand()
        };
//
//        help = new Help(commands);
        this.database = database;
    }

    public void process(MessageCreateEvent event) {

        final Message message = event.getMessage();
        final MessageChannel channel = message.getChannel().block();

        String received = message.getContent().trim();

        if (!received.startsWith(prefix)) {
            return;
        }


        String cmd = received.substring(prefix.length()).trim();

//        if (cmd.equals("help")) {
//            help.processAndOutputMessage(message, channel);
//        }

        for (Command command : commands) {

            if (cmd.startsWith(command.getName())) {
                // TASK
                command.processAndOutputMessage(event, gateway);
                break;
            }
        }
    }
}
