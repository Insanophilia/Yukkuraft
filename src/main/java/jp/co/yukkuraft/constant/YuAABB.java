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
    // 基準単位
    private static final double UNIT = 1D / 16D;
    private static final double D00  = UNIT * 0D;
    private static final double D01  = UNIT * 1D;
    private static final double D02  = UNIT * 2D;
    private static final double D03  = UNIT * 3D;
    private static final double D04  = UNIT * 4D;
    private static final double D05  = UNIT * 5D;
    private static final double D06  = UNIT * 6D;
    private static final double D07  = UNIT * 7D;
    private static final double D08  = UNIT * 8D;
    private static final double D09  = UNIT * 9D;
    private static final double D10  = UNIT * 10D;
    private static final double D11  = UNIT * 11D;
    private static final double D12  = UNIT * 12D;
    private static final double D13  = UNIT * 13D;
    private static final double D14  = UNIT * 14D;
    private static final double D15  = UNIT * 15D;
    private static final double D16  = UNIT * 16D;
    // 1ブロックをはみ出すサイズ
    private static final double D32 = UNIT * 16D * 2D;
    private static final double D48 = UNIT * 16D * 3D;

    // 1ブロック分の空間を占める立方体
    public static final AxisAlignedBB FULL_BLOCK = new AxisAlignedBB(D00, D00, D00, D16, D16, D16);
    // 周囲に隙間のある立方体
    public static final AxisAlignedBB GAP_BLOCK = new AxisAlignedBB(D01, D01, D01, D15, D15, D15);
    // 厚さ16分の1ブロックの壁
    public static final AxisAlignedBB WALL_UP    = new AxisAlignedBB(D00, D15, D00, D16, D16, D16);
    public static final AxisAlignedBB WALL_DOWN  = new AxisAlignedBB(D00, D00, D00, D16, D01, D16);
    public static final AxisAlignedBB WALL_NORTH = new AxisAlignedBB(D00, D00, D00, D16, D16, D01);
    public static final AxisAlignedBB WALL_SOUTH = new AxisAlignedBB(D00, D00, D15, D16, D16, D16);
    public static final AxisAlignedBB WALL_EAST  = new AxisAlignedBB(D15, D00, D00, D16, D16, D16);
    public static final AxisAlignedBB WALL_WEST  = new AxisAlignedBB(D00, D00, D00, D01, D16, D16);
    // 厚さ16分の1ブロックの床の接地面
    public static final AxisAlignedBB ON_FLOOR = new AxisAlignedBB(D00, D01, D00, D16, D02, D16);
    // 成体ゆっくりサイズの立方体
    public static final AxisAlignedBB YUKKURI = new AxisAlignedBB(D03, D00, D03, D13, D10, D13);
    // 子ゆっくりサイズの立方体
    public static final AxisAlignedBB CHILD_YUKKURI = new AxisAlignedBB(D05, D00, D05, D11, D06, D11);
    // 大サイズの立方体
    public static final AxisAlignedBB BIG_BLOCK_333 = new AxisAlignedBB(-D16, D00, -D16, D32, D48, D32);
}
