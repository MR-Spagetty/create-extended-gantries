package com.spag.extended_gantries.registry;

import static com.spag.extended_gantries.ExtendedGantries.MODID;

public interface ItemRegistry {
  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.Item, MODID);
  
}
