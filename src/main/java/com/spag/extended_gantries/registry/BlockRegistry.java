package com.spag.extended_gantries.registry;

import static com.spag.extended_gantries.ExtendedGantries.MODID;

public interface BlockRegistry {
  public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
  
}
