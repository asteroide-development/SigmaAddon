package spiggstm.sigmaddon.modules;

import meteordevelopment.meteorclient.events.packets.PacketEvent;
import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.meteorclient.mixin.PlayerMoveC2SPacketAccessor;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;
import spiggstm.sigmaddon.Addon;

public class CreativeFlightModule extends Module {
    public CreativeFlightModule() {
        super(Addon.CATEGORY, "creative-flight", "Allows you to fly like in creative mode");
    }

    @Override
    public void onActivate() {
        assert mc.player != null;
        mc.player.getAbilities().allowFlying = true;
    }
    @Override
    public void onDeactivate() {
        assert mc.player != null;
        if(!mc.player.getAbilities().creativeMode) {mc.player.getAbilities().allowFlying = false; mc.player.getAbilities().flying = false;}
    }
    @EventHandler
    private void onSendPacket(PacketEvent.Send event){
        if(!(event.packet instanceof PlayerMoveC2SPacketAccessor)) return;
        if(!isActive()) return;
        ((PlayerMoveC2SPacketAccessor) event.packet).setOnGround(true);
    }
}
