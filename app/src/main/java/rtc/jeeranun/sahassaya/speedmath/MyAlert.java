package rtc.jeeranun.sahassaya.speedmath;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

/**
 * Created by masterUNG on 1/4/2017 AD.
 */

public class MyAlert {

    private Context context;
    private String strTitle;
    private String strMessage;
    private Class<?> aClass;

    public MyAlert(Context context,
                   String strTitle,
                   String strMessage,
                   Class<?> aClass) {
        this.context = context;
        this.strTitle = strTitle;
        this.strMessage = strMessage;
        this.aClass = aClass;
    }

    public void myDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setIcon(R.drawable.nobita48);
        builder.setTitle(strTitle);
        builder.setMessage(strMessage);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(context, aClass);
                context.startActivity(intent);
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

}   // Main Class
