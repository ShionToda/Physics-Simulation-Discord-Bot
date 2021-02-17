package testing;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.object.entity.channel.TextChannel;
import discord4j.core.spec.EmbedCreateSpec;
import phyics.commands.Command;

import java.io.IOException;
import java.util.function.Consumer;

public class TestingCommand extends Command {

    public TestingCommand() {
        super("test");
    }

    @Override
    public void processAndOutputMessage(Message rawInput, MessageChannel inputLocation, GatewayDiscordClient gatewayDiscordClient) {

        inputLocation.createMessage("Running...").block();
        inputLocation.createMessage("Converting Graphics2D into images, then into GIF...").block();

        AnimatedGIFObject tester = new AnimatedGIFObject();

        try {
            tester.convert();
        } catch (IOException e) {
            inputLocation.createMessage("An unexpected error occurred during conversion!").block();
            e.printStackTrace();
            System.exit(1);
        }
        inputLocation.createMessage("Completed Converting!").block();

        inputLocation.createMessage("Sending the converted GIF...").block();
        try {
            inputLocation.createEmbed(new Consumer<EmbedCreateSpec>() {
                @Override
                public void accept(EmbedCreateSpec embedCreateSpec) {
                    // TODO fix discord4j.rest.http.client.ClientResponse.clientException
                    //  set up a server to retrieve the image and send the image
                    embedCreateSpec.setImage("https://cdn.discordapp.com/attachments/607096939082809345/811510688639615016/unknown.png");

                }
            }).block();
        }catch(Exception e) {
            inputLocation.createMessage("IDK how tho :P\n(If you see this, then I caught an exception OMEGA LOL)").block();
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public String getDescription() {
        return "testing results into discord; the outputs are random";
    }
}
