package yio.tro.antiyoy.gameplay.rules;

import yio.tro.antiyoy.gameplay.GameController;
import yio.tro.antiyoy.gameplay.Hex;
import yio.tro.antiyoy.gameplay.Province;
import yio.tro.antiyoy.gameplay.Unit;

public class RulesetSlay extends Ruleset{

    public RulesetSlay(GameController gameController) {
        super(gameController);
    }


    @Override
    public boolean canSpawnPineOnHex(Hex hex) {
        return hex.isFree() && howManyTreesNearby(hex) >= 2 && hex.hasPineReadyToExpandNearby() && gameController.getRandom().nextDouble() < 0.8;
    }


    @Override
    public boolean canSpawnPalmOnHex(Hex hex) {
        return hex.isFree() && hex.isNearWater() && hex.hasPalmReadyToExpandNearby();
    }


    @Override
    public void onUnitAdd(Hex hex) {

    }


    @Override
    public void onTurnEnd() {

    }


    @Override
    public boolean canMergeUnits(Unit unit1, Unit unit2) {
        int mergedUnitStrength = gameController.mergedUnitStrength(unit1, unit2);

        return mergedUnitStrength <= 4;
    }


    @Override
    public int getHexIncome(Hex hex) {
        if (hex.containsTree()) {
            return 0;
        }
        return 1;
    }


    @Override
    public int getHexTax(Hex hex) {
        if (hex.containsUnit()) {
            return hex.unit.getTax();
        }

        return 0;
    }


    @Override
    public boolean canBuildUnit(Province province, int strength) {
        return province.money >= GameRules.PRICE_UNIT * strength;
    }


    @Override
    public void onUnitMoveToHex(Unit unit, Hex hex) {

    }


    @Override
    public boolean canUnitAttackHex(int unitStrength, Hex hex) {
        return unitStrength > hex.getDefenseNumber();
    }


    @Override
    public int getColorIndexWithOffset(int srcIndex) {
        srcIndex += gameController.colorIndexViewOffset;
        if (srcIndex >= GameRules.colorNumber) {
            srcIndex -= GameRules.colorNumber;
        }
        return srcIndex;
    }


}