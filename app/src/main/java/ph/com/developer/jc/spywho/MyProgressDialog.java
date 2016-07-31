package ph.com.developer.jc.spywho;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by john_dongalen on 7/16/2016.
 */
public class MyProgressDialog extends AlertDialog {

    public static ProgressBar progVal;
    public static TextView progMax;

    private String progressMax;
    private String ProgressVal;

    private Context mContext;
    private static View view;

    public MyProgressDialog(Context context) {
        super(context, R.style.TransparentProgressDialog);
        this.mContext = context;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.horizontal_progressdialog, null);
        progVal = (ProgressBar)view.findViewById(R.id.progress);
        progMax = (TextView)view.findViewById(R.id.progress_number);
    }

    @Override
    public void show() {
        super.show();
        setContentView(view);
    }

    public void setProgMax(int val){
        progVal.setMax(val);
    }

    public void setProgVal(int val){
        progVal.setProgress(val);
    }

    @Override
    public void setCancelable(boolean flag) {
        super.setCancelable(flag);
    }
}