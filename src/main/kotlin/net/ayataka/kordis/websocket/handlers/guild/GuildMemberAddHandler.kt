package net.ayataka.kordis.websocket.handlers.guild

import com.google.gson.JsonObject
import net.ayataka.kordis.DiscordClientImpl
import net.ayataka.kordis.entity.server.ServerImpl
import net.ayataka.kordis.event.events.server.user.UserJoinEvent
import net.ayataka.kordis.websocket.handlers.GatewayHandler

class GuildMemberAddHandler : GatewayHandler {
    override val eventType = "GUILD_MEMBER_ADD"

    override fun handle(client: DiscordClientImpl, data: JsonObject) {
        val member = deserializeMember(client, data) ?: return
        val server = member.server as ServerImpl

        server.actualMemberCount.incrementAndGet()
        client.eventManager.fire(UserJoinEvent(member))
    }
}