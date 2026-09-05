package com.nosiphus.yogmod.world.level.block.piston;

import com.google.common.collect.Lists;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
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
            for (int i = 0; i < this.toPush.size(); ++i) {
                BlockPos pos = this.toPush.get(i);
                if (isSticky(this.level.getBlockState(pos)) && !this.addBranchingBlocks(pos)) {
                    return false;
                }
            }
            return true;
        }
    }

    private static boolean isSticky(BlockState state) {
        return state.is(Blocks.SLIME_BLOCK) || state.is(Blocks.HONEY_BLOCK);
    }

    private static boolean canStickTo(BlockState state1, BlockState state2) {
        if (state1.is(Blocks.SLIME_BLOCK) && state2.is(Blocks.HONEY_BLOCK)) {
            return false;
        } else if (state1.is(Blocks.HONEY_BLOCK) && state2.is(Blocks.SLIME_BLOCK)) {
            return false;
        } else {
            return isSticky(state1) || isSticky(state2);
        }
    }

    private boolean addBlockLine(BlockPos blockPos, Direction direction) {
        BlockState blockState = this.level.getBlockState(blockPos);

        if (this.level.isEmptyBlock(blockPos)) {
            return true;
        } else if (!PistonBaseBlock.isPushable(blockState, this.level, blockPos, this.pushDirection, false, direction)) {
            return true;
        } else if (blockPos.equals(this.pistonPos)) {
            return true;
        } else if (this.toPush.contains(blockPos)) {
            return true;
        } else {
            int lineLength = 1;
            if (lineLength + this.toPush.size() > MAX_PUSH_DEPTH) {
                return false;
            } else {
                BlockState previousState;
                while (isSticky(blockState)) {
                    BlockPos prevPos = blockPos.relative(this.pushDirection.getOpposite(), lineLength);
                    previousState = blockState;
                    blockState = this.level.getBlockState(prevPos);

                    if (blockState.isAir() || !canStickTo(previousState, blockState) || !PistonBaseBlock.isPushable(blockState, this.level, prevPos, this.pushDirection, false, this.pushDirection.getOpposite()) || prevPos.equals(this.pistonPos)) {
                        break;
                    }

                    ++lineLength;
                    if (lineLength + this.toPush.size() > MAX_PUSH_DEPTH) {
                        return false;
                    }
                }

                int addedCount = 0;
                for (int i = lineLength - 1; i >= 0; --i) {
                    this.toPush.add(blockPos.relative(this.pushDirection.getOpposite(), i));
                    ++addedCount;
                }

                int forwardOffset = 1;
                while (true) {
                    BlockPos nextPos = blockPos.relative(this.pushDirection, forwardOffset);
                    int existingIndex = this.toPush.indexOf(nextPos);

                    if (existingIndex > -1) {
                        this.reorderListAtCollision(addedCount, existingIndex);

                        for (int k = 0; k <= existingIndex + addedCount; ++k) {
                            BlockPos pos = this.toPush.get(k);
                            if (isSticky(this.level.getBlockState(pos)) && !this.addBranchingBlocks(pos)) {
                                return false;
                            }
                        }
                        return true;
                    }

                    blockState = this.level.getBlockState(nextPos);
                    if (blockState.isAir()) {
                        return true;
                    }

                    if (!PistonBaseBlock.isPushable(blockState, this.level, nextPos, this.pushDirection, true, this.pushDirection) || nextPos.equals(this.pistonPos)) {
                        return false;
                    }

                    if (blockState.getPistonPushReaction() == PushReaction.DESTROY) {
                        this.toDestroy.add(nextPos);
                        return true;
                    }

                    if (this.toPush.size() >= MAX_PUSH_DEPTH) {
                        return false;
                    }

                    this.toPush.add(nextPos);
                    ++addedCount;
                    ++forwardOffset;
                }
            }
        }
    }

    private void reorderListAtCollision(int addedCount, int collisionIndex) {
        List<BlockPos> before = Lists.newArrayList();
        List<BlockPos> newlyAdded = Lists.newArrayList();
        List<BlockPos> after = Lists.newArrayList();

        before.addAll(this.toPush.subList(0, collisionIndex));
        newlyAdded.addAll(this.toPush.subList(this.toPush.size() - addedCount, this.toPush.size()));
        after.addAll(this.toPush.subList(collisionIndex, this.toPush.size() - addedCount));

        this.toPush.clear();
        this.toPush.addAll(before);
        this.toPush.addAll(newlyAdded);
        this.toPush.addAll(after);
    }

    private boolean addBranchingBlocks(BlockPos blockPos) {
        BlockState blockState = this.level.getBlockState(blockPos);

        for (Direction side : Direction.values()) {
            if (side.getAxis() != this.pushDirection.getAxis()) {
                BlockPos sidePos = blockPos.relative(side);
                BlockState sideState = this.level.getBlockState(sidePos);
                if (canStickTo(blockState, sideState) && !this.addBlockLine(sidePos, side)) {
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