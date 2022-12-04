package com.mitosv.detectcolision.gui;

import com.mitosv.detectcolision.net.PacketHandler;
import com.simibubi.create.api.event.PlayerCollisionEvent;
import com.simibubi.create.content.contraptions.components.structureMovement.bearing.MechanicalBearingTileEntity;
import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.Axis;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHudLine;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class EditGUI extends LightweightGuiDescription {

    private final WToggleButton enable = new WToggleButton(Text.of("Knockback Activado"));
    private final WSlider speed = new WSlider(0,16, Axis.HORIZONTAL);
    private final WToggleButton inverted = new WToggleButton(Text.of("Invertido"));
    private final WToggleButton stopped = new WToggleButton(Text.of("Stop"));
    private final WLabel speedText;
    private final WSlider force = new WSlider(0,10, Axis.HORIZONTAL);
    private final WLabel forceText;

    private MechanicalBearingTileEntity mecha;
    private BlockPos pos;

    public EditGUI(MechanicalBearingTileEntity mechanical, BlockPos pos){
        mecha = mechanical;
        this.pos = pos;
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(256, 240);
        root.setBackgroundPainter(BackgroundPainter.createColorful(0x36D4D6));

        enable.setToggle(PlayerCollisionEvent.getEvent());
        if (mechanical.getSpeed()>0) {
            speed.setValue(((int) mechanical.getSpeed()));
            stopped.setToggle(false);
            inverted.setToggle(false);
        }
        else if (mechanical.getSpeed()==0){
            speed.setValue(((int) mechanical.getSpeed()));
            stopped.setToggle(true);
            inverted.setToggle(false);
        }else {
            speed.setValue((int) mechanical.getSpeed()*-1);
            stopped.setToggle(false);
            inverted.setToggle(true);
        }

        force.setValue((int) PlayerCollisionEvent.getForce());

        speedText = new WLabel(Text.of(String.valueOf(speed.getValue())));;
        forceText = new WLabel(Text.of(String.valueOf(force.getValue())));;

        root.add(enable,1,1);
        root.add(stopped,1,3);
        root.add(inverted,1,5);
        root.add(speed,1,6,6,2);
        root.add(speedText,8,6,2,2);
        root.add(force,1,8,8,2);
        root.add(forceText,10,8,2,2);


    }

    public void tick(){
        speedText.setText(Text.of(String.valueOf(speed.getValue())));
        forceText.setText(Text.of(String.valueOf(force.getValue())));
        sendPacket(enable.getToggle(),force.getValue());
    }


    public void onClicked(){
        sendPacket(enable.getToggle(),force.getValue());
    }

    private void sendPacket(boolean active, int force){
        if (stopped.getToggle())
            PacketHandler.sendToC2S(pos, 0);
        else
            PacketHandler.sendToC2S(pos,
                    inverted.getToggle()?-speed.getValue():speed.getValue());
        PacketHandler.sendServerValues(force,active);
        /*
        assert MinecraftClient.getInstance().player != null;
        if (MinecraftClient.getInstance().player.getServer()==null){
            PlayerCollisionEvent.setForce(force);
            PlayerCollisionEvent.setEvent(active);
        }*/


    }



}
