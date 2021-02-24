package phyics.commands;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import phyics.inputs.InputHandler;

public class Prefix extends Command {

    public Prefix() {
        super("prefix");
    }

    @Override
    public void update(MessageCreateEvent event, GatewayDiscordClient gatewayDiscordClient) {

    }

    @Override
    public void processWhenCalled(MessageCreateEvent event, GatewayDiscordClient gatewayDiscordClient) {

        final Message message = event.getMessage();
        final MessageChannel channel = message.getChannel().block();

        String input = message.getContent();
        InputHandler.prefix = input.substring(input.indexOf(' ', input.lastIndexOf("prefix") + 6) + 1);
        channel.createMessage("Changed prefix to: ``" + InputHandler.prefix + "``").block();
    }

    @Override
    public String getDescription() {
        return "changes the prefix of the bot";
    }
}
