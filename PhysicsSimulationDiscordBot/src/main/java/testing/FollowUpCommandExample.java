package testing;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import phyics.commands.Command;

public class FollowUpCommandExample extends Command {

    private String queuedUserID;
    private boolean isQueued;

    private int guess, answer, attempts;

    public FollowUpCommandExample () {
        super("fp");

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
            try {
                guess = Integer.parseInt(content);
            } catch (NumberFormatException e) {
                channel.createMessage("That is not a number! Type in \"quit\" to stop.").block();
                return;
            }
            attempts++;
        }

        if (guess == answer) {
            channel.createMessage(answer + " was the number! You got it right "
                    + (attempts == 1? ("on your first try, what a lucky person!") : ("with " + attempts + " tries!")) ).block();
            isQueued = false;
            queuedUserID = null;
        } else if (guess > answer) {
            channel.createMessage("The actual number is smaller than " + guess).block();
        } else if (guess < answer) {
            channel.createMessage("The actual number is greater than " + guess).block();
        }
    }

    @Override
    public void processWhenCalled(MessageCreateEvent event, GatewayDiscordClient gatewayDiscordClient) {
        final Message message = event.getMessage();
        final MessageChannel channel = message.getChannel().block();

        if (isQueued) {
            return;
        } else {
            channel.createMessage("Pick a random number from 1 to 100.").block();

            answer = (int)(Math.random() * 99 + 1);
            attempts = 0;

            isQueued = true;
            queuedUserID = message.getUserData().id();
        }
    }

    @Override
    public String getDescription() {
        return "This is an example program for the FollowUPCommand Class";
    }
}
