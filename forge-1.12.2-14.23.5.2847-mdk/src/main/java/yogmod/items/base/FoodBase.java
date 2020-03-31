package yogmod.items.base;

import net.minecraft.item.ItemFood;
import yogmod.Main;
import yogmod.init.ModItems;
import yogmod.tabs.CreativeTab;
import yogmod.util.IHasModel;
import yogmod.util.Reference;

public class FoodBase extends ItemFood implements IHasModel {

	public FoodBase(String name, int amount, float saturation, boolean isAnimalFood) {
		
		super(amount, saturation, isAnimalFood);
		setUnlocalizedName(Reference.MODID + "." + name);
		setRegistryName(name);
		setCreativeTab(CreativeTab.YogTab);
		
		ModItems.ITEMS.add(this);
		
	}
	
	@Override
	public void registerModels() {
		
		Main.proxy.registerModel(this, 0);
		
	}

}
