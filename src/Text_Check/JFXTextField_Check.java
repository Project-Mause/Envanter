package Text_Check;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.paint.Color;

public class JFXTextField_Check
{
    private JFXTextField_Check(){}
    private static JFXTextField_Check instance=new JFXTextField_Check();
    public static JFXTextField_Check getInstance(){return instance;}

    public boolean Check(JFXTextField check){
        if (!check.getText().isEmpty()){
            if (!(check.getLength()<50)) {
                check.setUnFocusColor(Color.RED);
                check.setFocusColor(Color.RED);
                return false;
            }else {
                check.setUnFocusColor(Color.rgb(77, 77, 77));
                check.setFocusColor(Color.rgb(77, 77, 77));
                return true;
            }
        }else{
            check.setUnFocusColor(Color.RED);
            check.setFocusColor(Color.RED);
            return false;
        }
    }
    public boolean Check(JFXPasswordField check){
        if (!check.getText().isEmpty()){
            if (!(check.getLength()<50)) {
                check.setUnFocusColor(Color.RED);
                check.setFocusColor(Color.RED);
                return false;
            }else {
                check.setUnFocusColor(Color.rgb(77, 77, 77));
                check.setFocusColor(Color.rgb(77, 77, 77));
                return true;
            }
        }else{
            check.setUnFocusColor(Color.RED);
            check.setFocusColor(Color.RED);
            return false;
        }
    }
    public boolean CheckMail(JFXTextField check){
        if (!check.getText().isEmpty()) {
            for (int i = 0; i < check.getLength(); i++) {
                if (check.getText().charAt(i) == '@') {
                    check.setUnFocusColor(Color.rgb(77, 77, 77));
                    check.setFocusColor(Color.rgb(77, 77, 77));
                    return true;
                }
            }
            check.setUnFocusColor(Color.RED);
            check.setFocusColor(Color.RED);
            return false;
        }
        else{
            check.setUnFocusColor(Color.RED);
            check.setFocusColor(Color.RED);
            return false;
        }
    }
}
