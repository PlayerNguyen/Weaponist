package com.playernguyen.ray;

import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;

public class RayResult {

    private final List<LivingEntity> entities;
    private final List<LivingEntity> headshotEntities;
    private final List<Block> blocks;

    public RayResult() {
        this.entities = new ArrayList<>();
        this.blocks = new ArrayList<>();
        this.headshotEntities = new ArrayList<>();
    }

    public List<LivingEntity> getEntities() {
        return entities;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public List<LivingEntity> getHeadshotEntities() {
        return headshotEntities;
    }
}
