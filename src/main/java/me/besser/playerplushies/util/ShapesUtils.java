package me.besser.playerplushies.util;

import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ShapesUtils {
    private ShapesUtils() {}

    /**
     * Rotates a {@link VoxelShape} around the Y axis in 90° steps.
     * <p>
     * Base orientation is SOUTH.
     *
     * @param shape the base shape facing SOUTH
     * @param steps number of 90° clockwise rotations (0–3)
     * @return rotated voxel shape
     */
    public static VoxelShape rotateY90(VoxelShape shape, int steps) {
        VoxelShape result = shape;
        int rotations = Math.floorMod(steps, 4);

        for (int i = 0; i < rotations; i++) {
            result = rotateOnce(result);
        }

        return result;
    }

    private static VoxelShape rotateOnce(VoxelShape shape) {
        VoxelShape[] buffer = new VoxelShape[] { Shapes.empty() };

        shape.forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) -> {
            buffer[0] = Shapes.or(
                    buffer[0],
                    Shapes.box(
                            1.0 - maxZ, minY, minX,
                            1.0 - minZ, maxY, maxX
                    )
            );
        });

        return buffer[0];
    }
}
