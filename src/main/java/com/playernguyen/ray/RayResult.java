package com.playernguyen.ray;

import com.playernguyen.entity.DefaultTarget;
import com.playernguyen.entity.Shooter;
import com.playernguyen.entity.Target;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
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

    public void setShooter(Shooter shooter) {
        this.shooter = shooter;
    }

    public Shooter getShooter() {
        return shooter;
    }

    public List<Target> getTargets() {
        return targets;
    }

    public void addTarget(Target e) {
        targets.add(e);
    }

    public void setTargets(List<Target> targets) {
        this.targets = targets;
    }
}
