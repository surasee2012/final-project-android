package kmitl.project.surasee2012.eatrightnow.model;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

/**
 * Created by Gun on 11/5/2017.
 */

public class Message {

    private Toast toast;
    private AlertDialog alertDialog;

    public Message(Context context) {
        toast = new Toast(context);
        alertDialog = new AlertDialog.Builder(context).create();
    }

    public void setToast(Context context, String message) {
        toast.cancel();
        toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void alert(String warning) {
        if (!alertDialog.isShowing()) {
            alertDialog.setTitle("Alert");
            alertDialog.setMessage(warning);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "ตกลง",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    }

    public void alert(int randomResult) {
        switch (randomResult) {
            case 1:
                alert("ขออภัย รายการอาหารที่ตรงกับตัวเลือกของคุณมีแค่ 1 รายการ");
                break;
            case 2:
                alert("ขออภัย ไม่มีรายการอาหารที่ตรงกับตัวเลือกของคุณ กรุณาเปลี่ยนตัวเลือกเพิ่มเติม");
                break;
        }
    }

    public AlertDialog getAlertDialog() {
        return alertDialog;
    }
}
