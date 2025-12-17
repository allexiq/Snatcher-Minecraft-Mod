package net.claudiu.snatcher.entity.spawn;

import net.claudiu.snatcher.entity.SnatcherEntity;
import net.claudiu.snatcher.registry.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraft.util.RandomSource;

public class SnatcherSpawnPlacement {

    public static void registerSpawnPlacement(SpawnPlacementRegisterEvent event){
        event.register(
                ModEntities.SNATCHER.get(),         //entitatea Snatcher inregistrata in joc
                SpawnPlacements.getPlacementType(ModEntities.SNATCHER.get()) ,      //unde il vreau spawnat
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,      //folosesc heightmap pt a gasi nivelul solului
                SnatcherSpawnPlacement::canSnatcherSpawn,       //daca spawnul e valid
                SpawnPlacementRegisterEvent.Operation.OR        //alte reguli
        );
    }

    private static boolean canSnatcherSpawn(
            EntityType<SnatcherEntity> type,
            LevelAccessor world,
            EntitySpawnReason category,
            BlockPos pos,
            RandomSource random
    ) {
        long time = world.dayTime() % 24000L;
        boolean isNight = time >= 13000L && time <= 23000L;     //verific daca e noapte (il vreau noaptea)

        BlockState state = world.getBlockState(pos.below());        //ce bloc e sub el cand se spawneaza
        boolean validBlock = state.isValidSpawn(world, pos.below(), type);      //verific daca blocul permite sa se spawneze

        return isNight && validBlock;
    }
}
