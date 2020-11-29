package com.playernguyen.weaponist.ray;

import com.playernguyen.weaponist.entity.Shooter;
import com.playernguyen.weaponist.entity.Target;
import org.bukkit.block.Block;

import java.util.List;

public class RayResult {

    private Shooter shooter;
    private List<Target> targets;
    private Block hitBlock;

    public RayResult(Shooter shooter, List<Target> targets, Block hitBlock) {
        this.shooter = shooter;
        this.targets = targets;
        this.hitBlock = hitBlock;
    }

    public Block getHitBlock() {
        return hitBlock;
    }

    public void setHitBlock(Block hitBlock) {
        this.hitBlock = hitBlock;
    }

    public Shooter getShooter() {
        return shooter;
    }

    public void setShooter(Shooter shooter) {
        this.shooter = shooter;
    }

    public List<Target> getTargets() {
        return targets;
    }

    public void setTargets(List<Target> targets) {
        this.targets = targets;
    }

    public void addTarget(Target e) {
        targets.add(e);
    }
}
