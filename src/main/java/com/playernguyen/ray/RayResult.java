package com.playernguyen.ray;

import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;

public class RayResult {

    private final List<LivingEntity> entities;
    private final List<LivingEntity> headshotEntities;
    private Block hitBlock;

    public RayResult() {
        this.entities = new ArrayList<>();
        this.headshotEntities = new ArrayList<>();
        this.hitBlock = null;
    }

    public List<LivingEntity> getEntities() {
        return entities;
    }

    public List<LivingEntity> getHeadshotEntities() {
        return headshotEntities;
    }

    public Block getHitBlock() {
        return hitBlock;
    }

    public void setHitBlock(Block hitBlock) {
        this.hitBlock = hitBlock;
    }
}
