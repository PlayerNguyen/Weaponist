package com.playernguyen.weaponist.event;

import com.playernguyen.weaponist.entity.Shooter;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class WeaponistPlayerShootEntityEvent extends Event {


    private static final HandlerList handlerList = new HandlerList();
    private final Shooter shooter;
    private final LivingEntity livingEntity;
    private final double damage;
    private final boolean headshot;

    public WeaponistPlayerShootEntityEvent(Shooter shooter, LivingEntity livingEntity, double damage, boolean headshot) {
        this.shooter = shooter;
        this.livingEntity = livingEntity;
        this.damage = damage;
        this.headshot = headshot;
    }

    public WeaponistPlayerShootEntityEvent(Shooter shooter, LivingEntity livingEntity, double damage) {
        this.shooter = shooter;
        this.livingEntity = livingEntity;
        this.damage = damage;
        this.headshot = false;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    public boolean isHeadshot() {
        return headshot;
    }

    public double getDamage() {
        return damage;
    }

    public Shooter getShooter() {
        return shooter;
    }

    public LivingEntity getLivingEntity() {
        return livingEntity;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
