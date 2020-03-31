package yogmod.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemFood;
import net.minecraftforge.common.util.EnumHelper;
import yogmod.items.*;
import yogmod.items.base.ArmorBase;
import yogmod.items.base.FoodBase;
import yogmod.util.Reference;

public class ModItems {

	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	//Armor Materials
	public static final ToolMaterial MATERIAL_CONSTRUCTION = EnumHelper.addToolMaterial("material_construction", 0, 32, 12.0F, 0.0F, 22);
	public static final ArmorMaterial ARMOR_MATERIAL_CONSTRUCTION = EnumHelper.addArmorMaterial("armor_material_construction", Reference.MODID + ":construction", 7, new int[]{1, 3, 5, 2}, 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F);
	
	public static final ToolMaterial MATERIAL_RIOT = EnumHelper.addToolMaterial("material_riot", 3, 1561, 8.0F, 3.0F, 10);
	public static final ArmorMaterial ARMOR_MATERIAL_RIOT = EnumHelper.addArmorMaterial("armor_material_riot", Reference.MODID + ":riot", 33, new int[]{3, 6, 8, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);
	
	public static final ToolMaterial MATERIAL_SUIT = EnumHelper.addToolMaterial("material_suit", 0, 59, 2.0F, 0.0F, 15);
	public static final ArmorMaterial ARMOR_MATERIAL_SUIT = EnumHelper.addArmorMaterial("armor_material_suit", Reference.MODID + ":suit", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);
	
	//Armor Wearables
	
	public static final Item SUNGLASSES = new ArmorBase("sunglasses", ARMOR_MATERIAL_SUIT, 0, EntityEquipmentSlot.HEAD);
	public static final Item SUIT_COAT = new ArmorBase("suit_coat", ARMOR_MATERIAL_SUIT, 0, EntityEquipmentSlot.CHEST);
	public static final Item SUIT_PANTS = new ArmorBase("suit_pants", ARMOR_MATERIAL_SUIT, 0, EntityEquipmentSlot.LEGS);
	public static final Item SPATTERDASH_SHOES = new ArmorBase("spatterdash_shoes", ARMOR_MATERIAL_SUIT, 0, EntityEquipmentSlot.FEET);
	
	public static final Item HARD_HAT = new ArmorBase("hard_hat", ARMOR_MATERIAL_CONSTRUCTION, 2, EntityEquipmentSlot.HEAD);
	public static final Item SAFETY_VEST = new ArmorBase("safety_vest", ARMOR_MATERIAL_CONSTRUCTION, 2, EntityEquipmentSlot.CHEST);
	public static final Item SEWER_WADERS = new ArmorBase("sewer_waders", ARMOR_MATERIAL_CONSTRUCTION, 2, EntityEquipmentSlot.LEGS);
	public static final Item STEELTOE_BOOTS = new ArmorBase("steeltoe_boots", ARMOR_MATERIAL_CONSTRUCTION , 2, EntityEquipmentSlot.FEET);
			
	public static final Item RIOT_HELMET = new ArmorBase("riot_helmet", ARMOR_MATERIAL_RIOT, 3, EntityEquipmentSlot.HEAD);
	public static final Item RIOT_VEST = new ArmorBase("riot_vest", ARMOR_MATERIAL_RIOT, 3, EntityEquipmentSlot.CHEST);
	public static final Item RIOT_PANTS = new ArmorBase("riot_pants", ARMOR_MATERIAL_RIOT, 3, EntityEquipmentSlot.LEGS);
	public static final Item RIOT_BOOTS = new ArmorBase("riot_boots", ARMOR_MATERIAL_RIOT, 3, EntityEquipmentSlot.FEET);
	
	
	//Food
	public static final ItemFood COFFEE = new FoodBase("coffee", 5, 0.3f, false);
	public static final ItemFood HAMBURGER = new FoodBase("hamburger", 8, 0.8F, true);
	public static final ItemFood JAFFA = new FoodBase("jaffa", 5, 0.3f, false);
	
	//Items
	public static final Item PENCIL = new ItemBase("pencil");
	
}
