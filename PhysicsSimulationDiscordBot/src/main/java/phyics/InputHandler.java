package phyics;

import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import phyics.commands.Command;
import phyics.commands.Prefix;

public class InputHandler {

    public static String prefix = "physic";
    private final Database database;
    private final Command[] commands;
//    private final Help help;

    public InputHandler(Database database) {
        commands = new Command[] {
                new Prefix(),
        };
//
//        help = new Help(commands);
        this.database = database;
    }

    public void process(Message message, MessageChannel channel) {

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
                command.processAndOutputMessage(message, channel);
                break;
            }
        }
    }
}
