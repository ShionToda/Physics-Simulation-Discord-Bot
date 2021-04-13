package phyics.inputs;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import phyics.commands.Command;
import phyics.commands.Prefix;
import phyics.commands.Formula;
import phyics.database.Database;
import testing.FollowUpCommandExample;
import testing.LessThanThree;
import testing.TestingCommand;
import testing.followup;

public class InputHandler {

    public static String prefix = "physic";
    private final Database database;
    private final Command[] commands;
    private GatewayDiscordClient gateway;

    private String lastMessageID;
//    private final Help help;

    public InputHandler(GatewayDiscordClient gateway, Database database) {
        this.gateway = gateway;
        commands = new Command[] {
                new Prefix(), new TestingCommand(), new LessThanThree(), new FollowUpCommandExample(), new followup(), new Formula()
        };
//
//        help = new Help(commands);
        this.database = database;
    }

    public void process(MessageCreateEvent event) {

        final Message message = event.getMessage();
        final MessageChannel channel = message.getChannel().block();

        String cmd = null;

        String received = message.getContent().trim();
        if (received.startsWith(prefix)) {
            cmd = received.substring(prefix.length()).trim();
        }

//        if (cmd.equals("help")) {
//            help.processAndOutputMessage(message, channel);
//        }

        for (Command command : commands) {
            if (cmd != null && cmd.startsWith(command.getName())) {
                // TASK
                command.processWhenCalled(event, gateway);
                updateLastMessage(message, channel);
                continue;
            }
            if (isNewMessageByUserSent(message, channel)) {
                command.update(event, gateway);
            }
        }
    }

    private void updateLastMessage(Message message, MessageChannel channel) {
        final Message lastMessage = channel.getLastMessage().block();

        if (!lastMessage.getUserData().id().equals("807816258199617536")) {
            lastMessageID = lastMessage.getId().asString();
        }
    }

    private boolean isNewMessageByUserSent(Message message, MessageChannel channel) {
        final Message lastMessage = channel.getLastMessage().block();

        String currentMessageID = lastMessage.getId().asString();

        if (lastMessage.getUserData().id().equals("807816258199617536")) {
            return false;
        }

        if (currentMessageID.equals(lastMessageID)) {
            lastMessageID = currentMessageID;
            return false;
        }
        return true;
    }
}
