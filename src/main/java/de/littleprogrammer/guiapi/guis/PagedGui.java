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

public class PagedGui extends Gui {

    private int page;
    private String title;
    private List<String> contents;
    private List<Text> texts;
    private Button leftButton;
    private Button middleButton;
    private Button rightButton;
    private float contentSize = 1.3f;
    private int contentSpacing = 60;

    public PagedGui(String title, int page) {
        super(UUID.randomUUID(), false);

        this.page = page;
        this.contents = new ArrayList<>();
        this.texts = new ArrayList<>();
        this.title = title;

        setSpacing(60);
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

        for (int i = 0; i < 3; i++) {
            if (contents.size() > i) {
                Text text = new Text(contents.get(i + page), i + 1).setSize(contentSize);
                text.setGui(this);
                texts.add(text);
                components.put(text.getUniqueId(), text);
            }
        }

        leftButton = new Button("<", "<<", 1).onClick(event -> {
            if (GuiApi.getInstance().getGuis().get(player.getUniqueId()) instanceof PagedGui) {
                PagedGui gui = (PagedGui) GuiApi.getInstance().getGuis().get(player.getUniqueId());
                if (gui.getPage() > 0) {
                    gui.changePage(gui.getPage() - 1);
                }
            }
        });
        leftButton.setGui(this);
        buttons.put(leftButton.getUniqueId(), leftButton);

        rightButton = new Button(">", ">>", 3).onClick(event -> {
            if (GuiApi.getInstance().getGuis().get(player.getUniqueId()) instanceof PagedGui) {
                PagedGui gui = (PagedGui) GuiApi.getInstance().getGuis().get(player.getUniqueId());
                System.out.println("Page " + gui.getPage() + " texts size " + contents.size() + " bool " + (gui.getPage() < contents.size() - 3));
                if (gui.getPage() < contents.size() - 3) {
                    gui.changePage(gui.getPage() + 1);
                }
            }
        });
        rightButton.setGui(this);
        buttons.put(rightButton.getUniqueId(), rightButton);

        components.put(leftButton.getUniqueId(), leftButton);
        components.put(rightButton.getUniqueId(), rightButton);

        for (Component component : components.values()) {
            component.spawn();
            component.show(player);
        }

        open = true;
    }

    @Override
    public void close() {
        GuiApi.getInstance().getGuis().remove(player.getUniqueId());

        for (Component component : components.values()) {
            component.hide(player);
            component.remove();
        }
        open = false;
    }

    @Override
    public void updatePosition(Location playerLoc) {
        if (player != null) {
            centerLocation = Calculations.calculateInventoryCenter(playerLoc);

            if (GuiApi.getInstance().getVersion().equals(ServerVersion.PRE_1_20_2)) {
                for (Text text : texts) {
                    Location newComponentLocation = Calculations.calculateComponentLocation(this, text, 3, contentSpacing);

                    TeleportInterpolator teleportInterpolator = new TeleportInterpolator(text.getEntity(), newComponentLocation, 5, 1);
                    teleportInterpolator.startInterpolation();
                }

                for (Button button : buttons.values()) {
                    Location newComponentLocation = Calculations.calculateComponentLocation(this, button, 3, spacing);

                    TeleportInterpolator teleportInterpolator = new TeleportInterpolator(button.getEntity(), newComponentLocation, 5, 1);
                    teleportInterpolator.startInterpolation();
                }
            } else {
                for (Text text : texts) {
                    Location newComponentLocation = Calculations.calculateComponentLocation(this, text, 3, contentSpacing);

                    text.getDisplay().setTeleportDuration(5);
                    text.getDisplay().teleport(newComponentLocation);
                }

                for (Button button : buttons.values()) {
                    Location newComponentLocation = Calculations.calculateComponentLocation(this, button, 3, spacing);

                    button.getDisplay().setTeleportDuration(5);
                    button.getDisplay().teleport(newComponentLocation);
                }
            }
        }
    }

    private void changePage(int newPage) {
        for (int i = 0; i < 3; i++) {
            texts.get(i).updateText(contents.get(i + newPage));
        }
        this.page = newPage;
    }

    public PagedGui addButton(Button button) {
        if (middleButton == null) {
            button.setSlot(2);
            components.put(button.getUniqueId(), button);
            buttons.put(button.getUniqueId(), button);
            middleButton = button;
            button.setGui(this);
        }
        return this;
    }

    public void setContents(List<String> contents) {
        this.contents = contents;
    }

    public void addContent(List<String> contents) {
        if (contents != null) {
            this.contents.addAll(contents);
        }
    }

    public void addContent(String content) {
        this.contents.add(content);
    }

    public List<String> getContents() {
        return contents;
    }

    public String getTitle() {
        return title;
    }

    public int getPage() {
        return page;
    }

    public void setContentSize(float contentSize) {
        this.contentSize = contentSize;
    }

    public void setContentSpacing(int contentSpacing) {
        this.contentSpacing = contentSpacing;
    }

    public PagedGui setLeftButtonText(String text, String hoverText) {
        leftButton.setTexture(text);
        leftButton.setHoverTexture(hoverText);
        return this;
    }

    public PagedGui setRightButtonText(String text, String hoverText) {
        rightButton.setTexture(text);
        rightButton.setHoverTexture(hoverText);
        return this;
    }
}
