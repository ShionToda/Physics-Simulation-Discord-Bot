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

import java.io.*;
import java.util.function.Consumer;

public class TestingCommand extends Command {

    public TestingCommand() {
        super("test");
    }

    @Override
    public void processAndOutputMessage(MessageCreateEvent event, GatewayDiscordClient gatewayDiscordClient) {

        final Message message = event.getMessage();
        final MessageChannel channel = message.getChannel().block();

        channel.createMessage("Running...").block();
        channel.createMessage("Converting Graphics2D into images, then into GIF...").block();

        AnimatedGIFObject tester = new AnimatedGIFObject();

        try {
            tester.convert();
        } catch (IOException e) {
            channel.createMessage("An unexpected error occurred during conversion!").block();
            e.printStackTrace();
            System.exit(1);
        }
        channel.createMessage("Completed Converting!").block();

        channel.createMessage("Sending the converted GIF...").block();
        try {
            channel.createMessage(new Consumer<MessageCreateSpec>() {
                @Override
                public void accept(MessageCreateSpec messageCreateSpec) {
                    // TODO fix discord4j.rest.http.client.ClientResponse.clientException
                    //  set up a server to retrieve the image and send the image
                    messageCreateSpec.setContent("Is this working?: ");

                    InputStream input = null;
                    try {
                        input = new FileInputStream(new File("res\\output.gif"));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    messageCreateSpec.addFile("output.gif", input).asRequest();
                }
            }).block();
        }catch(Exception e) {
            channel.createMessage("Error!").block();
            e.printStackTrace();
            System.exit(1);
        }
        channel.createMessage("Sent!").block();

//        if (channel instanceof TextChannel) {
//            TextChannel textChannel = (TextChannel)channel;
//            textChannel.creat
//        }

    }

    @Override
    public String getDescription() {
        return "testing results into discord; the outputs are random";
    }
}
