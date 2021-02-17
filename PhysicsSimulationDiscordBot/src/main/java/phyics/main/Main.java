package phyics.main;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import phyics.inputs.InputHandler;
import phyics.database.Database;

public class Main {

    public static void main(String[] args) {
        final DiscordClient client;
        String token = args[0];
        client = DiscordClient.create(token);
        final GatewayDiscordClient gateway = client.login().block();

        final Database database = new Database(args[1], args[2], args[3]);
//        database.connect();

        InputHandler inputHandler = new InputHandler(gateway, database);
        if (gateway == null) throw new AssertionError();

        gateway.on(MessageCreateEvent.class).subscribe(event -> {

            // database

            // PROCESSES COMMANDS
            inputHandler.process(event);
        });

        gateway.onDisconnect().block();
    }

}
