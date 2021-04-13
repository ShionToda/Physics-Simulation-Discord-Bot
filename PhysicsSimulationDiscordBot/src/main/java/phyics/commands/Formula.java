package phyics.commands;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.Embed;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.core.spec.MessageCreateSpec;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.function.Consumer;

public class Formula extends Command {
    private String queuedUserID;
    private boolean isQueued;
    public Formula() {
        super("formula");
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
            else if (content.equalsIgnoreCase("kinematics")){
                channel.createMessage("Branch? kinematics + (1D, 2D)").block();
            } else if (content.equalsIgnoreCase("kinematics1d")){
                try {
                    channel.createEmbed(new Consumer<EmbedCreateSpec>() {
                        @Override
                        public void accept(EmbedCreateSpec embedCreateSpec) {
                            embedCreateSpec.setImage("https://cdn.discordapp.com/attachments/699070356165754912/831371860540129321/BO82YZEm2LGnHiU5RcqaKRltWAkf4MTXv-QcUCe09uVP2h-2FSWaYTzTtEHxhD2-sehTRstmwW1MdPpTI5aIKC4.png").asRequest();
                        }
                    }).block();
                }catch(Exception e) {
                    channel.createMessage("Error!").block();
                    e.printStackTrace();
                    System.exit(1);
                }
            }
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
            channel.createMessage("Say a topic and \"reset\" or \"quit\" to stop").block();
            isQueued = true;
            queuedUserID = message.getUserData().id();
        }
    }

    @Override
    public String getDescription() {
        return null;
    }
}
