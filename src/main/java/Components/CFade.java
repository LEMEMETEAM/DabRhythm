package Components;

import Entities.Components.Component;

public class CFade extends Component {
    /*
    * Time in seconds
     */
    public float time;
    public float delta;
    /* in = 0
    * out = 1
    */
    public byte in_or_out;
    public byte in_and_out;
    public float timeBetweenInOut;
    public boolean in, out, waiting;
}