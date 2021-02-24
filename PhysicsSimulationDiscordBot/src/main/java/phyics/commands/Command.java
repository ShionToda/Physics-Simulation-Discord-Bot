package phyics.commands;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import phyics.inputs.InputHandler;

import java.io.InputStream;

public abstract class Command {

    private final String name;

    public Command(String name) {
        this.name = name;
    }

    public abstract void update(MessageCreateEvent event, GatewayDiscordClient gatewayDiscordClient);

    public abstract void processWhenCalled(MessageCreateEvent event, GatewayDiscordClient gatewayDiscordClient);

    public String getName() {
        return name;
    }

    public abstract String getDescription();

    public String getArgument(String input) {
        return input.substring( (InputHandler.prefix + name).length() );
    }

    public String[] getArguments(String input) {
        String entireArgument = input.substring( (InputHandler.prefix + name).length() );
        return entireArgument.trim().split("\\s+");
    }

    public boolean isCalled(MessageCreateEvent event) {
        String message = event.getMessage().getContent();
        return isCalled(message);
    }

    public boolean isCalled(String content) {
        String cmd = content.substring(InputHandler.prefix.length()).trim();
        if (cmd.startsWith(name)) {
            return true;
        }
        return  false;
    }
}
