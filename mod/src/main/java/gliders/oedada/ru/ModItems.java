package gliders.oedada.ru;

import java.util.function.Function;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

public class ModItems {
    public static Item register(ResourceKey<Item> itemKey, Function<Item.Properties, Item> itemFactory,
            Item.Properties settings) {
        // Create the item instance.
        Item item = itemFactory.apply(settings.setId(itemKey));

        // Register the item.
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    public static Item glider = register(ModItemIds.gliderId, Item::new, new Item.Properties().stacksTo(1));
    public static Item fabric = register(ModItemIds.fabricId, Item::new, new Item.Properties().stacksTo(64));

    private static void addToCreativeTab(ResourceKey<CreativeModeTab> tab, Item item){
        CreativeModeTabEvents.modifyOutputEvent(tab)
		.register((creativeTab) -> creativeTab.accept(item));

    }

    public static void initialize() {
        // Get the event for modifying entries in the ingredients group.
        // And register an event handler that adds our suspicious item to the ingredients group.
        addToCreativeTab(CreativeModeTabs.COMBAT, glider);
        addToCreativeTab(CreativeModeTabs.INGREDIENTS, fabric);
    }
}
