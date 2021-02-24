package testing;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.object.entity.channel.TextChannel;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.core.spec.MessageCreateSpec;
import phyics.commands.Command;
import phyics.graphics.AnimatedGIF;

public class LessThanThree extends Command{

    public LessThanThree(){
        super("<3");
    }

    @Override
    public void update(MessageCreateEvent event, GatewayDiscordClient gatewayDiscordClient) {

    }

    @Override
    public void processWhenCalled(MessageCreateEvent event, GatewayDiscordClient gatewayDiscordClient) {
        final Message message = event.getMessage();
        final MessageChannel channel = message.getChannel().block();

        channel.createMessage("<3").block();
    }

    @Override
    public String getDescription() {
        return "outputs a heart <3";
    }
}
