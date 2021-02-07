import discord4j.core.DiscordClient;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.Channel;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.object.entity.channel.TextChannel;

public class Main {

    public static void main (String[] arguments) {

        // Shortcut to new DiscordClientBuilder(token).build()
        DiscordClient client = DiscordClient.create(System.getenv(arguments[0]));
        // Acquire a synchronous handle on gateway connections, can be used to register events, get cached entities, etc.
        GatewayDiscordClient gateway = client.login().block();

        // Shortcut to gateway.getEventDispatcher().on(ReadyEvent.class)
        gateway.on(ReadyEvent.class)
                .subscribe(ready ->
                        System.out.println("Logged in as " + ready.getSelf().getUsername())
                );

        // add database initialization


        // add input handling initialization


        if (gateway == null) throw new AssertionError();

        gateway.on(MessageCreateEvent.class).subscribe(event -> {

            final Message message = event.getMessage();
            final MessageChannel channel = message.getChannel().block();

            // database


            // PROCESSES COMMANDS


        });

        // To keep the main thread alive, await until bot disconnects through logout()
        gateway.onDisconnect().block();
    }

}
