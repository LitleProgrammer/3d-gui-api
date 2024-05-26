package de.littleprogrammer.guiapi.guis;

import de.littleprogrammer.guiapi.GuiApi;
import de.littleprogrammer.guiapi.components.Button;
import de.littleprogrammer.guiapi.components.Component;
import de.littleprogrammer.guiapi.components.Text;
import de.littleprogrammer.guiapi.enums.ServerVersion;
import de.littleprogrammer.guiapi.utils.Calculations;
import de.littleprogrammer.guiapi.utils.TeleportInterpolator;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

public class SimpleGui extends Gui {

    private final Map<UUID, Button> buttons;
    private final String title;
    private Component content;

    public SimpleGui(String title) {
        super(UUID.randomUUID(), false);

        this.title = title;
        this.components = new HashMap<>();
        this.buttons = new HashMap<>();
        setSpacing(30);
    }

    @Override
    public void updatePosition(Location playerLoc) {
        if (player != null) {
            centerLocation = Calculations.calculateInventoryCenter(playerLoc);

            if (GuiApi.getInstance().getVersion().equals(ServerVersion.PRE_1_20_2)) {
                for (Component component : components.values()) {
                    Location newComponentLocation = Calculations.calculateComponentLocation(this, component, buttons.size(), spacing);

                    TeleportInterpolator teleportInterpolator = new TeleportInterpolator(component.getEntity(), newComponentLocation, 5, 1);
                    teleportInterpolator.startInterpolation();
                }
            } else {
                for (Component component : components.values()) {
                    Location newComponentLocation = Calculations.calculateComponentLocation(this, component, buttons.size(), spacing);

                    component.getDisplay().setTeleportDuration(5);
                    component.getDisplay().teleport(newComponentLocation);
                }
            }
        }
    }

    @Override
    public void close() {
        //close GUI
        GuiApi.getInstance().getGuis().remove(player.getUniqueId());

        for (Component component : components.values()) {
            component.hide(player);
            component.remove();
        }
        open = false;
    }

    @Override
    public void open(Player player) {
        if (this.player != null && this.player.getUniqueId().equals(player.getUniqueId())) {
            //close GUI and open for the new player
            close();
        }
        this.player = player;
        GuiApi.getInstance().getGuis().put(player.getUniqueId(), this);
        centerLocation = new Location(player.getWorld(), 0, 0, 0);

        for (Component component : components.values()) {
            component.spawn();
            component.show(player);
        }
        open = true;
    }


    public int getButtonAmount() {
        return buttons.size();
    }

    public SimpleGui addButton(Button button) {
        if (buttons.size() < 3) {
            components.put(button.getUniqueId(), button);
            buttons.put(button.getUniqueId(), button);
            button.setGui(this);
        }
        return this;
    }

    public SimpleGui addContent(Text content) {
        if (this.content != null) {
            components.remove(this.content.getUniqueId());
            this.content.remove();
        }
        this.content = content;
        components.put(content.getUniqueId(), content);
        content.setGui(this);
        return this;
    }

    public String getTitle() {
        return title;
    }
}
