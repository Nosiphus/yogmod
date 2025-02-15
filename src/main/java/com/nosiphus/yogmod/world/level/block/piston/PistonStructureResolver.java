package com.nosiphus.yogmod.world.level.block.piston;

import com.google.common.collect.Lists;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;

import java.util.List;

public class PistonStructureResolver {
    public static final int MAX_PUSH_DEPTH = 12;
    private final Level level;
    private final BlockPos pistonPos;
    private final boolean extending;
    private final BlockPos startPos;
    private final Direction pushDirection;
    private final List<BlockPos> toPush = Lists.newArrayList();
    private final List<BlockPos> toDestroy = Lists.newArrayList();
    private final Direction pistonDirection;

    public PistonStructureResolver(Level level, BlockPos blockPos, Direction direction, boolean extending) {
        this.level = level;
        this.pistonPos = blockPos;
        this.pistonDirection = direction;
        this.extending = extending;
        if (extending) {
            this.pushDirection = direction;
            this.startPos = blockPos.relative(direction);
        } else {
            this.pushDirection = direction.getOpposite();
            this.startPos = blockPos.relative(direction, 2);
        }

    }

    public boolean resolve() {
        this.toPush.clear();
        this.toDestroy.clear();
        BlockState blockState = this.level.getBlockState(this.startPos);
        if (!PistonBaseBlock.isPushable(blockState, this.level, this.startPos, this.pushDirection, false, this.pistonDirection)) {
            if (this.extending && blockState.getPistonPushReaction() == PushReaction.DESTROY) {
                this.toDestroy.add(this.startPos);
                return true;
            } else {
                return false;
            }
        } else if (!this.addBlockLine(this.startPos, this.pushDirection)) {
            return false;
        } else {
            for(int i = 0; i < this.toPush.size(); ++i) {
                BlockPos blockPos = this.toPush.get(i);
                if (this.level.getBlockState(blockPos).isStickyBlock() && !this.addBranchingBlocks(blockPos)) {
                    return false;
                }
            }

            return true;
        }
    }

    private boolean addBlockLine(BlockPos blockPos, Direction direction) {
        BlockState blockState = this.level.getBlockState(blockPos);
        if (level.isEmptyBlock(blockPos)) {
            return true;
        } else if (!net.minecraft.world.level.block.piston.PistonBaseBlock.isPushable(blockState, this.level, blockPos, this.pushDirection, false, direction)) {
            return true;
        } else if (blockPos.equals(this.pistonPos)) {
            return true;
        } else if (this.toPush.contains(blockPos)) {
            return true;
        } else {
            int i = 1;
            if (i + this.toPush.size() > 12) {
                return false;
            } else {
                BlockState oldBlockState;
                while(blockState.isStickyBlock()) {
                    BlockPos blockpos = blockPos.relative(this.pushDirection.getOpposite(), i);
                    oldBlockState = blockState;
                    blockState = this.level.getBlockState(blockpos);
                    if (blockState.isAir() || !(oldBlockState.canStickTo(blockState) && blockState.canStickTo(oldBlockState)) || !net.minecraft.world.level.block.piston.PistonBaseBlock.isPushable(blockState, this.level, blockpos, this.pushDirection, false, this.pushDirection.getOpposite()) || blockpos.equals(this.pistonPos)) {
                        break;
                    }

                    ++i;
                    if (i + this.toPush.size() > 12) {
                        return false;
                    }
                }

                int l = 0;

                for(int i1 = i - 1; i1 >= 0; --i1) {
                    this.toPush.add(blockPos.relative(this.pushDirection.getOpposite(), i1));
                    ++l;
                }

                int j1 = 1;

                while(true) {
                    BlockPos blockPos1 = blockPos.relative(this.pushDirection, j1);
                    int j = this.toPush.indexOf(blockPos1);
                    if (j > -1) {
                        this.reorderListAtCollision(l, j);

                        for(int k = 0; k <= j + l; ++k) {
                            BlockPos blockPos2 = this.toPush.get(k);
                            if (this.level.getBlockState(blockPos2).isStickyBlock() && !this.addBranchingBlocks(blockPos2)) {
                                return false;
                            }
                        }

                        return true;
                    }

                    blockState = this.level.getBlockState(blockPos1);
                    if (blockState.isAir()) {
                        return true;
                    }

                    if (!PistonBaseBlock.isPushable(blockState, this.level, blockPos1, this.pushDirection, true, this.pushDirection) || blockPos1.equals(this.pistonPos)) {
                        return false;
                    }

                    if (blockState.getPistonPushReaction() == PushReaction.DESTROY) {
                        this.toDestroy.add(blockPos1);
                        return true;
                    }

                    if (this.toPush.size() >= 12) {
                        return false;
                    }

                    this.toPush.add(blockPos1);
                    ++l;
                    ++j1;
                }
            }
        }
    }

    private void reorderListAtCollision(int int1, int int2) {
        List<BlockPos> list = Lists.newArrayList();
        List<BlockPos> list1 = Lists.newArrayList();
        List<BlockPos> list2 = Lists.newArrayList();
        list.addAll(this.toPush.subList(0, int2));
        list1.addAll(this.toPush.subList(this.toPush.size() - int1, this.toPush.size()));
        list2.addAll(this.toPush.subList(int2, this.toPush.size() - int1));
        this.toPush.clear();
        this.toPush.addAll(list);
        this.toPush.addAll(list1);
        this.toPush.addAll(list2);
    }

    private boolean addBranchingBlocks(BlockPos blockPos) {
        BlockState blockState = this.level.getBlockState(blockPos);

        for(Direction direction : Direction.values()) {
            if (direction.getAxis() != this.pushDirection.getAxis()) {
                BlockPos blockpos = blockPos.relative(direction);
                BlockState blockState1 = this.level.getBlockState(blockpos);
                if (blockState1.canStickTo(blockState) && blockState.canStickTo(blockState1) && !this.addBlockLine(blockpos, direction)) {
                    return false;
                }
            }
        }

        return true;
    }

    public Direction getPushDirection() {
        return this.pushDirection;
    }

    public List<BlockPos> getToPush() {
        return this.toPush;
    }

    public List<BlockPos> getToDestroy() {
        return this.toDestroy;
    }
}
