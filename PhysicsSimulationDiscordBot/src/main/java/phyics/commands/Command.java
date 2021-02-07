package phyics.commands;

import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import phyics.*;

public abstract class Command {

    private final String name;

    public Command(String name) {
        this.name = name;
    }

    public abstract void processAndOutputMessage(Message rawInput, MessageChannel inputLocation);

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
}
