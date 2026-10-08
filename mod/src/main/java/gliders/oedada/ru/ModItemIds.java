package gliders.oedada.ru;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public class ModItemIds {
	public static ResourceKey<Item> create(String name) {
		// Create the item key.
		return ResourceKey.create(Registries.ITEM, Gliders.id(name));
	}

    static public ResourceKey<Item> fabricId = create("fabric");
    static public ResourceKey<Item> gliderId = create("glider");
}
