package jp.co.yukkuraft.constant;

import net.minecraft.util.math.AxisAlignedBB;

/**
 * このクラスは AxisAlignedBB の定義を行います。
 *
 * @author Insanophilia
 *
 */
public class YuAABB
{
    private static final double UNIT = 1.0D / 16.0D;
    private static final double D00  = UNIT * 0.0D;
    private static final double D01  = UNIT * 1.0D;
    private static final double D15  = UNIT * 15.0D;
    private static final double D16  = UNIT * 16.0D;

    // 1 ブロック分の立方体の定義
    public static final AxisAlignedBB FULL_BLOCK = new AxisAlignedBB(D00, D00, D00, D16, D16, D16);
    // 厚さ 16 分の 1 ブロックの壁の定義
    public static final AxisAlignedBB WALL_UP    = new AxisAlignedBB(D00, D15, D00, D16, D16, D16);
    public static final AxisAlignedBB WALL_DOWN  = new AxisAlignedBB(D00, D00, D00, D16, D01, D16);
    public static final AxisAlignedBB WALL_NORTH = new AxisAlignedBB(D00, D00, D00, D16, D16, D01);
    public static final AxisAlignedBB WALL_SOUTH = new AxisAlignedBB(D00, D00, D15, D16, D16, D16);
    public static final AxisAlignedBB WALL_EAST  = new AxisAlignedBB(D15, D00, D00, D16, D16, D16);
    public static final AxisAlignedBB WALL_WEST  = new AxisAlignedBB(D00, D00, D00, D01, D16, D16);
}
