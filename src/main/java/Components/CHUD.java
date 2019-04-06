package Components;

import Entities.Components.Component;

public class CHUD extends Component {

    public enum HUD{
        SCORE,
        COMBO,
        ACC
    }
    public HUD hud;
    public int unit;
}