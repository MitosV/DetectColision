package com.mitosv.detectcolision.gui;

import com.simibubi.create.Create;
import com.simibubi.create.api.event.PlayerCollisionEvent;
import io.github.cottonmc.cotton.gui.GuiDescription;
import io.github.cottonmc.cotton.gui.client.CottonClientScreen;
import net.minecraft.text.Text;

public class EditScreen extends CottonClientScreen {

    private EditGUI gui;

    public EditScreen(EditGUI description) {
        super(description);
        gui = description;
    }

    @Override
    public void tick() {
        super.tick();
        gui.tick();
    }

    @Override
    public void close() {
        super.close();
        gui.onClicked();
    }
}
