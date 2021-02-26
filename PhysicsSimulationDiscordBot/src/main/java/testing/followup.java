package testing;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import phyics.commands.Command;

public class followup extends Command {
    private String queuedUserID;
    private boolean isQueued;

    public followup() {
        super("ak");
        isQueued = false;
    }

    @Override
    public void update(MessageCreateEvent event, GatewayDiscordClient gatewayDiscordClient) {
        if (!isQueued)
            return;

        final Message message = event.getMessage();
        final MessageChannel channel = message.getChannel().block();
        String content = message.getContent();
        if (message.getUserData().id().equals(queuedUserID)) {
            if (content.equalsIgnoreCase("reset")
                    || content.equalsIgnoreCase("quit")) {
                isQueued = false;
                queuedUserID = null;
                return;
            }
            channel.createMessage("hi").block();
        }
    }

    @Override
    public void processWhenCalled(MessageCreateEvent event, GatewayDiscordClient gatewayDiscordClient) {
        final Message message = event.getMessage();
        final MessageChannel channel = message.getChannel().block();

        if (isQueued) {
            return;
        }
        else {
            channel.createMessage("Say anything and \"reset\" or \"quit\" to stop").block();
            isQueued = true;
            queuedUserID = message.getUserData().id();
        }
    }

    @Override
    public String getDescription() {
        return null;
    }
}
