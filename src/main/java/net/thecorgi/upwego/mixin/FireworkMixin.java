package net.thecorgi.upwego.mixin;

import com.google.common.base.Predicates;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(FireworkRocketEntity.class)
public abstract class FireworkMixin extends Entity {
    public FireworkMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "tick", at = @At(value = "TAIL"))
    public void launchEntity(CallbackInfo info) {
        if (!this.world.isClient) {
            Box box = this.getBoundingBox().expand(0.5, 0.8, 0.5);

            List<MobEntity> list = world.getEntitiesByClass(MobEntity.class, box, Predicates.alwaysTrue());
            if (!list.isEmpty()) {
                MobEntity mobEntity = (MobEntity)list.get(this.world.random.nextInt(list.size()));
                if (!mobEntity.isAiDisabled() && !mobEntity.isInvisible() && !mobEntity.isDead() && !mobEntity.isFallFlying() && !mobEntity.isInvulnerable()) {
                    if (mobEntity.getVelocity().y < 0.5D) {
                        Vec3d launch = new Vec3d(0.0D, 0.2D, 0.0D);
                        mobEntity.addVelocity(launch.x, launch.y, launch.z);
                    }
                }
            }
        }
    }
}