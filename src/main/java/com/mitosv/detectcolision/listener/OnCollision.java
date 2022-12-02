package com.mitosv.detectcolision.listener;

import com.mitosv.detectcolision.DetectColision;
import com.simibubi.create.api.event.PlayerCollisionEvent;
import net.minecraft.util.math.Vec3d;

import java.util.function.Consumer;

public class OnCollision implements Consumer<PlayerCollisionEvent> {

    public static void register(){
        PlayerCollisionEvent.LISTENERS.register(new OnCollision());
    }

    @Override
    public void accept(PlayerCollisionEvent e) {


    }



}
