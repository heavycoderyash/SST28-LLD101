package snakeandladder;

class HardValidator extends Validator {
    @Override
    public boolean canRollAgain(int diceValue, int consecutiveSixCount) {
        return diceValue == 6 && consecutiveSixCount < 3;
    }
}