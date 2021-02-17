package phyics.commands;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import phyics.inputs.InputHandler;

public class Prefix extends Command {

    public Prefix() {
        super("prefix");
    }

    @Override
    public void processAndOutputMessage(Message rawInput, MessageChannel inputLocation, GatewayDiscordClient gatewayDiscordClient) {
        String input = rawInput.getContent();
        InputHandler.prefix = input.substring(input.indexOf(' ', input.lastIndexOf("prefix") + 6) + 1);
        inputLocation.createMessage("Changed prefix to: ``" + InputHandler.prefix + "``").block();
    }

    @Override
    public String getDescription() {
        return "changes the prefix of the bot";
    }
}
